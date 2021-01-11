package com.example.transactional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseDao<T>  {
    public static final int MAX_COLUMN_NAME_LENGTH = 63;

    protected T create(Map<String, Object> parameters) {
        List<String> insertingColumns = new ArrayList<>();
        List<String> values = new ArrayList<>();
        parameters.keySet().forEach(key -> {
            insertingColumns.add(getColumnName(key, true));
            values.add(":" + key);
        });
        List<String> returningColumns = new ArrayList<>(insertingColumns);
        returningColumns.add(getColumnName("id", true));
        String statement = String.format("INSERT INTO %s (%s) VALUES (%s) RETURNING %s", getTableNameForStatement(),
                String.join(",", insertingColumns), String.join(",", values),
                String.join(",", returningColumns));
        T created = getNamedJdbcTemplate().queryForObject(statement, parameters, getRowMapper());
        return created;
    }

    protected abstract String getTableName();
    protected abstract RowMapper<T> getRowMapper();
    protected abstract MyDataSource getDataSource();

    private NamedParameterJdbcTemplate getNamedJdbcTemplate() {
        return getConnectionHandler().getNamedJdbcTemplate();
    }

    private ConnectionHandler getConnectionHandler() {
        return getDataSource().getConnection(getSchemaName());
    }

    private String getColumnName(String attribute, boolean withQuotes) {
        String columnName = StringUtils.getLastCharacters(getTableName() + "_" + attribute, MAX_COLUMN_NAME_LENGTH);
        if (withQuotes) {
            return StringUtils.surroundWithQuotes(columnName);
        }
        return columnName;
    }

    private String getColumnName(String attribute) {
        return getColumnName(attribute, false);
    }

    private String getTableNameForStatement() {
        return String.format("%s.\"%s\"", getSchemaName(), getTableName());
    }

    private String getSchemaName() {
        return "example";
    }
}

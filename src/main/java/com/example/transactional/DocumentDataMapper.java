package com.example.transactional;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocumentDataMapper implements RowMapper<DocumentData> {
   @Override
   public DocumentData mapRow(ResultSet resultSet, int rowNum) throws SQLException {
      DocumentData entity = new DocumentData();
      if (ResultSetHelper.hasColumn(resultSet, "DocumentData_id")) {
         entity.setId(resultSet.getObject("DocumentData_id", Integer.class));
      }
      if (ResultSetHelper.hasColumn(resultSet, "DocumentData_data")) {
         entity.setData(resultSet.getString("DocumentData_data"));
      }
      return entity;
   }
}

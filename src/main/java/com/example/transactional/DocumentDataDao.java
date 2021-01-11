package com.example.transactional;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class DocumentDataDao extends BaseDao<DocumentData> {

   @Autowired
   private MyDataSource dataSource;

   public DocumentData create(DocumentData dto) {
      Map<String, Object> parameters = Maps.newHashMap();
      if(dto.getData() != null) {
         parameters.put("data", dto.getData());
      }
      return super.create(parameters);
   }

   @Override
   protected String getTableName() {
      return "DocumentData";
   }

   @Override
   protected RowMapper<DocumentData> getRowMapper() {
      return new DocumentDataMapper();
   }

   @Override
   protected MyDataSource getDataSource() {
      return dataSource;
   }
}

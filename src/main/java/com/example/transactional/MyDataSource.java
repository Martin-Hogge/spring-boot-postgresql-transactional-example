package com.example.transactional;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyDataSource {
   private final Map<String, ConnectionHandler> connections = new HashMap<>();

   private String schemaHost;
   private String schemaUser;
   private String schemaPassword;

   @PostConstruct
   private void init() {
      schemaHost = "jdbc:postgresql://127.0.0.1:5432/example";
      schemaUser = "exampleUser";
      schemaPassword = "examplePassword";
   }

   @Bean
   public HikariDataSource getDatabaseDataSource() {
      return createDataSource(schemaHost);
   }

   public ConnectionHandler getConnection(String schemaName) {
      ConnectionHandler connection = connections.get(schemaName);
      if (connection == null) {
         HikariDataSource dataSource = createDataSource(String.format("%s?currentSchema=%s", schemaHost, schemaName));
         connection = new ConnectionHandler(dataSource, schemaName);
         connections.put(schemaName, connection);
      }
      return connection;
   }

   private HikariDataSource createDataSource(String jdbcUrl) {
      HikariConfig config = new HikariConfig();
      config.setJdbcUrl(jdbcUrl);
      config.setUsername(schemaUser);
      config.setPassword(schemaPassword);
      config.setMaximumPoolSize(10);
      config.setMinimumIdle(0);
      config.setIdleTimeout(30000);
      // Inspired by https://stackoverflow.com/a/42529705/3520621
      config.setConnectionInitSql("SET extra_float_digits=0");
      return new HikariDataSource(config);
   }
}

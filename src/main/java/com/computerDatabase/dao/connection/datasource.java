package com.computerDatabase.dao.connection;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

@Component
public class datasource extends DriverManagerDataSource {

  public datasource() {
      this.setDriverClassName("com.mysql.jdbc.Driver");
      this.setUrl("jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull");
      this.setUsername("admincdb");
      this.setPassword("qwerty1234");
  }
}

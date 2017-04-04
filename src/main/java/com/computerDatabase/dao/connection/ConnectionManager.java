package com.computerDatabase.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.computerDatabase.exceptions.DAOException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class ConnectionManager {

  private ThreadLocal<Connection> connection = new ThreadLocal<Connection>();
  @Autowired
  private datasource ds;

  ConnectionManager(){
    
  }
  
  /** The Constant LOGGER. */
  public static final Logger LOGGER = LoggerFactory
          .getLogger(ConnectionManager.class);

  /**
   * Method to get a new instance of Connection class .
   * @return connect
   */
  public Connection getInstance(){
    try {
      if ((connection.get() == null)||(connection.get().isClosed())) {
        try {
          connection.set(ds.getConnection());
          LOGGER.info("connection created");
        } catch (SQLException e) {
          LOGGER.error("connection error");
          throw new DAOException("Error on connection to database");
        }
      } else {
        LOGGER.error("connection error");
        throw new DAOException("Error on connection to database");
      }
    } catch (SQLException e) {
      LOGGER.error("connection error");
      throw new DAOException("Error on connection to database");
    }
    LOGGER.info("Connection instance created");
    return connection.get();
  }
}

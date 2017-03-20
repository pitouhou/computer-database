package com.computerDatabase.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.computerDatabase.exceptions.DAOException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public enum ConnectionManager {

  INSTANCE;
  private ThreadLocal<Connection> connection = new ThreadLocal<Connection>();
  private HikariDataSource ds;

  ConnectionManager(){
    try {
      Class.forName("com.mysql.jdbc.Driver");
      HikariConfig cfg = new HikariConfig("/config.properties");
      ds = new HikariDataSource(cfg);
    } catch (ClassNotFoundException e) {
      throw new DAOException("Problème lors de la connexion à la base de données");
    }
  }
  
  /** The Constant LOGGER. */
  public static final Logger LOGGER = LoggerFactory
          .getLogger(ConnectionManager.class);

  /**
   * Method to get a new instance of Connection class .
   * @return connect
   */
  public Connection getInstance() {
    try {
      if ((connection.get() == null)||(connection.get().isClosed())) {
        try {
          connection.set(ds.getConnection());
          LOGGER.info("connection created");
        } catch (SQLException e) {
          throw new DAOException("Problème lors de la connexion à la base de données");
        }
      } else {
        
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    LOGGER.info("Connection instance created");
    return connection.get();
  }
}

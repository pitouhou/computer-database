package com.computerDatabase.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  /** The Constant LOGGER. */
  public static final Logger LOGGER = LoggerFactory
          .getLogger(ConnectionManager.class);
  
  public void closeConnexion(){
    try {
      LOGGER.info("connection closed");
      connection.get().close();
    } catch (SQLException e) {
      LOGGER.error("Error connection not closed");
    }
  }
  
  public void commit(){
    try{
      connection.get().commit();
    }catch(SQLException e){
      LOGGER.error("Error on commiting data");
      rollBack();
    }
  }
  
  public void rollBack(){
    try{
      connection.get().rollback();
    }catch(SQLException e){
      LOGGER.error("Error on transaction rollback");
    }
  }
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
          LOGGER.error("SQLException on the creation of connection");
        }
      } else {
        
      }
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    LOGGER.info("Connection instance created");
    return connection.get();
  }

  /**
   * Method to get database properties .
   * @param fileName : fileName
   * @return Optional<Properties>
   * @throws FileNotFoundException : e
   * @throws IOException : ex
   */
  public static Optional<Properties> getProperties(String fileName)
      throws FileNotFoundException, IOException {

    Properties properties = new Properties();
    File file = new File(fileName);

      InputStream input = ConnectionManager.class.getClassLoader().getResourceAsStream(fileName);

      try {

        properties.load(input);
        LOGGER.info("Success on loading properties");
        return Optional.of(properties);

      } finally {

        input.close();
        LOGGER.info("input stream closed");
      }
  }

}

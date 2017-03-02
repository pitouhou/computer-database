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

public class ConnectionManager {

  private static Connection connect;

  /** The Constant LOGGER. */
  public static final Logger LOGGER = LoggerFactory
          .getLogger(ConnectionManager.class);
  /**
   * Method to get a new instance of Connection class .
   * @return connect
   */
  public static Connection getInstance() {
    try {
      Optional<Properties> propertiesTmp = ConnectionManager.getProperties("/config.properties");
      Properties properties = propertiesTmp.get();
      if (connect == null) {
        try {
          Class.forName("com.mysql.jdbc.Driver");
          connect = DriverManager.getConnection(properties.getProperty("url"),
              properties.getProperty("user"), properties.getProperty("password"));
        } catch (SQLException e) {
          LOGGER.error("SQLException on the creation of connection");
        } catch (ClassNotFoundException ex) {
          LOGGER.error("ClassNotFoundException on the creation of connection");
        }
      } else {

        try {
          connect.close();
          Class.forName("com.mysql.jdbc.Driver");
          connect = DriverManager.getConnection(properties.getProperty("url"),
              properties.getProperty("user"), properties.getProperty("password"));
        } catch (SQLException e) {
          LOGGER.error("SQLException on the creation of connection");
        } catch (ClassNotFoundException ex) {
          LOGGER.error("ClassNotFoundException on the creation of connection");
        }
      }
      LOGGER.info("Connection instance created");
      return connect;
    } catch (IOException e1) {
      LOGGER.error("IOException on the creation of connection");
      e1.printStackTrace();
    }
    LOGGER.info("Connection instance created");
    return connect;
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

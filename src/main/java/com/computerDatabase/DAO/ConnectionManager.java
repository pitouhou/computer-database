package com.computerDatabase.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

public class ConnectionManager {

  private static Connection connect;

  /**
   * Method to get a new instance of Connection class .
   * @return connect
   */
  public static Connection getInstance() {
    try {
      Optional<Properties> propertiesTmp = ConnectionManager.getProperties("/config.properties");
      System.out.println(propertiesTmp.isPresent());
      Properties properties = propertiesTmp.get();
      if (connect == null) {
        try {
          Class.forName("com.mysql.jdbc.Driver");
          connect = DriverManager.getConnection(properties.getProperty("url"),
              properties.getProperty("user"), properties.getProperty("password"));
        } catch (SQLException e) {
          e.printStackTrace();
        } catch (ClassNotFoundException ex) {
          ex.printStackTrace();
        }
      } else {

        try {
          connect.close();
          Class.forName("com.mysql.jdbc.Driver");
          connect = DriverManager.getConnection(properties.getProperty("url"),
              properties.getProperty("user"), properties.getProperty("password"));
        } catch (SQLException e) {
          e.printStackTrace();
        } catch (ClassNotFoundException ex) {
          ex.printStackTrace();
        }
      }

      return connect;
    } catch (IOException e1) {
      e1.printStackTrace();
    }
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
    System.out.println(file.exists());
    
      InputStream input = ConnectionManager.class.getClassLoader().getResourceAsStream(fileName);;

      try {

        properties.load(input);
        return Optional.of(properties);

      } finally {

        input.close();

      }
  }

}

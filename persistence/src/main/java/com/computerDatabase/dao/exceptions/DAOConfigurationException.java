package com.computerDatabase.dao.exceptions;

public class DAOConfigurationException extends RuntimeException {

  /**
   * Method to configure DAOException .
   * @param message : message
   */
  public DAOConfigurationException(String message) {
    super(message);
  }

  /**
   * Method to configure DAOException .
   * @param message : message
   * @param cause : cause
   */
  public DAOConfigurationException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Method to configure DAOException .
   * @param cause : cause
   */
  public DAOConfigurationException(Throwable cause) {
    super(cause);
  }
}

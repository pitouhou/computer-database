package com.computerDatabase.exceptions;

public class DAOException extends RuntimeException {

  /**
   * Method to handle DAOException .
   * @param message : message
   */
  public DAOException(String message) {
    super(message);
  }

  /**
   * Method to handle DAOException .
   * @param message : message
   * @param cause : cause
   */
  public DAOException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Method to handle DAOException .
   * @param cause : cause
   */
  public DAOException(Throwable cause) {
    super(cause);
  }
}

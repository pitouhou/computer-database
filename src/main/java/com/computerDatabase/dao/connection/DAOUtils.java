package com.computerDatabase.dao.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.computerDatabase.exceptions.DAOException;

public class DAOUtils {

  /** The Constant LOGGER. */
  public static final Logger LOGGER = LoggerFactory
          .getLogger(ConnectionManager.class);

  /**
   * Method to prepare statement .
   * @param connexion : connexion
   * @param sql : sql
   * @param returnGeneratedKeys : returnGeneratedKeys
   * @param objets : objets
   * @return preparedStatement
   * @throws SQLException : SQLException
   */
  public static PreparedStatement initPreparedStatement(Connection connexion, String sql,
      boolean returnGeneratedKeys, Object... objets) throws SQLException {
    
    PreparedStatement preparedStatement = connexion.prepareStatement(sql,
        returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);

    for (int i = 0; i < objets.length; i++) {
      preparedStatement.setObject(i + 1, objets[i]);
    }
    return preparedStatement;
  }

  /**
   * Method to close resultSet .
   * @param resultSet : resulSet
   */
  public static void closeResultSet(ResultSet resultSet) {
    if (resultSet != null) {
      try {
        resultSet.close();
      } catch (SQLException e) {
        throw new DAOException("Problème lors de la fermeture de la connexion à la base de données");
      }
    }
  }

  /**
   * Method to close statement .
   * @param statement : statement
   */
  public static void closeStatement(Statement statement) {
    if (statement != null) {
      try {
        statement.close();
      } catch (SQLException e) {
        throw new DAOException("Problème lors de la fermeture de la connexion à la base de données");
      }
    }
  }

  public static void closeConnection(Connection connexion){
    try {
      LOGGER.info("connection closed");
      connexion.close();
    } catch (SQLException e) {
      throw new DAOException("Problème lors de la fermeture de la connexion à la base de données");
    }
  }
  
  /**
   * Method to close statement, resultSet .
   * @param resultSet : resultSet
   * @param statement : statement
   * @param connexion : connexion
   */
  public static void closeAll(ResultSet resultSet, Statement statement, Connection connexion) {
    closeResultSet(resultSet);
    closeStatement(statement);
    closeConnection(connexion);
  }
  
  /**
   * Method to close statement, resultSet .
   * @param statement : statement
   * @param connexion : connexion
   */
  public static void closeAll(Statement statement, Connection connexion) {
    closeStatement(statement);
    closeConnection(connexion);
  }

  /**
   * Method to commit .
   * @param connexion
   */
  public static void commit(Connection connexion){
    try{
      connexion.commit();
    }catch(SQLException e){
      rollBack(connexion);
      throw new DAOException("Problème lors de l'ajout de données");
    }
  }
  
  /**
   * Method to do rollBack .
   * @param connexion
   */
  public static void rollBack(Connection connexion){
    try{
      connexion.rollback();
    }catch(SQLException e){
      throw new DAOException("Problème lors de l'ajout de données");
    }
  }
}

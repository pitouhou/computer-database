package com.computerDatabase.dao.implementation;

import static com.computerDatabase.dao.connection.DAOUtils.closeAll;
import static com.computerDatabase.dao.connection.DAOUtils.initPreparedStatement;
import static com.computerDatabase.entity.mapper.CompanyMapper.mapCompany;
import static com.computerDatabase.entity.mapper.CompanyMapper.mapListCompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.computerDatabase.dao.CompanyDAOInterface;
import com.computerDatabase.dao.connection.ConnectionManager;
import com.computerDatabase.entity.model.Company;
import com.computerDatabase.exceptions.DAOException;

@Component
public class CompanyDAO implements CompanyDAOInterface {

  private static final String SQL_FIND_ALL_COMPANY = "SELECT * FROM company";
  private static final String SQL_FIND_BY_ID = "SELECT * FROM company WHERE id = ?";

  @Autowired
  private ConnectionManager connectionManager;
  
  /** The Constant LOGGER. */
  public static final Logger LOGGER = LoggerFactory
          .getLogger(CompanyDAO.class);

  /**
   * Constructor of CompanyDAO class .
   */
  private CompanyDAO() { }

  @Override
  public int count(){
    return 1;
  }
  
  @Override 
  public Optional<Company> findById(long id){
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {

      connexion = connectionManager.getInstance();
      preparedStatement = initPreparedStatement(connexion, SQL_FIND_BY_ID, false, id);
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        Optional<Company> company = mapCompany(resultSet);
        return company;
      }

    } catch (SQLException e) {
      LOGGER.error("error on request");
      throw new DAOException("Error on finding company : " + id);
    } catch(DAOException ex){
      throw new DAOException("Error on connection to database");
    }
    finally {
      closeAll(resultSet, preparedStatement, connexion);
    }
    return Optional.empty();
  }

  @Override 
  public List<Company> findAll(){
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Company> listCompany = new ArrayList<>();
    try {
      connexion = connectionManager.getInstance();
      preparedStatement = initPreparedStatement(connexion, SQL_FIND_ALL_COMPANY, false);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        listCompany = mapListCompany(resultSet);
        return listCompany;
      }

    } catch (SQLException e) {
      LOGGER.error("error on request");
      throw new DAOException("Error on finding companies" );
    } catch(DAOException ex){
      throw new DAOException("Error on connection to database");
    }
    finally {
      closeAll(resultSet, preparedStatement, connexion);
    }
    return listCompany;
  }

  @Override 
  public void create(Company obj) {

  }

  @Override 
  public void update(Company obj) {

  }

  @Override 
  public void delete(long id) {

  }
}

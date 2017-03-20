package com.computerDatabase.dao;

import static com.computerDatabase.dao.connection.DAOUtils.closeAll;
import static com.computerDatabase.dao.connection.DAOUtils.initPreparedStatement;
import static com.computerDatabase.mapper.CompanyMapper.mapCompany;
import static com.computerDatabase.mapper.CompanyMapper.mapListCompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.computerDatabase.dao.connection.ConnectionManager;
import com.computerDatabase.dao.interfaces.CompanyDAOImpl;
import com.computerDatabase.exceptions.DAOException;
import com.computerDatabase.model.Company;

public class CompanyDAO implements CompanyDAOImpl {

  private static final String SQL_FIND_ALL_COMPANY = "SELECT * FROM company";
  private static final String SQL_FIND_BY_ID = "SELECT * FROM company WHERE id = ?";

  /** The Constant LOGGER. */
  public static final Logger LOGGER = LoggerFactory
          .getLogger(CompanyDAO.class);

  /**
   * Constructor of CompanyDAO class .
   */
  private CompanyDAO() { }

  private static class CompanyDAOHolder {
    private static final CompanyDAO INSTANCE = new CompanyDAO();
  }

  public static CompanyDAO getInstance() {
    return CompanyDAOHolder.INSTANCE;
  }

  @Override
  public int count(){
    return 1;
  }
  
  @Override 
  public Optional<Company> findById(long id) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {

      connexion = ConnectionManager.INSTANCE.getInstance();
      preparedStatement = initPreparedStatement(connexion, SQL_FIND_BY_ID, false, id);
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        Optional<Company> company = mapCompany(resultSet);
        return company;
      }

    } catch (SQLException e) {
      throw new DAOException("Problème lors de l'accès aux données");
    } finally {
      closeAll(resultSet, preparedStatement, connexion);
    }
    return Optional.empty();
  }

  @Override 
  public List<Company> findAll() throws DAOException {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Company> listCompany = new ArrayList<>();
    try {
      connexion = ConnectionManager.INSTANCE.getInstance();
      preparedStatement = initPreparedStatement(connexion, SQL_FIND_ALL_COMPANY, false);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        listCompany = mapListCompany(resultSet);
        return listCompany;
      }

    } catch (SQLException e) {
      throw new DAOException("Problème lors de l'accès aux données");
    } finally {
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

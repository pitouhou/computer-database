package com.computerDatabase.DAO;

import static com.computerDatabase.DAO.DAOUtils.silentCloses;
import static com.computerDatabase.DAO.DAOUtils.initPreparedStatement;
import static com.computerDatabase.mapper.CompanyMapper.mapCompany;
import static com.computerDatabase.mapper.CompanyMapper.mapListCompany;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

import com.computerDatabase.model.Company;

public class CompanyDAO implements DAO<Company> {

  private static final String SQL_FIND_ALL_COMPANY = "SELECT * FROM company";
  private static final String SQL_FIND_BY_ID = "SELECT * FROM company WHERE id = ?";

  private CompanyDAO() { }

  private static class CompanyDAOHolder {
    private static final CompanyDAO INSTANCE = new CompanyDAO();
  }

  public static CompanyDAO getInstance() {
    return CompanyDAOHolder.INSTANCE;
  }

  @Override public Optional<Company> findById(long id) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try {

      connexion = ConnectionManager.getInstance();
      preparedStatement = initPreparedStatement(connexion, SQL_FIND_BY_ID, false, id);
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        Optional<Company> company = mapCompany(resultSet);
        return company;
      }

    } catch (SQLException e) {
      return Optional.empty();
    } finally {
      silentCloses(resultSet, preparedStatement, connexion);
    }
    return Optional.empty();
  }

  @Override public Optional<Collection<Company>> findAll() {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      connexion = ConnectionManager.getInstance();
      preparedStatement = initPreparedStatement(connexion, SQL_FIND_ALL_COMPANY, false);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        Optional<Collection<Company>> listCompany = mapListCompany(resultSet);
        return listCompany;
      }

    } catch (SQLException e) {
      return Optional.empty();
    } finally {
      silentCloses(resultSet, preparedStatement, connexion);
    }
    return Optional.empty();
  }

  @Override public void create(Company obj) {

  }

  @Override public void update(Company obj) {

  }

  @Override public void delete(long id) {

  }
}

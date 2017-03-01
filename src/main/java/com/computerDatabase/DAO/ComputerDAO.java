package com.computerDatabase.DAO;

import static com.computerDatabase.DAO.DAOUtils.silentCloses;
import static com.computerDatabase.DAO.DAOUtils.initPreparedStatement;
import static com.computerDatabase.mapper.ComputerMapper.mapComputer;
import static com.computerDatabase.mapper.ComputerMapper.mapListComputer;

import java.sql.Connection;
import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.computerDatabase.model.Computer;

public class ComputerDAO implements DAO<Computer> {

  private static final String SQL_FIND_BY_ID = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id = ?";
  private static final String SQL_FIND_ALL_COMPUTER = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id";
  private static final String SQL_CREATE_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES ( ?, ?, ?, ?)";
  private static final String SQL_UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
  private static final String SQL_DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";

  /**
   * Constructor of ComputerDAO class .
   */
  private ComputerDAO() { }

  private static class ComputerDAOHolder {
    private static final ComputerDAO INSTANCE = new ComputerDAO();
  }

  public static ComputerDAO getInstance() {
    return ComputerDAOHolder.INSTANCE;
  }

  @Override
  public Optional<Computer> findById(long id) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Optional<Computer> computer = Optional.empty();

    try {
      connexion = ConnectionManager.getInstance();
      preparedStatement = initPreparedStatement(connexion, SQL_FIND_BY_ID, false, id);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        computer = mapComputer(resultSet);
      }
    } catch (SQLException e) {
      return Optional.empty();
    } finally {
      silentCloses(resultSet, preparedStatement, connexion);
    }
    return computer;
  }

  @Override public List<Optional<Computer>> findAll() {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Optional<Computer>> listComputer = new ArrayList<>();

    try {

      connexion = ConnectionManager.getInstance();
      preparedStatement = initPreparedStatement(connexion, SQL_FIND_ALL_COMPUTER, false);
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        listComputer = mapListComputer(resultSet);
      }

    } catch (SQLException e) {
      return listComputer;
    } finally {
      silentCloses(resultSet, preparedStatement, connexion);
    }
    return listComputer;
  }

  @Override public void create(Computer computer) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    int resultSet;
    String name = computer.getName();
    LocalDate introduced = computer.getIntroduced().get();
    LocalDate discontinued = computer.getDiscontinued().get();
    long companyId = computer.getCompany().get().getId();

    try {

      connexion = ConnectionManager.getInstance();
      preparedStatement = initPreparedStatement(connexion, SQL_CREATE_COMPUTER, false, name,
          introduced, discontinued, companyId);
      resultSet = preparedStatement.executeUpdate();

    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      silentCloses(preparedStatement, connexion);
    }
  }

  @Override public void update(Computer computer) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    int resultSet;
    long id = computer.getId();
    String name = computer.getName();
    LocalDate introduced = computer.getIntroduced().get();
    LocalDate discontinued = computer.getDiscontinued().get();
    long companyId = computer.getCompany().get().getId();

    try {

      connexion = ConnectionManager.getInstance();
      preparedStatement = initPreparedStatement(connexion, SQL_UPDATE_COMPUTER, false, name,
          introduced, discontinued, companyId, id);
      resultSet = preparedStatement.executeUpdate();
      System.out.println(resultSet);
    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      silentCloses(preparedStatement, connexion);
    }
  }

  @Override public void delete(long id) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    int resultSet;

    try {

      connexion = ConnectionManager.getInstance();
      preparedStatement = initPreparedStatement(connexion, SQL_DELETE_COMPUTER, false, id);
      resultSet = preparedStatement.executeUpdate();

    } catch (SQLException e) {
      throw new DAOException(e);
    } finally {
      silentCloses(preparedStatement, connexion);
    }

  }

}

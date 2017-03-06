package com.computerDatabase.dao;

import static com.computerDatabase.dao.DAOUtils.initPreparedStatement;
import static com.computerDatabase.dao.DAOUtils.silentCloses;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.computerDatabase.model.Computer;

public class ComputerDAO implements DAO<Computer> {

  private static final String SQL_FIND_BY_ID = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id = ?";
  private static final String SQL_FIND_ALL_COMPUTER = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id LIMIT ? OFFSET ?";
  private static final String SQL_CREATE_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES ( ?, ?, ?, ?)";
  private static final String SQL_UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
  private static final String SQL_DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
  private static final String SQL_COUNT_COMPUTER = "SELECT COUNT(id) FROM computer";

  /** The Constant LOGGER. */
  public static final Logger LOGGER = LoggerFactory
          .getLogger(ComputerDAO.class);

  /**
   * Constructor of ComputerDAO class .
   */
  private ComputerDAO() { }

  private static class ComputerDAOHolder {
    private static final ComputerDAO INSTANCE = new ComputerDAO();
  }

  public static ComputerDAO getInstance() {
    LOGGER.info("ComputerDAO instance created");
    return ComputerDAOHolder.INSTANCE;
  }

  @Override
  public int count(){
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    int nbComputer = 1;

    try {
      connexion = ConnectionManager.getInstance();
      preparedStatement = initPreparedStatement(connexion, SQL_COUNT_COMPUTER, false);
      resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
      nbComputer = resultSet.getInt(1);
      }
    } catch (SQLException e) {
      LOGGER.error("SQLException on getting number of computer");
      //return Optional.empty();
    } finally {
      silentCloses(resultSet, preparedStatement, connexion);
    }
    return nbComputer;
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
      LOGGER.error("SQLException on getting computer by id");
      return Optional.empty();
    } finally {
      silentCloses(resultSet, preparedStatement, connexion);
    }
    return computer;
  }

  @Override public List<Computer> findAll() {
    return null;
  }
  
  public List<Computer> findAll(int current, int range) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<Computer> listComputer = new ArrayList<>();

    try {

      connexion = ConnectionManager.getInstance();
      preparedStatement = initPreparedStatement(connexion, SQL_FIND_ALL_COMPUTER, false, range, current*range);
      resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        listComputer = mapListComputer(resultSet);
      }

    } catch (SQLException e) {
      LOGGER.error("SQLException on getting computer list");
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
      LOGGER.error("SQLException on creating computer");
      throw new DAOException(e);
    } finally {
      silentCloses(preparedStatement, connexion);
    }
  }

  @Override public void update(Computer computer) {
    Connection connexion = null;
    PreparedStatement preparedStatement = null;
    String companyId;
    int resultSet;
    long id = computer.getId();
    String name = computer.getName();
    LocalDate introduced;
    LocalDate discontinued;
    
    if(computer.getIntroduced().isPresent()){
      introduced = computer.getIntroduced().get();
    }else{
      introduced = null;
    }
    
    if(computer.getDiscontinued().isPresent()){
      discontinued = computer.getDiscontinued().get();
    }else{
      discontinued = null;
    }
    
    if(computer.getCompany().isPresent()){
      companyId = Long.toString(computer.getCompany().get().getId());
    }else{
      companyId = null;
    }

    try {

      connexion = ConnectionManager.getInstance();
      preparedStatement = initPreparedStatement(connexion, SQL_UPDATE_COMPUTER, false, name,
          introduced, discontinued, companyId, id);
      resultSet = preparedStatement.executeUpdate();
    } catch (SQLException e) {
      LOGGER.error("SQLException on updating computer");
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
      LOGGER.error("SQLException on deleting computer");
      throw new DAOException(e);
    } finally {
      silentCloses(preparedStatement, connexion);
    }

  }

}

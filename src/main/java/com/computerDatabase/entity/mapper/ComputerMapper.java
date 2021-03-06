package com.computerDatabase.entity.mapper;

import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.computerDatabase.dao.connection.ConnectionManager;
import com.computerDatabase.entity.model.Company;
import com.computerDatabase.entity.model.Computer;

public class ComputerMapper {

  /** The Constant LOGGER. */
  public static final Logger LOGGER = LoggerFactory.getLogger(ConnectionManager.class);

 /**
   * Mapper of Computer class .
   * @param resultSet : resultSet
   * @return Optional<Computer>
   * @throws SQLException : e
   */
  public static Optional<Computer> mapComputer(ResultSet resultSet) throws SQLException {
    LocalDate introduced = null;
    LocalDate discontinued = null;
    if (resultSet.getDate("introduced") != null) {
      introduced = resultSet.getDate("introduced").toLocalDate();
    }
    if (resultSet.getDate("discontinued") != null) {
      discontinued = resultSet.getDate("discontinued").toLocalDate();
    }
    Company company = new Company(resultSet.getLong("company.id"), resultSet.getString("company.name"));
    Computer computer = new Computer(resultSet.getLong("id"), resultSet.getString("name"), introduced, discontinued, company);
    return Optional.of(computer);
  }

  /**
   * Mapper of Computer list .
   * @param resultSet : resultSet
   * @return Optional<Collection<Computer>>
   * @throws SQLException : e
   */
  public static List<Computer> mapListComputer(ResultSet resultSet)
      throws SQLException {

    List<Computer> computerList = new ArrayList<>();
    do{
      LocalDate introduced = null;
      LocalDate discontinued = null;
      if (resultSet.getDate("introduced") != null) {
        introduced = resultSet.getDate("introduced").toLocalDate();
      }
      if (resultSet.getDate("discontinued") != null) {
        discontinued = resultSet.getDate("discontinued").toLocalDate();
      }
      Company company = new Company(resultSet.getLong("company.id"), resultSet.getString("company.name"));
      Computer computer = new Computer(resultSet.getLong("id"), resultSet.getString("name"), introduced, discontinued, company);
      computerList.add(computer);
    }while(resultSet.next());
    
    LOGGER.info("New computer list created from database");
    return computerList;
  }

}

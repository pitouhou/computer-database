package com.computerDatabase.mapper;

import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.computerDatabase.DAO.ConnectionManager;
import com.computerDatabase.model.Company;
import com.computerDatabase.model.Computer;

public class ComputerMapper {

  /** The Constant LOGGER. */
  public static final Logger LOGGER = LoggerFactory
          .getLogger(ConnectionManager.class);
  
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
    Company company = new Company.CompanyBuilder(resultSet.getLong("company.id"), resultSet.getString("company.name")).build();
    Computer computer = new Computer.ComputerBuilder(resultSet.getString("name")).id(resultSet.getLong("id")).introduced(introduced).discontinued(discontinued).company(company).build();
    LOGGER.info("New computer created from database");
    return Optional.of(computer);
  }

  /**
   * Mapper of Computer list .
   * @param resultSet : resultSet
   * @return Optional<Collection<Computer>>
   * @throws SQLException : e
   */
  public static List<Optional<Computer>> mapListComputer(ResultSet resultSet)
      throws SQLException {

    List<Optional<Computer>> computerList = new ArrayList<>();
    while (resultSet.next()) {
      LocalDate introduced = null;
      LocalDate discontinued = null;
      if (resultSet.getDate("introduced") != null) {
        introduced = resultSet.getDate("introduced").toLocalDate();
      }
      if (resultSet.getDate("discontinued") != null) {
        discontinued = resultSet.getDate("discontinued").toLocalDate();
      }
      Company company = new Company.CompanyBuilder(resultSet.getLong("company.id"), resultSet.getString("company.name")).build();
      Computer computer = new Computer.ComputerBuilder(resultSet.getString("name")).id(resultSet.getLong("id")).introduced(introduced).discontinued(discontinued).company(company).build();
      LOGGER.info("New computer list created from database");
      computerList.add(Optional.of(computer));
    }

    return computerList;
  }

}

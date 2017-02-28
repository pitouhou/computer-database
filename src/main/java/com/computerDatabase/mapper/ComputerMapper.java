package com.computerDatabase.mapper;

import java.time.LocalDate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.computerDatabase.model.Company;
import com.computerDatabase.model.Computer;

public class ComputerMapper {

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
    return Optional.of(computer);
  }

  /**
   * Mapper of Computer list .
   * @param resultSet : resultSet
   * @return Optional<Collection<Computer>>
   * @throws SQLException : e
   */
  public static Optional<Collection<Computer>> mapListComputer(ResultSet resultSet)
      throws SQLException {

    Collection<Computer> computerList = new ArrayList<Computer>();
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
      computerList.add(computer);
    }

    return Optional.of(computerList);
  }

}

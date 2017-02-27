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
    Computer computer = new Computer();
    Company company = new Company();
    LocalDate introduced = null;
    LocalDate discontinued = null;
    if (resultSet.getDate("introduced") != null) {
      introduced = resultSet.getDate("introduced").toLocalDate();
    }
    if (resultSet.getDate("discontinued") != null) {
      discontinued = resultSet.getDate("discontinued").toLocalDate();
    }
    company.setId(resultSet.getLong("company.id"));
    company.setName(resultSet.getString("company.name"));
    computer.setId(resultSet.getLong("id"));
    computer.setName(resultSet.getString("name"));
    computer.setCompany(company);
    computer.setIntroduced(introduced);
    computer.setDiscontinued(discontinued);
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
      Computer computer = new Computer();
      Company company = new Company();
      LocalDate introduced = null;
      LocalDate discontinued = null;
      if (resultSet.getDate("introduced") != null) {
        introduced = resultSet.getDate("introduced").toLocalDate();
      }
      if (resultSet.getDate("discontinued") != null) {
        discontinued = resultSet.getDate("discontinued").toLocalDate();
      }
      company.setId(resultSet.getLong("company.id"));
      company.setName(resultSet.getString("company.name"));
      computer.setId(resultSet.getLong("id"));
      computer.setName(resultSet.getString("name"));
      computer.setCompany(company);
      computer.setIntroduced(introduced);
      computer.setDiscontinued(discontinued);
      computerList.add(computer);
    }

    return Optional.of(computerList);
  }

}

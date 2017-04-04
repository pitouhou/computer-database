package com.computerDatabase.entity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.computerDatabase.dao.connection.ConnectionManager;
import com.computerDatabase.entity.model.Company;

public class CompanyMapper {

  /** The Constant LOGGER. */
  public static final Logger LOGGER = LoggerFactory
          .getLogger(ConnectionManager.class);
 /**
   * Mapper of Company list .
   * @param resultSet : resultSet
   * @return : Optional<Collection<Company>>
   * @throws SQLException : e
   */
  public static List<Company> mapListCompany(ResultSet resultSet)
      throws SQLException {

    List<Company> companyList = new ArrayList<>();
    while (resultSet.next()) {
      Company company = new Company.CompanyBuilder(resultSet.getLong("id"), resultSet.getString("name")).build();
      companyList.add(company);
    }
    LOGGER.info("New company list created from database");
    return companyList;
  }

  /**
   * Mapper of Company object .
   * @param resultSet : resultSet
   * @return : Optional<Company>
   * @throws SQLException : e
   */
  public static Optional<Company> mapCompany(ResultSet resultSet) throws SQLException {

    Company company = new Company.CompanyBuilder(resultSet.getLong("id"), resultSet.getString("name")).build();
    LOGGER.info("New company created from database");
    return Optional.of(company);
  }

}

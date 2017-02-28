package com.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.computerDatabase.model.Company;

public class CompanyMapper {

  /**
   * Mapper of Company list .
   * @param resultSet : resultSet
   * @return : Optional<Collection<Company>>
   * @throws SQLException : e
   */
  public static List<Optional<Company>> mapListCompany(ResultSet resultSet)
      throws SQLException {

    List<Optional<Company>> companyList = new ArrayList<>();
    while (resultSet.next()) {
      Company company = new Company.CompanyBuilder(resultSet.getLong("id"), resultSet.getString("name")).build();
      companyList.add(Optional.of(company));
    }
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

    return Optional.of(company);
  }

}

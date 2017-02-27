package com.computerDatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.computerDatabase.model.Company;

public class CompanyMapper {

  public static Optional<Collection<Company>> mapListCompany(ResultSet resultSet)
      throws SQLException {

    Collection<Company> companyList = new ArrayList<Company>();
    while (resultSet.next()) {
      Company company = new Company();
      company.setId(resultSet.getLong("id"));
      company.setName(resultSet.getString("name"));
      companyList.add(company);
    }
    return Optional.of(companyList);
  }

  public static Optional<Company> mapCompany(ResultSet resultSet) throws SQLException {

    Company company = new Company();
    company.setId(resultSet.getLong("id"));
    company.setName(resultSet.getString("name"));

    return Optional.of(company);
  }

}

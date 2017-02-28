package com.computerDatabase.services;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.computerDatabase.model.Company;

public interface CompanyServicesInterface {

  /**
   * Method to get Company list .
   * @return : List<Optional<Company>>
   */
  List<Optional<Company>> getCompanyList();

  /**
   * Method to get Company by id .
   * @param id : id
   * @return : Company
   */
  Optional<Company> getCompany(long id);

}

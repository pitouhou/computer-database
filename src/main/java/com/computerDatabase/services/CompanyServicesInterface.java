package com.computerDatabase.services;

import java.util.Collection;
import java.util.Optional;

import com.computerDatabase.model.Company;

public interface CompanyServicesInterface {

  /**
   * Method to get Company list .
   * @return : Collection<Company>
   */
  Optional<Collection<Company>> getCompanyList();

  /**
   * Method to get Company by id .
   * @param id : id
   * @return : Company
   */
  Optional<Company> getCompany(long id);

}

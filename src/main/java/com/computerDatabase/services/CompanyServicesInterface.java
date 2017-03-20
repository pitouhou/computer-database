package com.computerDatabase.services;

import java.util.List;
import java.util.Optional;

import com.computerDatabase.exceptions.DAOException;
import com.computerDatabase.model.Company;

public interface CompanyServicesInterface {

  /**
   * Method to get Company list .
   * @return : List<Optional<Company>>
   */
  List<Company> getCompanyList() throws DAOException ;

  /**
   * Method to get Company by id .
   * @param id : id
   * @return : Company
   */
  Optional<Company> getCompany(long id);

}

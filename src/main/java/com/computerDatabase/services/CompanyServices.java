package com.computerDatabase.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.computerDatabase.dao.CompanyDAO;
import com.computerDatabase.exceptions.DAOException;
import com.computerDatabase.model.Company;

@Component
public class CompanyServices implements CompanyServicesInterface {

  @Autowired
  private CompanyDAO companyDAO;

  /** The Constant LOGGER. */
  public static final Logger LOGGER = LoggerFactory
          .getLogger(CompanyServices.class);

  /**
   * Constructor of CompanyServices class .
   */
  CompanyServices() {

  }

  @Override public List<Company> getCompanyList() {

    List<Company> comp1 = new ArrayList<>();
    try {
      comp1 = companyDAO.findAll();
      return comp1;
    } catch (DAOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return comp1;
  }

  @Override public Optional<Company> getCompany(long id) {
    Optional<Company> comp1;
    try {
      comp1 = companyDAO.findById(id);
    } catch (NullPointerException e) {
      LOGGER.error("NullPointerException on getting company by id");
      return Optional.empty();
    }
    return comp1;
  }
}

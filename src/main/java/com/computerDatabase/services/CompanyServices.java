package com.computerDatabase.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.computerDatabase.DAO.CompanyDAO;
import com.computerDatabase.DAO.ConnectionManager;
import com.computerDatabase.model.Company;

public enum CompanyServices implements CompanyServicesInterface {

  instance;

  /** The Constant LOGGER. */
  public static final Logger LOGGER = LoggerFactory
          .getLogger(ConnectionManager.class);
  
  public static CompanyServices getInstance() {
    return CompanyServices.instance;
  }
  /**
   * Constructor of CompanyServices class .
   */
  CompanyServices() {

  }

  @Override public List<Optional<Company>> getCompanyList() {
    CompanyDAO comp = CompanyDAO.getInstance();

    List<Optional<Company>> comp1 = comp.findAll();
    return comp1;

  }

  @Override public Optional<Company> getCompany(long id) {

    CompanyDAO comp = CompanyDAO.getInstance();
    Optional<Company> comp1;
    try {
      comp1 = comp.findById(id);
    } catch (NullPointerException e) {
      LOGGER.error("NullPointerException on getting company by id");
      return Optional.empty();
    }
    return comp1;
  }
}

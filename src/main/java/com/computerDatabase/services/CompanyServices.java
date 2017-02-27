package com.computerDatabase.services;

import java.util.Collection;
import java.util.Optional;

import com.computerDatabase.DAO.CompanyDAO;
import com.computerDatabase.model.Company;

public enum CompanyServices implements CompanyServicesInterface {

  instance;

  public static CompanyServices getInstance() {
    return CompanyServices.instance;
  }

  CompanyServices() {

  }

  @Override public Optional<Collection<Company>> getCompanyList() {
    CompanyDAO comp = CompanyDAO.getInstance();

    Optional<Collection<Company>> comp1 = comp.findAll();
    return comp1;

  }

  @Override public Optional<Company> getCompany(long id) {

    CompanyDAO comp = CompanyDAO.getInstance();
    Optional<Company> comp1;
    try {
      comp1 = comp.findById(id);
    } catch (NullPointerException e) {
      System.out.println("Aucune entreprise ne correspond à cet identifiant");
      return Optional.empty();
    }
    return comp1;
  }
}

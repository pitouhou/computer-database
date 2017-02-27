package com.computerDatabase.services;

import java.util.Collection;
import java.util.Optional;

import com.computerDatabase.model.Company;

public interface CompanyServicesInterface {

  Optional<Collection<Company>> getCompanyList();

  Optional<Company> getCompany(long id);

}

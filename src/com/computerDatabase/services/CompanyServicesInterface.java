package com.computerDatabase.services;

import java.util.Collection;
import java.util.Optional;

import com.computerDatabase.model.Company;

public interface CompanyServicesInterface {

	public Optional<Collection<Company>> getCompanyList();
	public Optional<Company> getCompany(long id);
	
}

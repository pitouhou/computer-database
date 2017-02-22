package com.computerDatabase.services;

import java.util.Collection;
import com.computerDatabase.model.Company;

public interface CompanyServicesInterface {

	public Collection<Company> getCompanyList();
	public Company getCompany(long id);
	
}

package com.computerDatabase.services;

import java.util.Collection;

import com.computerDatabase.DAO.CompanyDao;
import com.computerDatabase.DAO.DAOFactory;
import com.computerDatabase.model.Company;

public class CompanyServices {

	public Collection<Company> getCompanyList(){
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		CompanyDao comp = daoFactory.getCompanyDao();
				
		Collection<Company> comp1 = comp.list();		
				
		return comp1;
		
	}
	
}

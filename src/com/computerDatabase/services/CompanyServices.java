package com.computerDatabase.services;

import java.util.Collection;

import com.computerDatabase.DAO.CompanyDao;
import com.computerDatabase.DAO.DAOFactory;
import com.computerDatabase.model.Company;

public class CompanyServices {

	private static CompanyServices instance;
	
	public static CompanyServices getInstance() {
        if (null == instance) { 
            instance = new CompanyServices();
        }
        return instance;
    }

    
    private CompanyServices() {
    	
    }

    
	public Collection<Company> getCompanyList(){
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		CompanyDao comp = daoFactory.getCompanyDao();
				
		Collection<Company> comp1 = comp.list();		
				
		return comp1;
		
	}
	
	public Company getCompany(int id){
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		CompanyDao comp = daoFactory.getCompanyDao();
				
		Company comp1 = comp.getCompanyById(id);	
				
		return comp1;
		
	}
	
}

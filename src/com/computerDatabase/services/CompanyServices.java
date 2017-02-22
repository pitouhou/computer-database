package com.computerDatabase.services;

import java.util.Collection;

import com.computerDatabase.DAO.CompanyDao;
import com.computerDatabase.DAO.DAOFactory;
import com.computerDatabase.model.Company;

public enum CompanyServices implements CompanyServicesInterface {

	instance;
	
	public static CompanyServices getInstance() {
        return CompanyServices.instance;
    }

    
    private CompanyServices() {
    	
    }

    @Override
	public Collection<Company> getCompanyList(){
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		CompanyDao comp = daoFactory.getCompanyDao();
				
		Collection<Company> comp1 = comp.list();		
				
		return comp1;
		
	}
    @Override
	public Company getCompany(long id){
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		CompanyDao comp = daoFactory.getCompanyDao();
		Company comp1 = new Company();
		try{
			comp1 = comp.getCompanyById(id);
		}catch(NullPointerException e){
			System.out.println("Aucune entreprise ne correspond à cet identifiant");
			return comp1;
		}
			
				
		return comp1;
		
	}
	
}

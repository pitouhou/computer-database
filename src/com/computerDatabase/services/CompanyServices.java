package com.computerDatabase.services;

import java.util.Collection;

import com.computerDatabase.DAO.CompanyDAO;
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
		
		CompanyDAO comp = CompanyDAO.getInstance();
				
		Collection<Company> comp1 = comp.findAll();		
				
		return comp1;
		
	}
    @Override
	public Company getCompany(long id){
		
    	CompanyDAO comp = CompanyDAO.getInstance();
		Company comp1 = new Company();
		try{
			comp1 = comp.findById(id);
		}catch(NullPointerException e){
			System.out.println("Aucune entreprise ne correspond à cet identifiant");
			return comp1;
		}
			
				
		return comp1;
		
	}
	
}

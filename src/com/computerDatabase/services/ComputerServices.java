package com.computerDatabase.services;

import java.util.Collection;

import com.computerDatabase.DAO.ComputerDao;
import com.computerDatabase.DAO.DAOFactory;
import com.computerDatabase.model.Computer;

public class ComputerServices {
	
	private static ComputerServices instance;
	
	public static ComputerServices getInstance() {
        if (null == instance) { 
            instance = new ComputerServices();
        }
        return instance;
    }
    
	private ComputerServices(){
		
	}
	
	public Collection<Computer> getComputerList(){
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		ComputerDao comp = daoFactory.getComputerDao();
				
		Collection<Computer> comp1 = comp.list();		
				
		return comp1;
		
	}
	
	public Computer getComputerDetails(int id){
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		ComputerDao comp = daoFactory.getComputerDao();
				
		Computer comp1 = comp.details(id);
		
		return comp1;
	}
	
	public void addComputer(Computer computer){
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		ComputerDao comp = daoFactory.getComputerDao();
		
		comp.create(computer);
		
	}
	
	public void updateComputer(Computer computer){
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		ComputerDao comp = daoFactory.getComputerDao();
		
		comp.update(computer);
		
	}
	
	public void deleteComputer(int id){
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		ComputerDao comp = daoFactory.getComputerDao();
		
		comp.delete(id);
		
	}
	
}

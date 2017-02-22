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
	
	public Computer getComputerDetails(long id){
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		ComputerDao comp = daoFactory.getComputerDao();
		Computer comp1 = new Computer();
		try{
			comp1 = comp.details(id);
		}catch(NullPointerException e){
			System.out.println("L'identifiant ne correspond a aucun ordinateur");
			return comp1;
		}
		
		
		return comp1;
	}
	
	public void addComputer(Computer computer){
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		ComputerDao comp = daoFactory.getComputerDao();
		
		try{
			comp.create(computer);
			System.out.println("Ordinateur ajouté avec succés");
		}catch(NullPointerException e){
			System.out.println("Erreur lors de l'ajout de l'ordinateur");
		}
		
		
	}
	
	public void updateComputer(Computer computer){
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		ComputerDao comp = daoFactory.getComputerDao();
		
		try{
			comp.update(computer);
			System.out.println("Ordinateur modifié avec succés");
		}catch(NullPointerException e){
			System.out.println("Erreur lors de la modification des details de l'ordinateur");
		}
		
		
	}
	
	public void deleteComputer(long id){
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		ComputerDao comp = daoFactory.getComputerDao();
		try{
			comp.delete(id);
			System.out.println("Ordinateur supprimé avec succés");
		}catch(NullPointerException e){
			System.out.println("Erreur lors de la suppression de l'ordinateur");
		}
		
		
	}
	
}

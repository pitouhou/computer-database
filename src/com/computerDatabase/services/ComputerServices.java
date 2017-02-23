package com.computerDatabase.services;

import java.util.Collection;

import com.computerDatabase.DAO.ComputerDAO;
import com.computerDatabase.model.Computer;

public enum ComputerServices implements ComputerServicesInterface{
	
	instance;
	
	public static ComputerServices getInstance() {
        
		return ComputerServices.instance;
		
    }
	
	private ComputerServices(){
		
	}
	
	@Override
	public Collection<Computer> getComputerList(){
		
		ComputerDAO comp = ComputerDAO.getInstance();
				
		Collection<Computer> comp1 = comp.findAll();		
				
		return comp1;
		
	}
	
	@Override
	public Computer getComputerDetails(long id){
		
		ComputerDAO comp = ComputerDAO.getInstance();
		Computer comp1 = new Computer();
		try{
			comp1 = comp.findById(id);
		}catch(NullPointerException e){
			System.out.println("L'identifiant ne correspond a aucun ordinateur");
			return comp1;
		}
		
		
		return comp1;
	}
	
	@Override
	public void addComputer(Computer computer){
		
		ComputerDAO comp = ComputerDAO.getInstance();
		
		try{
			comp.create(computer);
			System.out.println("Ordinateur ajouté avec succés");
		}catch(NullPointerException e){
			System.out.println("Erreur lors de l'ajout de l'ordinateur");
		}
		
		
	}
	
	@Override
	public void updateComputer(Computer computer){
		
		ComputerDAO comp = ComputerDAO.getInstance();
		
		try{
			comp.update(computer);
			System.out.println("Ordinateur modifié avec succés");
		}catch(NullPointerException e){
			System.out.println("Erreur lors de la modification des details de l'ordinateur");
		}
		
		
	}
	
	@Override
	public void deleteComputer(long id){
		
		ComputerDAO comp = ComputerDAO.getInstance();
		try{
			comp.delete(id);
			System.out.println("Ordinateur supprimé avec succés");
		}catch(NullPointerException e){
			System.out.println("Erreur lors de la suppression de l'ordinateur");
		}
		
		
	}
	
}

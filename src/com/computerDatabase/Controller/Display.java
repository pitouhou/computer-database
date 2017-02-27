package com.computerDatabase.Controller;

import java.util.Collection;
import java.util.Optional;

import com.computerDatabase.model.Company;
import com.computerDatabase.model.Computer;

public class Display {

	public static void displayComputer(Optional<Computer> computer){
		
		if(computer.isPresent()){
			Computer computer1 = computer.get();
			System.out.println("| 	id	 | 	nom  |  date d'introduction	 | 		date d'arret	 | 	id de l'entreprise	 |");
			System.out.println("| " + computer1.getId() + " | 	" + computer1.getName() + " 	| 	" + computer1.getIntroduced() + "	 | 	" + computer1.getDiscontinued() + "	 | 	" + computer1.getCompany().getId() + "	 |	" + computer1.getCompany().getName() + "	|");
		}else{
			System.out.println("L'ordinateur spécifié n'a pas été trouvé!");
		}
		
	}
	
	public static void displayComputers(Optional<Collection<Computer>> listComputer){
		
		if(listComputer.isPresent()){
			
			System.out.println("| 	id	 | 	nom  |  date d'introduction	 | 		date d'arret	 | 	id de l'entreprise	 |");
			
			for(Computer comp : listComputer.get()){
				
				System.out.println("| " + comp.getId() + " | 	" + comp.getName() + " 	| 	" + comp.getIntroduced() + "	 | 	" + comp.getDiscontinued() + "	 | 	" + comp.getCompany().getId() + "	 |	" + comp.getCompany().getName() + "	|");
				
			}
		}else{
			System.out.println("Aucun ordinateur trouvé !");
		}
		
	}
	
	public static void displayCompanies(Optional<Collection<Company>> listCompany){
		
		if(listCompany.isPresent()){
			System.out.println("| 	id	 | 	nom  | ");
			for(Company comp : listCompany.get()){
				System.out.println("| " + comp.getId() + " | 	" + comp.getName() + " 	| 	");
			}
		}else{
			System.out.println("niqué");
		}
		
	}
	
}

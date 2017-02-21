package com.computerDatabase;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Scanner;

import com.computerDatabase.entryUtils.DateUtils;
import com.computerDatabase.model.Company;
import com.computerDatabase.model.Computer;
import com.computerDatabase.services.CompanyServices;
import com.computerDatabase.services.ComputerServices;

public class Pages {
	
	public Pages(){
		
	}
	
	public void menu(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("[ COMPUTER DATABASE ]");
		System.out.println("/////////////////////");
		System.out.println("| (1) liste des ordinateurs || (2) liste des entreprises || (3) details ordinateur || (4) nouvel ordinateur || (5) modifier ordinateur || (6) supprimer ordinateur |");
		System.out.println("Choisissez votre action et tapez entré");
		int pt = sc.nextInt();
		
		switch(pt){
		case 1 : listComputers(); break;
		case 2 : listCompanies(); break;
		case 3 : computerDetails(); break;
		case 4 : createComputer(); break;
		case 5 : updateComputer(); break;
		case 6 : deleteComputer(); break;
                                
            default : System.out.println("entrez un choix"); break;
		}
	}
	
	public void listComputers(){
		ComputerServices service = new ComputerServices();
		
		Collection<Computer> list = service.getComputerList();
		System.out.println("| 	id	 | 	nom  |  date d'introduction	 | 		date d'arret	 | 	id de l'entreprise	 |");
		
		for(Computer comp : list){
			
			System.out.println("| " + comp.getId() + " | 	" + comp.getName() + " 	| 	" + comp.getIntroduced() + "	 | 	" + comp.getDiscontinued() + "	 | 	" + comp.getCompany_id() + "	 |");
			
		}
		menu();
	}
	
	public void computerDetails(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrez l'identifiant de l'ordinateur :");
		int id = sc.nextInt();
		ComputerServices service = new ComputerServices();
		Computer computer = service.getComputerDetails(id);
		System.out.println("| " + computer.getId() + " | 	" + computer.getName() + " 	| 	" + computer.getIntroduced() + "	 | 	" + computer.getDiscontinued() + "	 | 	" + computer.getCompany_id() + "	 |");
		menu();
	}
	
	public void createComputer(){
		
		 
		
		Computer computer = new Computer();
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrez le nom de l'ordinateur :");
		String name = sc.next();
		System.out.println("Entrez la date d'introduction de l'ordinateur :");
		String introduce = sc.next();
		System.out.println("Entrez la date d'arrêt de l'ordinateur :");
		String discontinue = sc.next();
		System.out.println("Entrez l'identifiant de l'entreprise :");
		int company_id = sc.nextInt();
		Date introduced = DateUtils.convertDate(introduce);
		Date discontinued = DateUtils.convertDate(discontinue);
		if(DateUtils.compareDate( introduced, discontinued)){
			
			computer.setName(name);
			computer.setIntroduced(introduced);
			computer.setDiscontinued(discontinued);
			computer.setCompany_id(company_id);
			
			System.out.println(" | 	" + computer.getName() + " 	| 	" + computer.getIntroduced() + "	 | 	" + computer.getDiscontinued() + "	 | 	" + computer.getCompany_id() + "	 |");
			ComputerServices service = new ComputerServices();
			service.addComputer(computer);
			menu();
			
		
		}else{
			
			System.out.println("La date d'introduction ne peut pas être supérieur a la date d'arrêt");
			createComputer();
		}
	}
	
	public void updateComputer(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir le numéro d'identification de l'ordinateur à modifier :");
		int id = sc.nextInt();
		ComputerServices service = new ComputerServices();
		Computer computer = new Computer();
		computer = service.getComputerDetails(id);
		System.out.println("| " + computer.getId() + " | 	" + computer.getName() + " 	| 	" + computer.getIntroduced() + "	 | 	" + computer.getDiscontinued() + "	 | 	" + computer.getCompany_id() + "	 |");
		
		
		System.out.println("Entrez le nouveau nom de l'ordinateur :");
		String name = sc.next();
		System.out.println("Entrez la nouvelle date d'introduction de l'ordinateur :");
		String introduce = sc.next();
		System.out.println("Entrez la nouvelle date d'arrêt de l'ordinateur :");
		String discontinue = sc.next();
		System.out.println("Entrez le nouvel identifiant de l'entreprise :");
		int company_id = sc.nextInt();
		Date introduced = DateUtils.convertDate(introduce);
		Date discontinued = DateUtils.convertDate(discontinue);
		if(DateUtils.compareDate( introduced, discontinued)){
			
			computer.setName(name);
			computer.setIntroduced(introduced);
			computer.setDiscontinued(discontinued);
			computer.setCompany_id(company_id);
			
			System.out.println(" | 	" + computer.getName() + " 	| 	" + computer.getIntroduced() + "	 | 	" + computer.getDiscontinued() + "	 | 	" + computer.getCompany_id() + "	 |");
			//ComputerServices service = new ComputerServices();
			service.updateComputer(computer);
			menu();
			
		
		}else{
			
			System.out.println("La date d'introduction ne peut pas être supérieur a la date d'arrêt");
			updateComputer();
		}
		
	}
	
	public void deleteComputer(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir le numéro d'identification de l'ordinateur à modifier :");
		int id = sc.nextInt();
		ComputerServices service = new ComputerServices();
		Computer computer = new Computer();
		computer = service.getComputerDetails(id);
		System.out.println("| " + computer.getId() + " | 	" + computer.getName() + " 	| 	" + computer.getIntroduced() + "	 | 	" + computer.getDiscontinued() + "	 | 	" + computer.getCompany_id() + "	 |");
		System.out.println("Etes vous sûr de vouloir supprimer cette ordinateur ? (1) oui (2) non");
		int rep = sc.nextInt();
		if(rep == 1){
			
			service.deleteComputer(id);
			System.out.println("Ordinateur supprimé !");
			
		}else{
			System.out.println("Opération annulée !");
			menu();
		}
	}
	
	public void listCompanies(){
		
		CompanyServices service = new CompanyServices();
		
		Collection<Company> list = service.getCompanyList();
		System.out.println("| 	id	 | 	nom  | ");
		
		for(Company comp : list){
			
			System.out.println("| " + comp.getId() + " | 	" + comp.getName() + " 	| 	");
			
		}
		menu();
		
	}
	
}

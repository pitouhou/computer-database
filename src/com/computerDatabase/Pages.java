package com.computerDatabase;

import java.sql.Date;
import java.util.Collection;
import java.util.Optional;
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
		System.out.println("Choisissez votre action et tapez entrée");
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
		menu();
	}
	
	public void listComputers(){
		ComputerServices service = ComputerServices.getInstance();
		
		Optional<Collection<Computer>> list = service.getComputerList();
		if(list.isPresent()){
			
			System.out.println("| 	id	 | 	nom  |  date d'introduction	 | 		date d'arret	 | 	id de l'entreprise	 |");
			
			for(Computer comp : list.get()){
				
					System.out.println("| " + comp.getId() + " | 	" + comp.getName() + " 	| 	" + comp.getIntroduced() + "	 | 	" + comp.getDiscontinued() + "	 | 	" + comp.getCompany().getId() + "	 |	" + comp.getCompany().getName() + "	|");
				
			}
			menu();
		}else{
			System.out.println("Aucun ordinateur trouvé !");
			menu();
		}
	}
	
	public void computerDetails(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrez l'identifiant de l'ordinateur :");
		long id = sc.nextLong();
		ComputerServices service = ComputerServices.getInstance();
		Optional<Computer> computer = service.getComputerDetails(id);
		
		if(computer.isPresent()){
			Computer computer1 = computer.get();
			System.out.println("| " + computer1.getId() + " | 	" + computer1.getName() + " 	| 	" + computer1.getIntroduced() + "	 | 	" + computer1.getDiscontinued() + "	 | 	" + computer1.getCompany().getId() + "	 |	" + computer1.getCompany().getName() + "	|");
			sc.reset();
			menu();
		}else{
			System.out.println("L'ordinateur spécifié n'a pas été trouvé!");
			sc.reset();
			menu();
		}
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
		long company_id = sc.nextLong();
		Date introduced = DateUtils.convertDate(introduce);
		Date discontinued = DateUtils.convertDate(discontinue);
		if(DateUtils.compareDate( introduced, discontinued)){
			
			Company company1 = new Company();
			CompanyServices compService = CompanyServices.getInstance();
			Optional<Company> company = compService.getCompany(company_id);
			if(company.isPresent()){
				
				company1 = company.get();
				
			}else{
				
				company1 = null;
				
			}
			
			computer.setName(name);
			computer.setIntroduced(introduced);
			computer.setDiscontinued(discontinued);
			computer.setCompany(company1);
			
			System.out.println(" | 	" + computer.getName() + " 	| 	" + computer.getIntroduced() + "	 | 	" + computer.getDiscontinued() + "	 | 	" + computer.getCompany().getId() + "	 |	" + computer.getCompany().getName() + "		|");
			ComputerServices service = ComputerServices.getInstance();
			service.addComputer(computer);
			sc.reset();
			menu();
		
		}else{
			System.out.println("La date d'introduction ne peut pas être supérieur a la date d'arrêt");
			sc.reset();
			createComputer();
		}
	}
	
	public void updateComputer(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir le numéro d'identification de l'ordinateur à modifier :");
		long id = sc.nextLong();
		ComputerServices service = ComputerServices.getInstance();
		Computer computer1 = new Computer();
		Optional<Computer> computer = service.getComputerDetails(id);
		
		if(computer.isPresent()){
			
			computer1 = computer.get();
			System.out.println("| " + computer1.getId() + " | 	" + computer1.getName() + " 	| 	" + computer1.getIntroduced() + "	 | 	" + computer1.getDiscontinued() + "	 | 	" + computer1.getCompany().getId() + "	 |	" + computer1.getCompany().getName() + "		|");
			System.out.println("Entrez le nouveau nom de l'ordinateur :");
			String name = sc.next();
			System.out.println("Entrez la nouvelle date d'introduction de l'ordinateur :");
			String introduce = sc.next();
			System.out.println("Entrez la nouvelle date d'arrêt de l'ordinateur :");
			String discontinue = sc.next();
			System.out.println("Entrez le nouvel identifiant de l'entreprise :");
			long company_id = sc.nextLong();
			Date introduced = DateUtils.convertDate(introduce);
			Date discontinued = DateUtils.convertDate(discontinue);
			if(DateUtils.compareDate( introduced, discontinued)){
				
				Company company1 = new Company();
				Optional<Company> company = Optional.empty();
				CompanyServices compService = CompanyServices.getInstance();
				company = compService.getCompany(company_id);
				if(company.isPresent()){
					
					company1 = company.get();
					
				}else{
					
					company = null;
					
				}
				
				computer1.setName(name);
				computer1.setIntroduced(introduced);
				computer1.setDiscontinued(discontinued);
				computer1.setCompany(company1);
				
				System.out.println(" | 	" + computer1.getName() + " 	| 	" + computer1.getIntroduced() + "	 | 	" + computer1.getDiscontinued() + "	 | 	" + computer1.getCompany().getId() + "	 |	" + computer1.getCompany().getName() + "	|");
				
				service.updateComputer(computer1);
				sc.reset();
				menu();
			}else{
				System.out.println("La date d'introduction ne peut pas être supérieur a la date d'arrêt");
				sc.reset();
				updateComputer();
			}
		}else{
			System.out.println("L'ordinateur spécifié n'a pas été trouvé!");
			sc.reset();
			createComputer();
		}
	}
	
	public void deleteComputer(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir le numéro d'identification de l'ordinateur à modifier :");
		long id = sc.nextLong();
		ComputerServices service = ComputerServices.getInstance();
		Computer computer1 = new Computer();
		Optional<Computer> computer = Optional.empty();
		computer = service.getComputerDetails(id);
		
		if(computer.isPresent()){
			
			computer1 = computer.get();
			System.out.println("| " + computer1.getId() + " | 	" + computer1.getName() + " 	| 	" + computer1.getIntroduced() + "	 | 	" + computer1.getDiscontinued() + "	 | 	" + computer1.getCompany().getId() + "	 |	" + computer1.getCompany().getName() + "		|");
			System.out.println("Etes vous sûr de vouloir supprimer cette ordinateur ? (1) oui (2) non");
			int rep = sc.nextInt();
			if(rep == 1){
				service.deleteComputer(id);
				System.out.println("Ordinateur supprimé !");
				sc.reset();
				menu();
			}else{
				System.out.println("Opération annulée !");
				sc.reset();
				menu();
			}
			
		}else{
			System.out.println("L'ordinateur spécifié n'a pas été trouvé!");
			sc.reset();
			menu();
		}
	}
	
	public void listCompanies(){
		
		CompanyServices compService = CompanyServices.getInstance();
		Optional<Collection<Company>> list = compService.getCompanyList();
		
		if(list.isPresent()){
			System.out.println("| 	id	 | 	nom  | ");
			for(Company comp : list.get()){
				System.out.println("| " + comp.getId() + " | 	" + comp.getName() + " 	| 	");
			}
			menu();
			
		}else{
			
		}
	}
}

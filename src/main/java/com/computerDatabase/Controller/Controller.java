package com.computerDatabase.Controller;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.computerDatabase.entryUtils.DateUtils;
import com.computerDatabase.model.Company;
import com.computerDatabase.model.Computer;
import com.computerDatabase.services.CompanyServices;
import com.computerDatabase.services.ComputerServices;

public class Controller {

  /**
   * Constructor of Controller class .
   */
  public Controller() {

  }

  /**
   * Method to get main menu .
   */
  public static void menu() {

    Scanner sc = new Scanner(System.in);
    System.out.println("[ COMPUTER DATABASE ]");
    System.out.println("/////////////////////");
    System.out.println(
        "| (1) liste des ordinateurs || (2) liste des entreprises || (3) details ordinateur || (4) nouvel ordinateur || (5) modifier ordinateur || (6) supprimer ordinateur |");
    System.out.println("Choisissez votre action et tapez entrée");
    int pt = sc.nextInt();

    switch (pt) {
      case 1:
        sc.reset();
        listComputers();
        break;
      case 2:
        sc.reset();
        listCompanies();
        break;
      case 3:
        sc.reset();
        computerDetails();
        break;
      case 4:
        sc.reset();
        createComputer();
        break;
      case 5:
        sc.reset();
        updateComputer();
        break;
      case 6:
        sc.reset();
        deleteComputer();
        break;

      default:
        System.out.println("entrez un choix");
        break;
    }
    menu();
  }

  /**
   * Method to get Computer list .
   */
  public static void listComputers() {

    ComputerServices service = ComputerServices.getInstance();
    List<Optional<Computer>> list = service.getComputerList();
    Display.displayComputers(list);
    menu();

  }

  /**
   * Method to get Computer details .
   */
  public static void computerDetails() {

    Scanner sc = new Scanner(System.in);
    System.out.println("Entrez l'identifiant de l'ordinateur :");
    long id = sc.nextLong();
    ComputerServices service = ComputerServices.getInstance();
    Optional<Computer> computer = service.getComputerDetails(id);
    Display.displayComputer(computer);
    sc.reset();
    menu();

  }

  /**
   * Method to get create Computer menu .
   */
  public static void createComputer() {

    Computer computer = getInputComputer();
    ComputerServices service = ComputerServices.getInstance();
    service.addComputer(computer);

  }

  /**
   * Method to get update Computer menu .
   */
  public static void updateComputer() {

    Scanner sc = new Scanner(System.in);
    System.out.println("Veuillez saisir le numéro d'identification de l'ordinateur à modifier :");
    long id = sc.nextLong();
    ComputerServices service = ComputerServices.getInstance();
    Optional<Computer> computer = service.getComputerDetails(id);
    if (computer.isPresent()) {

      Computer computer1 = getInputComputer(id);
      service.updateComputer(computer1);
      sc.reset();
      menu();

    } else {
      System.out.println("L'ordinateur spécifié n'a pas été trouvé!");
      sc.reset();
      menu();
    }
  }

  /**
   * Method to get delete Computer menu .
   */
  public static void deleteComputer() {

    Scanner sc = new Scanner(System.in);
    System.out.println("Veuillez saisir le numéro d'identification de l'ordinateur à modifier :");
    long id = sc.nextLong();
    ComputerServices service = ComputerServices.getInstance();
    Optional<Computer> computer = Optional.empty();
    computer = service.getComputerDetails(id);
    if (computer.isPresent()) {

      Computer computer1 = computer.get();
      System.out.println("| " + computer1.getId() + " | " + computer1.getName() + " | "
          + computer1.getIntroduced() + " | " + computer1.getDiscontinued() + " | "
          + computer1.getCompany().get().getId() + " | " + computer1.getCompany().get().getName() + " |");
      System.out.println("Etes vous sûr de vouloir supprimer cette ordinateur ? (1) oui (2) non");
      int rep = sc.nextInt();
      if (rep == 1) {
        service.deleteComputer(id);
        System.out.println("Ordinateur supprimé !");
        sc.reset();
        menu();
      } else {
        System.out.println("Opération annulée !");
        sc.reset();
        menu();
      }

    } else {
      System.out.println("L'ordinateur spécifié n'a pas été trouvé!");
      sc.reset();
      menu();
    }
  }

  /**
   * Method to get Company list .
   */
  public static void listCompanies() {

    CompanyServices compService = CompanyServices.getInstance();
    List<Optional<Company>> list = compService.getCompanyList();
    Display.displayCompanies(list);
    menu();

  }

  /**
   * Method to get the input details of a computer .
   * @return computer
   */
  public static Computer getInputComputer() {

    try {

      Scanner sc = new Scanner(System.in);
      System.out.println("Entrez le nom de l'ordinateur :");
      String name = sc.next();
      System.out.println("Entrez la date d'introduction de l'ordinateur :");
      String introduce = sc.next();
      LocalDate introduced = DateUtils.convertDate(introduce);
      System.out.println("Entrez la date d'arrêt de l'ordinateur :");
      String discontinue = sc.next();
      LocalDate discontinued = DateUtils.convertDate(discontinue);
      System.out.println("Entrez l'identifiant de l'entreprise :");
      long companyId = sc.nextLong();
      Computer computer;
      
      if (Validation.compareDate(introduced, discontinued)) {

        Optional<Company> company = Validation.isCompanyValid(companyId);

        
        if (company.isPresent()) {
          computer = new Computer.ComputerBuilder(name).introduced(introduced).discontinued(discontinued).company(company.get()).build();
        } else {
          computer = new Computer.ComputerBuilder(name).introduced(introduced).discontinued(discontinued).build();
        }

        System.out.println(" | " + computer.getName() + " | " + computer.getIntroduced() + " | " + computer.getDiscontinued() + " | " + computer.getCompany().get().getId() + " | " + computer.getCompany().get().getName() + " |");
        return computer;
      } else {
        System.out.println("La date d'introduction ne peut pas être supérieur a la date d'arrêt");
        sc.reset();
        menu();
      }

    } catch (InputMismatchException e) {
      System.out.println("Entrée non valide !");

      menu();
    }
    return null;
  }
  
  public static Computer getInputComputer(long id) {

    try {

      Scanner sc = new Scanner(System.in);
      System.out.println("Entrez le nom de l'ordinateur :");
      String name = sc.next();
      System.out.println("Entrez la date d'introduction de l'ordinateur :");
      String introduce = sc.next();
      LocalDate introduced = DateUtils.convertDate(introduce);
      System.out.println("Entrez la date d'arrêt de l'ordinateur :");
      String discontinue = sc.next();
      LocalDate discontinued = DateUtils.convertDate(discontinue);
      System.out.println("Entrez l'identifiant de l'entreprise :");
      long companyId = sc.nextLong();
      Computer computer;
      
      if (Validation.compareDate(introduced, discontinued)) {

        Optional<Company> company = Validation.isCompanyValid(companyId);

        
        if (company.isPresent()) {
          computer = new Computer.ComputerBuilder(name).id(id).introduced(introduced).discontinued(discontinued).company(company.get()).build();
        } else {
          computer = new Computer.ComputerBuilder(name).id(id).introduced(introduced).discontinued(discontinued).build();
        }

        System.out.println(" | " + computer.getName() + " | " + computer.getIntroduced() + " | " + computer.getDiscontinued() + " | " + computer.getCompany().get().getId() + " | " + computer.getCompany().get().getName() + " |");
        return computer;
      } else {
        System.out.println("La date d'introduction ne peut pas être supérieur a la date d'arrêt");
        sc.reset();
        menu();
      }

    } catch (InputMismatchException e) {
      System.out.println("Entrée non valide !");

      menu();
    }
    return null;
  }
}

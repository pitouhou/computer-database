package com.computerDatabase.cli;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.computerDatabase.entity.dto.CompanyDTO;
import com.computerDatabase.entity.dto.ComputerDTO;
import com.computerDatabase.entity.model.Company;
import com.computerDatabase.entity.model.Computer;
import com.computerDatabase.entryUtils.DateUtils;
import com.computerDatabase.exceptions.DAOException;
import com.computerDatabase.pager.CompanyPager;
import com.computerDatabase.pager.ComputerPager;
import com.computerDatabase.services.CompanyServices;
import com.computerDatabase.services.ComputerServices;
import com.computerDatabase.validation.Validation;

@Component
public class Controller {

  @Autowired
  private ComputerServices computerServices;
  
  @Autowired
  private ComputerPager computerPager;
  
  @Autowired
  private CompanyServices companyServices;
  
  @Autowired
  private CompanyPager companyPager;
  
  @Autowired
  private Validation validation;
  
  /**
   * Constructor of Controller class .
   */
  public Controller() {

  }
  
  public void delete(long id){
    computerServices.deleteComputer(id);
  }
  
  public ComputerDTO getComputer(long id){
    ComputerDTO computer = computerPager.getComputer(id).get();
    return computer;
  }
  
  public List<CompanyDTO> getCompanies(){
    List<CompanyDTO> listIn = new ArrayList<>();
    try {
      listIn = companyPager.getCompanyPage();
    } catch (DAOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return listIn;
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
        //listComputers();
        break;
      case 2:
        sc.reset();
        //listCompanies();
        break;
      case 3:
        sc.reset();
        //computerDetails();
        break;
      case 4:
        sc.reset();
        //createComputer();
        break;
      case 5:
        sc.reset();
        //updateComputer();
        break;
      case 6:
        sc.reset();
        //deleteComputer();
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
  public void listComputers() {

    List<Computer> list = computerServices.getComputerList(1, 10);
    Display.displayComputers(list);
    menu();

  }

  /**
   * Method to get Computer details .
   */
  public void computerDetails() {

    Scanner sc = new Scanner(System.in);
    System.out.println("Entrez l'identifiant de l'ordinateur :");
    long id = sc.nextLong();
    Optional<Computer> computer = computerServices.getComputerDetails(id);
    Display.displayComputer(computer);
    sc.reset();
    menu();

  }

  /**
   * Method to get create Computer menu .
   */
  public void createComputer() {

    Optional<Computer> computer = getInputComputer();
    if(computer.isPresent()){
      Computer computer1 = computer.get();
      computerServices.addComputer(computer1);
    }
  }

  /**
   * Method to get update Computer menu .
   */
  public void updateComputer() {

    Scanner sc = new Scanner(System.in);
    System.out.println("Veuillez saisir le numéro d'identification de l'ordinateur à modifier :");
    long id = sc.nextLong();
    Optional<Computer> computer = computerServices.getComputerDetails(id);
    if (computer.isPresent()) {

      Optional<Computer> computerOp = getInputComputer(id);
      if(computerOp.isPresent()){
        Computer computer1 = computerOp.get();
        computerServices.updateComputer(computer1);
        sc.reset();
        menu();
      }
      System.out.println("L'ordinateur spécifié n'a pas été trouvé!");
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
  public void deleteComputer() {

    Scanner sc = new Scanner(System.in);
    System.out.println("Veuillez saisir le numéro d'identification de l'ordinateur à modifier :");
    long id = sc.nextLong();
    Optional<Computer> computer = Optional.empty();
    computer = computerServices.getComputerDetails(id);
    if (computer.isPresent()) {

      Computer computer1 = computer.get();
      System.out.println("| " + computer1.getId() + " | " + computer1.getName() + " | "
          + computer1.getIntroduced() + " | " + computer1.getDiscontinued() + " | "
          + computer1.getCompany().get().getId() + " | " + computer1.getCompany().get().getName() + " |");
      System.out.println("Etes vous sûr de vouloir supprimer cette ordinateur ? (1) oui (2) non");
      int rep = sc.nextInt();
      if (rep == 1) {
        computerServices.deleteComputer(id);
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
  public void listCompanies() {

    List<Company> list = companyServices.getCompanyList();
    Display.displayCompanies(list);
    menu();

  }

  /**
   * Method to get the input details of a computer .
   * @return computer
   */
  public Optional<Computer> getInputComputer() {

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

        Optional<Company> company = validation.isCompanyValid(companyId);

        if (company.isPresent()) {
          computer = new Computer.ComputerBuilder(name).introduced(introduced).discontinued(discontinued).company(company.get()).build();
        } else {
          computer = new Computer.ComputerBuilder(name).introduced(introduced).discontinued(discontinued).build();
        }

        System.out.println(" | " + computer.getName() + " | " + computer.getIntroduced() + " | " + computer.getDiscontinued() + " | " + computer.getCompany().get().getId() + " | " + computer.getCompany().get().getName() + " |");
        return Optional.of(computer);
      } else {
        System.out.println("La date d'introduction ne peut pas être supérieur a la date d'arrêt");
        sc.reset();
        menu();
      }

    } catch (InputMismatchException e) {
      System.out.println("Entrée non valide !");

      menu();
    }
    return Optional.empty();
  }

  public Optional<Computer> getInputComputer(long id) {

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

        Optional<Company> company = validation.isCompanyValid(companyId);

        if (company.isPresent()) {
          computer = new Computer.ComputerBuilder(name).id(id).introduced(introduced).discontinued(discontinued).company(company.get()).build();
        } else {
          computer = new Computer.ComputerBuilder(name).id(id).introduced(introduced).discontinued(discontinued).build();
        }

        System.out.println(" | " + computer.getName() + " | " + computer.getIntroduced() + " | " + computer.getDiscontinued() + " | " + computer.getCompany().get().getId() + " | " + computer.getCompany().get().getName() + " |");
        return Optional.of(computer);
      } else {
        System.out.println("La date d'introduction ne peut pas être supérieur a la date d'arrêt");
        sc.reset();
        menu();
      }

    } catch (InputMismatchException e) {
      System.out.println("Entrée non valide !");

      menu();
    }
    return Optional.empty();
  }
}

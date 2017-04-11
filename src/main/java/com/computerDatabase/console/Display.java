package com.computerDatabase.console;

import java.util.List;
import java.util.Optional;

import com.computerDatabase.core.model.Company;
import com.computerDatabase.core.model.Computer;

public class Display {

  /**
   * Method to display details of a Computer .
   * @param computer : computer
   */
  public static void displayComputer(Optional<Computer> computer) {

    if (computer.isPresent()) {
      Computer computer1 = computer.get();
      System.out.println(
          "| id | nom | date d'introduction | date d'arret | id de l'entreprise |");
      System.out.println("| " + computer1.getId() + " | " + computer1.getName() + " | " + computer1.getIntroduced() + " | " + computer1.getDiscontinued() + " | " + computer1.getCompany().getId() + " | " + computer1.getCompany().getName() + " | ");
    } else {
      System.out.println("L'ordinateur spécifié n'a pas été trouvé!");
    }

  }

  /**
   * Method to display a list of Computer .
   * @param listComputer : listComputer
   */
  public static void displayComputers(List<Computer> listComputer) {

      System.out.println(
          "| id | nom | date d'introduction | date d'arret | id de l'entreprise |");

      for (Computer comp : listComputer) {
          System.out.println("| " + comp.getId() + " | " + comp.getName() + " | " + comp.getIntroduced() + " | " + comp.getDiscontinued() + " | " + comp.getCompany().getId() + " | " + comp.getCompany().getName() + " |");
      }
  }

  /**
   * Method to display a list of Company .
   * @param listCompany : listCompany
   */
  public static void displayCompanies(List<Company> listCompany) {

    
      System.out.println("| id | nom | ");
      for (Company comp : listCompany) {
          System.out.println("| " + comp.getId() + " | " + comp.getName() + " | ");
      }
  }

}

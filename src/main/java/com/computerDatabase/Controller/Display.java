package com.computerDatabase.Controller;

import java.util.List;
import java.util.Optional;

import com.computerDatabase.model.Company;
import com.computerDatabase.model.Computer;

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
      System.out.println("| " + computer1.getId() + " | " + computer1.getName() + " | " + computer1.getIntroduced() + " | " + computer1.getDiscontinued() + " | " + computer1.getCompany().get().getId() + " | " + computer1.getCompany().get().getName() + " | ");
    } else {
      System.out.println("L'ordinateur spécifié n'a pas été trouvé!");
    }

  }

  /**
   * Method to display a list of Computer .
   * @param listComputer : listComputer
   */
  public static void displayComputers(List<Optional<Computer>> listComputer) {
/*
    <c:choose>
    <c:when test="${ computer.getCompany().get().getId().get() }!=0">
        <p>${ computer.getCompany().getName().get() }</p>
    </c:when>
    <c:otherwise>
        <p>Non définit</p>
    </c:otherwise>
</c:choose>*/
    if (!listComputer.isEmpty()) {

      System.out.println(
          "| id | nom | date d'introduction | date d'arret | id de l'entreprise |");

      for (Optional<Computer> comp : listComputer) {
        if(comp.isPresent()){
          Computer comp1 = comp.get();
          System.out.println("| " + comp1.getId() + " | " + comp1.getName() + " | " + comp1.getIntroduced() + " | " + comp1.getDiscontinued() + " | " + comp1.getCompany().get().getId() + " | " + comp1.getCompany().get().getName().get() + " |");
        }
      }
    } else {
      System.out.println("Aucun ordinateur trouvé !");
    }

  }

  /**
   * Method to display a list of Company .
   * @param listCompany : listCompany
   */
  public static void displayCompanies(List<Optional<Company>> listCompany) {

    if (!listCompany.isEmpty()) {
      System.out.println("| id | nom | ");
      for (Optional<Company> comp : listCompany) {
        if(comp.isPresent()){
          System.out.println("| " + comp.get().getId() + " | " + comp.get().getName().get() + " | ");
        }
      }
    } else {
      System.out.println("Aucune entreprise trouvé");
    }

  }

}

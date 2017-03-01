package com.computerDatabase.Controller;

import java.time.LocalDate;
import java.util.Optional;

import com.computerDatabase.model.Company;
import com.computerDatabase.services.CompanyServices;

public class Validation {

  /**
   * Method to compare two LocalDate .
   * @param introduced : introduced
   * @param discontinued : discontinued
   * @return boolean
   */
  public static boolean compareDate(LocalDate introduced, LocalDate discontinued) {

   return introduced.isBefore(discontinued);

  }
  
  /**
   * Method to check if the company is valid .
   * @param id : id
   * @return Company
   */
  public static Optional<Company> isCompanyValid(long id) {

    CompanyServices compService = CompanyServices.getInstance();
    Optional<Company> company = compService.getCompany(id);
    if (company.isPresent()) {

      return company;

    } else {

      return Optional.empty();

    }
  }
}

package com.computerDatabase.controller;

import java.time.LocalDate;
import java.util.Optional;

import com.computerDatabase.dto.ComputerDTO;
import com.computerDatabase.entryUtils.DateUtils;
import com.computerDatabase.model.Company;
import com.computerDatabase.model.Computer;
import com.computerDatabase.services.CompanyServices;
import com.computerDatabase.services.ComputerServices;

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
  
  public static Optional<Computer> isComputerValid(ComputerDTO computer){
    ComputerServices service = ComputerServices.instance;
    CompanyServices compService = CompanyServices.instance;
    Optional<Computer> comp;
    Computer newComputer;
    long id;
    String name;
    LocalDate introduced;
    LocalDate discontinued;
    Company company;
    if(computer.getId()!=null){
      comp = service.getComputerDetails(Long.parseLong(computer.getId()));
      id = Long.parseLong(computer.getId());
      if(computer.getName()==null){
        name = comp.get().getName();
      }else{
        name = computer.getName();
      }
      if(computer.getIntroduced().length()>9){
        introduced = DateUtils.convertDate(computer.getIntroduced());
      }
      if((computer.getIntroduced().length()<9)&&(comp.get().getIntroduced().isPresent())){
        introduced = comp.get().getIntroduced().get();
      }else{
        introduced = null;
      }
      if(computer.getDiscontinued().length()>9){
        discontinued = DateUtils.convertDate(computer.getDiscontinued());
      }
      if((computer.getDiscontinued().length()<9)&&(comp.get().getDiscontinued().isPresent())){
        discontinued = comp.get().getDiscontinued().get();
      }else{
        discontinued = null;
      }
      if(computer.getCompany()!=null){
        company = compService.getCompany(Long.parseLong(computer.getCompany().getId())).get();
      }else{
        company = null;
      }
      newComputer = new Computer.ComputerBuilder(name).id(id).introduced(introduced).discontinued(discontinued).company(company).build();
      return Optional.of(newComputer);
    }else{
      
      name = computer.getName();
      
      if(computer.getIntroduced().length()>9){
        introduced = DateUtils.convertDate(computer.getIntroduced());
      }else{
        introduced = null;
      }
      
      if(computer.getDiscontinued().length()>9){
        discontinued = DateUtils.convertDate(computer.getDiscontinued());
      }else{
        discontinued = null;
      }
      
      if(computer.getCompany()!=null){
        company = compService.getCompany(Long.parseLong(computer.getCompany().getId())).get();
      }else{
        company = null;
      }
      newComputer = new Computer.ComputerBuilder(name).introduced(introduced).discontinued(discontinued).company(company).build();
      return Optional.of(newComputer);
    }
  }
}

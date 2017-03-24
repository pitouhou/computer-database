package com.computerDatabase.validation;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.computerDatabase.dto.ComputerDTO;
import com.computerDatabase.entryUtils.DateUtils;
import com.computerDatabase.model.Company;
import com.computerDatabase.model.Computer;
import com.computerDatabase.services.CompanyServices;
import com.computerDatabase.services.ComputerServices;

@Component
public class Validation {
  
  @Autowired
  private ComputerServices computerServices;
  
  @Autowired
  private CompanyServices companyServices;

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
  public Optional<Company> isCompanyValid(long id) {

    Optional<Company> company = companyServices.getCompany(id);
    if (company.isPresent()) {

      return company;

    } else {

      return Optional.empty();

    }
  }
  
  /**
   * Method to check if a computer is valid .
   * @param computer
   * @return Optional<Computer>
   */
  public Optional<Computer> isComputerValid(ComputerDTO computer){
    Optional<Computer> comp;
    Computer newComputer;
    long id;
    String name;
    LocalDate introduced;
    LocalDate discontinued;
    Company company;
    if(computer.getId()!=0){
      comp = computerServices.getComputerDetails(computer.getId());
      id = computer.getId();
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
        company = companyServices.getCompany(computer.getCompany().getId()).get();
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
      
      if(computer.getCompany().getId()!=0){
        company = companyServices.getCompany(computer.getCompany().getId()).get();
      }else{
        company = null;
      }
      newComputer = new Computer.ComputerBuilder(name).introduced(introduced).discontinued(discontinued).company(company).build();
      return Optional.of(newComputer);
    }
  }
}

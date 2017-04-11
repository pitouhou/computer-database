package com.computerDatabase.validation;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.computerDatabase.entity.dto.CompanyDTO;
import com.computerDatabase.entity.dto.ComputerDTO;
import com.computerDatabase.entity.model.Company;
import com.computerDatabase.entity.model.Computer;
import com.computerDatabase.entryUtils.DateUtils;
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
  public Optional<ComputerDTO> isComputerValid(ComputerDTO computer){
    Optional<Computer> comp;
    ComputerDTO newComputer;
    long id;
    String name;
    String introduced;
    String discontinued;
    CompanyDTO company = new CompanyDTO();
    if(computer.getId()!=0){
      comp = computerServices.getComputerDetails(computer.getId());
      id = computer.getId();
      if(computer.getName()==null){
        name = comp.get().getName();
      }else{
        name = computer.getName();
      }
      if(computer.getIntroduced().length()>9){
        introduced = computer.getIntroduced();
      }
      else if((computer.getIntroduced().length()<9)&&(comp.get().getIntroduced()!=null)){
        introduced = comp.get().getIntroduced().toString();
      }else{
        introduced = null;
      }
      if(computer.getDiscontinued().length()>9){
        discontinued = computer.getDiscontinued();
      }
      else if((computer.getDiscontinued().length()<9)&&(comp.get().getDiscontinued()!=null)){
        discontinued = comp.get().getDiscontinued().toString();
      }else{
        discontinued = null;
      }
      if(computer.getCompany().getId() != 0){
        company.setId(companyServices.getCompany(computer.getCompany().getId()).get().getId());
        company.setName(companyServices.getCompany(computer.getCompany().getId()).get().getName().get());
      }else{
        company = null;
      }
      newComputer = new ComputerDTO( id, name, introduced, discontinued, company);
      return Optional.of(newComputer);
    }else{
      
      name = computer.getName();
      
      if(computer.getIntroduced().length()>9){
        introduced = computer.getIntroduced();
      }else{
        introduced = null;
      }
      
      if(computer.getDiscontinued().length()>9){
        discontinued = computer.getDiscontinued();
      }else{
        discontinued = null;
      }
      
      if(computer.getCompany().getId()!=0){
        company.setId(companyServices.getCompany(computer.getCompany().getId()).get().getId());
        company.setName(companyServices.getCompany(computer.getCompany().getId()).get().getName().get());
      }else{
        company = null;
      }
      newComputer = new ComputerDTO( 0, name, introduced, discontinued, company);
      return Optional.of(newComputer);
    }
  }
}

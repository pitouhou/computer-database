package com.computerDatabase.entity.mapper;

import java.time.LocalDate;

import com.computerDatabase.entity.dto.CompanyDTO;
import com.computerDatabase.entity.dto.ComputerDTO;
import com.computerDatabase.entity.model.Company;
import com.computerDatabase.entity.model.Computer;
import com.computerDatabase.entryUtils.DateUtils;

public class ComputerDTOMapper {
  
  public static Computer mapperToComputer(ComputerDTO computer){
    
    LocalDate introduced;
    LocalDate discontinued;
    Computer comp;
    Company company;
    
    if(computer.getIntroduced()!=null){
      introduced = DateUtils.convertDate(computer.getIntroduced());
    }else{
      introduced = null;
    }
    if(computer.getDiscontinued()!=null){
      discontinued = DateUtils.convertDate(computer.getDiscontinued());
    }else{
      discontinued = null;
    }
    if(computer.getCompany() != null){
      company = new Company(computer.getCompany().getId(), computer.getCompany().getName());
    }else{
      company = null;
    }
    if(computer.getId() != 0){
      comp = new Computer.ComputerBuilder(computer.getName()).id(computer.getId()).introduced(introduced).discontinued(discontinued).company(company).build();
    }else{
      comp = new Computer.ComputerBuilder(computer.getName()).introduced(introduced).discontinued(discontinued).company(company).build();
    }
    
    return comp;
  }
  
public static ComputerDTO mapperToDTO(Computer computer){
    
    CompanyDTO company;
    String introduced;
    String discontinued;
    if (computer.getCompany().get().getId() != 0) {
      company = new CompanyDTO(computer.getCompany().get().getId(), computer.getCompany().get().getName().get().toString());
    } else {
      company = new CompanyDTO(0, "Non définit");
    }
    if (computer.getIntroduced().isPresent()) {
      introduced = DateUtils.convertToString(computer.getIntroduced().get());
      introduced = introduced.replace(" ", "-");
    } else {
      introduced = "Non définit";
    }
    if (computer.getDiscontinued().isPresent()) {
      discontinued = DateUtils.convertToString(computer.getDiscontinued().get());
      discontinued = discontinued.replace(" ", "-");
    } else {
      discontinued = "Non définit";
    }
    ComputerDTO computerDTO = new ComputerDTO(computer.getId(), computer.getName().toString(), introduced, discontinued, company);
    return computerDTO;
   
  
  }

}

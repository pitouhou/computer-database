package com.computerDatabase.pager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.computerDatabase.dto.CompanyDTO;
import com.computerDatabase.dto.ComputerDTO;
import com.computerDatabase.entryUtils.DateUtils;
import com.computerDatabase.model.Computer;
import com.computerDatabase.services.ComputerServices;

@Component
public class ComputerPager {
  
  @Autowired
  private ComputerServices computerServices;
  
  public static int current;
  public static int nbComputer;

  public List<ComputerDTO> computerList(int current, int range) {
    System.out.println("hello billy 222222222222222222!!!!!!!!!!");
    List<Computer> list = computerServices.getComputerList(current, range);
    List<ComputerDTO> listOut = new ArrayList<>();
    CompanyDTO company;
    String introduced;
    String discontinued;
    if (!list.isEmpty()) {
      for (Computer comp : list) {
          if (comp.getCompany().get().getId() != 0) {
            company = new CompanyDTO(comp.getCompany().get().getId(), comp.getCompany().get().getName().get().toString());
          } else {
            company = new CompanyDTO(0, "Non définit");
          }
          if (comp.getIntroduced().isPresent()) {
            introduced = DateUtils.convertToString(comp.getIntroduced().get());
          } else {
            introduced = "Non définit";
          }
          if (comp.getDiscontinued().isPresent()) {
            discontinued = DateUtils.convertToString(comp.getDiscontinued().get());
          } else {
            discontinued = "Non définit";
          }
          ComputerDTO computer = new ComputerDTO(comp.getId(), comp.getName().toString(), introduced, discontinued, company);
          listOut.add(computer);
      }
    }
    return listOut;
  }

  public List<ComputerDTO> searchByName(String name) {
    List<Computer> list = computerServices.getByName(name);
    List<ComputerDTO> listOut = new ArrayList<>();
    CompanyDTO company;
    String introduced;
    String discontinued;
    if (!list.isEmpty()) {

      for (Computer comp : list) {
          if (comp.getCompany().get().getId() != 0) {
            company = new CompanyDTO(comp.getCompany().get().getId(), comp.getCompany().get().getName().get().toString());
          } else {
            company = new CompanyDTO(0, "Non définit");
          }
          if (comp.getIntroduced().isPresent()) {
            introduced = DateUtils.convertToString(comp.getIntroduced().get());
          } else {
            introduced = "Non définit";
          }
          if (comp.getDiscontinued().isPresent()) {
            discontinued = DateUtils.convertToString(comp.getDiscontinued().get());
          } else {
            discontinued = "Non définit";
          }
          ComputerDTO computer = new ComputerDTO(comp.getId(), comp.getName().toString(), introduced, discontinued, company);
          System.out.println("computer added");
          listOut.add(computer);
      }
    }
    return listOut;
  }
  
  public ComputerDTO getComputer(long id){
    
    Computer computer = computerServices.getComputerDetails(id).get();
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
    } else {
      introduced = "Non définit";
    }
    if (computer.getDiscontinued().isPresent()) {
      discontinued = DateUtils.convertToString(computer.getDiscontinued().get());
    } else {
      discontinued = "Non définit";
    }
    ComputerDTO computerDTO = new ComputerDTO(computer.getId(), computer.getName().toString(), introduced, discontinued, company);
    return computerDTO;
  }
  
  public int getNbPage(int range) {

    int nb = computerServices.countComputer();
    return nb/range;

  }

  public int getNbComputer(){
    return computerServices.countComputer();
  }

}

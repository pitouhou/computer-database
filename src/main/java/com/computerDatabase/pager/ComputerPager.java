package com.computerDatabase.pager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.computerDatabase.entity.dto.CompanyDTO;
import com.computerDatabase.entity.dto.ComputerDTO;
import com.computerDatabase.entity.mapper.ComputerDTOMapper;
import com.computerDatabase.entity.model.Computer;
import com.computerDatabase.entryUtils.DateUtils;
import com.computerDatabase.services.ComputerServices;

@Component
public class ComputerPager {
  
  @Autowired
  private ComputerServices computerServices;
  
  public static int current;
  public static int nbComputer;

  public List<ComputerDTO> computerList(int current, int range) {
    List<Computer> list = computerServices.getComputerList(current, range);
    List<ComputerDTO> listOut = new ArrayList<>();
    CompanyDTO company;
    String introduced;
    String discontinued;
    if (!list.isEmpty()) {
      for (Computer comp : list) {
          if ((comp.getCompany()!=null)&&(comp.getCompany().getId()!=0)) {
            company = new CompanyDTO(comp.getCompany().getId(), comp.getCompany().getName().get().toString());
          } else {
            company = new CompanyDTO(0, "Non défini");
          }
          if (comp.getIntroduced()!=null) {
            introduced = DateUtils.convertToString(comp.getIntroduced());
          } else {
            introduced = "Non défini";
          }
          if (comp.getDiscontinued()!=null) {
            discontinued = DateUtils.convertToString(comp.getDiscontinued());
          } else {
            discontinued = "Non défini";
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
          if (comp.getCompany()!=null) {
            company = new CompanyDTO(comp.getCompany().getId(), comp.getCompany().getName().get().toString());
          } else {
            company = new CompanyDTO(0, "Non définit");
          }
          if (comp.getIntroduced()!=null) {
            introduced = DateUtils.convertToString(comp.getIntroduced());
          } else {
            introduced = "Non définit";
          }
          if (comp.getDiscontinued()!=null) {
            discontinued = DateUtils.convertToString(comp.getDiscontinued());
          } else {
            discontinued = "Non définit";
          }
          ComputerDTO computer = new ComputerDTO(comp.getId(), comp.getName().toString(), introduced, discontinued, company);
          listOut.add(computer);
      }
    }
    return listOut;
  }
  
  public Optional<ComputerDTO> getComputer(long id){
    
    if(computerServices.getComputerDetails(id).isPresent()){
      Computer computer = computerServices.getComputerDetails(id).get();
      ComputerDTO computerDTO = ComputerDTOMapper.mapperToDTO(computer);
      return Optional.of(computerDTO);
    }else{
      return Optional.empty();
    }
  }
  
  public int getNbPage(int range) {

    int nb = computerServices.countComputer();
    return nb/range;

  }

  public int getNbComputer(){
    return computerServices.countComputer();
  }

}

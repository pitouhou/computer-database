package com.computerDatabase.pager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.computerDatabase.DTO.CompanyDTO;
import com.computerDatabase.DTO.ComputerDTO;
import com.computerDatabase.entryUtils.DateUtils;
import com.computerDatabase.model.Company;
import com.computerDatabase.model.Computer;
import com.computerDatabase.services.ComputerServices;

public class ComputerPager {

  public static List<ComputerDTO> computerList(){
    
    ComputerServices service = ComputerServices.getInstance();
    List<Optional<Computer>> list = service.getComputerList();
    List<ComputerDTO> listOut = new ArrayList<>();
    CompanyDTO company;
    String introduced;
    String discontinued;
    if (!list.isEmpty()) {

      for (Optional<Computer> comp : list) {
        if(comp.isPresent()){
          if(comp.get().getCompany().get().getId()!=0){
            company = new CompanyDTO.CompanyDTOBuilder(Long.toString(comp.get().getCompany().get().getId()), comp.get().getCompany().get().getName().get().toString()).build();
          }else{
            company = new CompanyDTO.CompanyDTOBuilder("Non définit", "Non définit").build();
          }
          if(comp.get().getIntroduced().isPresent()){
            introduced = DateUtils.convertToString(comp.get().getIntroduced().get());
          }else{
            introduced = "Non définit";
          }
          if(comp.get().getDiscontinued().isPresent()){
            discontinued = DateUtils.convertToString(comp.get().getDiscontinued().get());
          }else{
            discontinued = "Non définit";
          }
          ComputerDTO computer = new ComputerDTO.ComputerDTOBuilder(comp.get().getName().toString()).id(Long.toString(comp.get().getId())).introduced(introduced).discontinued(discontinued).company(company).build();
          listOut.add(computer);
        }
      }
    }
    return listOut;
  }
  
  public static int getNbPage(int nb){
    
    ComputerServices service = ComputerServices.getInstance();
    List<Optional<Computer>> list = service.getComputerList();
    return list.size()/nb;
    
  }
  
  public static List<ComputerDTO> getComputerPage(int current, int nb){
    
    List<ComputerDTO> listOut = new ArrayList<>();
    List<ComputerDTO> listIn = computerList();
    for(int i = ((current*nb)-nb); i<(current*nb);i++){
      if(i<listIn.size()){
        listOut.add(listIn.get(i));
      }
    }
    return listOut;
  }
  
}

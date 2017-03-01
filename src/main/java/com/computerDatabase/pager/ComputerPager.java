package com.computerDatabase.pager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.computerDatabase.model.Company;
import com.computerDatabase.model.Computer;
import com.computerDatabase.services.ComputerServices;

public class ComputerPager {

  public static List<Computer> computerList(){
    
    ComputerServices service = ComputerServices.getInstance();
    List<Optional<Computer>> list = service.getComputerList();
    List<Computer> listOut = new ArrayList<>();
    Company company;
    LocalDate introduced;
    LocalDate discontinued;
    if (!list.isEmpty()) {

      for (Optional<Computer> comp : list) {
        if(comp.isPresent()){
          if(comp.get().getCompany().get().getId()!=0){
            company = new Company.CompanyBuilder(comp.get().getCompany().get().getId(), comp.get().getCompany().get().getName().get()).build();
          }else{
            company = null;
          }
          if(comp.get().getIntroduced().isPresent()){
            introduced = comp.get().getIntroduced().get();
          }else{
            introduced = null;
          }
          if(comp.get().getDiscontinued().isPresent()){
            discontinued = comp.get().getDiscontinued().get();
          }else{
            discontinued = null;
          }
          Computer computer = new Computer.ComputerBuilder(comp.get().getName()).id(comp.get().getId()).introduced(introduced).discontinued(discontinued).company(company).build();
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
  
  public static List<Computer> getComputerPage(int current, int nb){
    
    List<Computer> listOut = new ArrayList<>();
    List<Computer> listIn = computerList();
    for(int i = ((current*nb)-nb); i<(current*nb);i++){
      if(i<listIn.size()){
        listOut.add(listIn.get(i));
      }
    }
    return listOut;
  }
  
}

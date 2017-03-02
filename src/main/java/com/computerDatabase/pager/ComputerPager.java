package com.computerDatabase.pager;

import java.util.ArrayList;
import java.util.List;

import com.computerDatabase.dto.CompanyDTO;
import com.computerDatabase.dto.ComputerDTO;
import com.computerDatabase.entryUtils.DateUtils;
import com.computerDatabase.model.Computer;
import com.computerDatabase.services.ComputerServices;

public class ComputerPager {

  public static List<ComputerDTO> computerList() {
    ComputerServices service = ComputerServices.getInstance();
    List<Computer> list = service.getComputerList();
    List<ComputerDTO> listOut = new ArrayList<>();
    CompanyDTO company;
    String introduced;
    String discontinued;
    if (!list.isEmpty()) {

      for (Computer comp : list) {
          if (comp.getCompany().get().getId() != 0) {
            company = new CompanyDTO.CompanyDTOBuilder(Long.toString(comp.getCompany().get().getId()), comp.getCompany().get().getName().get().toString()).build();
          } else {
            company = new CompanyDTO.CompanyDTOBuilder("Non définit", "Non définit").build();
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
          ComputerDTO computer = new ComputerDTO.ComputerDTOBuilder(comp.getName().toString()).id(Long.toString(comp.getId())).introduced(introduced).discontinued(discontinued).company(company).build();
          listOut.add(computer);
      }
    }
    return listOut;
  }

  public static int getNbPage(int nb) {

    ComputerServices service = ComputerServices.getInstance();
    List<Computer> list = service.getComputerList();
    return list.size() / nb;

  }

  public static List<ComputerDTO> getComputerPage(int current, int nb) {

    List<ComputerDTO> listOut = new ArrayList<>();
    List<ComputerDTO> listIn = computerList();
    for (int i = ((current * nb) - nb); i < (current * nb); i++) {
      if (i < listIn.size()) {
        listOut.add(listIn.get(i));
      }
    }
    return listOut;
  }

}

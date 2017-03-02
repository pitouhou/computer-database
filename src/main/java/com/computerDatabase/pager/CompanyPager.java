package com.computerDatabase.pager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.computerDatabase.dto.CompanyDTO;
import com.computerDatabase.model.Company;
import com.computerDatabase.services.CompanyServices;

public class CompanyPager {

  public static List<CompanyDTO> getCompanyPage() {
    CompanyServices compService = CompanyServices.getInstance();
    List<Optional<Company>> list = compService.getCompanyList();
    List<CompanyDTO> listOut = new ArrayList<>();
    CompanyDTO company;
    if (!list.isEmpty()) {
      for (Optional<Company> comp : list) {
        if (comp.isPresent()) {
          company = new CompanyDTO.CompanyDTOBuilder(Long.toString(comp.get().getId()), comp.get().getName().get().toString()).build();
        } else {
          company = null;
        }
        listOut.add(company);
      }
      return listOut;
    } else {
      return null;
    }
  }
}

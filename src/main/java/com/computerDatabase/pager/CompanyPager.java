package com.computerDatabase.pager;

import java.util.ArrayList;
import java.util.List;

import com.computerDatabase.dto.CompanyDTO;
import com.computerDatabase.model.Company;
import com.computerDatabase.services.CompanyServices;

public class CompanyPager {

  public static List<CompanyDTO> getCompanyPage() {
    CompanyServices compService = CompanyServices.getInstance();
    List<Company> list = compService.getCompanyList();
    List<CompanyDTO> listOut = new ArrayList<>();
    CompanyDTO company;
    if (!list.isEmpty()) {
      for (Company comp : list) {
        company = new CompanyDTO.CompanyDTOBuilder(Long.toString(comp.getId()), comp.getName().get().toString()).build();
        listOut.add(company);
      }
      return listOut;
    } else {
      return null;
    }
  }
}

package com.computerDatabase.pager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.computerDatabase.dto.CompanyDTO;
import com.computerDatabase.exceptions.DAOException;
import com.computerDatabase.model.Company;
import com.computerDatabase.services.CompanyServices;

public class CompanyPager {

  public static List<CompanyDTO> getCompanyPage() throws DAOException {
    CompanyServices compService = CompanyServices.getInstance();
    List<Company> list = compService.getCompanyList();
    List<CompanyDTO> listOut = new ArrayList<>();
    CompanyDTO company;
    if (!list.isEmpty()) {
      for (Company comp : list) {
        company = new CompanyDTO.CompanyDTOBuilder(comp.getId(), comp.getName().get().toString()).build();
        listOut.add(company);
      }
      return listOut;
    } else {
      return null;
    }
  }
  
  public static Optional<CompanyDTO> getCompany(long id) {
    CompanyServices compService = CompanyServices.getInstance();
    Optional<Company> company = compService.getCompany(id);
    CompanyDTO companyDto;
    if(company.isPresent()){
      companyDto = new CompanyDTO.CompanyDTOBuilder(company.get().getId(), company.get().getName().get().toString()).build();
      return Optional.of(companyDto);
    }
    return Optional.empty();
  }
}

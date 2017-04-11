package com.computerDatabase.services.pager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.computerDatabase.core.dto.CompanyDTO;
import com.computerDatabase.core.model.Company;
import com.computerDatabase.dao.exceptions.DAOException;
import com.computerDatabase.services.CompanyServices;

@Component
public class CompanyPager {
  
  @Autowired
  private CompanyServices companyServices;

  public List<CompanyDTO> getCompanyPage() throws DAOException {
    List<Company> list = companyServices.getCompanyList();
    
    List<CompanyDTO> listOut = new ArrayList<>();
    CompanyDTO company;
    if (!list.isEmpty()) {
      for (Company comp : list) {
        company = new CompanyDTO(comp.getId(), comp.getName().toString());
        listOut.add(company);
      }
      return listOut;
    } else {
      return null;
    }
  }
  
  public Optional<CompanyDTO> getCompany(long id) {
    Optional<Company> company = companyServices.getCompany(id);
    CompanyDTO companyDto;
    if(company.isPresent()){
      companyDto = new CompanyDTO(company.get().getId(), company.get().getName().toString());
      return Optional.of(companyDto);
    }
    return Optional.empty();
  }
}

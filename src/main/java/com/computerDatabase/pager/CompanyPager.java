package com.computerDatabase.pager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.computerDatabase.model.Company;
import com.computerDatabase.services.CompanyServices;

public class CompanyPager {

  public static List<Company> getCompanyPage(){
    
    CompanyServices compService = CompanyServices.getInstance();
    List<Optional<Company>> list = compService.getCompanyList();
    List<Company> listOut =new ArrayList<>();
    Company company;
    if (!list.isEmpty()) {
      for (Optional<Company> comp : list) {
        if(comp.isPresent()){
          company = new Company.CompanyBuilder(comp.get().getId(), comp.get().getName().get()).build();
        }else{
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

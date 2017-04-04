package com.computerDatabase.formatter;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import com.computerDatabase.entity.dto.CompanyDTO;
import com.computerDatabase.pager.CompanyPager;

@Component
public class CompanyDTOFormatter implements Formatter<CompanyDTO> {

  @Autowired
  private CompanyPager companyPager;
  
  @Override 
  public String print(CompanyDTO company, Locale arg1) {
    
    return company.toString();
  }

  @Override 
  public CompanyDTO parse(String companyId, Locale arg1) throws ParseException {
    long id = Long.parseLong(companyId);
    Optional<CompanyDTO> company = companyPager.getCompany(id);
    CompanyDTO comp;
    if(company.isPresent()){
      System.out.println(company.get().getName());
      comp = company.get();
    }else{
      comp = null;
    }
    return comp;
  }
}

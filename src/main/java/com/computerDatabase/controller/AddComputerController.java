package com.computerDatabase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.computerDatabase.dto.CompanyDTO;
import com.computerDatabase.dto.ComputerDTO;
import com.computerDatabase.exceptions.DAOException;
import com.computerDatabase.model.Computer;
import com.computerDatabase.pager.CompanyPager;
import com.computerDatabase.services.ComputerServices;
import com.computerDatabase.validation.Validation;

@Controller
@RequestMapping("/addComputer")
public class AddComputerController {

  @Autowired
  private CompanyPager companyPager;
  
  @Autowired
  private ComputerServices computerServices;
  
  @Autowired
  private Validation validation;
  
  @RequestMapping(method = RequestMethod.GET)
  public String addComputer(ModelMap model) {
    List<CompanyDTO> listIn;
    ComputerDTO computer = new ComputerDTO();
    CompanyDTO company = new CompanyDTO();
    computer.setCompany(company);
    try {
      listIn = companyPager.getCompanyPage();
      model.addAttribute("computer", computer);
      model.addAttribute("list", listIn);
    } catch (DAOException e) {
    }
    return "addComputer";
  }
  
  
  @RequestMapping(method = RequestMethod.POST)
  public String addComputer(ModelMap model, @ModelAttribute("computer") @Validated ComputerDTO computer, BindingResult result) {
    
    Computer computerUp = validation.isComputerValid(computer).get();
    computerServices.addComputer(computerUp);
    return "redirect:/";
  }
}

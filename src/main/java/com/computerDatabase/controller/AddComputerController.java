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

import com.computerDatabase.entity.dto.CompanyDTO;
import com.computerDatabase.entity.dto.ComputerDTO;
import com.computerDatabase.entity.mapper.ComputerDTOMapper;
import com.computerDatabase.entity.model.Computer;
import com.computerDatabase.exceptions.DAOException;
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
      return "addComputer";
    } catch (DAOException e) {
      model.addAttribute("error", e.getMessage());
      return "redirect:/";
    }
    
  }
  
  
  @RequestMapping(method = RequestMethod.POST)
  public String addComputer(ModelMap model, @ModelAttribute("computer") @Validated ComputerDTO computer, BindingResult result) {
    try{
      ComputerDTO computerUp = validation.isComputerValid(computer).get();
      Computer newComputer = ComputerDTOMapper.mapperToComputer(computerUp);
      computerServices.addComputer(newComputer);
      return "redirect:/";
    } catch (DAOException e){
      model.addAttribute("error", e.getMessage());
      return "redirect:/";
    }
  }
}

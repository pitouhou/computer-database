package com.computerDatabase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.computerDatabase.dto.CompanyDTO;
import com.computerDatabase.dto.ComputerDTO;
import com.computerDatabase.exceptions.DAOException;
import com.computerDatabase.pager.CompanyPager;
import com.computerDatabase.pager.ComputerPager;

@Controller
@RequestMapping("/")
public class EditComputerController {
  
  @Autowired
  private ComputerPager computerPager;
  
  @Autowired
  private CompanyPager companyPager;

  @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
  public String addComputer(@PathVariable("id") int id, ModelMap model) {
    ComputerDTO computer = computerPager.getComputer(id);
    List<CompanyDTO> listIn;
    CompanyDTO company = new CompanyDTO();
    computer.setCompany(company);
    try {
      listIn = companyPager.getCompanyPage();
      model.addAttribute("computer", computer);
      model.addAttribute("list", listIn);
    } catch (DAOException e) {
    }
    return "editComputer";
  }
  
  
  @RequestMapping(method = RequestMethod.POST)
  public String addComputer(ModelMap model, @ModelAttribute("computer") @Validated ComputerDTO computer, BindingResult result) {
    /*
    Computer computerUp = validation.isComputerValid(computer).get();
    computerServices.addComputer(computerUp);*/
    return "redirect:/";
  }
  
}

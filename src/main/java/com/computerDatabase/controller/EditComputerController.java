package com.computerDatabase.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.computerDatabase.entity.dto.CompanyDTO;
import com.computerDatabase.entity.dto.ComputerDTO;
import com.computerDatabase.entity.mapper.ComputerDTOMapper;
import com.computerDatabase.entity.model.Computer;
import com.computerDatabase.exceptions.DAOException;
import com.computerDatabase.pager.CompanyPager;
import com.computerDatabase.pager.ComputerPager;
import com.computerDatabase.services.ComputerServices;
import com.computerDatabase.validation.Validation;

@Controller
@RequestMapping("/editComputer")
public class EditComputerController {
  
  @Autowired
  private ComputerPager computerPager;
  
  @Autowired
  private CompanyPager companyPager;
  
  @Autowired
  private Validation validation;
  
  @Autowired
  private ComputerServices computerServices;

  @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
  public String editComputer(@PathVariable("id") int id, ModelMap model) {
    try {
    Optional<ComputerDTO> computer = computerPager.getComputer(id);
      if(computer.isPresent()){
        List<CompanyDTO> listIn;
        listIn = companyPager.getCompanyPage();
        model.addAttribute("computer", computer);
        model.addAttribute("list", listIn);
      }else{
        model.addAttribute("error", "Pas d'ordinateur trouvé");
        return "redirect:/";
      }
    } catch (DAOException e) {
      model.addAttribute("error", e.getMessage());
      return "redirect:/";
    }
    return "editComputer";
  }

  @RequestMapping(method = RequestMethod.POST)
  public String editComputer(ModelMap model, @ModelAttribute("computer") @Validated ComputerDTO computer, BindingResult result) {
    
    ComputerDTO computerUp = validation.isComputerValid(computer).get();
    Computer newComputer = ComputerDTOMapper.mapperToComputer(computerUp);
    computerServices.updateComputer(newComputer);
    return "redirect:/";
  }
  
}

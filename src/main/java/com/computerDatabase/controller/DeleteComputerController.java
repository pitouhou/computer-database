package com.computerDatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.computerDatabase.exceptions.DAOException;
import com.computerDatabase.services.ComputerServices;

@Controller
@RequestMapping("/delete")
public class DeleteComputerController {

  @Autowired
  private ComputerServices computerServices;
  
  @RequestMapping(method = RequestMethod.GET)
  public String deleteComputer(ModelMap model) {
    return "redirect:/";
  }
  
  @RequestMapping(method = RequestMethod.POST)
  public String deleteComputer(ModelMap model, @ModelAttribute("selection") String selection) {
    String[] id = selection.split(",");
    try{
      for(int i = 0; i<id.length; i++){
        computerServices.deleteComputer(Long.parseLong(id[i], 10));
      }
      return "redirect:/";
    } catch (DAOException e){
      model.addAttribute("error", e.getMessage());
      return "redirect:/";
    }
  }
}

package com.computerDatabase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.computerDatabase.entity.dto.ComputerDTO;
import com.computerDatabase.exceptions.DAOException;
import com.computerDatabase.pager.ComputerPager;

@Controller
@RequestMapping("/Search")
public class SearchController {

  @Autowired
  private ComputerPager computerPager;
  
  @RequestMapping(method = RequestMethod.GET)
  public String search(ModelMap model, @RequestParam(value="search") String search) {
    if(search.isEmpty()){
      model.addAttribute("error", "Votre recherche est vide !");
      return "redirect:/";
    }else{
      try{
        List<ComputerDTO> list = computerPager.searchByName(search);
        if(list.isEmpty()){
          model.addAttribute("error", "Aucun ordinateur trouvé pour votre recherche ! ");
          return "redirect:/";
        }
        model.addAttribute("list", list);
        model.addAttribute("nbComputer", computerPager.getNbComputer());
        return "Dashboard";
      } catch (DAOException e){
        model.addAttribute("error", e.getMessage());
        return "redirect:/";
      }
      
    }
    
  }
}

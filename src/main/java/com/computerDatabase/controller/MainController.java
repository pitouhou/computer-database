package com.computerDatabase.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.CriteriaBuilder;
import com.computerDatabase.entity.dto.ComputerDTO;
import com.computerDatabase.entity.model.Computer;
import com.computerDatabase.exceptions.DAOException;
import com.computerDatabase.pager.ComputerPager;
import com.computerDatabase.services.ComputerServices;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/")
public class MainController {

  @Autowired
  private ComputerPager computerPager;
  
  @Autowired
  private ComputerServices computerServices;
  
  /*@Autowired
  private EntityManager em;*/
  
  /*@Autowired
  private CriteriaBuilderFactory cbf;*/
  
  @RequestMapping(method = RequestMethod.GET)
  public String dashboard(ModelMap model, @RequestParam(value="error", required=false) String error) {
    int current = 1;
    int range = 10;
    try{
      //List<ComputerDTO> list = computerPager.computerList(current, range);
      //CriteriaBuilder<Computer> cb = cbf.create(em, Computer.class);
      model.addAttribute("error", error);
      //model.addAttribute("list", cb);
      model.addAttribute("nbPage", computerPager.getNbPage(range));
      model.addAttribute("current", current);
      model.addAttribute("range", range);
      model.addAttribute("nbComputer", computerPager.getNbComputer());
    
      return "Dashboard";
    }catch(DAOException ex){
      model.addAttribute("error", ex.getMessage());
      return "Dashboard";
    }
    
    
  }

  @RequestMapping(value = "/current/{current}/range/{range}", method = RequestMethod.GET)
  public String dashboard(@PathVariable("current") int current, @PathVariable("range") int range, ModelMap model) {
    try{
      List<ComputerDTO> list = computerPager.computerList(current, range);
      model.addAttribute("list", list);
      model.addAttribute("nbPage", computerPager.getNbPage(range));
      model.addAttribute("current", current);
      model.addAttribute("range", range);
      model.addAttribute("nbComputer", computerPager.getNbComputer());
    
      return "Dashboard";
    }catch(DAOException ex){
      model.addAttribute("error", ex.getMessage());
      return "Dashboard";
    }
  }
}

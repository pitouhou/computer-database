package com.computerDatabase.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.computerDatabase.dto.ComputerDTO;
import com.computerDatabase.pager.ComputerPager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/")
public class MainController {

  @Autowired
  private ComputerPager computerPager;
  
  @RequestMapping(method = RequestMethod.GET)
  public String dashboard(ModelMap model) {
    int current = 1;
    int range = 10;
    List<ComputerDTO> list = computerPager.computerList(current, range);
    model.addAttribute("list", list);
    model.addAttribute("nbPage", computerPager.getNbPage(range));
    model.addAttribute("current", current);
    model.addAttribute("range", range);
    model.addAttribute("nbComputer", computerPager.getNbComputer());
  
    return "Dashboard";
  }

  @RequestMapping(value = "/current/{current}/range/{range}", method = RequestMethod.GET)
  public String dashboard(@PathVariable("current") int current, @PathVariable("range") int range, ModelMap model) {
    List<ComputerDTO> list = computerPager.computerList(current, range);
    model.addAttribute("list", list);
    model.addAttribute("nbPage", computerPager.getNbPage(range));
    model.addAttribute("current", current);
    model.addAttribute("range", range);
    model.addAttribute("nbComputer", computerPager.getNbComputer());
  
    return "Dashboard";
  }
}

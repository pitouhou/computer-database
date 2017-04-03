package com.computerDatabase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.computerDatabase.dto.ComputerDTO;
import com.computerDatabase.pager.ComputerPager;

@Controller
@RequestMapping("/Search")
public class SearchController {

  @Autowired
  private ComputerPager computerPager;
  
  @RequestMapping(method = RequestMethod.GET)
  public String search(ModelMap model, @RequestParam(value="search") String search) {
    if(search.isEmpty()){
      return "redirect:/";
    }else{
      List<ComputerDTO> list = computerPager.searchByName(search);
      model.addAttribute("list", list);
      model.addAttribute("nbComputer", computerPager.getNbComputer());
    }
    return "Dashboard";
  }
}

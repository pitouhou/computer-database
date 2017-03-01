package com.computerDatabase.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.computerDatabase.model.Computer;
import com.computerDatabase.pager.ComputerPager;

@WebServlet(name = "Dashboard", urlPatterns = { "/dashboard" })
public class Dashboard extends HttpServlet {
  public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    int current;
    int range;
    if(request.getParameter("current")==null){
      current = 1;
    }else{
      current = Integer.parseInt(request.getParameter("current"));
    }
    if(request.getParameter("range")==null){
      range = 10;
    }else{
      range = Integer.parseInt(request.getParameter("range"));
    }
    List<Computer> list = ComputerPager.getComputerPage(current, range);
    request.setAttribute( "list", list );
    request.setAttribute( "nbPage", ComputerPager.getNbPage(range) );
    request.setAttribute( "current", current );
    request.setAttribute( "range", range );
    this.getServletContext().getRequestDispatcher( "/WEB-INF/Dashboard.jsp" ).forward( request, response );
  
  }
}

package com.computerDatabase.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.computerDatabase.dto.CompanyDTO;
import com.computerDatabase.dto.ComputerDTO;
import com.computerDatabase.entryUtils.DateUtils;
import com.computerDatabase.exceptions.DAOException;
import com.computerDatabase.model.Computer;
import com.computerDatabase.pager.CompanyPager;
import com.computerDatabase.services.CompanyServices;
import com.computerDatabase.services.ComputerServices;
import com.computerDatabase.validation.Validation;

@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<CompanyDTO> listIn;
    try {
      listIn = CompanyPager.getCompanyPage();
    } catch (DAOException e) {
      request.setAttribute("error", e.getMessage());
      request.getServletContext().getRequestDispatcher("dashboard").forward(request, response);
    }
    request.setAttribute("list", listIn);
    this.getServletContext().getRequestDispatcher("/WEB-INF/addComputer.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    Optional<CompanyDTO> company = CompanyPager.getCompany(Integer.parseInt(request.getParameter("companyId")));
    CompanyDTO comp;
    if(company.isPresent()){
      comp = company.get();
    }else{
      comp = null;
    }
    ComputerDTO computer = new ComputerDTO.ComputerDTOBuilder(request.getParameter("computerName")).id(Long.parseLong(request.getParameter("computerId"))).introduced(request.getParameter("introduced")).discontinued(request.getParameter("discontinued")).company(comp).build();
    Computer computerUp = Validation.isComputerValid(computer).get();
    ComputerServices service = ComputerServices.getInstance();
    service.addComputer(computerUp);
    response.sendRedirect("dashboard");
  }
}

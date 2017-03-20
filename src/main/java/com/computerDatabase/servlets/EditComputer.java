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

import com.computerDatabase.cli.Controller;
import com.computerDatabase.dto.CompanyDTO;
import com.computerDatabase.dto.ComputerDTO;
import com.computerDatabase.entryUtils.DateUtils;
import com.computerDatabase.model.Computer;
import com.computerDatabase.pager.CompanyPager;
import com.computerDatabase.pager.ComputerPager;
import com.computerDatabase.services.CompanyServices;
import com.computerDatabase.services.ComputerServices;
import com.computerDatabase.validation.Validation;

@WebServlet("/editComputer")
public class EditComputer extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  List<CompanyDTO> listIn = Controller.getCompanies();
	  long id = Long.parseLong(request.getParameter("id"));
	  ComputerDTO computer = Controller.getComputer(id);
	  request.setAttribute("list", listIn);
	  request.setAttribute("computer", computer);
	  request.setAttribute("introduced", computer.getIntroduced().replaceAll("\\s", "-"));
	  request.setAttribute("discontinued", computer.getDiscontinued().replaceAll("\\s", "-"));
    this.getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp").forward(request, response);
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
    service.updateComputer(computerUp);
    response.sendRedirect("dashboard");
	}
}

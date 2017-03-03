package com.computerDatabase.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.computerDatabase.dto.CompanyDTO;
import com.computerDatabase.dto.ComputerDTO;
import com.computerDatabase.entryUtils.DateUtils;
import com.computerDatabase.model.Computer;
import com.computerDatabase.pager.CompanyPager;
import com.computerDatabase.pager.ComputerPager;
import com.computerDatabase.services.CompanyServices;
import com.computerDatabase.services.ComputerServices;

@WebServlet("/editComputer")
public class EditComputer extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  List<CompanyDTO> listIn = CompanyPager.getCompanyPage();
    request.setAttribute("list", listIn);
	  long id = Long.parseLong(request.getParameter("id"));
	  ComputerDTO computer = ComputerPager.getComputer(id);
	  request.setAttribute("computer", computer);
	  request.setAttribute("introduced", computer.getIntroduced().replaceAll("\\s", "-"));
	  request.setAttribute("discontinued", computer.getDiscontinued().replaceAll("\\s", "-"));
    this.getServletContext().getRequestDispatcher("/WEB-INF/editComputer.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  String name = request.getParameter("computerName");
    LocalDate introduced = DateUtils.convertDate(request.getParameter("introduced"));
    LocalDate discontinued = DateUtils.convertDate(request.getParameter("discontinued"));
    long companyId = Integer.parseInt(request.getParameter("companyId"));
    CompanyServices service1 = CompanyServices.getInstance();
    Computer computer = new Computer.ComputerBuilder(name).introduced(introduced).discontinued(discontinued).company(service1.getCompany(companyId).get()).build();
    ComputerServices service = ComputerServices.getInstance();
    service.updateComputer(computer);
    response.sendRedirect("dashboard");
	}
}

package com.computerDatabase.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.computerDatabase.dto.ComputerDTO;
import com.computerDatabase.pager.ComputerPager;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  if(request.getParameter("search").isEmpty()){
	    this.getServletContext().getRequestDispatcher("/WEB-INF/Dashboard.jsp").forward(request, response);
	  }else{
	    String search = request.getParameter("search");
	    int current;
	    int range;
	    if (request.getParameter("current") == null) {
	      current = 1;
	    } else {
	      current = Integer.parseInt(request.getParameter("current"));
	    }
	    if (request.getParameter("range") == null) {
	      range = 10;
	    } else {
	      range = Integer.parseInt(request.getParameter("range"));
	    }
	    List<ComputerDTO> list = ComputerPager.searchByName(search);
	    request.setAttribute("list", list);
	    request.setAttribute("nbPage", ComputerPager.getNbPage(range));
	    request.setAttribute("current", current);
	    request.setAttribute("range", range);
	    request.setAttribute("nbComputer", ComputerPager.getNbComputer());
	  }
	  this.getServletContext().getRequestDispatcher("/WEB-INF/Dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

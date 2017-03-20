package com.computerDatabase.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.computerDatabase.cli.Controller;

/**
 * Servlet implementation class Delete
 */
@WebServlet("/Delete")
public class Delete extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  response.sendRedirect("dashboard");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  System.out.println("hgfoiygulikuhg");
	  String ids = request.getParameter("selection");
	  String[] id = ids.split(",");
	  for(int i = 0; i<id.length; i++){
	    Controller.delete(Long.parseLong(id[i], 10));
	  }
	  response.sendRedirect("dashboard");
	}

}

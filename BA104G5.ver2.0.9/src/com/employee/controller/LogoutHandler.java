package com.employee.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/back/logout.do")
public class LogoutHandler extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest req,
	          HttpServletResponse res) throws ServletException, IOException {
	        req.getSession().invalidate();
	        res.sendRedirect("bklogin.jsp");
	    }

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {
	        
	    	
	    	processRequest(req, res);
	    } 

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse res)
	            throws ServletException, IOException {
	        processRequest(req, res);
	    }
}
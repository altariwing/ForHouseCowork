package com.slr.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/front/seller/logout.do")
public class Logout extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest req,
	          HttpServletResponse res) throws ServletException, IOException {
	        req.getSession().invalidate();
	        res.sendRedirect(req.getContextPath()+"/front/seller/login.jsp");
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
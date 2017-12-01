package com.realtor.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RealtorLogout extends HttpServlet {
	
	protected void processRequest(HttpServletRequest req,
	          HttpServletResponse res) throws ServletException, IOException {
	        req.getSession().invalidate();
	        res.sendRedirect("realtor_login.jsp");
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

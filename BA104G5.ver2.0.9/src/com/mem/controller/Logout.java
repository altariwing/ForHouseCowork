package com.mem.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/front/logout.do")
public class Logout extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest req,
	          HttpServletResponse res) throws ServletException, IOException {
	        
			req.getSession().invalidate();

	        String logoutSuccess = "您已成功登出!";
	        req.setAttribute("logoutSuccess", logoutSuccess);
			
			RequestDispatcher successView = req.getRequestDispatcher("/front/index.jsp"); // 新增成功後轉交
			successView.forward(req, res);
	        
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
package com.followRTR.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.followRTR.model.*;

@WebServlet("/FRServlet")
public class FRServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		String local = req.getParameter("localtion");

		if ("findByMemNo".equals(action)) {
			FRService frSvc = new FRService();
			String mem_no = req.getParameter("mem_no");
			List<FRVO> list = frSvc.findByNumber(mem_no);
			req.setAttribute("list", list);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/front/followRTR/followRTR.jsp");
			dispatcher.forward(req, res);
		}

		if ("insert".equals(action)) {
				FRService frSvc = new FRService();
			try{
				String mem_no = req.getParameter("mem_no");
				String rtr_no = req.getParameter("rtr_no");
				String state = "ON";			
				frSvc.addFollowRTR(rtr_no, mem_no, state);
			}catch(Exception e){
				throw new RuntimeException("ERROR!!!");
			}
			

		}

		if ("delete".equals(action)) {
			FRService frSvc = new FRService();
			String mem_no = req.getParameter("mem_no");
			String rtr_no = req.getParameter("rtr_no");
			frSvc.deleteFollowRTR(rtr_no, mem_no);
			List<FRVO> list = frSvc.findByNumber(mem_no);
			req.setAttribute("list", list);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/front/followRTR/followRTR.jsp");
			dispatcher.forward(req, res);

		}

	}
}

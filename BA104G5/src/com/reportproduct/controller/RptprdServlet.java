package com.reportproduct.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.reportproduct.model.*;
import com.mem.model.*;


@WebServlet("/front/shopping/RptPrd")
public class RptprdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/text;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		MemVO memVO = (MemVO)session.getAttribute("memVO");
		String url = req.getParameter("requestURL");
		String action = req.getParameter("action");
		
		RptprdVO rptprdVO = new RptprdVO();
		RptprdService rptprdSvc = new RptprdService();
		
		
		if ("addReportPrd".equals(action)) {
			String prd_no = req.getParameter("prd_no");
			String rp_reason = req.getParameter("reportReason");
			
			rptprdVO.setPrd_no(prd_no);
			rptprdVO.setRp_reason(rp_reason);
			rptprdVO.setMem_no(memVO.getMem_no());
			
			rptprdSvc.addNewReport(rptprdVO);
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			req.setAttribute("reportSuccess", "檢舉成功!");
			successView.forward(req, res);
		
		}
		
		
		if ("closeReport".equals(action)) {
			String emp_no = req.getParameter("emp_no");
			String rp_no = req.getParameter("rp_no");
			String rp_result = req.getParameter("rp_result");
			
			rptprdVO.setEmp_no(emp_no);
			rptprdVO.setRp_result(rp_result);
			rptprdVO.setRp_state("已結案");
			rptprdVO.setRp_no(rp_no);

			rptprdSvc.updateReport(rptprdVO);
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			req.setAttribute("closeSuccess", "案件處理成功!");
			successView.forward(req, res);
		}
	}
}

package com.reportrealtor.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemVO;
import com.reportrealtor.model.*;


@WebServlet("/front/realtor/RptRtr")
public class RptrtrServlet extends HttpServlet {
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
		
		RptrtrVO rptrtrVO = new RptrtrVO();
		RptrtrService rptrtrSvc = new RptrtrService();
		
		
		if ("addReportRtr".equals(action)) {
			String rtr_no = req.getParameter("rtr_no");
			String rr_reason = req.getParameter("rr_reason");
			
			rptrtrVO.setRtr_no(rtr_no);
			rptrtrVO.setRr_reason(rr_reason);
			rptrtrVO.setMem_no(memVO.getMem_no());
			
			rptrtrSvc.addNewReport(rptrtrVO);

//			RequestDispatcher successView = req.getRequestDispatcher("/front/realtor/realtor_search.jsp");
//			req.setAttribute("reportSuccess", "檢舉成功!");
//			successView.forward(req, res);
		
		}
		
		
		if ("closeReport".equals(action)) {
			String emp_no = req.getParameter("emp_no");
			String rr_no = req.getParameter("rr_no");
			String rr_result = req.getParameter("rr_result");
			
			rptrtrVO.setEmp_no(emp_no);
			rptrtrVO.setRr_result(rr_result);
			rptrtrVO.setRr_state("已結案");
			rptrtrVO.setRr_no(rr_no);

			rptrtrSvc.updateReport(rptrtrVO);
			
			RequestDispatcher successView = req.getRequestDispatcher(url);
			req.setAttribute("closeSuccess", "案件處理成功!");
			successView.forward(req, res);
		}
	}
}

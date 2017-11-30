package com.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.employee.model.EmployeeService;
import com.employee.model.EmployeeVO;



@WebServlet("/back/login.do")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		 doPost(req, res);
//	  }
	
	
	  public void doPost(HttpServletRequest req, HttpServletResponse res)
	                                throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    res.setContentType("text/html; charset=UTF-8");
	    
	    String emp_id = req.getParameter("emp_id"); 
	    String emp_psw = req.getParameter("emp_psw");
	    
	    // 【檢查該帳號 , 密碼是否有效】
	    EmployeeService empSvc = new EmployeeService();
	    EmployeeVO empVO = null;
	    String errorMsgs = "";
	   
	    try {
	    	empVO = empSvc.getOneEmpbyID(emp_id);
			if ( empVO.getEmp_state().equals("OFF") ) {
				errorMsgs = "您的帳號尚未啟用或已被鎖定";
			} else if (!emp_psw.equals(empVO.getEmp_psw())) {
					errorMsgs = "你的帳號或密碼無效!";
			}
		} catch (NullPointerException e) {
			errorMsgs = "你的帳號或密碼無效!";
		}
		
	    if (errorMsgs.length()>0) {
			req.setAttribute("errorMsgs", errorMsgs); // 將errorMsgs存入req
			RequestDispatcher failureView = req.getRequestDispatcher("bklogin.jsp");
			failureView.forward(req, res);
			return;
		}
	  //memVO.getMem_psw()
	      HttpSession session = req.getSession();
	      
	      session.setAttribute("empVO", empVO); //*工作1: 才在session內做已經登入過的標識
	      
	      
	      
	      try {                                                        
	         String location = (String) session.getAttribute("location");
	         if (location != null) {
	           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
	           res.sendRedirect(location);     
	           
	           return;
	         }
	       }catch (Exception ignored) { }
	      //System.out.print("123");
	      res.sendRedirect(req.getContextPath()+"/back/index.jsp");  //*工作3: (-->如無來源網頁:則重導至index.jsp) bklogin重導至index.jsp
	 } //doPost
}
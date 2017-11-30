package com.hsecoll.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.house.model.HouseVO;
import com.hsecoll.model.*;
import com.mem.model.*;

public class HseCollServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) { // 來自addHseColl.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/***********************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 *************************/
			String mem_no = req.getParameter("mem_no");
			if (mem_no == null || mem_no.trim().length() == 0) {
				System.out.println("mem_no會員編號: 請勿空白");
			}

			String house_no = req.getParameter("house_no");
			if (house_no == null || house_no.trim().length() == 0) {
				System.out.println("house_no 房屋編號: 請勿空白");
			}
			res.getWriter().write("成功啦");

			// Send the use back to the form, if there were errors

			/*************************** 2.開始新增資料 ***************************************/
			HseCollService hseCollSvc = new HseCollService();
			hseCollSvc.addHseColl(mem_no, house_no);
			/***************************
			 * 3.新增完成,準備轉交(Send the Success view)
			 ***********/
			RequestDispatcher failureView = req.getRequestDispatcher("/front/hescoll/hescoll.jsp");
			failureView.forward(req, res);
			/*************************** 其他可能的錯誤處理 **********************************/
		}

		if ("getHesCollByMenNo".equals(action)) {
			MemVO MemVo = (MemVO) req.getSession().getAttribute("memVO");
			String mem_no = MemVo.getMem_no();
			HseCollService hseCollSvc = new HseCollService();
			List<HouseVO> list = hseCollSvc.getAllByMemNoWithHouseInfo(mem_no);
			req.setAttribute("Collectionlist", list);

			RequestDispatcher failureView = req.getRequestDispatcher("/front/hescoll/hescoll.jsp");
			failureView.forward(req, res);
		}

		if ("makeaAppointment".equals(action)) {
			String[] house_nos = req.getParameterValues("house_nos");

			for (int i = 0; i < house_nos.length; i++) {
				System.out.println(house_nos[i]);
			}

		}

		if ("delete".equals(action)) { // 來自addHseColl.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String mem_no = req.getParameter("mem_no");
			if (mem_no == null || mem_no.trim().length() == 0) {
				System.out.println("mem_no會員編號: 請勿空白");
			}
			String house_no = req.getParameter("house_no");
			if (house_no == null || house_no.trim().length() == 0) {
				System.out.println("house_no 房屋編號: 請勿空白");
			}

			HseCollService hseCollSvc = new HseCollService();
			hseCollSvc.deletHseColl(mem_no, house_no);

			List<HouseVO> list = hseCollSvc.getAllByMemNoWithHouseInfo(mem_no);
			req.setAttribute("Collectionlist", list);
			RequestDispatcher failureView = req.getRequestDispatcher("/front/hescoll/hescoll.jsp");
			failureView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/
		}
	}
}
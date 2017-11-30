package com.realtor.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.realtor.model.RealtorService;
import com.realtor.model.RealtorVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 50 * 50 * 1024 * 1024)
public class RealtorChange extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");

		// 來自房仲註冊的請求
		if ("realtorRegister".equals(action)) {
			List<String> regErrors = new LinkedList<String>();
			req.setAttribute("regErrors", regErrors);

			RealtorService realtorSvc = new RealtorService();
			List<RealtorVO> list = realtorSvc.getIdList();

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/

				String rtr_id = req.getParameter("rtr_id");
				String rtr_psw = req.getParameter("rtr_psw");
				String rtr_psw2 = req.getParameter("rtr_psw2").trim();
				String rtr_name = req.getParameter("rtr_name");
				String re_no = req.getParameter("re_no");
				String rtr_area = req.getParameter("rtr_area");
				String rtr_intro = req.getParameter("rtr_intro");
				String rtr_idno = req.getParameter("rtr_idno");
				String default_item = req.getParameter("default_item");
				String aggrement = null;
System.out.println(default_item);
System.out.println(rtr_area);
System.out.println(re_no);
				//判斷服務地區
				if(rtr_area == null || rtr_area.trim().length()==0 || rtr_area==default_item){
					regErrors.add("請選擇服務地區!");
				}
				
				//判斷房仲公司
				if(re_no == null || re_no.trim().length()==0 || re_no==default_item){
					regErrors.add("請選擇服務公司!");
				}

				// 檢查房仲名是否為空
				if ((rtr_name.trim()).length() == 0) {
					regErrors.add("姓名不可為空!");
				}

				// 檢查帳號是否已存在
				if ((rtr_id.trim()).length() == 0) {
					regErrors.add("帳號不可為空");
				} else {
					for (RealtorVO idList : list) {
						if (rtr_id.equals((idList.getRtr_id()))) {
							regErrors.add("此帳號已存在!");
						}
					}
				}

				// 檢查密碼是否為空
				if (rtr_psw.length() == 0 || rtr_psw2.length() == 0) {
					regErrors.add("密碼不可為空!");
					return;
				}

				// 檢查兩次密碼是否相同
				if (!rtr_psw.equals(rtr_psw2)) {
					regErrors.add("兩次輸入的密碼不同!");
					return;
				}

				// 檢查簡介是否為空
				if ((rtr_intro.trim()).length() == 0) {
					regErrors.add("簡介不可為空!");
					return;
				}

				// 檢查是否同意 "服務條款 & 隱私權政策"
				try {
					(req.getParameter("aggrement")).equals("on");
					aggrement = req.getParameter("aggrement");
				} catch (NullPointerException e) {
					regErrors.add("需同意 '服務條款 & 隱私權政策' 才能註冊!");
					aggrement = "";
				}

				byte[] rtr_photo = null;
				Part photo = req.getPart("rtr_photo");
				InputStream in = photo.getInputStream();
				if ((in.available()) == 0) {
					regErrors.add("請選擇一張照片上傳");
				}
				rtr_photo = new byte[in.available()];
				in.read(rtr_photo);
				in.close();

				RealtorVO realtorVOreg = new RealtorVO();

				realtorVOreg.setRtr_id(rtr_id);
				realtorVOreg.setRtr_psw(rtr_psw);
				realtorVOreg.setRtr_name(rtr_name);
				realtorVOreg.setRtr_area(rtr_area);
				realtorVOreg.setRtr_intro(rtr_intro);
				realtorVOreg.setRtr_idno(rtr_idno);
				realtorVOreg.setRe_no(re_no);
				realtorVOreg.setAggrement(aggrement);

				// Send the use back to the form, if there were errors
				if (!regErrors.isEmpty()) {
					req.setAttribute("realtorVOreg", realtorVOreg); // 含有輸入格式錯誤的slrVOreg物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front/realtor/realtor_register.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 *****************************************/
				RealtorVO realtorVO = new RealtorVO();
				realtorVO = realtorSvc.add(rtr_id, rtr_psw, rtr_name, rtr_photo, rtr_area, rtr_intro, rtr_idno, re_no);

				/****************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				String url = "/front/realtor/realtor_success.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交success.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				// errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/realtor/realtor_register.jsp");
				failureView.forward(req, res);
			}
		} // 來自房仲註冊的請求結束

		// 得到該房重要被修改的房仲編號
		if ("getOne_For_Update".equals(action)) { // 來自realtor_center.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				System.out.println("realtor_getOne_For_Update");
				/*************************** 1.接收請求參數 ****************************************/
				String rtr_no = req.getParameter("rtr_no");

				/*************************** 2.開始查詢資料 ****************************************/
				RealtorService realtorSvc = new RealtorService();
				RealtorVO realtorVO = realtorSvc.getOne(rtr_no);
				if (realtorVO == null) {
					errorMsgs.add("查無此房仲");
					return;
				}

				/*******************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/

				HttpSession session = req.getSession();
				session.setAttribute("realtorVO", realtorVO);
				String url = "/front/realtor/realtor_data.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交給update_realtor_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/realtor/realtor_center.jsp");
				failureView.forward(req, res);
			}
		} // 得到該房重要被修改的房仲編號結束

		// 來自房仲中心修改個人資料請求
		System.out.println(action);
		if ("realtor_Change_data".equals(action)) {
			List<String> dataErrors = new LinkedList<String>();
			req.setAttribute("dataErrors", dataErrors);

			Enumeration names = req.getParameterNames();
			while (names.hasMoreElements()) {
				System.out.println(names.nextElement());
			}

			RealtorService realtorSvc = new RealtorService();
			RealtorVO realtorVOtmp = new RealtorVO();
			RealtorVO realtorVO = (RealtorVO) req.getSession().getAttribute("realtorVO");
			String nameErr = "";
			String introErr = "";

			try {

				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String rtr_no = req.getParameter("rtr_no");
				String rtr_name = req.getParameter("rtr_name").trim();
				if (rtr_name == null || rtr_name.length() == 0) {
					dataErrors.add("姓名不可為空!");
					nameErr = "姓名不可為空!";
					return;
				}
				String rtr_intro = req.getParameter("rtr_intro").trim();
				if (rtr_intro.length() == 0) {
					dataErrors.add("簡介不可為空 !");
					introErr = "簡介不可為空 !";
					return;
				}

				byte[] rtr_photo = null;
				try {
					Part photo = req.getPart("rtr_photo");

					if (!photo.getContentType().equalsIgnoreCase("application/octet-stream")) {
						InputStream in = photo.getInputStream();
						ByteArrayOutputStream buffer = new ByteArrayOutputStream();
						byte[] temp = new byte[4096];
						int read;
						try {
							while ((read = in.read(temp)) >= 0) {
								buffer.write(temp, 0, read);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						rtr_photo = buffer.toByteArray();
						// System.out.println("進來照片:" + photo);
						// InputStream in = photo.getInputStream();
						// rtr_photo = new byte[in.available()];
						// in.read(rtr_photo);
						// in.close();
					} else {
						System.out.println("沒有照片");
						rtr_photo = realtorVO.getRtr_photo();

					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				realtorVOtmp.setRtr_no(rtr_no);
				realtorVOtmp.setRtr_name(rtr_name);
				realtorVOtmp.setRtr_intro(rtr_intro);
				realtorVOtmp.setRtr_photo(rtr_photo);
				if (!dataErrors.isEmpty()) {
					req.getSession().setAttribute("realtorVO", realtorVO); // 含有輸入格式錯誤的relatorVO物件,也存入req
					req.setAttribute("realtorVOtmp", realtorVOtmp);

					req.setAttribute("nameErr", nameErr);
					req.setAttribute("introErr", introErr);

					RequestDispatcher failureView = req.getRequestDispatcher("/front/realtor/realtor_center.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 全部沒問題才開始包裝資料、更新db ****************************/
				realtorSvc.update(rtr_no, rtr_name, rtr_photo, rtr_intro, realtorVO.getRtr_psw());
				realtorVO.setRtr_no(rtr_no);
				realtorVO.setRtr_name(rtr_name);
				realtorVO.setRtr_intro(rtr_intro);
				realtorVO.setRtr_photo(rtr_photo);
				req.getSession().setAttribute("realtorVO", realtorVO);

				/****************************
				 * 更新完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/front/realtor/realtor_center.jsp";
				String dataSuccess = "更新成功";
				req.setAttribute("dataSuccess", dataSuccess);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);// 新增成功後轉交

			} catch (Exception e) {
				dataErrors.add(e.getMessage());
				req.setAttribute("dataErrors", dataErrors);
				RequestDispatcher failureView = req.getRequestDispatcher("/front/realtor/realtor_center.jsp");
				failureView.forward(req, res);
			}
		} // 來自房仲中心修改個人資料請求結束

		// 來自房仲中心的修改密碼請求
		if ("realtor_Change_Psw".equals(action)) {
			List<String> pswErrors = new LinkedList<String>();
			req.setAttribute("pswErrors", pswErrors);
			Enumeration names = req.getParameterNames();
			while (names.hasMoreElements()) {
				System.out.println("realtor_Change_Psw: " + names.nextElement());
			}

			RealtorService realtorSvc = new RealtorService();
			try {

				System.out.println("realtor_Change_Psw_try_in");
				String psw_ori = req.getParameter("psw_ori");
				String psw_new1 = req.getParameter("psw_new1");
				String psw_new2 = req.getParameter("psw_new2");

				RealtorVO realtorVO = (RealtorVO) req.getSession().getAttribute("realtorVO");

				if (psw_ori.equals(realtorVO.getRtr_psw())) {
					if (psw_new1.equals(psw_new2)) {
						realtorSvc.changePassword(psw_new1, realtorVO.getRtr_no());
						/***************************
						 * 更新完成,準備轉交(Send the Success view)
						 ***********/
						String url = "/front/realtor/realtor_center.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
						successView.forward(req, res);

					} else {
						pswErrors.add("兩次新密碼不同!");
					}
				} else {
					pswErrors.add("舊密碼輸入錯誤");
				}

				if (!pswErrors.isEmpty()) {
					req.setAttribute("pswErrors", pswErrors);
					RequestDispatcher failureView = req.getRequestDispatcher("/front/realtor/realtor_center.jsp");
					failureView.forward(req, res);
					return;
				}
			} catch (Exception e) {
				req.setAttribute("pswErrors", pswErrors);
				RequestDispatcher failureView = req.getRequestDispatcher("/front/realtor/realtor_center.jsp");
				failureView.forward(req, res);
			}

		} // 來自房仲中心的修改密碼請求結束

	}

}

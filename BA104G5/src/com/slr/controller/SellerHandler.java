package com.slr.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.slr.model.*;

@WebServlet("/front/seller/slr.do")
public class SellerHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public SellerHandler() {
		super();
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		// 來自廠商註冊的請求
		if ("slrRegister".equals(action)) {
			List<String> regErrors = new LinkedList<String>();
			req.setAttribute("regErrors", regErrors);
			
			SlrService slrSvc = new SlrService();
			List<SlrVO> list = slrSvc.getIdList();
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String slr_name = req.getParameter("slr_name").trim();
				String slr_taxid = req.getParameter("slr_taxid").trim();
				String slr_id = req.getParameter("slr_id").trim().toLowerCase();
				String slr_psw = req.getParameter("slr_psw").trim();
				String slr_psw2 = req.getParameter("slr_psw2").trim();
				String slr_contact = req.getParameter("slr_contact").trim();
				String slr_phone = req.getParameter("slr_phone").trim();
				String slr_intro = req.getParameter("slr_intro").trim();
				String aggrement = null;
				
				//檢查公司名是否為空
				if ( (slr_name.trim()).length()==0 ) {
					regErrors.add("姓名不可為空!");
				}
				
				//檢查統編是否為空
				if ( (slr_taxid.trim()).length()==0 ) {
					regErrors.add("統編不可為空!");
				}
				
				//檢查帳號是否已存在
				if((slr_id.trim()).length()==0) {
					regErrors.add("帳號不可為空");
				} else {
					for (SlrVO idList : list) {
						if( slr_id.equals((idList.getSlr_id())) ) {
							regErrors.add("此帳號已存在!");
						}
					}
				}
				
				//檢查密碼是否為空
				if (slr_psw.length()==0 || slr_psw2.length()==0) {
					regErrors.add("密碼不可為空!");
				}
				
				//檢查兩次密碼是否相同
				if ( !slr_psw.equals(slr_psw2) ) {
					regErrors.add("兩次輸入的密碼不同!");
				}
				
				//檢查聯絡人是否為空
				if ( (slr_contact.trim()).length()==0 ) {
					regErrors.add("聯絡人不可為空!");
				}
				
				//檢查連絡電話是否為空
				if ( (slr_phone.trim()).length()==0 ) {
					regErrors.add("統編不可為空!");
				}
				
				//檢查簡介是否為空
				if ( (slr_intro.trim()).length()==0 ) {
					regErrors.add("簡介不可為空!");
				}
				
				// 檢查是否同意 "服務條款 & 隱私權政策"
				try {
					(req.getParameter("aggrement")).equals("on");
					aggrement = req.getParameter("aggrement");
				} catch (NullPointerException e) {
					regErrors.add("需同意 '服務條款 & 隱私權政策' 才能註冊!");
					aggrement = "";
				}
				
				SlrVO slrVOreg = new SlrVO();
				slrVOreg.setSlr_name(slr_name);
				slrVOreg.setSlr_taxid(slr_taxid);
				slrVOreg.setSlr_id(slr_id);
				slrVOreg.setSlr_psw(slr_psw);
				slrVOreg.setSlr_contact(slr_contact);
				slrVOreg.setSlr_phone(slr_phone);
				slrVOreg.setSlr_intro(slr_intro);
				slrVOreg.setAggrement(aggrement);
				
				if (!regErrors.isEmpty()) {
					req.setAttribute("slrVOreg", slrVOreg); // 含有輸入格式錯誤的slrVOreg物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front/seller/register.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************開始新增資料***************************************/
				SlrVO slrVO = new SlrVO();
				slrVO.setSlr_name(slr_name);
				slrVO.setSlr_taxid(slr_taxid);
				slrVO.setSlr_id(slr_id);
				slrVO.setSlr_psw(slr_psw);
				slrVO.setSlr_contact(slr_contact);
				slrVO.setSlr_phone(slr_phone);
				slrVO.setSlr_intro(slr_intro);
				slrSvc.addSlr(slr_name, slr_taxid, slr_id, slr_psw, slr_contact, slr_phone, slr_intro);
				
				/***************************新增完成,準備轉交(Send the Success view)***********/
				String url = "/front/seller/success.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交success.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
				} catch (Exception e) {
					e.printStackTrace();
					regErrors.add(e.getMessage());
					RequestDispatcher failureView = req.getRequestDispatcher("/front/seller/register.jsp");
					failureView.forward(req, res);
				} 
		}//來自廠商註冊的請求[結束]
		
		
		
		//來自廠商登入的請求
		if ("slrLogin".equals(action)) {
			String loginError = "";
			req.setAttribute("loginError", loginError);
			
			String slr_id = req.getParameter("slr_id").trim().toLowerCase();
			String slr_psw = req.getParameter("slr_psw").trim();
			
			SlrService slrSvc = new SlrService();
			SlrVO slrVO = null;
			
			try {
				slrVO = slrSvc.findById(slr_id);
				if ( slrVO.getSlr_state().equals("OFF") ) {
					loginError = "您的帳號尚未啟用或已被鎖定";
				} else if (!slr_psw.equals(slrVO.getSlr_psw())) {
					loginError = "你的帳號或密碼無效!";
				}
			} catch (NullPointerException e) {
				loginError = "你的帳號或密碼無效!";
			} catch (Exception e) { e.printStackTrace(); }
			
			if (loginError.length()>0) {
				req.setAttribute("loginError", loginError);
				RequestDispatcher failureView = req.getRequestDispatcher("/front/seller/login.jsp");
				failureView.forward(req, res);
				return;
			}
			HttpSession session = req.getSession(); //檢查到這表示帳號密碼沒問題
			session.setAttribute("slrVO", slrVO);   //在session內做已經登入過的標識
			try {                                                        
		         String location = (String) session.getAttribute("location");
		         if (location != null) {
		           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
		           res.sendRedirect(location);            
		           return;
		         }
		       }catch (Exception ignored) { }
			res.sendRedirect(req.getContextPath()+"/front/seller/profile/slrindex.jsp");  //重導至會員中心
		}// 來自廠商登入的請求[結束]
		
		
		
		// 來自廠商修改資料的請求
		if ("modifyData".equals(action)) {
			List<String> dataErrors = new LinkedList<String>();
			req.setAttribute("dataErrors", dataErrors);
			
			SlrService slrSvc = new SlrService();
			SlrVO slrVOtmp = new SlrVO();
			SlrVO slrVO = (SlrVO) req.getSession().getAttribute("slrVO");
			
			String contactErr = "";
			String phoneErr = "";
			String introErr = "";
			
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String slr_contact = req.getParameter("slr_contact").trim();
				String slr_phone = req.getParameter("slr_phone").trim();
				String slr_intro = req.getParameter("slr_intro").trim();
				
				//檢查聯絡人是否為空
				if ( slr_contact.length()==0 ) {
					dataErrors.add("聯絡人不可為空 !");
					contactErr = "聯絡人不可為空 !";
				}
				
				//檢查連絡電話是否為空
				if ( slr_phone.length()==0 ) {
					dataErrors.add("連絡電話不可為空 !");
					phoneErr = "連絡電話不可為空 !";
				}
				
				//檢查簡介是否為空
				if ( slr_intro.length()==0 ) {
					dataErrors.add("簡介不可為空 !");
					introErr = "簡介不可為空 !";
				}
				
				slrVOtmp.setSlr_contact(slr_contact);
				slrVOtmp.setSlr_phone(slr_phone);
				slrVOtmp.setSlr_intro(slr_intro);
				
				if (!dataErrors.isEmpty()) {
					req.setAttribute("dataErrors", dataErrors);
					req.setAttribute("slrVOtmp", slrVOtmp); // 含有輸入格式錯誤的slrVO物件,也存入req
					
					req.setAttribute("contactErr", contactErr);
					req.setAttribute("phoneErr", phoneErr);
					req.setAttribute("introErr", introErr);
					
					RequestDispatcher failureView = req.getRequestDispatcher("/front/seller/profile/slrdata.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 全部沒問題才開始包裝資料、更新db ***********/
				slrSvc.updateSlr(slr_contact, slr_phone, slr_intro, slrVO.getSlr_no());
				slrVO.setSlr_contact(slr_contact);
				slrVO.setSlr_phone(slr_phone);
				slrVO.setSlr_intro(slr_intro);
				req.getSession().setAttribute("slrVO", slrVO);
				
				/*************************** 更新完成,準備轉交(Send the Success view) ***********/
				String url = "/front/seller/profile/slrdata.jsp";
				String dataSuccess = "更新成功!";
				req.setAttribute("dataSuccess", dataSuccess);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
				successView.forward(req, res);	
				
				
			} catch (Exception e) {
				dataErrors.add(e.getMessage());
				req.setAttribute("dataErrors", dataErrors);
				RequestDispatcher failureView = req.getRequestDispatcher("/front/seller/profile/slrdata.jsp");
				failureView.forward(req, res);
			} 
			
		}// 來自廠商修改的請求[結束]
		
		
		
		// 來自廠商更改密碼的請求
		if ("changePsw".equals(action)) {
			String pswError = "";
			
			SlrService slrSvc = new SlrService();
			
			try {
				String psw_ori = req.getParameter("psw_ori");
				String psw_new1 = req.getParameter("psw_new1");
				String psw_new2 = req.getParameter("psw_new2");
				
				SlrVO slrVO = (SlrVO) req.getSession().getAttribute("slrVO");
				
				if (psw_ori.equals(slrVO.getSlr_psw())) {
					if (psw_new1.equals(psw_new2)) {
						
						/********* 檢查現在的密碼符合，以及兩次新密碼match之後開始更新 ********/
						slrSvc.changePassword(psw_new1, slrVO.getSlr_no());
						
						/*************************** 更新完成,準備轉交(Send the Success view) ***********/
						String pswSuccess = "密碼更改成功 !";
						req.setAttribute("pswSuccess", pswSuccess);
						String url = "/front/seller/profile/slrpsw.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
						successView.forward(req, res);
						
					} else {
						pswError = "兩次的新密碼比對不同 !";
					}
				} else {
					pswError = "現在的密碼輸入錯誤 !";
				}
				
				if (pswError.length()>0) {
					req.setAttribute("pswError", pswError);
					RequestDispatcher failureView = req.getRequestDispatcher("/front/seller/profile/slrpsw.jsp");
					failureView.forward(req, res);
					return;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/front/seller/profile/slrpsw.jsp");
				failureView.forward(req, res);
			}
		}
		
	}
}

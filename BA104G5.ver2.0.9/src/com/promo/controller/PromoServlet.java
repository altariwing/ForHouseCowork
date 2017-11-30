package com.promo.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.coupon.model.CouponVO;
import com.news.model.NewsService;
import com.news.model.NewsVO;
import com.promo.model.PromoService;
import com.promo.model.PromoVO;

@SuppressWarnings("serial")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 50 * 50 * 1024 * 1024)
public class PromoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 查單一
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");// 送出修改的來源網頁路徑
			System.out.println("promo_getOne_For_Update: " + requestURL);
			String whichPage = req.getParameter("whichPage");

			try {
				System.out.println("promo_getOne_For_Display_try_in");
				/*****************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String promo_no = req.getParameter("promo_no");
				if (promo_no == null || (promo_no.trim()).length() == 0) {// 來自listAllPromo.jsp的請求
					errorMsgs.add("請輸入促銷編號編號");
				}
				// Send the use back to the form, if there were errors
				// send the ErrorPage view.
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/promo/listAllPromo.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				PromoService promoSvc = new PromoService();
				PromoVO promoVO = promoSvc.getOne(promo_no);
				if (promoVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/promo/listAllPromo.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/******************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				if (requestURL.equals("/back/promo/listAllPromo.jsp")) {
					req.setAttribute("promoVO", promoVO); // 資料庫取出的promoVO物件,存入req
					String url = requestURL;
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/promo/listAllPromo.jsp");
				failureView.forward(req, res);
			}
		} // 查單一結束

		// 查單一修改結束
		if ("getOne_For_Update".equals(action)) { // 來自listAllPromo.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");// 送出修改的來源網頁路徑
			System.out.println("promo_getOne_For_Update: " + requestURL);

			try {
				System.out.println("promo_getOne_For_Update_try_in");
				/*************************** 1.接收請求參數 ****************************************/
				String promo_no = req.getParameter("promo_no");

				/*************************** 2.開始查詢資料 ****************************************/
				PromoService promoSvc = new PromoService();
				PromoVO promoVO = promoSvc.getOne(promo_no);

				/*****************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("promoVO", promoVO);
				String url = "/back/promo/update_promo_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交update_promoVO_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		} // 查單一修改結束

		// 更新促銷資訊
		if ("update".equals(action)) { // 來自update_promo_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");// 送出修改的來源網頁路徑
			System.out.println("promo_update: " + requestURL);

			Enumeration<String> allname = req.getParameterNames();
			while (allname.hasMoreElements()) {
				System.out.println("promo_全部的值: " + allname.nextElement());
			}

			try {

				/*****************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *****************/
				String promo_no = req.getParameter("promo_no");
				String promo_name = req.getParameter("promo_name");
				String promo_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,100}$";
				if (promo_name == null || promo_name.trim().length() == 0) {
					errorMsgs.add("促銷標題: 請勿空白");
				} else if (!promo_name.trim().matches(promo_titleReg)) {// 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("促銷標題: 只能是中、英文字母、數字");
				}

				String promo_content = req.getParameter("promo_content").trim();
				if (promo_content == null || promo_content.trim().length() == 0) {
					errorMsgs.add("促銷內容請勿空白");
				}

				String promo_state = req.getParameter("promo_state").trim();
				String default_item2 = req.getParameter("default_item"); // 判斷促銷狀態的選擇
				if (promo_state == null || promo_state.trim().length() == 0 || promo_state == default_item2) {
					errorMsgs.add("請選擇促銷狀態");
				}

				String emp_no = req.getParameter("emp_no");

				PromoVO promoVO = new PromoVO();

				byte[] promo_photo = null;
				try {
					Part photo = req.getPart("promo_photo");
					if (!photo.getContentType().equalsIgnoreCase("application/octet-stream")) {
						InputStream in = photo.getInputStream();
						promo_photo = new byte[in.available()];
						in.read(promo_photo);
						in.close();
					} else {
						promo_photo = promoVO.getPromo_photo();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				java.sql.Date promo_from = null;
				java.sql.Date promo_to = null;
				try {
					promo_from = java.sql.Date.valueOf(req.getParameter("promo_from").trim());
					promo_to = java.sql.Date.valueOf(req.getParameter("promo_to").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請選擇日期");
				}

				promoVO.setPromo_from(promo_from);
				promoVO.setPromo_to(promo_to);
				promoVO.setPromo_name(promo_name);
				promoVO.setPromo_content(promo_content);
				promoVO.setPromo_photo(promo_photo);
				promoVO.setPromo_state(promo_state);
				promoVO.setPromo_no(promo_no);
				promoVO.setEmp_no(emp_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("promoVO", promoVO); // 含有輸入格式錯誤的promoVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back/promo/listAllPromo.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				PromoService promoSvc = new PromoService();
				promoVO = promoSvc.update(promo_from, promo_to, promo_name, promo_content, promo_photo, promo_state,
						promo_no, emp_no);

				/*******************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				if (requestURL.equals("/back/promo/listAllPromo.jsp")) {
					String url = requestURL;
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				// errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/promo/listAllPromo.jsp");
				failureView.forward(req, res);
			}
		} // 更新促銷結束

		// 新增促銷
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				/********************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *****************/
				String promo_name = req.getParameter("promo_name");
				if (promo_name == null || promo_name.trim().length() == 0) {
					errorMsgs.add("促銷標題: 請勿空白");
				}

				String promo_content = req.getParameter("promo_content").trim();
				if (promo_content == null || promo_content.trim().length() == 0) {
					errorMsgs.add("促銷內容請勿空白");
				}

				String promo_state = req.getParameter("promo_state").trim();
				String default_item2 = req.getParameter("default_item"); // 判斷促銷狀態的選擇
				if (promo_state == null || promo_state.trim().length() == 0 || promo_state == default_item2) {
					errorMsgs.add("請選擇促銷狀態");
				}

				byte[] promo_photo = null;
				Part photo = req.getPart("promo_photo");
				InputStream in = photo.getInputStream();
				promo_photo = new byte[in.available()];
				in.read(promo_photo);
				in.close();

				java.sql.Date promo_from = null;
				java.sql.Date promo_to = null;
				try {
					promo_from = java.sql.Date.valueOf(req.getParameter("promo_from").trim());
					promo_to = java.sql.Date.valueOf(req.getParameter("promo_to").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請選擇日期");
				}

				String emp_no = req.getParameter("emp_no");

				PromoVO promoVO = new PromoVO();
				promoVO.setPromo_from(promo_from);
				promoVO.setPromo_to(promo_to);
				promoVO.setPromo_name(promo_name);
				promoVO.setPromo_content(promo_content);
				promoVO.setPromo_photo(promo_photo);
				promoVO.setPromo_state(promo_state);
				promoVO.setEmp_no(emp_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("promoVO", promoVO); // 含有輸入格式錯誤的promoVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back/promo/listAllPromo.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				PromoService promoSvc = new PromoService();
				promoVO = promoSvc.add(promo_from, promo_to, promo_name, promo_content, promo_photo, promo_state,
						emp_no);

				/******************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("promoVO", promoVO);
				String url = "/back/promo/listAllPromo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後,轉交listAllPromo.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/news/listAllPromo.jsp");
				failureView.forward(req, res);
			}
		} // 新增促銷結束

		// 查詢促銷資訊對應的優惠卷
		if ("listCP".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Enumeration<String> allname = req.getParameterNames();
				while (allname.hasMoreElements()) {
					System.out.println("listCP_全部的值: " + allname.nextElement());
				}
				/*************************** 1.接收請求參數 ****************************************/
				String promo_no = req.getParameter("promo_no");

				/*************************** 2.開始查詢資料 ****************************************/
				PromoService promoSvc = new PromoService();
				Set<CouponVO> set = promoSvc.getCPsByPromono(promo_no);
				/****************************** 3.查詢完成,準備轉交(Send the Success view)***********/
				req.setAttribute("listCP", set);

				String url = "/back/coupon/listAllCoupon";

				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		// 查詢促銷資訊對應的優惠卷結束
	}
}

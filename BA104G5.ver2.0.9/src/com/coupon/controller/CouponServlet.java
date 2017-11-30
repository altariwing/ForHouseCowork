package com.coupon.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coupon.model.CouponService;
import com.coupon.model.CouponVO;

@SuppressWarnings("serial")
public class CouponServlet extends HttpServlet {
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
			
			String whichPage = req.getParameter("whichPage");

			try {

				Enumeration<String> allname = req.getParameterNames();
				while (allname.hasMoreElements()) {
					System.out.println("coupon_全部的值: " + allname.nextElement());
				}
				
				System.out.println(whichPage);
				/*****************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String cp_no = req.getParameter("cp_no");
			
				// Send the use back to the form, if there were errors
				// send the ErrorPage view.
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/coupon/listAllCoupon.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				CouponService couponSvc = new CouponService();
				CouponVO couponVO = couponSvc.getOne(cp_no);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/coupon/listAllCoupon.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/******************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/

				req.setAttribute("couponVO", couponVO);
				req.setAttribute("cp_no", cp_no);
				String url = "/back/coupon/listAllCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/coupon/listAllCoupon.jsp");
				failureView.forward(req, res);
			}
		} // 查單一結束

		// 查單一修改結束
		if ("getOne_For_Update".equals(action)) { // 來自listAllCoupon.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");// 送出修改的來源網頁路徑

			try {

				Enumeration<String> allname = req.getParameterNames();
				while (allname.hasMoreElements()) {
					System.out.println("getOneForUpdate_coupon_全部的值: " + allname.nextElement());
				}

				/*************************** 1.接收請求參數 ****************************************/
				String cp_no = req.getParameter("cp_no");

				/*************************** 2.開始查詢資料 ****************************************/
				CouponService couponSvc = new CouponService();
				CouponVO couponVO = couponSvc.getOne(cp_no);

				/*****************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("couponVO", couponVO);
				String url = "/back/coupon/update_coupon_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/coupon/update_coupon_input.jsp");
				failureView.forward(req, res);
			}
		} // 查單一修改結束

		// 更新優惠卷資訊
		if ("update".equals(action)) { // 來自update_coupon_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");// 送出修改的來源網頁路徑

			try {

				Enumeration<String> allname = req.getParameterNames();
				while (allname.hasMoreElements()) {
					System.out.println("coupon_全部的值: " + allname.nextElement());
				}
				/*****************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *****************/
				java.sql.Date cp_from = null;
				java.sql.Date cp_to = null;
				try {
					cp_from = java.sql.Date.valueOf(req.getParameter("cp_from").trim());
					cp_to = java.sql.Date.valueOf(req.getParameter("cp_to").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請選擇日期");
				}

				String cp_content = req.getParameter("cp_content");
				String cp_discount = req.getParameter("cp_discount");
				String pdo_no = req.getParameter("pdo_no");
				String cp_state = req.getParameter("cp_state").trim();
				String default_item2 = req.getParameter("default_item"); // 判斷優惠卷狀態的選擇
				if (cp_state == null || cp_state.trim().length() == 0 || cp_state == default_item2) {
					errorMsgs.add("請選擇優惠卷狀態");
				}

				String mem_no = req.getParameter("mem_no");
				String promo_no = req.getParameter("promo_no");
				String cp_no = req.getParameter("cp_no");

				CouponVO couponVO = new CouponVO();
				couponVO.setCp_from(cp_from);
				couponVO.setCp_to(cp_to);
				couponVO.setCp_content(cp_content);
				couponVO.setCp_discount(cp_discount);
				couponVO.setPdo_no(pdo_no);
				couponVO.setCp_state(cp_state);
				couponVO.setMem_no(mem_no);
				couponVO.setPromo_no(promo_no);
				couponVO.setCp_no(cp_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("couponVO", couponVO); // 含有輸入格式錯誤的promoVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back/coupon/listAllCoupon.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				CouponService couponSvc = new CouponService();
				couponVO = couponSvc.update(cp_from, cp_to, cp_content, cp_discount, pdo_no, cp_state, mem_no, promo_no,
						cp_no);

				/*******************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				if (requestURL.equals("/back/coupon/listAllCoupon.jsp")) {
					String url = requestURL;
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
				}
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				// errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/coupon/listAllCoupon.jsp");
				failureView.forward(req, res);
			}
		} // 更新促銷結束

		// 新增優惠卷
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {

				Enumeration<String> allname = req.getParameterNames();
				while (allname.hasMoreElements()) {
					System.out.println("coupon_全部的值: " + allname.nextElement());
				}
				/************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 ************************/

				java.sql.Date cp_from = null;
				java.sql.Date cp_to = null;
				try {
					cp_from = java.sql.Date.valueOf(req.getParameter("cp_from").trim());
					cp_to = java.sql.Date.valueOf(req.getParameter("cp_to").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請選擇日期");
				}

				String cp_content = req.getParameter("cp_content");
				String cp_discount = req.getParameter("cp_discount");
				String pdo_no = req.getParameter("pdo_no");
				String cp_state = req.getParameter("cp_state").trim();
				String default_item2 = req.getParameter("default_item"); // 判斷優惠卷狀態的選擇
				if (cp_state == null || cp_state.trim().length() == 0 || cp_state == default_item2) {
					errorMsgs.add("請選擇優惠卷狀態");
				}

				String mem_no = req.getParameter("mem_no");
				String promo_no = req.getParameter("promo_no");

				CouponVO couponVO = new CouponVO();
				couponVO.setCp_from(cp_from);
				couponVO.setCp_to(cp_to);
				couponVO.setCp_content(cp_content);
				couponVO.setCp_discount(cp_discount);
				couponVO.setPdo_no(pdo_no);
				couponVO.setCp_state(cp_state);
				couponVO.setMem_no(mem_no);
				couponVO.setPromo_no(promo_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("couponVO", couponVO); // 含有輸入格式錯誤的couponVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back/coupon/listAllCoupon.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				CouponService couponSvc = new CouponService();
				couponVO = couponSvc.add(cp_from, cp_to, cp_content, cp_discount, pdo_no, cp_state, mem_no, promo_no);

				/****************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("couponVO", couponVO);
				String url = "/back/coupon/listAllCoupon.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後,轉交listAllNews.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/coupon/listAllCoupon.jsp");
				failureView.forward(req, res);
			}
		} // 新增優惠卷結束

	}
}

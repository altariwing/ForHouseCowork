package com.coupon.controller;

import java.io.IOException;
import java.sql.Date;
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
import com.promo.model.PromoService;
import com.promo.model.PromoVO;
import com.mem.model.*;

@SuppressWarnings("serial")
public class CouponServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
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

			try {

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
				return;

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/coupon/update_coupon_input.jsp");
				failureView.forward(req, res);
				return;
			}
		} // 查單一修改結束

		// 更新優惠卷資訊
		if ("update".equals(action)) { // 來自update_coupon_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			// Enumeration<String> allname = req.getParameterNames();
			// while (allname.hasMoreElements()) {
			// System.out.println("coupon_全部的值: " + allname.nextElement());
			// }
			/************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 ************************/

			java.sql.Date cp_from = null;
			java.sql.Date cp_to = null;

			cp_from = java.sql.Date.valueOf(req.getParameter("cp_from").trim());
			cp_to = java.sql.Date.valueOf(req.getParameter("cp_to").trim());

			String cp_no = req.getParameter("cp_no");
			String cp_content = req.getParameter("cp_content");

			String discount_type = req.getParameter("discount_type");

			String cp_discountCondition = req.getParameter("cp_discountCondition");
			String cp_discountPrice = req.getParameter("cp_discountPrice");

			if ("priceDiscount".equals(discount_type)) {
				if (Integer.valueOf(cp_discountCondition) != 0
						&& Integer.valueOf(cp_discountCondition) < Integer.valueOf(cp_discountPrice)) {
					errorMsgs.add("折扣金額比金額還高了");

				}
			} else if ("percentDiscount".equals(discount_type) && Integer.valueOf(cp_discountPrice) > 100) {
				errorMsgs.add("折％數沒關係，但要倒貼錢就不行");

			}

			String cp_discount = discount_type + ":" + cp_discountCondition + ":" + cp_discountPrice;

			String pdo_no = "";
			if (req.getParameter("pdo_no") != null) {
				pdo_no = req.getParameter("pdo_no");
			}
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
			couponVO = couponSvc.update(cp_from, cp_to, cp_content, cp_discount, pdo_no, cp_state, mem_no, promo_no,
					cp_no);

			List<CouponVO> couponList = couponSvc.getAll();
			/****************************
			 * 3.修改完成,準備轉交(Send the Success view)
			 *************/
			req.setAttribute("couponList", couponList);
			String url = "/back/coupon/listAllCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後,轉交listAllNews.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/

		} // 更新促銷結束

		// 新增優惠卷
		if ("insert".equals(action) || "slotMachine".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			// try {
			// Enumeration<String> allname = req.getParameterNames();
			// while (allname.hasMoreElements()) {
			// System.out.println("coupon_全部的值: " + allname.nextElement());
			// }
			/************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 ************************/
			String discount_type = req.getParameter("discount_type");
			if(discount_type.equals("none")){
				res.getWriter().println("別灰心，明天再挑戰！");
				return;
			}

			String promo_no = req.getParameter("promo_no");
			java.sql.Date cp_from = null;
			java.sql.Date cp_to = null;
			String cp_content = null;
			String cp_discountCondition = null;
			String cp_discountPrice = null;

			PromoService promoSvc = new PromoService();
			CouponService couponSvc = new CouponService();
			PromoVO tempPVO = promoSvc.getOne(promo_no);
			

			if (action.equals("slotMachine")) {
				long currentTime = new java.util.Date().getTime();
				cp_from = new java.sql.Date(currentTime);
				cp_to = new java.sql.Date(currentTime + 604800000L);
				cp_content = tempPVO.getPromo_content();
			} else {
				cp_from = java.sql.Date.valueOf(req.getParameter("cp_from").trim());
				cp_to = java.sql.Date.valueOf(req.getParameter("cp_to").trim());
				cp_content = req.getParameter("cp_content");
			}

		
			cp_discountCondition = req.getParameter("cp_discountCondition");
			cp_discountPrice = req.getParameter("cp_discountPrice");

			if ("priceDiscount".equals(discount_type)) {
				if (Integer.valueOf(cp_discountCondition) != 0
						&& Integer.valueOf(cp_discountCondition) < Integer.valueOf(cp_discountPrice)) {
					errorMsgs.add("折扣金額比金額還高了");

				}
			} else if ("percentDiscount".equals(discount_type) && Integer.valueOf(cp_discountPrice) > 100) {
				errorMsgs.add("折％數沒關係，但要倒貼錢就不行");
			}

			String cp_discount = discount_type + ":" + cp_discountCondition + ":" + cp_discountPrice;

			String pdo_no = "";
			if (req.getParameter("pdo_no") != null) {
				pdo_no = req.getParameter("pdo_no");
			}

			String cp_state = "notUse";
			if (req.getParameter("cp_state") != null) {
				req.getParameter("cp_state").trim();
			}
			String default_item2 = req.getParameter("default_item"); // 判斷優惠卷狀態的選擇
			if (cp_state == null || cp_state.trim().length() == 0 || cp_state == default_item2) {
				errorMsgs.add("請選擇優惠卷狀態");
			}

			String mem_no = null;
			if (req.getParameter("mem_no") != null) {
				mem_no = req.getParameter("mem_no");
			}else{
				mem_no = ((MemVO)req.getSession().getAttribute("memVO")).getMem_no();
			}
			
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
			if (errorMsgs.isEmpty()) {
				Integer couponQuanityBefore = tempPVO.getCoupon_quanity();
				synchronized (this) {
					if (couponQuanityBefore > 0) {
						Integer couponQuanityAfter = couponQuanityBefore - 1;
						promoSvc.updateCoupon_quanity(couponQuanityAfter, promo_no);
						Integer mem_game = Integer.valueOf(req.getParameter("mem_game"));
						
						Integer mem_gameAfter = mem_game -1 ;
						
						MemService memSvc = new MemService();
						
						memSvc.SetGameCount(mem_gameAfter, mem_no);	
						
						couponVO = couponSvc.add(cp_from, cp_to, cp_content, cp_discount, pdo_no, cp_state, mem_no,	promo_no);
						MemVO memVO_onSession =(MemVO)req.getSession().getAttribute("memVO");
						memVO_onSession.setMem_game(mem_gameAfter);
						res.getWriter().println("恭喜中獎！！");
					} else {
						res.getWriter().println("折價券已搶購一空,下次請早!");
						errorMsgs.add("折價券已搶購一空,下次請早!");
					}
				}
			}
			
			if (action.equals("slotMachine")) {
				return;				
			}

			/****************************
			 * 3.修改完成,準備轉交(Send the Success view)
			 *************/
			req.setAttribute("couponVO", couponVO);
			String url = "/back/coupon/listAllCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後,轉交listAllNews.jsp
			successView.forward(req, res);
			return;

			/*************************** 其他可能的錯誤處理 **********************************/
			// } catch (Exception e) {
			// errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
			// RequestDispatcher failureView =
			// req.getRequestDispatcher("/back/coupon/listAllCoupon.jsp");
			// failureView.forward(req, res);
			// return;
			// }
		} // 新增優惠卷結束

		if ("insertaCouponFromPromo".equals(action)) {
			System.out.println("進入了insertaCouponFromPromo");
			PromoService promoSvc = new PromoService();
			String promo_no = req.getParameter("promo_no");
			PromoVO pVO = promoSvc.getOne(promo_no);
			Integer coupon_quanity = pVO.getCoupon_quanity();
			String cp_content = new String(req.getParameter("promo_content").getBytes("ISO-8859-1"), "UTF-8");
			Date cp_from = Date.valueOf(req.getParameter("cp_from"));
			Date cp_to = Date.valueOf(req.getParameter("cp_to"));
			CouponVO couponVO = new CouponVO();
			couponVO.setPromo_no(promo_no);
			couponVO.setCp_content(cp_content);
			couponVO.setCp_from(cp_from);
			couponVO.setCp_to(cp_to);
			req.setAttribute("couponVO", couponVO);
			req.setAttribute("coupon_quanity", coupon_quanity);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/back/coupon/addCoupon.jsp");
			dispatcher.forward(req, res);
		}
	}
}

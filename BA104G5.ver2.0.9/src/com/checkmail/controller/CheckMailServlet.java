package com.checkmail.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.realtor.model.RealtorService;
import com.slr.model.SlrService;
import com.tool.controller.RtrMailOn;
import com.tool.controller.SlrMailOn;

@SuppressWarnings("serial")
public class CheckMailServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 更改房仲狀態ON
		if ("onRtr".equals(action)) {// 後端更改房仲狀態
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {

				/*********************** 接收請求參數 *************************/
				String rtr_no = req.getParameter("rtr_no");
				String rtr_state = "ON";
				String rtr_name = req.getParameter("rtr_name");

				// String rtr_id = req.getParameter("rtr_id");
				/*********************** 開始update資料 **********************/

				RealtorService realtorSvc = new RealtorService();
				realtorSvc.changeRealtorState(rtr_state, rtr_no);

				// 寄信通知
				String rtr_id = "eatkaikai@gmail.com";
				String msg = "非常感謝你加入本網站[ 房仲 ]會員，已經為你開啟使用本網站服務的權利了，請一定Ipad溫開水!" + "\r\n" + "請不用懷疑，馬上點開下面網址: "
						+ "http://localhost:8081/BA104G5/front/realtor/realtor_login.jsp";
				RtrMailOn rtrMail = new RtrMailOn();
				rtrMail.sendMail(rtr_name, rtr_id, msg);

				/*********************** 新增完成,準備轉交 ********************/

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*********************** 錯誤處理 *****************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		} // end

		// 更改房仲狀態OFF
		if ("offRtr".equals(action)) {// 後端更改房仲狀態
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
				/*********************** 接收請求參數 *************************/
				String rtr_no = req.getParameter("rtr_no");
				String rtr_state = "OFF";
				/*********************** 開始update資料 **********************/

				RealtorService realtorSvc = new RealtorService();
				realtorSvc.changeRealtorState(rtr_state, rtr_no);
				/*********************** 新增完成,準備轉交 ********************/

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*********************** 錯誤處理 ****************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		} // end

		// 更改廠商狀態ON
		if ("onSlr".equals(action)) {// 後端更改廠商狀態
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {

				/*********************** 接收請求參數 *************************/
				String slr_no = req.getParameter("slr_no");
				String slr_state = "ON";
				String slr_name = req.getParameter("slr_name");

				
				/*********************** 開始update資料 **********************/
				SlrService slrSvc = new SlrService();
				slrSvc.changeSlrState(slr_state, slr_no);

				// 寄信通知
				String slr_id = "eatkaikai@gmail.com";
				String msg = "非常感謝你加入本網站[ 廠商 ]會員，已經為你開啟使用本網站服務的權利了，請一定Ipad溫開水!" + "\r\n" + "請不用懷疑，馬上點開下面網址: "
						+ "http://localhost:8081/BA104G5/front/realtor/realtor_login.jsp";//記得換成廠商的登入畫面
				SlrMailOn slrMail = new SlrMailOn();
				slrMail.sendMail(slr_name, slr_id, msg);

				/*********************** 新增完成,準備轉交 ********************/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*********************** 錯誤處理 *****************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		} // end

		// 更改廠商狀態OFF
		if ("offSlr".equals(action)) {// 後端更改廠商狀態
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL");

			try {
				/*********************** 接收請求參數 *************************/
				String slr_no = req.getParameter("slr_no");
				String slr_state = "OFF";
				/*********************** 開始update資料 **********************/

				SlrService slrSvc = new SlrService();
				slrSvc.changeSlrState(slr_state, slr_no);

				/*********************** 新增完成,準備轉交 ********************/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*********************** 錯誤處理 *****************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		} // end
	}
}

package com.ann.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ann.model.AnnService;
import com.ann.model.AnnVO;

@SuppressWarnings("serial")
public class AnnServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");// 送出查詢的來源網頁路徑

			System.out.println(requestURL);

			try {
				/*****************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String str = req.getParameter("ann_no");
				if (str == null || (str.trim()).length() == 0) {// 來自listAllAnn.jsp的請求
					errorMsgs.add("請輸入公告編號");
				}
				// Send the use back to the form, if there were errors
				// send the ErrorPage view.
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/ann/listAllAnn.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer ann_no = null;
				try {
					ann_no = new Integer(str);

				} catch (Exception e) {
					errorMsgs.add("公告編號不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/ann/listAllAnn.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				AnnService annSvc = new AnnService();
				AnnVO annVO = annSvc.getOne(ann_no);
				if (annVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/ann/listAllAnn.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*****************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/

				req.setAttribute("annVO", annVO);

				String url = "/back/ann/listOneAnn.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/ann/listAllAnn.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllAnn.jsp的請求
			System.out.println("ann_getOne_For_Update");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				System.out.println("getOne_For_Update_try_in");
				/*************************** 1.接收請求參數 ****************************************/
				Integer ann_no = new Integer(req.getParameter("ann_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				AnnService annSvc = new AnnService();
				AnnVO annVO = annSvc.getOne(ann_no);

				System.out.println(ann_no);
				/*****************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("annVO", annVO);
				String url = "/back/ann/update_ann_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交update_ann_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/ann/listAllAnn.jsp");
				failureView.forward(req, res);
			}

		}

		if ("update".equals(action)) { // 來自update_ann_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();

			Enumeration<String> allname = req.getParameterNames();
			while (allname.hasMoreElements()) {
				System.out.println("ann_全部的值: " + allname.nextElement());
			}

			try {

				System.out.println("ann_update_try_in");
				/*****************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *****************/
				Integer ann_no = new Integer(req.getParameter("ann_no").trim());

				String ann_title = req.getParameter("ann_title");
				String ann_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,100}$";
				if (ann_title == null || ann_title.trim().length() == 0) {
					errorMsgs.add("公告標題: 請勿空白");
				} else if (!ann_title.trim().matches(ann_titleReg)) {// 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("公告標題: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String ann_content = req.getParameter("ann_content").trim();
				if (ann_content == null || ann_content.trim().length() == 0) {
					errorMsgs.add("公告內容請勿空白");
				}

				String ann_state = req.getParameter("ann_state").trim();
				String default_item = req.getParameter("default_item"); // 判斷公告狀態的選擇
				if (ann_state == null || ann_state.trim().length() == 0 || ann_state == default_item) {
					errorMsgs.add("請選擇公告狀態");
				}
				String emp_no = req.getParameter("emp_no");

				AnnVO annVO = new AnnVO();
				annVO.setAnn_no(ann_no);
				annVO.setAnn_title(ann_title);
				annVO.setAnn_content(ann_content);
				annVO.setAnn_state(ann_state);
				annVO.setEmp_no(emp_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("annVO", annVO); // 含有輸入格式錯誤的annVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back/ann/update_ann_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				AnnService annSvc = new AnnService();
				annVO = annSvc.update(ann_no, ann_title, ann_content, ann_state, emp_no);

				/****************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("annVO", annVO); // 資料庫update成功後,正確的的annVO物件,存入req
				String url = "/back/ann/listOneAnn.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneAnn.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/ann/update_ann_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addAnn.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				System.out.println("ann_insert_try_in");
				/************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 ************************/
				String ann_title = req.getParameter("ann_title");
				String ann_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,100}$";
				if (ann_title == null || ann_title.trim().length() == 0) {
					errorMsgs.add("公告標題: 請勿空白");
				} else if (!ann_title.trim().matches(ann_titleReg)) {// 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("公告標題: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String ann_content = req.getParameter("ann_content").trim();
				if (ann_content == null || ann_content.trim().length() == 0) {
					errorMsgs.add("公告內容請勿空白");
				}

				String ann_state = req.getParameter("ann_state").trim();
				String default_item = req.getParameter("default_item"); // 判斷公告狀態的選擇
				if (ann_state == null || ann_state.trim().length() == 0 || ann_state == default_item) {
					errorMsgs.add("請選擇公告狀態");
				}
				String emp_no = req.getParameter("emp_no");

				AnnVO annVO = new AnnVO();
				annVO.setAnn_title(ann_title);
				annVO.setAnn_content(ann_content);
				annVO.setAnn_state(ann_state);
				annVO.setEmp_no(emp_no);
				System.out.println(ann_title);
				System.out.println(ann_content);
				System.out.println(ann_state);
				System.out.println(emp_no);
				System.out.println(default_item);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("annVO", annVO); // 含有輸入格式錯誤的annVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back/ann/listAllAnn.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				AnnService annSvc = new AnnService();
				annVO = annSvc.add(ann_title, ann_content, ann_state, emp_no);

				/****************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("annVO", annVO);
				String url = "/back/ann/listAllAnn.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後,轉交listAllAnn.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/ann/listAllAnn.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllAnn.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				System.out.println("ann_delete_try_in");
				/*************************** 1.接收請求參數 ***************************************/
				Integer ann_no = new Integer(req.getParameter("ann_no"));

				/*************************** 2.開始刪除資料 ***************************************/
				AnnService annSvc = new AnnService();
				annSvc.delete(ann_no);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/back/ann/listAllAnn.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/ann/listAllAnn.jsp");
				failureView.forward(req, res);
			}
		}

		if ("leave".equals(action)) {
			System.out.println("ann_leave_in");
			String url = "/back/ann/listAllAnn.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 按下離開後,回到顯示全部系統公告頁面
			successView.forward(req, res);
		}
	}
}

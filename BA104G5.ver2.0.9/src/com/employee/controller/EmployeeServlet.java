package com.employee.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.employee.model.*;
@WebServlet("/back/emp.do")
public class EmployeeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String emp_name = req.getParameter("emp_name");
				String emp_name_Reg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.put("emp_name", "員工姓名: 請勿空白");
				} else if (!emp_name.trim().matches(emp_name_Reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("emp_name", "員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				
				String emp_id = req.getParameter("emp_id").trim();
				//EmployeeService
				EmployeeService empSvc = new EmployeeService();
				empSvc.getCountByID(emp_id);
				String emp_id_Reg = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
				if (emp_id == null || emp_id.trim().length() == 0) {
					errorMsgs.put("emp_id", "員工帳號: 請勿空白");
				} else if (!emp_id.trim().matches(emp_id_Reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("emp_id", "員工帳號: 必須為電子郵件");
				} else if (empSvc.getCountByID(emp_id).intValue()!=0){
					errorMsgs.put("emp_id", "員工帳號: 已存在");
				}
				
				EmployeeVO empVO = new EmployeeVO();
				empVO.setEmp_id(emp_id);
				empVO.setEmp_name(emp_name);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back/employee/addEmp.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				
				/*************************** 2.開始新增資料 ***************************************/


				/* emp_psw 由以下程式碼產生 */
				final String PSWDIC = "23456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";// 包含字元
				final int PSWLENGTH = 10;// 長度
				Random random = new Random();
				StringBuilder sb = new StringBuilder(PSWLENGTH);
				for (int i = 0; i < PSWLENGTH; i++)
					sb.append(PSWDIC.charAt(random.nextInt(PSWDIC.length())));
				String emp_psw = sb.toString();

				
				empSvc.addEmp(emp_id, emp_psw, emp_name);

				SendMail SendMail = new SendMail();
				SendMail.sendMail(emp_id, "密碼通知", "你的密碼是" + emp_psw);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				
				RequestDispatcher successView = req.getRequestDispatcher("/back/employee/listAllEmp.jsp"); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/employee/addEmp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑:
																// 【/emp/listAllEmp.jsp】
			req.setAttribute("requestURL", requestURL); // 送出修改的來源網頁路徑, 存入req
														// (是為了給update_emp_input.jsp)

			String whichPage = req.getParameter("whichPage");
			req.setAttribute("whichPage", whichPage); // 送出修改的來源網頁的第幾頁,
														// 存入req(只用於:istAllEmp.jsp)

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String emp_no = new String(req.getParameter("emp_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO empVO = empSvc.getOneEmp(emp_no);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/back/employee/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}

		}

		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑:
																// 【/emp/listAllEmp.jsp】
			req.setAttribute("requestURL", requestURL); // 送出修改的來源網頁路徑, 存入req

			String whichPage = req.getParameter("whichPage"); // 送出修改的來源網頁的第幾頁(只用於:istAllEmp.jsp)
			req.setAttribute("whichPage", whichPage); // 送出修改的來源網頁的第幾頁,
														// 存入req(只用於:istAllEmp.jsp)

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/

				String emp_no = req.getParameter("emp_no");
				String emp_name = req.getParameter("emp_name");
				String emp_name_Reg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (emp_name == null || emp_name.trim().length() == 0) {
					errorMsgs.add("員工姓名: 請勿空白");
				} else if (!emp_name.trim().matches(emp_name_Reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String emp_id = req.getParameter("emp_id").trim();
				String emp_id_Reg = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";
				if (emp_id == null || emp_id.trim().length() == 0) {
					errorMsgs.add("員工帳號請勿空白");
				} else if (!emp_id.trim().matches(emp_id_Reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工帳號: 必須為電子郵件");
				}

				String emp_psw = req.getParameter("emp_psw").trim();
				String emp_psw_Reg = "^([a-zA-Z0-9@*#]{6,15})$";
				if (emp_psw == null || emp_psw.trim().length() == 0) {
					errorMsgs.add("員工密碼請勿空白");
				} else if (!emp_psw.trim().matches(emp_psw_Reg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("員工密碼: 只能是英文字母、數字和*#@ 且長度必需在6到15之間");
				}

				String emp_state = req.getParameter("emp_state").trim();

				EmployeeVO empVO = new EmployeeVO();
				empVO.setEmp_id(emp_id);
				empVO.setEmp_psw(emp_psw);
				empVO.setEmp_name(emp_name);
				empVO.setEmp_state(emp_state);
				empVO.setEmp_no(emp_no);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/employee/update_emp_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 ***************************************/

				byte[] emp_photo = "abc".getBytes();
				EmployeeService empSvc = new EmployeeService();
				empSvc.updateEmp(emp_id, emp_psw, emp_name, emp_photo, emp_state, emp_no);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 ***********/

				req.setAttribute("empVO", empVO);

				String url = requestURL + "?whichPage=" + whichPage + "&empno=" + emp_no; // 送出修改的來源網頁的第幾頁(只用於:istAllEmp.jsp)和修改的是哪一筆
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/employee/update_emp_input.jsp");
				failureView.forward(req, res);
			}

		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑:
																// 可能為【/emp/listAllEmp.jsp】
																// 或
																// 【/dept/listEmps_ByDeptno.jsp】
																// 或 【
																// /dept/listAllDept.jsp】
			String whichPage = req.getParameter("whichPage"); // 送出刪除的來源網頁的第幾頁(只用於:istAllEmp.jsp)

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/
				String emp_no = req.getParameter("emp_no");

				/*************************** 2.開始刪除資料 ***************************************/
				EmployeeService empSvc = new EmployeeService();
				EmployeeVO empVO = empSvc.getOneEmp(emp_no);
				empSvc.deleteEmp(emp_no);

				/***************************
				 * 3.刪除完成,準備轉交(Send the Success view)
				 ***********/
				String url = requestURL + "?whichPage=" + whichPage;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

	}
}

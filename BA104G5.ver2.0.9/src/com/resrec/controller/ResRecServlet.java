package com.resrec.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.breaktime.model.BreakTimeService;
import com.breaktime.model.BreakTimeVO;
import com.google.gson.Gson;
import com.resrec.model.ResRecService;
import com.resrec.model.ResRecVO;

@SuppressWarnings("serial")
public class ResRecServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Update".equals(action)) { // 來自listAllResRec.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String resr_no = new String(req.getParameter("resr_no"));

				/*************************** 2.開始查詢資料 ****************************************/
				ResRecService resRecSvc = new ResRecService();
				ResRecVO resRecVO = resRecSvc.getOneResR(resr_no);

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("resRecVO", resRecVO); // 資料庫取出的resRecVO物件,存入req
				String url = "/resrec/update_resrec_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交
																				// update_resRec_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/resrec/listAllResRec.jsp");
				failureView.forward(req, res);
			}
		}

		if ("resr_update".equals(action)) { // 來自calendar.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			 String requestURL = req.getParameter("requestURL"); //
			// 送出修改的來源網頁路徑: 可能為【/resRec/listAllResRec.jsp】 或
			// 【/dept/listResRecs_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String resr_no = req.getParameter("resr_no").trim();
			
				java.sql.Date resr_date = null;
				try {
					resr_date = java.sql.Date.valueOf(req.getParameter("resr_date").trim());
				} catch (IllegalArgumentException e) {
					resr_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
			

				String resr_period = req.getParameter("resr_period").trim();

				String resr_states = States.ESTABLISH.getString();

				ResRecVO resRecVO = new ResRecVO();
				resRecVO.setResr_no(resr_no);
				resRecVO.setResr_date(resr_date);
				resRecVO.setResr_period(resr_period);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("resRecVO", resRecVO); // 含有輸入格式錯誤的resRecVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/resrec/update_resrec_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ResRecService resRecSvc = new ResRecService();
				resRecVO = resRecSvc.updateResRec_kuei3(resr_states, resr_date,resr_period,resr_no);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				// DeptService deptSvc = new DeptService();
				// if(requestURL.equals("/dept/listResRecs_ByDeptno.jsp") ||
				// requestURL.equals("/dept/listAllDept.jsp"))
				// req.setAttribute("listResRecs_ByDeptno",deptSvc.getResRecsByDeptno(deptno));
				// // 資料庫取出的list物件,存入request

				String url = "/member/member.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/resrec/update_resrec_input.jsp");
				failureView.forward(req, res);
			}
		}
		

		if ("resrecCancel".equals(action)) { 
			
			String resr_no = req.getParameter("resr_no").trim();
			Date resr_date = java.sql.Date.valueOf(req.getParameter("resr_date").trim());
			String resr_period = req.getParameter("resr_period");
			String resr_states = States.CANCEL.getString().trim();
				
			ResRecVO resRecVO = new ResRecVO();
			resRecVO.setResr_no(resr_no);
			resRecVO.setResr_date(resr_date);
			resRecVO.setResr_period(resr_period);
			
			ResRecService resRecSvc = new ResRecService();
			resRecVO = resRecSvc.updateResRec_kuei3(resr_states, resr_date,resr_period,resr_no);
			res.setContentType("text/html; charset=UTF-8");
			String success = "取消預約拉";
			
			PrintWriter out = res.getWriter();
			out.print(success);
			out.flush();
			out.close();
		}
		
		if ("resrecChange".equals(action)) { 
			String resr_no = req.getParameter("resr_no").trim();
			Date resr_date = java.sql.Date.valueOf(req.getParameter("resr_date").trim());
			String resr_period = req.getParameter("resr_period");
			String resr_states = States.ESTABLISH.getString().trim();

			ResRecVO resRecVO = new ResRecVO();
			resRecVO.setResr_no(resr_no);
			resRecVO.setResr_date(resr_date);
			resRecVO.setResr_period(resr_period);
			
			ResRecService resRecSvc = new ResRecService();
			resRecVO = resRecSvc.updateResRec_kuei3(resr_states, resr_date,resr_period,resr_no);
			String success = "預約時間修改成功";
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.print(success);
			out.flush();
			out.close();
			
		}
		
		if ("findRtr".equals(action)) { 
			
			String rtr_no = req.getParameter("rtr_no").trim();
			String mem_no = req.getParameter("mem_no").trim();
			ResRecService resRecSvc = new ResRecService();
			//String rtrno =resRecSvc.getOneResR(resr_no).getRtr_no();
			
			List<ResRecVO> list = resRecSvc.getRtr_kuei(rtr_no);
			StringBuffer strtojson = new StringBuffer("{\"ResRecVO\": [");
			for(ResRecVO resr :list){
				if(!resr.getMem_no().equals(mem_no)){
				strtojson.append("{\"resr_no\"" +":\""+ resr.getResr_no()+"\",");
				//strtojson.append("\"rtr_no\"" +":\""+ resr.getRtr_no()+"\",");
				strtojson.append("\"resr_date\"" +":\""+ resr.getResr_date()+"\",");
				strtojson.append("\"resr_period\"" +":\""+ resr.getResr_period()+"\"},");
			  }
			};
			
			BreakTimeService BTSvc = new BreakTimeService();
			List<BreakTimeVO> list1 =BTSvc.getRtr_bt_kuei(rtr_no);
			for(BreakTimeVO bt :list1){
				strtojson.append("{\"resr_no\"" +":\""+ bt.getRtr_no()+"\",");
				strtojson.append("\"resr_date\"" +":\""+ bt.getBrk_date()+"\",");
				strtojson.append("\"resr_period\"" +":\""+ bt.getBrk_period()+"\"},");
			};
			
			strtojson.replace(strtojson.length()-1, strtojson.length(), "]}");
System.out.println(strtojson);
			res.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = res.getWriter();
			out.print(strtojson);
			out.flush();
			out.close();
		}

		if ("changehseRev".equals(action)) { 
			String resr_no = req.getParameter("resr_no").trim();
			
			Double resr_hpric = Double.valueOf((req.getParameter("resr_hpric").trim().length()>4)?req.getParameter("resr_hpric").trim().substring(0, 4):req.getParameter("resr_hpric").trim());	
			Double resr_hsize = Double.valueOf((req.getParameter("resr_hsize").trim().length()>4)?req.getParameter("resr_hsize").trim().substring(0, 4):req.getParameter("resr_hsize").trim());
			Double resr_wall = Double.valueOf((req.getParameter("resr_wall").trim().length()>4)?req.getParameter("resr_wall").trim().substring(0, 4):req.getParameter("resr_wall").trim());
			Double resr_str = Double.valueOf((req.getParameter("resr_str").trim().length()>4)?req.getParameter("resr_str").trim().substring(0, 4):req.getParameter("resr_str").trim());
			Double resr_lc = Double.valueOf((req.getParameter("resr_lc").trim().length()>4)?req.getParameter("resr_lc").trim().substring(0, 4):req.getParameter("resr_lc").trim());

			
			ResRecService resRecSvc = new ResRecService();

			ResRecVO resRecVO = resRecSvc.getOneResR(resr_no);
			
			resRecVO.setResr_hpric(resr_hpric);
			resRecVO.setResr_hsize(resr_hsize);
			resRecVO.setResr_wall(resr_wall);
			resRecVO.setResr_str(resr_str);
			resRecVO.setResr_lc(resr_lc);

			resRecSvc.updateResRec(resRecVO);
			
			String success = "修改成功";
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.print(success);
			out.flush();
			out.close();
		}
		
		if ("changeRtrRev".equals(action)) { 
			String resr_no = req.getParameter("resr_no").trim();
			
			Double mem_rate = Double.valueOf((req.getParameter("mem_rate")));	
			String mem_rev = req.getParameter("mem_rev").trim();
		
			
			ResRecService resRecSvc = new ResRecService();

			ResRecVO resRecVO = resRecSvc.getOneResR(resr_no);
			
			resRecVO.setMem_rate(mem_rate);
			resRecVO.setMem_rev(mem_rev);
			
			resRecSvc.updateResRec(resRecVO);
			
			String success = "修改成功";
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.print(success);
			out.flush();
			out.close();
		}
		
		
		if ("update".equals(action)) { // 來自update_resRec_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			 String requestURL = req.getParameter("requestURL"); //
			// 送出修改的來源網頁路徑: 可能為【/resRec/listAllResRec.jsp】 或
			// 【/dept/listResRecs_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

			try {
				/***************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String resr_no = req.getParameter("resr_no").trim();
				String mem_no = req.getParameter("mem_no").trim();
				String house_no = req.getParameter("house_no").trim();
				String rtr_no = req.getParameter("rtr_no").trim();

				java.sql.Date resr_date = null;
				try {
					resr_date = java.sql.Date.valueOf(req.getParameter("resr_date").trim());
				} catch (IllegalArgumentException e) {
					resr_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				String resr_period = req.getParameter("resr_period").trim();

				String resr_states = States.ESTABLISH.getString();

				ResRecVO resRecVO = new ResRecVO();
				resRecVO.setResr_no(resr_no);
				resRecVO.setMem_no(mem_no);
				resRecVO.setHouse_no(house_no);
				resRecVO.setRtr_no(rtr_no);
				resRecVO.setResr_date(resr_date);
				resRecVO.setResr_period(resr_period);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("resRecVO", resRecVO); // 含有輸入格式錯誤的resRecVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/resrec/update_resrec_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ResRecService resRecSvc = new ResRecService();
				resRecVO = resRecSvc.updateResRec_kuei1(resr_no, mem_no, house_no, rtr_no, resr_states, resr_date,
						resr_period);

				/***************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				// DeptService deptSvc = new DeptService();
				// if(requestURL.equals("/dept/listResRecs_ByDeptno.jsp") ||
				// requestURL.equals("/dept/listAllDept.jsp"))
				// req.setAttribute("listResRecs_ByDeptno",deptSvc.getResRecsByDeptno(deptno));
				// // 資料庫取出的list物件,存入request

				String url = "/resrec/listAllResRec.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/resrec/update_resrec_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addResRec.jsp的請求

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *************************/

				String mem_no = req.getParameter("mem_no").trim();

				String house_no = req.getParameter("house_no").trim();
				if (house_no == null || house_no.trim().length() == 0) {
					errorMsgs.put("house_no", "請選擇房屋");
				}

				String rtr_no = req.getParameter("rtr_no").trim();
				if (rtr_no == null || rtr_no.trim().length() == 0) {
					errorMsgs.put("rtr_no", "請選擇房仲");
				}

				java.sql.Date resr_date = null;
				try {
					resr_date = java.sql.Date.valueOf(req.getParameter("resr_date").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("resr_date", "請輸入預約日期");
				}

				String resr_period = req.getParameter("resr_period").trim();
				if (resr_period == null || resr_period.trim().length() == 0) {
					errorMsgs.put("resr_period", "請選擇預約時段");
				}

				String resr_states = States.ESTABLISH.getString();

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/resrec/addResRec.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				ResRecService resRecSvc = new ResRecService();
				resRecSvc.addResRec_kuei1(mem_no, house_no, rtr_no, resr_states, resr_date, resr_period);

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				String url = "/resrec/listAllResRec.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllResRec.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.put("Exception", e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/resrec/addResRec.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/resRec/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

			try {
				/***************************1.接收請求參數***************************************/
				String resr_no = req.getParameter("resr_no").trim();
				
				/***************************2.開始刪除資料***************************************/
				ResRecService resRecSvc = new ResRecService();
//				ResRecVO resRecVO = resRecSvc.getOneResR(resr_no);
				resRecSvc.deleteResRec(resr_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
//				DeptService deptSvc = new DeptService();
//				if(requestURL.equals("/dept/listResRecs_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
//					req.setAttribute("listResRecs_ByDeptno",deptSvc.getResRecsByDeptno(resRecVO.getDeptno())); // 資料庫取出的list物件,存入request
				
				String url = "/resrec/listAllResRec.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}
}

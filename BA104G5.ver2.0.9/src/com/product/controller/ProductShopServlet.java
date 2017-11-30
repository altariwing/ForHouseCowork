package com.product.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.prdimg.model.PrdImgService;
import com.prdimg.model.PrdImgVO;
import com.product.model.*;

public class ProductShopServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");

		if ("insert".equals(action)) { }

		
		
		if ("getOne_Product".equals(action)) { // 來自listAllPrd.jsp 的請求
			@SuppressWarnings("unchecked")
			List<PrdVO> cartlist = (ArrayList<PrdVO>) session.getAttribute("cartlist");
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑:
																// 【/prd/listAllPrd.jsp】	
			try {
				/*************************** 1.接收請求參數 ****************************************/
				String prd_no = new String(req.getParameter("prd_no"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				PrdService prdSvc = new PrdService();
				PrdVO prdVO = prdSvc.getOnByPrdNo(prd_no);

				PrdImgService prdImgService = new PrdImgService();
				List<PrdImgVO> list = prdImgService.findByPrdNo(prdVO.getPrd_no());

				/***************** 3.查詢完成,準備轉交(Send the Success view) ************/
				session.setAttribute("cartlist", cartlist);
				session.setAttribute("PrdVO", prdVO); // 資料庫取出的prdVO物件,存入req
				
				session.setAttribute("list", list);// 資料庫取出的img物件,存入req
				
				String url = "/front/shopping/PrdInfo.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交PrdInfo.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ************************************/
			} catch (Exception e) {
				System.out.print("走到商品頁面失敗,送回原頁面" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}

		}

		
		if ("listPrd_ByCompositeQuery".equals(action)) { // 來自listAllPrd.jsp 的複合查詢請求
			

			try {
				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				Map<String, String[]> map = req.getParameterMap();
				
				/***************************2.開始複合查詢***************************************/
				PrdService prdSvc = new PrdService();
				List<PrdVO> list  = prdSvc.getAll(map);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listEmps_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front/shopping/listAllPrd.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front/shopping/listAllPrd.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) {}

	}
}

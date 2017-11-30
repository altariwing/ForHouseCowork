package com.newstype.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.model.NewsVO;
import com.newstype.model.NewsTypeService;
import com.newstype.model.NewsTypeVO;

@SuppressWarnings("serial")
public class NewsTypeServlet extends HttpServlet {

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

			try {
				System.out.println("newstype_getOne_For_Display_try_in");
				/******************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String ntype_no = req.getParameter("ntype_no");
				// Send the use back to the form, if there were errors
				// send the ErrorPage view.
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/news/listAllNewsType.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始查詢資料 *****************************************/
				NewsTypeService newstypeSvc = new NewsTypeService();
				NewsTypeVO newstypeVO = newstypeSvc.getOne(ntype_no);
				if (newstypeVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/news/listAllNewsType.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/******************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("newstypeVO", newstypeVO); // 資料庫取出的newstypeVO物件,存入req
				String url = "/back/newstype/listOneNewsType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/newstype/listAllNewsType.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {// 來自addNewsType.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				System.out.println("newstype_insert_try_in");
				/*******************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *****************/
				String ntype_no = req.getParameter("ntype_no");
				String news_type = req.getParameter("news_type");

				NewsTypeVO newstypeVO = new NewsTypeVO();
				newstypeVO.setNtype_no(ntype_no);
				newstypeVO.setNews_type(news_type);
				System.out.println(ntype_no);
				System.out.println(news_type);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/newstype/listAllNewsType.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				NewsTypeService newstypeSvc = new NewsTypeService();
				newstypeVO = newstypeSvc.add(ntype_no, news_type);

				/****************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("newstypeVO", newstypeVO);
				String url = "/back/newstype/listAllNewsType.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後,轉交listAllNews.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/newstype/listAllNewsType.jsp");
				failureView.forward(req, res);
			}
		}

		// 來自listAllNewsType.jsp的請求 & newstype/listAllNewsType.jsp的請求
		if ("listNews_ByNtype_No_A".equals(action) || "listNews_ByNtype_No_B".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
 
			try { 
				System.out.println("listNews_ByNtype_No");
				/*************************** 1.接收請求參數 ****************************************/
				String ntype_no = req.getParameter("ntype_no");

				/*************************** 2.開始查詢資料 ****************************************/
				NewsTypeService newstypeSvc = new NewsTypeService();
				Set<NewsVO> set = newstypeSvc.getNewsByNtype_no(ntype_no);
				/****************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/ 
//				HttpSession session = req.getSession();// 先暫時用session設定，之後要再改成req
				req.setAttribute("listNews_ByNtype_No", set); // 從資料庫取出set物件，存req
 
				String url = null;
				if ("listNews_ByNtype_No_A".equals(action))
					url = "/back/newstype/listNews_ByNtype_No.jsp"; // 成功轉交
																	// newstype/listNews_ByNtype_No.jsp
				else if ("listNews_ByNtype_No_B".equals(action))
					url = "/back/newstype/listAllNewsType.jsp";// 成功轉交
																// newstype/listAllNewsType.jsp
				
				System.out.println(url);
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

	}

}

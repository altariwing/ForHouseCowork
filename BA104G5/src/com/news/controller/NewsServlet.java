package com.news.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.news.model.NewsService;
import com.news.model.NewsVO;
import com.newstype.model.NewsTypeService;

@SuppressWarnings("serial")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 50 * 50 * 1024 * 1024)
public class NewsServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//查單一
		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				System.out.println("news_getOne_For_Display_try_in");
				/*****************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String news_no = req.getParameter("news_no");
				if (news_no == null || (news_no.trim()).length() == 0) {// 來自listAllNews.jsp的請求
					errorMsgs.add("請輸入新聞編號");
				}
				// Send the use back to the form, if there were errors
				// send the ErrorPage view.
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/news/listAllNews.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				// Integer news_no = null;
				// try {
				// news_no = new Integer(str);
				//
				// } catch (Exception e) {
				// errorMsgs.add("新聞編號不正確");
				// }
				// Send the use back to the form, if there were errors

				/*************************** 2.開始查詢資料 *****************************************/
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.getOne(news_no);
				if (newsVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back/news/listAllNews.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*****************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("newsVO", newsVO); // 資料庫取出的newsVO物件,存入req
				String url = "/back/news/listOneNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/news/listAllNews.jsp");
				failureView.forward(req, res);
			}
		}//查單一結束
		
		
		//查單一修改結束
		if ("getOne_For_Update".equals(action)) { // 來自listAllNews.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");// 送出修改的來源網頁路徑
			System.out.println("news_getOne_For_Update: " + requestURL);

			try {
				System.out.println("news_getOne_For_Update_try_in");
				/*************************** 1.接收請求參數 ****************************************/
				String news_no = req.getParameter("news_no");

				/*************************** 2.開始查詢資料 ****************************************/
				NewsService newsSvc = new NewsService();
				NewsVO newsVO = newsSvc.getOne(news_no);

				/*****************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("newsVO", newsVO);
				String url = "/back/news/update_news_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交update_news_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}//查單一修改結束
		
		
		//更新新聞
		if ("update".equals(action)) { // 來自update_news_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL");// 送出修改的來源網頁路徑
			System.out.println("news_update: " + requestURL);
			
			Enumeration<String> allname = req.getParameterNames();
			while (allname.hasMoreElements()) {
				System.out.println("news_全部的值: " + allname.nextElement());
			}

			try {

				System.out.println("news_update_try_in");
				/*****************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 *****************/
				String news_no = req.getParameter("news_no");
				String ntype_no = req.getParameter("ntype_no");
				String default_item1 = req.getParameter("default_item1"); // 判斷狀態的選擇
				if (ntype_no == null || ntype_no.trim().length() == 0 || ntype_no == default_item1) {
					errorMsgs.add("新聞種類: 請勿空白");
				}

				String news_title = req.getParameter("news_title");
				String news_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,100}$";
				if (news_title == null || news_title.trim().length() == 0) {
					errorMsgs.add("新聞標題: 請勿空白");
				} else if (!news_title.trim().matches(news_titleReg)) {// 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("新聞標題: 只能是中、英文字母、數字");
				}

				String news_content = req.getParameter("news_content").trim();
				if (news_content == null || news_content.trim().length() == 0) {
					errorMsgs.add("新聞內容請勿空白");
				}

				String news_state = req.getParameter("news_state").trim();
				String default_item2 = req.getParameter("default_item"); // 判斷新聞狀態的選擇
				if (news_state == null || news_state.trim().length() == 0 || news_state == default_item2) {
					errorMsgs.add("請選擇新聞狀態");
				}
				
				NewsVO newsVO = new NewsVO();
				
				byte[] news_photo = null;
				try {
					Part photo = req.getPart("news_photo");
					if (!photo.getContentType().equalsIgnoreCase("application/octet-stream")) {
						InputStream in = photo.getInputStream();
						news_photo = new byte[in.available()];
						in.read(news_photo);
						in.close();
					} else {
						news_photo = newsVO.getNews_photo();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

				String emp_no = req.getParameter("emp_no");

				newsVO.setNews_no(news_no);
				newsVO.setNtype_no(ntype_no);
				newsVO.setNews_title(news_title);
				newsVO.setNews_content(news_content);
				newsVO.setNews_state(news_state);
				newsVO.setNews_photo(news_photo);
				newsVO.setEmp_no(emp_no);

				System.out.println(ntype_no);
				System.out.println(news_title);
				System.out.println(news_content);
				System.out.println(news_state);
				System.out.println(news_photo.equals(news_photo));
				System.out.println(emp_no);
				System.out.println(errorMsgs);
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("newsVO", newsVO); // 含有輸入格式錯誤的newsVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back/news/listAllNews.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				NewsService newsSvc = new NewsService();
				newsVO = newsSvc.update(news_no, ntype_no, news_title, news_content, news_photo, news_state, emp_no);

				/***************************** 3.修改完成,準備轉交(Send the Success view)*************/
				NewsTypeService newstypeSvc = new NewsTypeService();
				if(requestURL.equals("/back/newstype/listNews_ByNtype_No.jsp")||requestURL.equals("/back/newstype/listAllNewsType.jsp"))
				req.setAttribute("listNews_ByNtype_No", newstypeSvc.getNewsByNtype_no(ntype_no)); // 資料庫取出的list物件，存入req
							
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				// errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/news/listAllNews.jsp");
				failureView.forward(req, res);
			}
		}//更新新聞結束
		
		
		//新增新聞
		if ("insert".equals(action)) { 
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				System.out.println("news_insert_try_in");
				/************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 ************************/

				String ntype_no = req.getParameter("ntype_no");
				String default_item1 = req.getParameter("default_item1"); // 判斷狀態的選擇
				if (ntype_no == null || ntype_no.trim().length() == 0 || ntype_no == default_item1) {
					errorMsgs.add("新聞種類: 請勿空白");
				}

				String news_title = req.getParameter("news_title");
				String news_titleReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,100}$";
				if (news_title == null || news_title.trim().length() == 0) {
					errorMsgs.add("新聞標題: 請勿空白");
				} else if (!news_title.trim().matches(news_titleReg)) {// 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("新聞標題: 只能是中、英文字母、數字");
				}
				System.out.println("5555555555" + news_title.trim().matches(news_titleReg));
				String news_state = req.getParameter("news_state").trim();
				String default_item2 = req.getParameter("default_item2"); // 判斷狀態的選擇
				if (news_state == null || news_state.trim().length() == 0 || news_state == default_item2) {
					errorMsgs.add("請選擇新聞狀態");
				}

				byte[] news_photo = null;
				Part photo = req.getPart("news_photo");
				InputStream in = photo.getInputStream();
				news_photo = new byte[in.available()];
				in.read(news_photo);
				in.close();

				String news_content = req.getParameter("news_content").trim();
				if (news_content == null || news_content.trim().length() == 0) {
					errorMsgs.add("新聞內容請勿空白");
				}

				String emp_no = req.getParameter("emp_no");

				NewsVO newsVO = new NewsVO();
				newsVO.setNtype_no(ntype_no);
				newsVO.setNews_title(news_title);
				newsVO.setNews_content(news_content);
				newsVO.setNews_state(news_state);
				newsVO.setNews_photo(news_photo);
				newsVO.setEmp_no(emp_no);
				System.out.println(ntype_no);
				System.out.println(news_title);
				System.out.println(news_content);
				System.out.println(news_state);
				System.out.println(news_photo.equals(news_photo));
				System.out.println(emp_no);
				System.out.println(default_item1);
				System.out.println(default_item2);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("newsVO", newsVO); // 含有輸入格式錯誤的newsVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back/news/listAllNews.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				NewsService newsSvc = new NewsService();
				newsVO = newsSvc.add(ntype_no, news_title, news_content, news_photo, news_state, emp_no);

				/****************************
				 * 3.修改完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("newsVO", newsVO);
				String url = "/back/news/listAllNews.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後,轉交listAllNews.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back/news/listAllNews.jsp");
				failureView.forward(req, res);
			}
		}//新增新聞結束
		
		//依新聞發布時間排序
		if("getAllByTime".equals(action)){
			
			
			
			
			
		}//依新聞發布時間排序結束
		
		
		if ("leave".equals(action)) {
			System.out.println("news_leave_in");
			String url = "/back/news/listAllNews.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 按下離開後,回到顯示全部新聞頁面
			successView.forward(req, res);
		}
	}
}
package com.product.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.product.model.*;
import com.prdimg.model.*;


@WebServlet("/front/seller/product/prd.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class PrdHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); // 處理中文檔名
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		
		//來自 addprd.jsp 的請求
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			PrdService prdSvc = new PrdService();
			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String slr_no = req.getParameter("slr_no");
				String cate_no = req.getParameter("cate_no");
				String prd_name = req.getParameter("prd_name").trim();
				String prd_desc = req.getParameter("prd_desc").trim();
				Integer prd_stock = 0;
				Integer prd_price = 0;
				
				//檢查商品名稱是否為空
				if ( prd_name.length()==0 ) {
					errorMsgs.add("商品名稱不可為空!");
				}
				
				//檢查商品詳情是否為空
				if ( prd_desc.length()==0 ) {
					errorMsgs.add("商品詳情不可為空!");
				}
				
				//檢查數量是否為空
				try {
					prd_stock = new Integer(req.getParameter("prd_stock"));
					if (prd_stock<=0) {
						errorMsgs.add("商品數量不可少於0 !");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("商品數量不可為空!");  //未輸入時
				}
				
				//檢查單價是否為空
				try {
					prd_price = new Integer(req.getParameter("prd_price"));
					if (prd_price<=0) {
						errorMsgs.add("商品單價不可少於0 !");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("商品單價不可為空!");  //未輸入時
				}
				

				PrdVO prdVOtmp = new PrdVO();
				prdVOtmp.setSlr_no(slr_no);
				prdVOtmp.setCate_no(cate_no);
				prdVOtmp.setPrd_name(prd_name);
				prdVOtmp.setPrd_desc(prd_desc);
				prdVOtmp.setPrd_stock(prd_stock);
				prdVOtmp.setPrd_price(prd_price);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("prdVOtmp", prdVOtmp);   // 含有輸入格式錯誤的prdVOtmp物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front/seller/product/addprd.jsp");
					failureView.forward(req, res);
					return;
				}
				
				
				/************************ 圖片部分 **********************/
				Collection<Part> parts = req.getParts(); 
				List<byte[]> imgList = new ArrayList<byte[]>();
				byte[] prd_img = null;
				
				for (Part part : parts) {
					// 因要使用part.getContentType()所獲得的物件去用.startsWith方法, 因此如果part.getContentType()是null即會500, 故需先排除				
					if (getFileNameFromPart(part) != null && part.getContentType() != null && part.getContentType().startsWith("image")) {

						/******************* 從part取出圖片放入byte[] ********************/
						InputStream in = part.getInputStream();
						prd_img = new byte[in.available()];
						in.read(prd_img);
						
						imgList.add(prd_img);
						
						in.close();
					}
				}
				
				/***************************開始新增資料***************************************/
				PrdVO prdVO = new PrdVO();
				prdVO.setSlr_no(slr_no);
				prdVO.setCate_no(cate_no);
				prdVO.setPrd_name(prd_name);
				prdVO.setPrd_desc(prd_desc);
				prdVO.setPrd_stock(prd_stock);
				prdVO.setPrd_price(prd_price);
				
				String keyPrdNo = prdSvc.addPrd(prdVO);  //新增商品時同時取得自增主鍵prd_no
				
				if (prd_img != null) {
					PrdImgService prdImgSvc = new PrdImgService();
					prdImgSvc.addPrdImg(imgList, keyPrdNo);
				}
				
				/***************************新增完成,準備轉交(Send the Success view)***********/
				String url = "/front/seller/product/PrdList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				req.setAttribute("keyPrdNo", keyPrdNo);
				successView.forward(req, res);			
				
			/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/seller/product/addprd.jsp");
				failureView.forward(req, res);
			} 
		}
		
		
		
		if ("getOne_For_Update".equals(action)) { // 來自PrdList.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑為【PrdList.jsp】		
			
			try {
				/***************************1.接收請求參數****************************************/
				String prd_no = req.getParameter("prd_no");
				
				/***************************2.開始查詢資料****************************************/
				PrdService prdSvc = new PrdService();
				PrdVO prdVO = prdSvc.getOnByPrdNo(prd_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("prdVO", prdVO); // 資料庫取出的empVO物件,存入req
				String url = "/front/seller/product/updateprd.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交updateprd.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		//來自 stopSellList.jsp 的刪除圖片按鈕
		if ("deleteImg".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); //取得來源網頁路徑
			PrdVO prdVO = new PrdVO();

			try {
				/***********************1.接收請求參數 *************************/
				String img_no = req.getParameter("img_no");
				
				String prd_no = req.getParameter("prd_no");
				String prd_name = req.getParameter("prd_name");
				String prd_desc = req.getParameter("prd_desc");
				Integer prd_stock = new Integer(req.getParameter("prd_stock"));
				Integer prd_price = new Integer(req.getParameter("prd_price"));
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}

				/***************************開始刪除圖片***************************************/	
				PrdImgService prdImgSvc = new PrdImgService();
				prdImgSvc.delImg(img_no);

				/***************************刪除完成,準備轉交(Send the Success view)***********/
				List<PrdImgVO> imgListAfterDelOne = prdImgSvc.findByPrdNo(prd_no);
				req.setAttribute("imgListAfterDelOne", imgListAfterDelOne);

				prdVO.setPrd_no(prd_no);
				prdVO.setPrd_name(prd_name);
				prdVO.setPrd_desc(prd_desc);
				prdVO.setPrd_stock(prd_stock);
				prdVO.setPrd_price(prd_price);
				req.setAttribute("prdVO", prdVO);
				req.setAttribute("prdVOtmp", prdVO);
				
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);			
				
			/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("圖片刪除失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			} 
		}
				
		
		// 來自 updateprd.jsp 的更新商品資料的請求
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); //取得來源網頁路徑

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String prd_no = req.getParameter("prd_no");
				String cate_no = req.getParameter("cate_no");
				String prd_name = req.getParameter("prd_name").trim();
				String prd_desc = req.getParameter("prd_desc").trim();
				Integer prd_stock = 0;
				Integer prd_price = 0;
				
				//檢查商品名稱是否為空
				if ( prd_name.length()==0 ) {
					errorMsgs.add("商品名稱不可為空!");
				}
				
				//檢查商品詳情是否為空
				if ( prd_desc.length()==0 ) {
					errorMsgs.add("商品詳情不可為空!");
				}
				
				//檢查數量是否為空
				try {
					prd_stock = new Integer(req.getParameter("prd_stock"));
					if (prd_stock<=0) {
						errorMsgs.add("商品數量不可少於0 !");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("商品數量不可為空!");  //未輸入時
				}
				
				//檢查單價是否為空
				try {
					prd_price = new Integer(req.getParameter("prd_price"));
					if (prd_price<=0) {
						errorMsgs.add("商品單價不可少於0 !");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("商品單價不可為空!");  //未輸入時
				}

				PrdVO prdVO = new PrdVO();
				prdVO.setPrd_no(prd_no);
				prdVO.setCate_no(cate_no);
				prdVO.setPrd_name(prd_name);
				prdVO.setPrd_desc(prd_desc);
				prdVO.setPrd_stock(prd_stock);
				prdVO.setPrd_price(prd_price);
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("prdVO", prdVO);   // 含有輸入格式錯誤的prdVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
				
				/************************ 圖片部分 **********************/
				Collection<Part> parts = req.getParts(); 
				List<byte[]> imgList = new ArrayList<byte[]>();
				byte[] prd_img = null;
				
				for (Part part : parts) {
					// 因要使用part.getContentType()所獲得的物件去用.startsWith方法, 因此如果part.getContentType()是null即會500, 故需先排除				
					if (getFileNameFromPart(part) != null && part.getContentType() != null && part.getContentType().startsWith("image")) {

						/******************* 從part取出圖片放入byte[] ********************/
						InputStream in = part.getInputStream();
						prd_img = new byte[in.available()];
						in.read(prd_img);
						imgList.add(prd_img);
						in.close();
					}
				}
				
				/***************************開始update資料***************************************/	
				PrdService prdSvc = new PrdService();
				prdSvc.updatePrd(prdVO);
				
				if (prd_img != null) {
					PrdImgService prdImgSvc = new PrdImgService();
					prdImgSvc.addPrdImg(imgList, prd_no);
				}
				
				/***************************新增完成,準備轉交(Send the Success view)***********/
				String url = "/front/seller/product/PrdList.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				String updateSuccess = "商品資料更新成功!";
				req.setAttribute("updateSuccess", updateSuccess);
				successView.forward(req, res);			
				
			/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			} 
		}
		
		
		//來自 PrdList.jsp 的下架按鈕
		if ("stopSell".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); //取得來源網頁路徑

			try {
				/***********************1.接收請求參數 *************************/
				String prd_no = req.getParameter("prd_no");
				String prd_state = "OFF";

				/***************************開始update資料***************************************/	
				PrdService prdSvc = new PrdService();
				prdSvc.changeState(prd_no, prd_state);

				/***************************下架完成,準備轉交(Send the Success view)***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				String stopSell = "已下架!";
				req.setAttribute("stopSell", stopSell);
				req.setAttribute("prd_no", prd_no);
				successView.forward(req, res);			
				
			/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("下架失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			} 
		}
		
		
		//來自 stopSellList.jsp 的再上架按鈕
		if ("makeOn".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); //取得來源網頁路徑

			try {
				/***********************1.接收請求參數 *************************/
				String prd_no = req.getParameter("prd_no");
				String prd_state = "ON";

				/***************************開始update資料***************************************/	
				PrdService prdSvc = new PrdService();
				prdSvc.changeState(prd_no, prd_state);

				/***************************新增完成,準備轉交(Send the Success view)***********/
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);
				String reSell = "已重新上架!";
				req.setAttribute("reSell", reSell);
				req.setAttribute("prd_no", prd_no);
				successView.forward(req, res);			
				
			/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("上架失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			} 
		}
		
		
	}//doPost結束
	
	
		// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
		public String getFileNameFromPart(Part part) {
			String header = part.getHeader("content-disposition");
			String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName(); 
			if (filename.length() == 0) {
				return null;
			}
			return filename;
		}	
}

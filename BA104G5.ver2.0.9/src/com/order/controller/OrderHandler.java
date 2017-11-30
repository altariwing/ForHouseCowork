package com.order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mem.model.MemVO;
import com.odrdetail.model.OdrDetailService;
import com.odrdetail.model.OdrDetailVO;
import com.order.model.PrdOdrService;
import com.order.model.PrdOdrVO;
import com.prdimg.model.PrdImgService;
import com.prdimg.model.PrdImgVO;
import com.product.model.PrdVO;

@WebServlet("/front/order/PrdOrd.do")
public class OrderHandler extends HttpServlet {
private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); // 處理中文檔名
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
	
			// 來自 addprd.jsp 的請求
					if ("insert".equals(action)) {
						List<String> errorMsgs = new LinkedList<String>();
						req.setAttribute("errorMsgs", errorMsgs);
						
						HttpSession session = req.getSession();
	
						PrdOdrService poSvc = new PrdOdrService();
						OdrDetailService odrDetailService = new OdrDetailService();
						try {
							/***********************
							 * 1.接收請求參數 - 輸入格式的錯誤處理
							 *************************/
							
							List<PrdVO> cartlist = (List<PrdVO>) session.getAttribute("cartlist");
			System.out.println(cartlist == null);
			System.out.println(cartlist.size());
							//arrlist處理PrdVO
							ArrayList<PrdVO> arrlist = new ArrayList<PrdVO>();
							arrlist.addAll(cartlist);
			System.out.println(arrlist.size());				
							// 購買人mem_no 在session裡面
							MemVO memVO = (MemVO) session.getAttribute("memVO");
							String mem_no = memVO.getMem_no();
							// 折價卷cp_no 在參數裡
							String cp_no = req.getParameter("cp_no");
			System.out.println("優惠卷cp_no:"+cp_no);
							if(cp_no==null){cp_no="NULL";}
							
							
							//要送到刷卡頁面的pdo_no list
							List<String> pdo_noList = new ArrayList<String>();
							/***************************2.開始新增資料***************************************/				
							
							
							
							PrdOdrVO prdOdrVO = new PrdOdrVO();
							OdrDetailVO odrDetailVO = new OdrDetailVO();
							String slr_no = null; 
							String pdo_no = null; 
							// 新增訂單及新增訂單明細
							while(arrlist.size()>0){
	
								/*新增訂單*/					
								prdOdrVO.setMem_no(mem_no);
								slr_no = arrlist.get(0).getSlr_no(); //arrlist[0] prdVO.getSlr_no();
								prdOdrVO.setSlr_no(slr_no); 
								prdOdrVO.setCp_no(cp_no);
								prdOdrVO.setPdo_params("未付款");
			System.out.println(mem_no);					
			System.out.print(slr_no==null);System.out.println(slr_no);						
			System.out.println(cp_no);	
	
								pdo_no = poSvc.addOrder(prdOdrVO);									
								pdo_noList.add(pdo_no);//要送到刷卡頁面的pdo_no list
								/*新增訂單明細*/
								for(int i=0; i < arrlist.size(); i++){
									if(arrlist.get(i).getSlr_no()==slr_no){							
										odrDetailVO.setPdo_no(pdo_no);
										odrDetailVO.setPrd_no(arrlist.get(i).getPrd_no());
										odrDetailVO.setUnit_price(arrlist.get(i).getPrd_price());
										odrDetailVO.setQuantity(arrlist.get(i).getQuantity()); // prdVO.getQuantity(); 
										odrDetailService.insert(odrDetailVO);
										arrlist.remove(i);
								}
									else continue;						
								} //end for
							    
							} //end while
	
							/***************************
							 * 訂單處理完成,準備轉交(Send the Success view)
							 ***********/
							session.removeAttribute("cartlist");
							
							String orderCreated = "訂單已建立!";
							System.out.println("新增成功!我們去yahooㄅ");
							req.setAttribute("orderCreated", orderCreated);
							
							session.setAttribute("pdo_noList", pdo_noList);
							
							RequestDispatcher successView = req.getRequestDispatcher("/front/shopping/payment.jsp");
							successView.forward(req, res);
	
							/*************************** 其他可能的錯誤處理 **********************************/
						} catch (Exception e) {
							e.printStackTrace();
							errorMsgs.add(e.getMessage());
							System.out.println("新增失敗:"+e.getMessage());
							RequestDispatcher failureView = req.getRequestDispatcher("/front/seller/product/addprd.jsp");
							failureView.forward(req, res);
						}
					}
	
					// 來自 管理訂單 的請求
					if ("cancel".equals(action)) {
						List<String> errorMsgs = new LinkedList<String>();
						req.setAttribute("errorMsgs", errorMsgs);
	
						String requestURL = req.getParameter("requestURL"); // 取得來源網頁路徑
						HttpSession session = req.getSession();
	
						PrdOdrService poSvc = new PrdOdrService();
						try {
							/***********************
							 * 1.接收請求參數 - 輸入格式的錯誤處理
							 *************************/
	
							String pdo_no = req.getParameter("pdo_no");
	
							/*************************** 開始更新訂單狀態 ***************************************/
							PrdOdrService prdOdrSvc = new PrdOdrService();
							prdOdrSvc.setPdoStat("取消", pdo_no);
	
							/***************************
							 * 訂單處理完成,準備轉交(Send the Success view)
							 ***********/
							String orderCanceled = "訂單已取消!";
							req.setAttribute("orderCanceled", orderCanceled);
							RequestDispatcher successView = req.getRequestDispatcher(requestURL);
							successView.forward(req, res);
	
							/*************************** 其他可能的錯誤處理 **********************************/
						} catch (Exception e) {
							e.printStackTrace();
							errorMsgs.add(e.getMessage());
							RequestDispatcher failureView = req.getRequestDispatcher("/front/seller/product/addprd.jsp");
							failureView.forward(req, res);
						}
					}
		
		
		//來自 OdrList.jsp 的設定出貨按鈕
		if ("set_shipped".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); //取得來源網頁路徑
			

			try {
				/***********************1.接收請求參數 *************************/
				String pdo_no = req.getParameter("pdo_no");
				

				/***************************開始更新出貨狀態***************************************/	
				PrdOdrService prdOdrSvc = new PrdOdrService();
				prdOdrSvc.setPdoStat("已出貨",pdo_no);

				/***************************刪除完成,準備轉交(Send the Success view)***********/
				String setShipStat = "已設定為出貨!";
				req.setAttribute("setShipStat", setShipStat);
				RequestDispatcher successView = req.getRequestDispatcher(requestURL);
				successView.forward(req, res);			
				
			/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("設定出貨失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			} 
		}
		
		
		
		if ("pay".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); //取得來源網頁路徑
			
			HttpSession session = req.getSession();
			try {
				/***********************1.接收請求參數 *************************/
				
				StringBuffer strBuffer = new StringBuffer();
				strBuffer.append("{number:");
				strBuffer.append(req.getParameter("number"));
				strBuffer.append(",");
				strBuffer.append("first-name:");
				strBuffer.append(req.getParameter("first-name"));
				strBuffer.append(",");
				strBuffer.append("last-name:");
				strBuffer.append(req.getParameter("last-name"));
				strBuffer.append(",");
				strBuffer.append("expiry:");
				strBuffer.append(req.getParameter("expiry"));
				strBuffer.append(",");
				strBuffer.append("cvc:");
				strBuffer.append(req.getParameter("cvc"));
				strBuffer.append("}");
				
				/***************************開始更新付款狀態***************************************/	
				PrdOdrService prdOdrSvc = new PrdOdrService();
				ArrayList<String> pdo_noList = (ArrayList<String>)session.getAttribute("pdo_noList");
				for(String pdo_no : pdo_noList){
					prdOdrSvc.setPayment(strBuffer.toString(), pdo_no);
				}
				

				/***************************刪除完成,準備轉交(Send the Success view)***********/
				String paymentSuccess = "付款成功!";
				req.setAttribute("paymentSuccess", paymentSuccess);
				RequestDispatcher successView = req.getRequestDispatcher("/front/member/MemNewOdr.jsp");
				successView.forward(req, res);			
				
			/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("設定出貨失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			} 
		}
		
		
	}
}

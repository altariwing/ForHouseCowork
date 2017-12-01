package com.order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
							//總價
							Integer subtotal = new Integer(req.getParameter("subtotal"));							
							
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
							
							String orderCreated = "訂單已成立!";
							System.out.println("新增成功!我們去yahooㄅ");
							req.setAttribute("orderCreated", orderCreated);
							
							
							session.setAttribute("subtotal", subtotal);
							session.setAttribute("pdo_noList", pdo_noList);
							
							RequestDispatcher successView = req.getRequestDispatcher("/front/shopping/payment.jsp");
							successView.forward(req, res);
	
							/*************************** 其他可能的錯誤處理 **********************************/
						} catch (Exception e) {
							e.printStackTrace();
							errorMsgs.add(e.getMessage());
							System.out.println("新增失敗:"+e.getMessage());
							RequestDispatcher failureView = req.getRequestDispatcher("/front/shopping/Checkout.jsp");
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
			
			//把資料送回去
			Map<String,String> formVelues = new LinkedHashMap<String,String>();
			req.setAttribute("formVelues", formVelues);
			
			String requestURL = req.getParameter("requestURL"); //取得來源網頁路徑
			
			HttpSession session = req.getSession();
			try {
				/*****************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String number =req.getParameter("number").trim();
				String card_number_Reg = "^((67\\d{2})|(4\\d{3})|(5[1-5]\\d{2})|(6011))-?\\s?\\d{4}-?\\s?\\d{4}-?\\s?\\d{4}|3[4,7]\\d{13}$";
				if (number == null || (number.trim()).length() == 0) {
					errorMsgs.add("請輸入信用卡號");
				} else if (!number.trim().matches(card_number_Reg)) { //card_number regular-expression
					errorMsgs.add("請重新確認信用卡號");
				} else {					
					formVelues.put("number",number);
				}
				
				String first_name = req.getParameter("first_name").trim();
				String name_Reg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,30}$";
				if (first_name == null || first_name.trim().length() == 0) {
					errorMsgs.add("請輸入first-name");
				} else if (!first_name.trim().matches(name_Reg)) { // name regular-expression
					errorMsgs.add("請重新確認first-name");
				} else {					
					formVelues.put("first_name",first_name);
				}
				
				String last_name =req.getParameter("last_name").trim();
				if (last_name == null || last_name.trim().length() == 0) {
					errorMsgs.add("請輸入last_name");
				} else if (!last_name.trim().matches(name_Reg)) { // name regular-expression
					errorMsgs.add("請重新確認last_name");
				} else {					
					formVelues.put("last_name",last_name);
				}
				
				String expiry =req.getParameter("expiry").trim();
				if (expiry == null || expiry.trim().length() == 0) {
					errorMsgs.add("請輸入到期日");
				} else {					
					formVelues.put("expiry",expiry);
				}
				
				String cvc =req.getParameter("cvc").trim();
				if (cvc == null || cvc.trim().length() == 0) {
					errorMsgs.add("請輸入cvc號碼");
				} else {					
					formVelues.put("cvc",cvc);
				}									
				
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front/shopping/payment.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				
				/***************************開始更新付款狀態***************************************/	
				
				StringBuffer strBuffer = new StringBuffer();
				strBuffer.append("{number:");
				strBuffer.append(number);
				strBuffer.append(",");
				strBuffer.append("first_name:");
				strBuffer.append(first_name);
				strBuffer.append(",");
				strBuffer.append("last_name:");
				strBuffer.append(last_name);
				strBuffer.append(",");
				strBuffer.append("expiry:");
				strBuffer.append(expiry);
				strBuffer.append(",");
				strBuffer.append("cvc:");
				strBuffer.append(cvc);
				strBuffer.append("}");
				
				
				PrdOdrService prdOdrSvc = new PrdOdrService();
				ArrayList<String> pdo_noList = (ArrayList<String>)session.getAttribute("pdo_noList");
				for(String pdo_no : pdo_noList){
					prdOdrSvc.setPayment(strBuffer.toString(), pdo_no);
				}
				

				/***************************修改完成,準備轉交(Send the Success view)***********/
				session.removeAttribute("pdo_noList");
				
				String paymentSuccess = "付款成功!";
				req.setAttribute("paymentSuccess", paymentSuccess);
				RequestDispatcher successView = req.getRequestDispatcher("/front/member/MemNewOdr.jsp");
				successView.forward(req, res);			
				
			/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("失敗:連線逾時");
				RequestDispatcher failureView = req.getRequestDispatcher("/front/shopping/payment.jsp");
				failureView.forward(req, res);
			} 
		}
		
		// prepared for request from MemNewOdr.jsp
		if ("pay_ProductOrders".equals(action)) {

		// 取得來源網頁路徑
		String requestURL = req.getParameter("requestURL");

		try {

		/***********************
		* 1.接收請求參數 - 輸入格式的錯誤處理
		*************************/
		// 取得訂單日期
		String pdo_resr_date = req.getParameter("pdo_resr_date");

		// 購買人mem_no 在session裡面
		HttpSession session = req.getSession();
		MemVO memVO = (MemVO) session.getAttribute("memVO");
		String mem_no = memVO.getMem_no();



		/*************************** 2.開始查詢資料 ***************************************/
		PrdOdrService prdOdrSvc = new PrdOdrService();
		Map<String, List<PrdOdrVO>> getPrdOdrVOMap = prdOdrSvc.memGetByNew(mem_no);
		List<PrdOdrVO> list = getPrdOdrVOMap.get(pdo_resr_date);

		OdrDetailService odrDetailService = new OdrDetailService();

		// 要送到刷卡頁面的subtotal
		Integer subtotal = 0;

		// 要送到刷卡頁面的pdo_no list
		ArrayList<String> pdo_noList = new ArrayList<String>();
		for(PrdOdrVO item:list){

		System.out.println("有這些訂單:" + item.getPdo_no());
		pdo_noList.add(item.getPdo_no());
		List<OdrDetailVO> listOdrDetailVO = odrDetailService.getListByPdoNo(item.getPdo_no());//拿OrderDetail

		for(OdrDetailVO od:listOdrDetailVO){
		subtotal += od.getUnit_price()*od.getQuantity();
		};


		}

		/***************************
		* 3.新增完成,準備轉交(Send the Success view)
		***********/
		// 要送到刷卡頁面的subtotal
		session.setAttribute("subtotal", subtotal);

		// 要送到刷卡頁面的pdo_no list
		session.setAttribute("pdo_noList", pdo_noList); //pay

		String orderCreated = "請刷卡付款!";
		req.setAttribute("orderCreated", orderCreated);

		RequestDispatcher successView = req.getRequestDispatcher("/front/shopping/payment.jsp");
		successView.forward(req, res);


		System.out.println(mem_no + "說要付款" + subtotal);
		/*************************** 其他可能的錯誤處理 **********************************/

		} catch (Exception e) {
		RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
		failureView.forward(req, res);
		}

		}
		
	}
}

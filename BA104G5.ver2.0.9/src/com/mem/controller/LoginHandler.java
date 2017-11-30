package com.mem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mem.model.MemService;
import com.mem.model.MemVO;

@WebServlet("/front/checkIn.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	  public void doPost(HttpServletRequest req, HttpServletResponse res)
	                                throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8");
	    res.setContentType("text/html; charset=UTF-8");
	    String action = req.getParameter("action");
	    
	    
	 // 來自首頁"註冊"的請求
	    if ("memRegister".equals(action)) {   
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			MemService memSvc = new MemService();
			List<MemVO> list = memSvc.getAllIdList();
			
			//取得 IdentityServlet 產生的驗證碼 (captcha)
			String captchaId = (String)req.getSession().getAttribute("randomString");

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mem_id = req.getParameter("mem_id").trim().toLowerCase();
				String mem_psw = req.getParameter("mem_psw").trim();
				String mem_psw2 = req.getParameter("mem_psw2").trim();
				String mem_name = req.getParameter("mem_name").trim();
				String mem_addr = req.getParameter("mem_addr").trim();
				String twcity = req.getParameter("twcity");
				String twdist = req.getParameter("twdist");
				String twcitytext = req.getParameter("twcitytext").trim();

				Part part = req.getPart("mem_img");
				
				String search_state = null;
				String aggrement = null;
				String captcha = req.getParameter("captcha").trim().toUpperCase();
				

				//檢查帳號是否已存在
				if((mem_id.trim()).length()==0) {
					errorMsgs.add("帳號不可為空");
				} else {
					for (MemVO idList : list) {
						if( mem_id.equals((idList.getMem_id())) ) {
							errorMsgs.add("此帳號已存在!");
						}
					}
				}
				
				//檢查密碼是否為空
				if ((mem_psw.trim()).length()==0 || (mem_psw2.trim()).length()==0) {
					errorMsgs.add("密碼不可為空!");
				}
				
				//檢查兩次密碼是否相同
				if ( !mem_psw.equals(mem_psw2) ) {
					errorMsgs.add("兩次輸入的密碼不同!");
				}
				
				//檢查姓名是否為空
				if ( (mem_name.trim()).length()==0 ) {
					errorMsgs.add("姓名不可為空!");
				}

				//檢查地址是否為空
				if ( twcitytext.length()==0 ) {
					errorMsgs.add("街道內容不可為空!");
				}
				
				//檢查照片是否為空
				if ( part.getSize()<=0 ) {
					errorMsgs.add("照片不可為空!");
				}

				// 檢查是否開啟找房狀態
				try {
					search_state = req.getParameter("search_state").toUpperCase();
				} catch (NullPointerException e) {
					search_state = "OFF";
				}

				// 檢查是否同意 "服務條款 & 隱私權政策"
				try {
					(req.getParameter("aggrement")).equals("on");
					aggrement = req.getParameter("aggrement");
				} catch (NullPointerException e) {
					errorMsgs.add("需同意 '服務條款 & 隱私權政策' 才能註冊!");
					aggrement = "";
				}
				
				//檢查驗證碼
				if (!captcha.equals(captchaId)) {
					errorMsgs.add("驗證碼錯誤!");
				}

				
				MemVO memVOtmp = new MemVO();
				memVOtmp.setMem_id(mem_id);
				memVOtmp.setMem_psw(mem_psw);
				memVOtmp.setMem_name(mem_name);
				memVOtmp.setTwcity(twcity);
				memVOtmp.setTwdist(twdist);
				memVOtmp.setTwcitytext(twcitytext);
				memVOtmp.setSearch_state(search_state);
				memVOtmp.setAggrement(aggrement);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memVOtmp", memVOtmp);  // 含有輸入格式錯誤的empVOtmp物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front/register.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemVO memVO = new MemVO();
				memVO.setMem_id(mem_id);
				memVO.setMem_psw(mem_psw);
				memVO.setMem_name(mem_name);
				memVO.setTwcity(twcity);
				memVO.setTwdist(twdist);
				memVO.setTwcitytext(twcitytext);
				memVO.setMem_addr(mem_addr);
				
				InputStream in = part.getInputStream();
				byte[] mem_img = new byte[in.available()];
				in.read(mem_img);
				memVO.setMem_img(mem_img);
				in.close();
				
				memVO.setSearch_state(search_state);
				memVO.setAggrement(aggrement);
				memVO = memSvc.addMem(mem_id, mem_psw, mem_name, mem_addr, search_state, mem_img);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front/success.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交success.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/register.jsp");
				failureView.forward(req, res);
			}
		}

	    
	    // 來自首頁 login.jsp 登入的請求
	    if ("login".equals(action)) {
		    String mem_id = req.getParameter("mem_id").toLowerCase(); //把使用者輸入的e-mail轉成小寫
		    String mem_psw = req.getParameter("mem_psw");
	
		    // 【檢查該帳號 , 密碼是否有效】
		    MemService memSvc = new MemService();
		    MemVO memVO = null;
		    String errorMsgs = "";
		    
		    try {
				memVO = memSvc.getOneById(mem_id);
				if ( memVO.getLock_state().equals("OFF") ) {
					errorMsgs = "您的帳號尚未啟用或已被鎖定";
				} else if (!mem_psw.equals(memVO.getMem_psw())) {
						errorMsgs = "你的帳號或密碼無效!";
				}
			} catch (NullPointerException e) {
				errorMsgs = "你的帳號或密碼無效!";
			}
			
		    if (errorMsgs.length()>0) {
				req.setAttribute("errorMsgs", errorMsgs); // 將errorMsgs存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front/login.jsp");
				failureView.forward(req, res);
				return;
			}
		    
		      HttpSession session = req.getSession();
		      session.setAttribute("memVO", memVO);   // 在session內做已經登入過的標識
		       try {                                                        
		         String location = (String) session.getAttribute("location");
		         if (location != null) {
		           session.removeAttribute("location");   //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
		           res.sendRedirect(location);            
		           return;
		         }
		       }catch (Exception ignored) { }
	
		      res.sendRedirect(req.getContextPath()+"/front/index.jsp");  //*工作3: (-->如無來源網頁:則重導至index.jsp)
	    }
	    
	    
	 } //doPost
}
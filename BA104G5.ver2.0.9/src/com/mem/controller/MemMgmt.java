package com.mem.controller;

import java.io.IOException;
import java.io.InputStream;
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

import com.mem.model.*;

@WebServlet("/front/member/memMgmt.do")  //控制會員修改基本資料與更改密碼
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemMgmt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemMgmt() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
				
		
		
		// 來自會員中心基本資料的修改請求
		if ("memModify".equals(action)) {
			List<String> dataErrors = new LinkedList<String>();
			req.setAttribute("dataErrors", dataErrors);
			
			MemService memSvc = new MemService();
			String search_state = null;

			try {
				MemVO memVO = (MemVO) req.getSession().getAttribute("memVO");
				String mem_name = req.getParameter("mem_name").trim();
				String mem_addr = req.getParameter("mem_addr").trim();
				Part part = req.getPart("mem_img");
				
				//檢查姓名是否為空
				if ( mem_name==null || mem_name.length()==0 ) {
					dataErrors.add("姓名不可為空!");
					mem_name = memVO.getMem_name();
				}

				//檢查地址是否為空
				if ( mem_addr.length()==0 ) {
					dataErrors.add("地址不可為空!");
					mem_addr = memVO.getMem_addr();
				}
				
				// 檢查是否開啟找房狀態
				try {
					search_state = req.getParameter("search_state").toUpperCase();
				} catch (NullPointerException e) {
					search_state = "OFF";
				}
				
				//檢查照片是否為空
				if ( part.getSize()<=0 ) {
					byte[] mem_img = memVO.getMem_img();
					memVO.setMem_img(mem_img);
				} else {
					InputStream in = part.getInputStream();
					byte[] mem_img = new byte[in.available()];
					in.read(mem_img);
					memVO.setMem_img(mem_img);
					in.close();
				}
								
				memVO.setMem_name(mem_name);
				memVO.setMem_addr(mem_addr);
				memVO.setSearch_state(search_state);
				
				if (!dataErrors.isEmpty()) {
					req.getSession().setAttribute("memVO", memVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher(req.getContextPath()+"/front/member/memdata.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 開始更新資料 ***************************************/
				memSvc.updateMem(memVO);
				req.getSession().setAttribute("memVO", memVO);
				
				/*************************** 更新完成,準備轉交(Send the Success view) ***********/
				String url = "/front/member/memdata.jsp";
				req.setAttribute("url", url);
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
				successView.forward(req, res);	
				
			} catch (Exception e) {
				dataErrors.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/member/memdata.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		// 來自更改密碼的請求
		if ("change_psw".equals(action)) {
			List<String> pswErrors = new LinkedList<String>();
			req.setAttribute("pswErrors", pswErrors);
			
			MemService memSvc = new MemService();
			
			try {
				String psw_ori = req.getParameter("psw_ori");
				String psw_new1 = req.getParameter("psw_new1");
				String psw_new2 = req.getParameter("psw_new2");
				
				MemVO memVO = (MemVO) req.getSession().getAttribute("memVO");
				
				if (psw_ori.equals(memVO.getMem_psw())) {
					if (psw_new1.equals(psw_new2)) {
						memSvc.changePassword(psw_new1, memVO.getMem_no());
						
						/*************************** 更新完成,準備轉交(Send the Success view) ***********/
						String url = "/front/member/mempsw.jsp";
						req.setAttribute("url", url);
						RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交
						successView.forward(req, res);
						
					} else {
						pswErrors.add("兩次的新密碼比對不同!");
					}
				} else {
					pswErrors.add("現在的密碼輸入錯誤!");
				}
				
				if (!pswErrors.isEmpty()) {
					req.setAttribute("pswErrors", pswErrors);
					RequestDispatcher failureView = req.getRequestDispatcher("/front/member/mempsw.jsp");
					failureView.forward(req, res);
					return;
				}
				
			} catch (Exception e) {
				req.setAttribute("pswErrors", pswErrors);
				RequestDispatcher failureView = req.getRequestDispatcher("/front/member/mempsw.jsp");
				failureView.forward(req, res);
			}
		}
		
	}

}
package com.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product.model.*;
import com.mem.model.MemVO;
import com.prdimg.model.*;
import com.prdimg.model.PrdImgService;

@WebServlet("/front/shopping/cart.do")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/text;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		Map<String,String> cartMap = (Map<String,String>) session.getAttribute("cartMap");
		String requestURL = req.getParameter("requestURL");
		String action = req.getParameter("action");
		
		
		if (!action.equals("CHECKOUT")) { // 來自listAllPrd.jsp "購物車" 的請求

			// 刪除購物車中的商品
			if (action.equals("itemRemoved")) {
				String prd_no = req.getParameter("prd_no");
				cartMap.remove(prd_no);
			}
			
			// 新增商品至購物車中
			else if (action.equals("addToCart")) {
				
				// 取得後來新增的商品
				String prd_no = req.getParameter("prd_no");
				String quantity = req.getParameter("quantity");

				if (cartMap == null) {
					cartMap = new HashMap<String,String>();
					cartMap.put(prd_no, quantity);
				} else {
					cartMap.put(prd_no, quantity);	
				}
			}

			// 從購物車按 "清空" 按鈕
			else if (action.equals("cartCleared")) {
				cartMap.clear();
			}

			// 全部完成將cartMap放回session
			session.setAttribute("cartMap", cartMap);
		} 
		
		
		// 從購物車按下 "查看" 準備要進入 Checkout.jsp
		else {
			PrdService prdSvc = new PrdService();
			PrdVO prdVO = null;
			List<PrdVO> cartlist = new ArrayList<PrdVO>();
			MemVO memVO = (MemVO) session.getAttribute("memVO");
			
			if (memVO == null) {
				session.setAttribute("cartMap", cartMap);
				session.setAttribute("location", req.getContextPath() + "/front/shopping/Checkout.jsp"); 
				res.sendRedirect(req.getContextPath() + "/front/login.jsp"); 
				return;
			} else {
			
				for(String prd_no : cartMap.keySet()){
					System.out.println("prd_no: "+prd_no+", quantity: "+cartMap.get(prd_no));
					
					prdVO = prdSvc.getOnByPrdNo(prd_no);
					prdVO.setQuantity(Integer.valueOf(cartMap.get(prd_no)));
					cartlist.add(prdVO);
				}
				
				session.setAttribute("cartlist", cartlist);
				String url = "/front/shopping/Checkout.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
			
			}
		} //從購物車按下 "查看" 準備要進入Checkout.jsp
		
		
	} //doPost結束
}

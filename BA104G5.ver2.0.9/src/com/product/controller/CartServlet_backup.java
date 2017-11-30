package com.product.controller;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

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


public class CartServlet_backup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/text;charset=UTF-8");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		PrdVO aPrdVO = null;

		List<PrdVO> cartlist = (ArrayList<PrdVO>) session.getAttribute("cartlist");
		String requestURL = req.getParameter("requestURL");
		String action = req.getParameter("action");

		if (!action.equals("CHECKOUT")) { // 來自listAllPrd.jsp "購物車" 的請求

			// 刪除購物車中的商品
			if (action.equals("DELETE")) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				cartlist.remove(d);
			}
			// 新增商品至購物車中
			else if (action.equals("addToCart")) {				
				PrdService prdSvc = new PrdService();
				Integer quantity = 1;			
				boolean match = false;
				
				// 取得後來新增的商品
				String prd_no = req.getParameter("prd_no");
				aPrdVO = prdSvc.getOnByPrdNo(prd_no);
				
				if (req.getParameter("quantity") != null) {
					quantity = new Integer(req.getParameter("quantity"));
				} else {
					quantity = 1;
				}

				int newQuantity = 0 ;

				if (cartlist == null) {
					cartlist = new ArrayList<PrdVO>();
					cartlist.add(aPrdVO);
				} else {
					for (int i = 0; i < cartlist.size(); i++) {
						if (aPrdVO.getPrd_no().equals(cartlist.get(i).getPrd_no())) {
							newQuantity = cartlist.get(i).getQuantity() + quantity;
							if(newQuantity>aPrdVO.getPrd_stock()) {
								String errorQuantity = "購買數量大於庫存數量!";
								req.setAttribute("errorQuantity", errorQuantity);
								RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
								failureView.forward(req, res);
								return;
							}
							cartlist.get(i).setQuantity(cartlist.get(i).getQuantity() + quantity);
							match = true;
						}
					}
					if (!match) {
						aPrdVO.setQuantity(quantity);
						cartlist.add(aPrdVO);
					}
						
				}
				if(req.getParameter("isAjax")!=null){
					Integer aaa = aPrdVO.getPrd_price()*newQuantity;
					String totalPrice = aaa.toString();
					res.getWriter().write(totalPrice);
					return;
				}
			}

			else if (action.equals("deleteALL")) {
				cartlist.removeAll(cartlist);
			}
	

			// 全部完成，準備返回來源網頁
			session.setAttribute("cartlist", cartlist);
			RequestDispatcher successView = req.getRequestDispatcher(requestURL);
			successView.forward(req, res);
		}

		// 結帳，計算購物車商品價錢總數
		else if (action.equals("CHECKOUT")) {
			Integer subtotal = new Integer(req.getParameter("subtotal"));
			MemVO memVO = (MemVO) session.getAttribute("memVO");

			if (subtotal == 0) {
				String cartEmpty = "購物車是空的!";
				req.setAttribute("cartEmpty", cartEmpty);
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			} else if (memVO == null) {
				session.setAttribute("subtotal", subtotal);
				session.setAttribute("location", req.getContextPath() + "/front/shopping/Checkout.jsp"); 
				res.sendRedirect(req.getContextPath() + "/front/login.jsp"); 
				return;
			} else {

				session.setAttribute("subtotal", subtotal);
				session.setAttribute("cartlist", cartlist);
				String url = "/front/shopping/Checkout.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
			}
		}



	}

}

package com.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.slr.model.SlrVO;
import com.employee.model.EmployeeVO;

public class SellerFilter implements Filter{

	private FilterConfig config;//宣告
	
	@Override
	public void destroy() {
		config = null;//幹掉它
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
			
		SlrVO slrVO = (SlrVO) session.getAttribute("slrVO");
		EmployeeVO empVO = (EmployeeVO) session.getAttribute("empVO");
		
		if (empVO != null) {
			chain.doFilter(req, res);
		} else if (slrVO == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
		    session.setAttribute("location", req.getRequestURI());       //*工作1 : 同時記下目前位置 , 以便於bklogin.jsp登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
		    res.sendRedirect(req.getContextPath()+"/front/seller/login.jsp");   //*工作2 : 請該user去登入網頁(bklogin.jsp) , 進行登入
		    return;
		} 
		else {
			chain.doFilter(req, res);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;//取值
	}

}

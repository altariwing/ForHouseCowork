package com.mem.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;

import com.mem.model.*;

// 用於訂單管理頁面 讀取商品的第一張圖片
@WebServlet("/front/MemImg")
public class MemImgReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemImgReader() {
        super();
    }
    
    Connection con;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			
			String mem_no2 = req.getParameter("mem_no");
			String mem_no = new String(mem_no2.getBytes("ISO-8859-1"),"UTF-8");
			MemService memSvc = new MemService();
			MemVO memVO = memSvc.getOneByNo(mem_no);
				
				try {
					InputStream in = new ByteArrayInputStream(memVO.getMem_img());
					byte[] buf = new byte[in.available()];
					int len;
					while ((len = in.read(buf)) != -1)
						out.write(buf, 0, len);
					out.close();
					return;
				} catch (IOException e) {
					
				}
//				in.close();
			
				//res.sendError(HttpServletResponse.SC_NOT_FOUND);
//				InputStream in = getServletContext().getResourceAsStream("/images/nopic.jpg");
//				byte[] buf = new byte[in.available()];
//				in.read(buf);
//				out.write(buf);
//				in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			InputStream in = getServletContext().getResourceAsStream("/images/nopic.jpg");
			byte[] buf = new byte[in.available()];
			in.read(buf);
			out.write(buf);
			in.close();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
	
	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G5");
			con = ds.getConnection();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}

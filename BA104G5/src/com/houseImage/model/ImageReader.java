package com.houseImage.model;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageReader extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/jpeg");
		String img_no = req.getPathInfo().substring(1);
		HouseImageJDBCDAO dao = new HouseImageJDBCDAO();
		HouseImageVO houseImageVO = dao.findByPrimaryKey(img_no);
		try {
			byte[] imgByteArray = houseImageVO.getImg();
			res.getOutputStream().write(imgByteArray);
		} catch (Exception e) {
			InputStream fis = req.getServletContext().getResourceAsStream("/images/nopic.jpg");
			byte[] imgByteArray = new byte[fis.available()];
			fis.read(imgByteArray);
			res.getOutputStream().write(imgByteArray);		
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

}

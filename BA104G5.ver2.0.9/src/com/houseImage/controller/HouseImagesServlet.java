package com.houseImage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.houseImage.model.*;


@WebServlet("/HouseImagesServlet")
public class HouseImagesServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	this.doPost(req, res);
	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String action = req.getParameter("action");
		
		if("findByHouseNo".equals(action)){
			HouseImageService imgSvc = new HouseImageService();
			String house_no = req.getParameter("house_no"); 
			List<HouseImageVO> list  = imgSvc.findByHouseNo(house_no);
			req.setAttribute("list", list );
			req.setAttribute("action", action);		
		}
		
		if("insert".equals(action)){
			HouseImageService imgSvc = new HouseImageService();
			List<HouseImageVO> houseImageVOList = new ArrayList<HouseImageVO>();
			
			
		}
		
		
	}

}

package com.breaktime.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.breaktime.model.BreakTimeService;
import com.breaktime.model.BreakTimeVO;


public class BreakTimeServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("btCancel".equals(action)) { 
			
			String brk_no = req.getParameter("brk_no").trim();
			
			BreakTimeService resRecSvc = new BreakTimeService();
			resRecSvc.deletBreakTime(brk_no);
			res.setContentType("text/html; charset=UTF-8");
			String success = "取消休假";
			
			PrintWriter out = res.getWriter();
			out.print(success);
			out.flush();
			out.close();
		}
		
		if("btAdd".equals(action)){
			JSONObject obj;
			JSONArray btlists = new JSONArray();
			try {
				obj = new JSONObject(req.getParameter("btlist").trim());

				JSONArray btlist = obj.getJSONArray("btlist");
				
				for(int i = 0;i <btlist.length() ;i++ ){
					String rtr_no = btlist.getJSONObject(i).getString("rtr_no").trim();
					Date brk_date = java.sql.Date.valueOf(btlist.getJSONObject(i).getString("brk_date").trim());
					String brk_period = btlist.getJSONObject(i).getString("brk_period").trim();
					
					BreakTimeVO btVO = new BreakTimeVO();
					btVO.setRtr_no(rtr_no);
					btVO.setBrk_date(brk_date);
					btVO.setBrk_period(brk_period);
					
					BreakTimeService btSvc = new BreakTimeService();
					String brk_no = btSvc.addBreakTime(btVO).getBrk_no();
					
					JSONObject bt = new JSONObject();
					bt.put("id", brk_no);
					bt.put("start", brk_date+"T"+brk_period.split("-")[0]);
					bt.put("end", brk_date+"T"+brk_period.split("-")[1]);
					bt.put("title", "休假");
					bt.put("color", "#257e4a");
					
					btlists.put(bt);
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.print(btlists);
			out.flush();
			out.close();
		}
		
		if ("btChange".equals(action)) { 
			String brk_no = req.getParameter("brk_no").trim();
			String rtr_no = req.getParameter("rtr_no").trim();
			Date brk_date = java.sql.Date.valueOf(req.getParameter("brk_date").trim());
			String brk_period = req.getParameter("brk_period");

			BreakTimeVO btVO = new BreakTimeVO();
			btVO.setBrk_no(brk_no);
			btVO.setRtr_no(rtr_no);
			btVO.setBrk_date(brk_date);
			btVO.setBrk_period(brk_period);
			
			BreakTimeService btSvc = new BreakTimeService();
			btSvc.updateBreakTime(btVO);
			String success = "更改成功";
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.print(success);
			out.flush();
			out.close();
			
		}
	}
}

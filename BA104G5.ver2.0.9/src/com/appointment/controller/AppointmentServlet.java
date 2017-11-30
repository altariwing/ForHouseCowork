package com.appointment.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

import com.followRTR.model.FRService;
import com.followRTR.model.FRVO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.house.model.*;
import com.hsecoll.model.HseCollService;
import com.mem.model.*;
import com.realestate.model.RealEstateService;
import com.realestate.model.RealEstateVO;
import com.realtor.model.RealtorService;
import com.realtor.model.RealtorVO;
import com.resrec.model.ResRecService;
import com.resrec.model.ResRecVO;


/**
 * Servlet implementation class AppointmentServlet
 */

public class AppointmentServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");

		if ("makeAppointmentByHouseNo".equals(action)) {
			String mem_no = ((MemVO) req.getSession().getAttribute("memVO")).getMem_no();
			String[] house_nos = req.getParameterValues("house_nos");
			List<HouseVO> checkedHouseList = new ArrayList<HouseVO>();
			HouseService houseSvc = new HouseService();
			for (int i = 0; i < house_nos.length; i++) {
				HouseVO houseVO = houseSvc.getOneHouseInfo(house_nos[i]);
				checkedHouseList.add(houseVO);
			}

			HseCollService HesCollSvc = new HseCollService();
			List<HouseVO> HseCollList = HesCollSvc.getAllByMemNoWithHouseInfo(mem_no, house_nos);
			req.getSession().setAttribute("checkedHouseList", checkedHouseList);
			req.getSession().setAttribute("HseCollList", HseCollList);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/front/house/appointment1.jsp");
			dispatcher.forward(req, res);
			return;
		}

		if ("removeFromAppointment".equals(action)) {
			System.out.println("來到了removeFromAppointment");
			String house_no = req.getParameter("house_no");		
			HouseVO houseVO = null;
			List<HouseVO> checkedHouseList = (List<HouseVO>) (req.getSession().getAttribute("checkedHouseList"));
			List<HouseVO> HseCollList = (List<HouseVO>) (req.getSession().getAttribute("HseCollList"));
			for (int i = 0; i < checkedHouseList.size(); i++) {
				if (checkedHouseList.get(i).getHouse_no().equals(house_no)) {			
					houseVO = checkedHouseList.get(i);
					HseCollList.add(houseVO);
					checkedHouseList.remove(i);
				}
			}
			req.getSession().setAttribute("checkedHouseList", checkedHouseList);
			req.getSession().setAttribute("HseCollList", HseCollList);
			res.getWriter().write("移除" + house_no + "成功");
//			RequestDispatcher dispatcher = req.getRequestDispatcher("/front/house/appointment1.jsp");
//			dispatcher.forward(req, res);
//			return;
		}

		if ("addToAppointment".equals(action)) {
			//System.out.println("來到了addToAppointment");
			String house_no = req.getParameter("house_no");
			HouseVO houseVO = null;
			List<HouseVO> checkedHouseList = (List<HouseVO>) req.getSession().getAttribute("checkedHouseList");
			List<HouseVO> HseCollList = (List<HouseVO>) req.getSession().getAttribute("HseCollList");
			for (int i = 0; i < HseCollList.size(); i++) {
				if (HseCollList.get(i).getHouse_no().equals(house_no)) {
					houseVO = HseCollList.get(i);
					checkedHouseList.add(houseVO);
					HseCollList.remove(i);
				}
			}			
			res.getWriter().write("增加" + house_no + "成功");
			req.getSession().setAttribute("checkedHouseList", checkedHouseList);
			req.getSession().setAttribute("HseCollList", HseCollList);
//			RequestDispatcher dispatcher = req.getRequestDispatcher("/front/house/appointment1.jsp");
//			dispatcher.forward(req, res);
//			return;
		}
		
		
		if("checkHouseList".equals(action)){
//System.out.println("來到了checkHouseList");
			FRService frSvc = new FRService();
			String mem_no = req.getParameter("mem_no");
			
			List<FRVO> list = frSvc.findByNumber(mem_no);
			req.getSession().setAttribute("list", list);
		
			String[] house_nos = req.getParameterValues("house_no");
			List<String> house_nosCol = Arrays.asList(house_nos);
			req.getSession().setAttribute("house_nosCol", house_nosCol);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/front/house/appointment2.jsp");
			dispatcher.forward(req,res);
			
		}
		
		if("preEstablich".equals(action)){
			String list = req.getParameter("list");
			Gson gson=  new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

			Type collectionType = new TypeToken<List<ResRecVO>>() {	}.getType();
			List<ResRecVO> resrecvolist =  gson.fromJson(list, collectionType);
			Collections.sort(resrecvolist);
//			for(ResRecVO rrVO:resrecvolist){
//				String rtr_no = rrVO.getRtr_no();
//				String mem_no = rrVO.getMem_no();
//				String house_no = rrVO.getHouse_no();
//				Date resr_date = rrVO.getResr_date();
//				String resr_period = rrVO.getResr_period();
//				System.out.println(rtr_no+","+mem_no+","+house_no+","+resr_date+","+resr_period);
//			}
				
			req.getSession().setAttribute("resrecvolist", resrecvolist);
		}
		
		if("Establich".equals(action)){
			List<ResRecVO> resrecvolist = (List) req.getSession().getAttribute("resrecvolist");
			String resr_states = req.getParameter("resr_states").trim();
			ResRecService rrSvc = new ResRecService(); 
			for(ResRecVO rrVO:resrecvolist){
				rrVO.setResr_states(resr_states);
				rrSvc.addResRec_kuei1(rrVO.getMem_no(),rrVO.getHouse_no(),rrVO.getRtr_no(),rrVO.getResr_states(),rrVO.getResr_date(),rrVO.getResr_period());
				
			}
			req.getSession().removeAttribute("resrecvolist");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/front/index.jsp");
			dispatcher.forward(req,res);
		}
	}
}

package com.house.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import com.house.model.*;
import com.houseImage.model.*;

@MultipartConfig()
public class HouseServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// for 使用搜尋BAR的換頁用

		String action = req.getParameter("action");
		if ("findByKeyword".equals(action)) {
			String keyword = (new String(req.getParameter("para").getBytes("ISO-8859-1"), "UTF-8"));
			System.out.println("搜尋關鍵字為" + keyword);
			Integer whichPage = 0;
			String sortedCondition = req.getParameter("sortedCondition");
			try {
				Integer.valueOf(req.getParameter("whichPage"));
			} catch (Exception e) {
				whichPage = 1;
			}
			HouseService houseSvc = new HouseService();
			List<HouseVO> list = houseSvc.findByKeyword(keyword, sortedCondition);
			if (list.size() == 0) {
				req.setAttribute("warning", "查無資料，請重新查詢");
			}
			req.setAttribute("sortedCondition", sortedCondition);
			req.setAttribute("list", list);
			req.setAttribute("para", keyword);
			req.setAttribute("action", action);
			req.setAttribute("whichPage", whichPage);
			RequestDispatcher locationView = req.getRequestDispatcher("/front/house/house_search_condition.jsp");
			locationView.forward(req, res);

		} else {
			doPost(req, res);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");

		if ("getOneHouseInfo".equals(action) || "getOneHouseInfo_b".equals(action)) {
			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String house_no = req.getParameter("house_no");
				// 有沒有輸入東西
				if (house_no == null || house_no.trim().length() == 0) {
					errorMsgs.add("請輸入房屋編號");
				}
				// 錯誤LIST是否有內容
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failDispatcher = req.getRequestDispatcher("/back/house/house_select_page.jsp");
					failDispatcher.forward(req, res);
					return;
				}
				// 格式是否正確
				if (!house_no.matches("HSE[0-9]{8}")) {
					errorMsgs.add("房屋編號格式不正確");

				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failDispatcher = req.getRequestDispatcher("/back/house/house_select_page.jsp");
					failDispatcher.forward(req, res);
					return;
				}

				// ***********************查資料PART***********************
				HouseService housesvc = new HouseService();
				HouseVO houseVO = housesvc.getOneHouseInfo(house_no);
				if (houseVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failDispatcher = req.getRequestDispatcher("/back/house/house_select_page.jsp");
					failDispatcher.forward(req, res);
					return;
				}

				// ********************如果他跨越了重重困難********************

				if ("getOneHouseInfo_b".equals(action)) {
					String locationOrigin = houseVO.getLocation();

					Pattern p = Pattern.compile("[\u4e00-\u9fa5]*市[\u4e00-\u9fa5]*區");
					Matcher m = p.matcher(locationOrigin);
					String location = "台北市";
					while (m.find()) {
						location = m.group();
					}

					List<HouseVO> subList = housesvc.findByCondition(location);
					int subListSize = subList.size();

					if (subListSize > 3) {

						Set<Integer> randomNums = new HashSet<>();
						while (randomNums.size() < 3) {
							int randomNum = (int) (Math.random() * subListSize) - 1;
							randomNums.add(randomNum);
						}

						int count = 1;
						for (int randomNum : randomNums) {
							HouseVO adHouseVO = subList.get(randomNum);
							String name = "houseVO" + count++;
							req.setAttribute(name, adHouseVO);
						}
					} else {

						subList = housesvc.findByCondition("台北市");
						Set<Integer> randomNums = new HashSet<>();
						while (randomNums.size() < 3) {
							int randomNum = (int) (Math.random() * subListSize) - 1;
							randomNums.add(randomNum);
						}

						int count = 1;
						for (int randomNum : randomNums) {
							HouseVO adHouseVO = subList.get(randomNum);
							String name = "houseVO" + count++;
							req.setAttribute(name, adHouseVO);
						}

					}

					HouseImageService svc = new HouseImageService();
					List<HouseImageVO> list = svc.findByHouseNo(houseVO.getHouse_no());

					req.setAttribute("houseVO", houseVO);
					req.setAttribute("ImageList", list);
					String url = "/front/house/house_info.jsp";
					RequestDispatcher successDispatcher = req.getRequestDispatcher(url);
					successDispatcher.forward(req, res);
				}
				if ("getOneHouseInfo".equals(action)) {
					req.setAttribute("houseVO", houseVO);
					String url = "/back/house/ListOneHouse.jsp";
					RequestDispatcher successDispatcher = req.getRequestDispatcher(url);
					successDispatcher.forward(req, res);
				}

			} catch (Exception e) {
				errorMsgs.add("---查詢失敗,並非格式問題，請再確認是否有錯誤---");
				RequestDispatcher failureView = req.getRequestDispatcher("/back/house/house_select_page.jsp");
				failureView.forward(req, res);
			}
		}

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~修改~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		if ("GetOndHouseInfoForUpdate".equals(action)) {

			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				String house_no = req.getParameter("house_no");
				HouseService houseSvc = new HouseService();
				HouseVO houseVO = houseSvc.getOneHouseInfo(house_no);

				req.setAttribute("houseVO", houseVO);
				String url = "/back/house/update_house.jsp";
				RequestDispatcher successDispatcher = req.getRequestDispatcher(url);
				successDispatcher.forward(req, res);
			} catch (Exception e) {
				System.out.println("讀取欲修改資料失敗，請聯絡開發人員");
			}
		}

		if ("update".equals(action)) {
			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String house_no = req.getParameter("house_no");
			String re_no = req.getParameter("re_no");
			String house_serial_number = req.getParameter("house_serial_number");
			String title = req.getParameter("title");
			String location = req.getParameter("location");
			String house_type = req.getParameter("house_type");
			Integer price = Integer.valueOf(req.getParameter("price"));
			Double total_pings = null;

			try {
				total_pings = new Double(req.getParameter("total_pings"));
			} catch (Exception e) {
				errorMsgs.add("建物總坪數格式輸入錯誤");
			}
			Double main_pings = null;
			try {
				main_pings = new Double(req.getParameter("main_pings"));
			} catch (Exception e) {
				errorMsgs.add("主建物坪數格式輸入錯誤");
			}
			Double amenity_pings = null;
			try {
				amenity_pings = new Double(req.getParameter("amenity_pings"));
			} catch (Exception e) {
				errorMsgs.add("共有建物坪數格式輸入錯誤");
			}
			Double accessory_pings = null;
			try {
				accessory_pings = new Double(req.getParameter("accessory_pings"));
			} catch (Exception e) {
				errorMsgs.add("附屬建物坪數格式輸入錯誤");
			}
			String floor = req.getParameter("floor");
			Double age = null;
			try {
				age = new Double(req.getParameter("age"));
			} catch (Exception e) {
				errorMsgs.add("共有建物坪數格式輸入錯誤");
			}
			String pattern = req.getParameter("pattern");
			String orientation = req.getParameter("orientation");
			String building_materials = req.getParameter("building_materials");
			String parking_space = req.getParameter("parking_space");
			String classification_of_land = req.getParameter("classification_of_land");
			Double land_pings = null;
			try {
				land_pings = new Double(req.getParameter("land_pings"));
			} catch (Exception e) {
				errorMsgs.add("共有建物坪數格式輸入錯誤");
			}
			String data_info = req.getParameter("data_info");

			byte[] main_img = null;
			Part part_main_img = req.getPart("main_img");
			if (part_main_img.getSize() == 0) {
				HouseJDBCDAO dao = new HouseJDBCDAO();
				HouseVO houseVO = dao.findByPrimaryKey(house_no);
				main_img = houseVO.getMain_img();
			} else {
				InputStream fis_main_img = part_main_img.getInputStream();
				main_img = new byte[fis_main_img.available()];
				fis_main_img.read(main_img);
				fis_main_img.close();
			}
			Double lat = null;
			try {
				lat = new Double(req.getParameter("lat"));
			} catch (Exception e) {
				errorMsgs.add("經度格式輸入錯誤");
			}
			Double lng = null;
			try {
				lng = new Double(req.getParameter("lng"));
			} catch (Exception e) {
				errorMsgs.add("緯度格式輸入錯誤");
			}
			String house_states = req.getParameter("house_states");

			Timestamp insert_time = null;
			try {
				insert_time = Timestamp.valueOf(req.getParameter("insert_time"));
			} catch (Exception e) {
				errorMsgs.add("建立時間格式輸入錯誤");
			}

			Timestamp final_update_time = null;
			try {
				final_update_time = Timestamp.valueOf(req.getParameter("final_update_time"));
			} catch (Exception e) {
				errorMsgs.add("最終修改時間輸入錯誤");
			}

			HouseVO houseVO = new HouseVO();
			houseVO.setHouse_no(house_no);
			houseVO.setRe_no(re_no);
			houseVO.setHouse_serial_number(house_serial_number);
			houseVO.setTitle(title);
			houseVO.setLocation(location);
			houseVO.setHouse_type(house_type);
			houseVO.setPrice(price);
			houseVO.setTotal_pings(total_pings);
			houseVO.setMain_pings(main_pings);
			houseVO.setAmenity_pings(amenity_pings);
			houseVO.setAccessory_pings(accessory_pings);
			houseVO.setFloor(floor);
			houseVO.setAge(age);
			houseVO.setPattern(pattern);
			houseVO.setOrientation(orientation);
			houseVO.setBuilding_materials(building_materials);
			houseVO.setParking_space(parking_space);
			houseVO.setClassification_of_land(classification_of_land);
			houseVO.setLand_pings(land_pings);
			houseVO.setData_info(data_info);
			houseVO.setMain_img(main_img);
			houseVO.setLat(lat);
			houseVO.setLng(lng);
			houseVO.setHouse_states(house_states);
			houseVO.setInsert_time(insert_time);
			houseVO.setFinal_update_time(final_update_time);
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("houseVO", houseVO);
				RequestDispatcher failDispatcher = req.getRequestDispatcher("/back/house/update_house.jsp");
				failDispatcher.forward(req, res);
				return;
			}

			// **************開始上傳更新*****************
			HouseService houseSvc = new HouseService();
			houseVO = houseSvc.updateHouse(re_no, house_serial_number, title, location, house_type, price, total_pings,
					main_pings, amenity_pings, accessory_pings, floor, age, pattern, orientation, building_materials,
					parking_space, classification_of_land, land_pings, data_info, main_img, lat, lng, house_states,
					insert_time, final_update_time, house_no);
			// **************************更新成功*********************************
			req.setAttribute("houseVO", houseVO);
			RequestDispatcher successDispatcher = req.getRequestDispatcher("/back/house/ListOneHouse.jsp");
			successDispatcher.forward(req, res);

		}

		if ("insert".equals(action)) {
			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String re_no = req.getParameter("re_no");
			String house_serial_number = req.getParameter("house_serial_number");
			String title = req.getParameter("title");

			String location = req.getParameter("location");
			String house_type = req.getParameter("house_type");
			Integer price = Integer.valueOf(req.getParameter("price"));
			Double total_pings = null;

			try {
				total_pings = new Double(req.getParameter("total_pings"));
			} catch (Exception e) {
				errorMsgs.add("建物總坪數格式輸入錯誤");
			}
			Double main_pings = null;
			try {
				main_pings = new Double(req.getParameter("main_pings"));
			} catch (Exception e) {
				errorMsgs.add("主建物坪數格式輸入錯誤");
			}
			Double amenity_pings = null;
			try {
				amenity_pings = new Double(req.getParameter("amenity_pings"));
			} catch (Exception e) {
				errorMsgs.add("共有建物坪數格式輸入錯誤");
			}
			Double accessory_pings = null;
			try {
				accessory_pings = new Double(req.getParameter("accessory_pings"));
			} catch (Exception e) {
				errorMsgs.add("附屬建物坪數格式輸入錯誤");
			}
			String floor = req.getParameter("floor");
			Double age = null;
			try {
				age = new Double(req.getParameter("age"));
			} catch (Exception e) {
				errorMsgs.add("共有建物坪數格式輸入錯誤");
			}
			String pattern = req.getParameter("pattern");
			String orientation = req.getParameter("orientation");
			String building_materials = req.getParameter("building_materials");
			System.out.println(building_materials);
			String parking_space = req.getParameter("parking_space");
			String classification_of_land = req.getParameter("classification_of_land");
			Double land_pings = null;
			try {
				land_pings = new Double(req.getParameter("land_pings"));
			} catch (Exception e) {
				errorMsgs.add("共有建物坪數格式輸入錯誤");
			}
			String data_info = req.getParameter("data_info");

			byte[] main_img = null;
			Part part_main_img = req.getPart("main_img");
			if (part_main_img.getSize() != 0) {
				// HouseJDBCDAO dao = new HouseJDBCDAO();
				// HouseVO houseVO = dao.findByPrimaryKey(house_no);
				// main_img = houseVO.getMain_img();
				// System.out.println("yayayayayayaya沒有讀到圖片");
				InputStream fis_main_img = part_main_img.getInputStream();
				main_img = new byte[fis_main_img.available()];
				fis_main_img.read(main_img);
				fis_main_img.close();
			} else {
			}
			Double lat = null;
			try {
				lat = new Double(req.getParameter("lat"));
			} catch (Exception e) {
				errorMsgs.add("經度格式輸入錯誤");
			}
			Double lng = null;
			try {
				lng = new Double(req.getParameter("lng"));
			} catch (Exception e) {
				errorMsgs.add("緯度格式輸入錯誤");
			}
			String house_states = req.getParameter("house_states");

			// Timestamp insert_time = null;
			// try {
			// insert_time = Timestamp.valueOf(req.getParameter("insert_time"));
			// System.out.println("a" + insert_time);
			// } catch (Exception e) {
			// errorMsgs.add("建立時間格式輸入錯誤");
			// }
			//
			// Timestamp final_update_time = null;
			// try {
			// final_update_time =
			// Timestamp.valueOf(req.getParameter("final_update_time"));
			// System.out.println("b" + final_update_time);
			// } catch (Exception e) {
			// errorMsgs.add("最終修改時間輸入錯誤");
			// }

			HouseVO houseVO = new HouseVO();
			// houseVO.setHouse_no(house_no);
			houseVO.setRe_no(re_no);
			houseVO.setHouse_serial_number(house_serial_number);
			houseVO.setTitle(title);
			houseVO.setLocation(location);
			houseVO.setHouse_type(house_type);
			houseVO.setPrice(price);
			houseVO.setTotal_pings(total_pings);
			houseVO.setMain_pings(main_pings);
			houseVO.setAmenity_pings(amenity_pings);
			houseVO.setAccessory_pings(accessory_pings);
			houseVO.setFloor(floor);
			houseVO.setAge(age);
			houseVO.setPattern(pattern);
			houseVO.setOrientation(orientation);
			houseVO.setBuilding_materials(building_materials);
			houseVO.setParking_space(parking_space);
			houseVO.setClassification_of_land(classification_of_land);
			houseVO.setLand_pings(land_pings);
			houseVO.setData_info(data_info);
			houseVO.setMain_img(main_img);
			houseVO.setLat(lat);
			houseVO.setLng(lng);
			houseVO.setHouse_states(house_states);
			// houseVO.setInsert_time(insert_time);
			// houseVO.setFinal_update_time(final_update_time);
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("houseVO", houseVO);
				RequestDispatcher failDispatcher = req.getRequestDispatcher("/back/house/AddHouse.jsp");
				failDispatcher.forward(req, res);
				return;
			}

			// **************開始上傳更新*****************
			HouseService houseSvc = new HouseService();
			houseVO = houseSvc.addHouse(re_no, house_serial_number, title, location, house_type, price, total_pings,
					main_pings, amenity_pings, accessory_pings, floor, age, pattern, orientation, building_materials,
					parking_space, classification_of_land, land_pings, data_info, main_img, lat, lng, house_states);

			// **************************更新成功*********************************
			System.out.println("更新成功");
			req.setAttribute("houseVO", houseVO);
			RequestDispatcher successDispatcher = req.getRequestDispatcher("/back/house/ListOneHouse.jsp");
			successDispatcher.forward(req, res);

		}

		if ("delete".equals(action)) {
			List<String> errorMsgs = new ArrayList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String house_no = req.getParameter("house_no");
			System.out.println(house_no);
			try {
				// 有沒有輸入東西
				if (house_no == null || house_no.trim().length() == 0) {
					errorMsgs.add("查無資料，請重新確認");
				}
				// 錯誤LIST是否有內容
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failDispatcher = req.getRequestDispatcher("/back/house/house_select_page.jsp");
					failDispatcher.forward(req, res);
					return;
				}
				HouseService houseSvc = new HouseService();
				houseSvc.delete(house_no);

				String url = "/back/house/ListALLHouse.jsp";
				RequestDispatcher successDispatcher = req.getRequestDispatcher(url);
				successDispatcher.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("---查詢失敗,並非格式問題，請再確認是否有錯誤---");
				RequestDispatcher failureView = req.getRequestDispatcher("/back/house/ListALLHouse.jsp");
				failureView.forward(req, res);
			}
		}

		if (action.equals("findByCondition_a")) {
			String location = (new String(req.getParameter("para").getBytes("ISO-8859-1"), "UTF-8"));
			String sortedCondition = "";
			Integer whichPage = 0;
			try {
				whichPage = Integer.valueOf(req.getParameter("whichPage"));
			} catch (Exception e) {
				whichPage = 1;
			}
			if (req.getParameter("sortedCondition") == null) {

				HouseService houseSvc = new HouseService();
				List<HouseVO> list = houseSvc.findByCondition(location);

				if (list.size() == 0) {
					req.setAttribute("warning", "查無資料，請重新查詢");
				}
				req.setAttribute("list", list);
				req.setAttribute("action", action);
				req.setAttribute("para", location);
				req.setAttribute("whichPage", whichPage);
				RequestDispatcher locationView = req.getRequestDispatcher("/front/house/house_search_condition.jsp");
				locationView.forward(req, res);
			} else {
				sortedCondition = req.getParameter("sortedCondition");
				HouseService houseSvc = new HouseService();
				List<HouseVO> list = houseSvc.findByCondition(location, sortedCondition);

				if (list.size() == 0) {
					req.setAttribute("warning", "查無資料，請重新查詢");
				}
				req.setAttribute("sortedCondition", sortedCondition);
				req.setAttribute("list", list);
				req.setAttribute("action", action);
				req.setAttribute("para", location);
				req.setAttribute("whichPage", whichPage);
				RequestDispatcher locationView = req.getRequestDispatcher("/front/house/house_search_condition.jsp");
				locationView.forward(req, res);
			}
		}

		if (action.equals("findByCondition_b")) {
			String price = req.getParameter("para");
			Integer whichPage = 0;
			String sortedCondition = "";
			try {
				whichPage = Integer.valueOf(req.getParameter("whichPage"));
			} catch (Exception e) {
				whichPage = 1;
			}
			String[] result = price.split("_");
			Integer price_str = Integer.valueOf(result[0]);
			Integer price_end = Integer.valueOf(result[1]);
			HouseService houseSvc = new HouseService();

			if (req.getParameter("sortedCondition") == null) {

				List<HouseVO> list = houseSvc.findByPrice(price_str, price_end);
				if (list.size() == 0) {
					req.setAttribute("warning", "查無資料，請重新查詢");
				}
				req.setAttribute("whichPage", whichPage);
				req.setAttribute("list", list);
				req.setAttribute("action", action);
				req.setAttribute("para", price);
				RequestDispatcher locationView = req.getRequestDispatcher("/front/house/house_search_condition.jsp");
				locationView.forward(req, res);
			} else {
				sortedCondition = req.getParameter("sortedCondition");
				List<HouseVO> list = houseSvc.findByPrice(price_str, price_end, sortedCondition);
				if (list.size() == 0) {
					req.setAttribute("warning", "查無資料，請重新查詢");
				}
				req.setAttribute("sortedCondition", sortedCondition);
				req.setAttribute("whichPage", whichPage);
				req.setAttribute("list", list);
				req.setAttribute("action", action);
				req.setAttribute("para", price);
				RequestDispatcher locationView = req.getRequestDispatcher("/front/house/house_search_condition.jsp");
				locationView.forward(req, res);
			}
		}
		if (action.equals("findByCondition_c")) {
			Integer roomCount = Integer.valueOf(req.getParameter("para"));
			Integer whichPage = 0;
			String sortedCondition = "";
			try {
				whichPage = Integer.valueOf(req.getParameter("whichPage"));
			} catch (Exception e) {
				whichPage = 1;
			}
			HouseService houseSvc = new HouseService();
			if (req.getParameter("sortedCondition") == null) {

				List<HouseVO> list = houseSvc.findByPattern(roomCount);
				if (list.size() == 0) {
					req.setAttribute("warning", "查無資料，請重新查詢");
				}
				req.setAttribute("whichPage", whichPage);
				req.setAttribute("list", list);
				req.setAttribute("action", action);
				req.setAttribute("para", roomCount);
				RequestDispatcher locationView = req.getRequestDispatcher("/front/house/house_search_condition.jsp");
				locationView.forward(req, res);
			} else {
				sortedCondition = req.getParameter("sortedCondition");
				List<HouseVO> list = houseSvc.findByPattern(roomCount, sortedCondition);
				if (list.size() == 0) {
					req.setAttribute("warning", "查無資料，請重新查詢");
				}
				req.setAttribute("sortedCondition", sortedCondition);
				req.setAttribute("whichPage", whichPage);
				req.setAttribute("list", list);
				req.setAttribute("action", action);
				req.setAttribute("para", roomCount);
				RequestDispatcher locationView = req.getRequestDispatcher("/front/house/house_search_condition.jsp");
				locationView.forward(req, res);
			}
		}

		if (action.equals("findByCondition_d")) {
			String houseType = (new String(req.getParameter("para").getBytes("ISO-8859-1"), "UTF-8"));
			Integer whichPage = 0;
			String sortedCondition = "";
			try {
				Integer.valueOf(req.getParameter("whichPage"));
			} catch (Exception e) {
				whichPage = 1;
			}
			HouseService houseSvc = new HouseService();
			if (req.getParameter("sortedCondition") == null) {
				List<HouseVO> list = houseSvc.findByHouseType(houseType);
				if (list.size() == 0) {
					req.setAttribute("warning", "查無資料，請重新查詢");
				}
				req.setAttribute("list", list);
				req.setAttribute("para", houseType);
				req.setAttribute("action", action);
				req.setAttribute("whichPage", whichPage);
				RequestDispatcher locationView = req.getRequestDispatcher("/front/house/house_search_condition.jsp");
				locationView.forward(req, res);
			} else {
				sortedCondition = req.getParameter("sortedCondition");
				List<HouseVO> list = houseSvc.findByHouseType(houseType, sortedCondition);
				if (list.size() == 0) {
					req.setAttribute("warning", "查無資料，請重新查詢");
				}
				req.setAttribute("sortedCondition", sortedCondition);
				req.setAttribute("list", list);
				req.setAttribute("para", houseType);
				req.setAttribute("action", action);
				req.setAttribute("whichPage", whichPage);
				RequestDispatcher locationView = req.getRequestDispatcher("/front/house/house_search_condition.jsp");
				locationView.forward(req, res);
			}
		}

		if (action.equals("findByCondition_e")) {
			String totalPings = req.getParameter("para");
			Integer whichPage = 0;
			String sortedCondition = "";
			try {
				whichPage = Integer.valueOf(req.getParameter("whichPage"));
			} catch (Exception e) {
				whichPage = 1;
			}
			System.out.println(whichPage);
			String[] result = totalPings.split("_");
			Double total_pings_str = Double.valueOf(result[0]);
			Double total_pings_end = Double.valueOf(result[1]);
			HouseService houseSvc = new HouseService();
			if (req.getParameter("sortedCondition") == null) {

				List<HouseVO> list = houseSvc.findByTotalPings(total_pings_str, total_pings_end);
				if (list.size() == 0) {
					req.setAttribute("warning", "查無資料，請重新查詢");
				}
				req.setAttribute("whichPage", whichPage);
				req.setAttribute("list", list);
				req.setAttribute("action", action);
				req.setAttribute("para", totalPings);
				RequestDispatcher locationView = req.getRequestDispatcher("/front/house/house_search_condition.jsp");
				locationView.forward(req, res);
			} else {
				sortedCondition = req.getParameter("sortedCondition");
				List<HouseVO> list = houseSvc.findByTotalPings(total_pings_str, total_pings_end, sortedCondition);
				if (list.size() == 0) {
					req.setAttribute("warning", "查無資料，請重新查詢");
				}
				req.setAttribute("sortedCondition", sortedCondition);
				req.setAttribute("whichPage", whichPage);
				req.setAttribute("list", list);
				req.setAttribute("action", action);
				req.setAttribute("para", totalPings);
				RequestDispatcher locationView = req.getRequestDispatcher("/front/house/house_search_condition.jsp");
				locationView.forward(req, res);
			}
		}

		if ("getAll".equals(action)) {
			Integer whichPage = 0;
			try {
				whichPage = Integer.valueOf(req.getParameter("whichPage"));
			} catch (Exception e) {
				whichPage = 1;
			}
			HouseService houseSvc = new HouseService();
			String sortedCondition = "";

			if (req.getParameter("sortedCondition") == null) {

				List<HouseVO> list = houseSvc.getAll();
				req.setAttribute("whichPage", whichPage);
				req.setAttribute("list", list);
				req.setAttribute("action", action);
				req.getSession().setAttribute("sortedCondition", "house_no");
				RequestDispatcher locationView = req.getRequestDispatcher("/front/house/house_search_condition.jsp");
				locationView.forward(req, res);
			} else {
				sortedCondition = req.getParameter("sortedCondition");
				List<HouseVO> list = houseSvc.getAll(sortedCondition);
				req.setAttribute("sortedCondition", sortedCondition);
				req.setAttribute("whichPage", whichPage);
				req.setAttribute("list", list);
				req.setAttribute("action", action);
				RequestDispatcher locationView = req.getRequestDispatcher("/front/house/house_search_condition.jsp");
				locationView.forward(req, res);
			}
		}

		if ("findBySeveralConds".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.將輸入資料轉為Map **********************************/
			// 採用Map<String,String[]> getParameterMap()的方法
			// 注意:an immutable java.util.Map
			// Map<String, String[]> map = req.getParameterMap();
			HttpSession session = req.getSession();
			Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
			String sortedCondition = req.getParameter("sortedCondition");

			if (req.getParameter("whichPage") == null && sortedCondition == null) {
				HashMap<String, String[]> map1 = (HashMap<String, String[]>) req.getParameterMap();
				HashMap<String, String[]> map2 = new HashMap<String, String[]>();
				map2 = (HashMap<String, String[]>) map1.clone();
				session.setAttribute("map", map2);
				map = (HashMap<String, String[]>) req.getParameterMap();
			}

			/*************************** 2.開始複合查詢 ***************************************/
			HouseService houseSvc = new HouseService();
			List<HouseVO> list = null;
			System.out.println(sortedCondition);
			if (req.getParameter("sortedCondition") != null) {
				sortedCondition = req.getParameter("sortedCondition");
				list = houseSvc.findBySeveralConds(map, sortedCondition);
				req.setAttribute("sortedCondition", sortedCondition);
			} else {
				list = houseSvc.findBySeveralConds(map);
			}

			/***************************
			 * 3.查詢完成,準備轉交(Send the Success view)
			 ************/
			req.setAttribute("list", list); // 資料庫取出的list物件,存入request
			req.setAttribute("action", action);
			RequestDispatcher successView = req.getRequestDispatcher("/front/house/house_search_condition.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
			successView.forward(req, res);

			/*************************** 其他可能的錯誤處理 **********************************/

		}

		if ("findByKeyword".equals(action)) {
			String keyword = req.getParameter("para");
			String sortedCondition = req.getParameter("sortedCondition");
			try {
				System.out.println("搜尋關鍵字為" + keyword);
				Integer whichPage = 0;
				try {
					Integer.valueOf(req.getParameter("whichPage"));
				} catch (Exception e) {
					whichPage = 1;
				}
				HouseService houseSvc = new HouseService();
				List<HouseVO> list = houseSvc.findByKeyword(keyword, sortedCondition);
				if (list.size() == 0) {
					req.setAttribute("warning", "查無資料，請重新查詢");
				}
				req.setAttribute("sortedCondition", sortedCondition);
				req.setAttribute("list", list);
				req.setAttribute("para", keyword);
				req.setAttribute("action", action);
				req.setAttribute("whichPage", whichPage);
				RequestDispatcher locationView = req.getRequestDispatcher("/front/house/house_search_condition.jsp");
				locationView.forward(req, res);
			} catch (Exception e) {
				req.setAttribute("warning", "查無資料，請重新查詢");
				req.setAttribute("sortedCondition", sortedCondition);
				req.setAttribute("list", new ArrayList<HouseVO>());
				req.setAttribute("para", keyword);
				req.setAttribute("action", action);
				req.setAttribute("whichPage", 1);
				RequestDispatcher locationView = req.getRequestDispatcher("/front/house/house_search_condition.jsp");
				locationView.forward(req, res);
			}

		}

	}
	// doPost結束
}
// servlet結束

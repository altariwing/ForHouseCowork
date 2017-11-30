package com.realtor.controller;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mem.model.MemService;
import com.mem.model.MemVO;
import com.realestate.model.RealEstateService;
import com.realestate.model.RealEstateVO;
import com.realtor.model.RealtorService;
import com.realtor.model.RealtorVO;
import com.tool.controller.FPwMailService;
import com.tool.controller.GetURLPic;

@SuppressWarnings("serial")
public class RealtorServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				System.out.println("realtor_getOne_For_Display_try_in");
				/******************************
				 * 1.接收請求參數 - 輸入格式的錯誤處理
				 **********************/
				String rtr_no = req.getParameter("rtr_no");
				if (rtr_no == null || (rtr_no.trim()).length() == 0) {
					errorMsgs.add("請輸入房仲編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/realtor/listAllRealtor.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				RealtorService realtorSvc = new RealtorService();
				RealtorVO realtorVO = realtorSvc.getOne(rtr_no);
				if (rtr_no == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/realtor/listAllRealtor.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/******************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("realtorVO", realtorVO);// 從資料庫取的realtorVO物件，存入req
				String url = "/realtor/listOneRealtor.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/realtor/listAllRealtor.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllRealtor.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				System.out.println("realtor_getOne_For_Update");
				/*************************** 1.接收請求參數 ****************************************/
				String rtr_no = req.getParameter("rtr_no");

				/*************************** 2.開始查詢資料 ****************************************/
				RealtorService realtorSvc = new RealtorService();
				RealtorVO realtorVO = realtorSvc.getOne(rtr_no);
				if (realtorVO == null) {
					errorMsgs.add("查無此房仲");
				}

				/********************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 *************/
				req.setAttribute("realtorVO", realtorVO);
				String url = "/realtor/update_realtor_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交給update_realtor_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/realtor/listAllRealtor.jsp");
				failureView.forward(req, res);
			}
		}

		// // 房仲複合查詢
		// if ("listRealtor_ByCompositeQuery".equals(action)) {
		//
		// List<String> errorMsgs = new LinkedList<String>();
		// req.setAttribute("errorMsgs", errorMsgs);
		//
		// try {
		// /*************************** 1.將輸入資料轉為Map
		// **********************************/
		// // 採用Map<String,String[]> getParameterMap()的方法
		// // 注意:an immutable java.util.Map
		// // Map<String, String[]> map = req.getParameterMap();
		// // HttpSession session = req.getSession();
		// // Map<String, String[]> map = (Map<String,
		// // String[]>)session.getAttribute("map");
		// // if (req.getParameter("whichPage") == null){
		// // HashMap<String, String[]> map1 = (HashMap<String,
		// // String[]>)req.getParameterMap();
		// // HashMap<String, String[]> map2 = new HashMap<String,
		// // String[]>();
		// // map2 = (HashMap<String, String[]>)map1.clone();
		// // session.setAttribute("map",map2);
		// // map = (HashMap<String, String[]>)req.getParameterMap();
		// // }
		// /*************************** 2.開始複合查詢
		// ***************************************/
		// Map<String, String[]> map = new HashMap<String, String[]>();
		//
		//// String rtr_area = req.getParameter("rtr_area").trim();
		//// rtr_area = new String(rtr_area.getBytes("ISO-8859-1"),"UTF-8");
		//// System.out.println(rtr_area);
		//// map.put("rtr_area", new String[]{rtr_area});
		//
		// String re_no = req.getParameter("re_no").trim();
		// System.out.println(re_no);
		// re_no = new String(re_no.getBytes("ISO-8859-1"),"UTF-8");
		// map.put("re_no", new String[]{re_no});
		// //如果要模糊搜尋就在這邊設定同樣的parameter值
		//// map.put("rtr_no", new String[]{rtrNo});
		//// map.put("rtr_area", new String[]{rtrNo});
		//// map.put("rtr_area", new String[]{rtrNo});
		//// map.put("rtr_area", new String[]{rtrNo});
		// RealtorService realtorSvc = new RealtorService();
		// List<RealtorVO> list = realtorSvc.getAll(map);
		// /***************************
		// * 3.查詢完成,準備轉交(Send the Success view)
		// ************/
		// String whichPage = req.getParameter("whichPage");
		// req.setAttribute("whichPage", whichPage);
		// req.setAttribute("list", list);
		// RequestDispatcher successView =
		// req.getRequestDispatcher("/front/realtor/realtor_search.jsp");
		// successView.forward(req, res);
		//
		// /*************************** 其他可能的錯誤處理
		// **********************************/
		// } catch (Exception e) {
		// errorMsgs.add(e.getMessage());
		// RequestDispatcher failureView =
		// req.getRequestDispatcher("/front/realtor/try.jsp");
		// failureView.forward(req, res);
		// }
		// }

		// //房仲查詢
		// if ("query".equals(action)) {
		//
		// List<String> errorMsgs = new LinkedList<String>();
		// req.setAttribute("errorMsgs", errorMsgs);

		// try {
		// // 採用Map<String,String[]> getParameterMap()的方法
		// // 注意:an immutable java.util.Map
		// //Map<String, String[]> map = req.getParameterMap();
		// HttpSession session = req.getSession();
		// Map<String, String[]> map = (Map<String, String[]>)
		// session.getAttribute("map");
		// if (req.getParameter("whichPage") == null) {
		// HashMap<String, String[]> map1 = (HashMap<String, String[]>)
		// req.getParameterMap();
		// HashMap<String, String[]> map2 = new HashMap<String, String[]>();
		// map2 = (HashMap<String, String[]>) map1.clone();
		// session.setAttribute("map", map2);
		// map = (HashMap<String, String[]>) req.getParameterMap();
		// }
		// RealtorService realtorSvc = new RealtorService();
		// RealEstateService realestateSvc = new RealEstateService();
		// List<RealtorVO> list = realtorSvc.getAll(map);
		// List<RealEstateVO> estatelist = realestateSvc.getAll();

		// /***************************
		// * 3.查詢完成,準備轉交(Send the Success view)
		// ************/
		// req.setAttribute("list", list);
		// req.setAttribute("estatelist", estatelist);
		// RequestDispatcher successView =
		// req.getRequestDispatcher("/front/realtor/realtor_search.jsp");
		// successView.forward(req, res);

		// /*************************** 其他可能的錯誤處理
		// **********************************/
		// } catch (Exception e) {
		// errorMsgs.add(e.getMessage());
		// RequestDispatcher failureView =
		// req.getRequestDispatcher("/front/realtor/try.jsp");
		// failureView.forward(req, res);
		// }
		// }

		// if ("query".equals(action)) {
		// System.out.println(action);
		// List<String> errorMsgs = new LinkedList<String>();
		// req.setAttribute("errorMsgs", errorMsgs);
		//
		// try {
		// // 採用Map<String,String[]> getParameterMap()的方法
		// // 注意:an immutable java.util.Map
		// // Map<String, String[]> map = req.getParameterMap();
		// HttpSession session = req.getSession();
		// Map<String, String[]> map = (Map<String, String[]>)
		// session.getAttribute("map");
		// if (req.getParameter("whichPage") == null) {
		// HashMap<String, String[]> map1 = (HashMap<String, String[]>)
		// req.getParameterMap();
		// HashMap<String, String[]> map2 = new HashMap<String, String[]>();
		// map2 = (HashMap<String, String[]>) map1.clone();
		// session.setAttribute("map", map2);
		// map = (HashMap<String, String[]>) req.getParameterMap();
		// }
		// RealtorService realtorSvc = new RealtorService();
		// RealEstateService realestateSvc = new RealEstateService();
		// List<RealtorVO> list = realtorSvc.getAll(map);
		// List<RealEstateVO> estatelist = realestateSvc.getAll();
		//
		// /**************************** 3.查詢完成,準備轉交(Send the Success
		// view)************/
		// req.setAttribute("list", list);
		// req.setAttribute("estatelist", estatelist);
		// RequestDispatcher successView =
		// req.getRequestDispatcher("/front/realtor/realtor_search.jsp");
		// successView.forward(req, res);
		//
		// /**************************** 其他可能的錯誤處理
		// **********************************/
		// } catch (Exception e) {
		// errorMsgs.add(e.getMessage());
		// RequestDispatcher failureView =
		// req.getRequestDispatcher("/front/realtor/realtor_search.jsp");
		// failureView.forward(req, res);
		// }
		// }

		// if ("listQueryB".equals(action)) {
		//
		// List<String> errorMsgs = new LinkedList<String>();
		// req.setAttribute("errorMsgs", errorMsgs);
		// try {
		//
		// Map<String, String[]> map = new HashMap<String, String[]>();
		//
		// // String RTR_AREA = req.getParameter("RTR_AREA").trim();
		// // System.out.println(RTR_AREA);
		// // RTR_AREA = new String(RTR_AREA.getBytes("ISO-8859-1"),
		// // "UTF-8");
		// // map.put("RTR_AREA", new String[] {RTR_AREA});
		// String RE_NO = req.getParameter("RE_NO").trim();
		// RE_NO = new String(RE_NO.getBytes("ISO-8859-1"), "UTF-8");
		// map.put("RE_NO", new String[] { RE_NO });
		//
		// RealtorService realtorSvc = new RealtorService();
		// List<RealtorVO> list = realtorSvc.getAll(map);
		// /**************************** 3.查詢完成,準備轉交(Send the Success view)
		// ************/
		// String whichPage = req.getParameter("whichPage");
		// req.setAttribute("whichPage", whichPage);
		// req.setAttribute("list", list);
		// RequestDispatcher successView =
		// req.getRequestDispatcher("/front/realtor/realtor_search.jsp");
		// successView.forward(req, res);
		//
		// /**************************** 其他可能的錯誤處理
		// **********************************/
		// } catch (Exception e) {
		// errorMsgs.add(e.getMessage());
		// RequestDispatcher failureView =
		// req.getRequestDispatcher("/front/realtor/realtor_search.jsp");
		// failureView.forward(req, res);
		// }
		// }

		// 來自房仲登入的請求
		if ("realtorLogin".equals(action)) {

			String loginError = "";
			req.setAttribute("loginError", loginError);

			String rtr_id = req.getParameter("rtr_id").trim().toLowerCase();
			String rtr_psw = req.getParameter("rtr_psw").trim();

			Enumeration names = req.getParameterNames();
			while (names.hasMoreElements()) {
				System.out.println("realtorLogin: " + names.nextElement());
			}

			RealtorService realtorSvc = new RealtorService();
			RealtorVO realtorVO = null;

			try {
				realtorVO = realtorSvc.findById(rtr_id);
				if (realtorVO.getRtr_state().equals("OFF")) {
					loginError = "你的帳號尚未啟用或已被鎖定";
				} else if (!rtr_psw.equals(realtorVO.getRtr_psw())) {
					System.out.println(realtorSvc);
					loginError = "你的帳號或密碼無效!";
				}
			} catch (NullPointerException e) {
				loginError = "你的帳號或密碼無效!";
			} catch (Exception ignored) {
			}

			if (loginError.length() > 0) {
				req.setAttribute("loginError", loginError);
				RequestDispatcher failureView = req.getRequestDispatcher("realtor_login.jsp");
				failureView.forward(req, res);
				return;
			}

			HttpSession session = req.getSession(); // 檢查到這表示帳號密碼沒問題
			session.setAttribute("realtorVO", realtorVO); // 在session內做已經登入過的標識
			RequestDispatcher successView = req.getRequestDispatcher("realtor_center.jsp");
			successView.forward(req, res);// 重導至會員中心
		} // 來自房仲登入的請求結束

		// 房仲複合查詢
		if ("listQueryB".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/***************************
				 * 1.將輸入資料轉為Map
				 **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				// Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = (HashMap<String, String[]>) req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>) map1.clone();
					session.setAttribute("map", map2);
					map = (HashMap<String, String[]>) req.getParameterMap();
				}
				/***************************
				 * 2.開始複合查詢
				 ***************************************/
				RealtorService realtorSvc = new RealtorService();
				List<RealtorVO> list = realtorSvc.getAll(map);

				RealEstateService realestateSvc = new RealEstateService();
				List<RealEstateVO> estatelist = realestateSvc.getAll();
				List<RealtorVO> list2 = realtorSvc.getAll();

				/***************************
				 * 3.查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("list", list);
				req.setAttribute("list2", list2);
				req.setAttribute("estatelist", estatelist);
				// 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front/realtor/realtor_search.jsp");
				successView.forward(req, res);

				/***************************
				 * 其他可能的錯誤處理
				 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/realtor/realtor_search.jsp");
				failureView.forward(req, res);
			}
		} // 房仲複合查詢結束

		// 房仲查詢關鍵字
		if ("findBykeyword".equals(action)) {
			String keyword = req.getParameter("keyword");
			System.out.println("關建字: " + keyword);
			Integer whichPage = 0;
			try {
				Integer.valueOf(req.getParameter("whichPage"));
			} catch (Exception e) {
				whichPage = 1;
			}

			RealtorService realtorSvc = new RealtorService();
			List<RealtorVO> list = realtorSvc.findByKeyword(keyword);
			if (list.size() == 0) {
				req.setAttribute("警告", "查無資料， 請重新查詢");
			}

			req.setAttribute("list", list);
			req.setAttribute("keyword", keyword);
			req.setAttribute("action", action);
			req.setAttribute("whichPage", whichPage);
			RequestDispatcher successView = req.getRequestDispatcher("/front/realtor/realtor_search.jsp");
			successView.forward(req, res);
		} // 房仲查詢關鍵字結束

		// 房仲查找會員找房狀態Map
		if ("memOpen".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				System.out.println("房仲查找會員找房狀態Map");

				/***************************
				 * 1.將輸入資料轉為Map
				 **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				// Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
				if (req.getParameter("whichPage") == null) {
					HashMap<String, String[]> map1 = (HashMap<String, String[]>) req.getParameterMap();
					HashMap<String, String[]> map2 = new HashMap<String, String[]>();
					map2 = (HashMap<String, String[]>) map1.clone();
					session.setAttribute("map", map2);
					map = (HashMap<String, String[]>) req.getParameterMap();
				}
				/***************************
				 * 2.開始複合查詢
				 ***************************************/
				MemService memSvc = new MemService();
				List<MemVO> list = memSvc.getOpenMap(map);
				/****************************
				 * 查詢完成,準備轉交(Send the Success view)
				 ************/
				req.setAttribute("list", list);
				// 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/front/realtor/memOpen.jsp");
				successView.forward(req, res);

				/**************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/realtor/memOpen.jsp");
				failureView.forward(req, res);
			}
		} // 房仲查找會員找房狀態Map結束

		// 房仲忘記密碼
		if ("rtrForgot".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Enumeration names = req.getParameterNames();
				while (names.hasMoreElements()) {
					System.out.println(names.nextElement());
				}

				String rtr_id = req.getParameter("rtr_id");
				if (rtr_id == null || (rtr_id.trim()).length() == 0) {
					errorMsgs.add("email帳號請勿空白");
				}
				String rtr_name = req.getParameter("rtr_name");
				if (rtr_name == null || (rtr_name.trim()).length() == 0) {
					errorMsgs.add("帳號姓名請勿空白");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/front/realtor/realtor_forgot.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				RealtorService realtorSvc = new RealtorService();
				List<RealtorVO> list = realtorSvc.getAll();
				for (RealtorVO rtrVO : list) {
					if ((rtrVO.getRtr_id()).equals(rtr_id) && (rtrVO.getRtr_name()).equals(rtr_name)) {
						String pw = "";
						int a = 0;
						for (int i = 0; i < 4; i++) {
							a = (int) Math.floor(Math.random() * 10);
							pw += a + "";
						}
						RealtorService rtrSvc1 = new RealtorService();
						rtrSvc1.update(rtrVO.getRtr_no(), rtrVO.getRtr_name(), rtrVO.getRtr_photo(),
								rtrVO.getRtr_intro(), pw);

						System.out.println(pw);
						System.out.println((rtrVO.getRtr_id()).equals(rtr_id));
						System.out.println((rtrVO.getRtr_name()).equals(rtr_name));

						FPwMailService ms = new FPwMailService();
						try {
							ms.sendPassword(rtrVO.getRtr_name(), pw, "eatkaikai@gmail.com");// 信箱更換處
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						String url = "/front/realtor/realtor_login.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						return;
					}
				}

				res.sendRedirect(req.getContextPath() + "/front/realtor/realtor_forgot.jsp");
				return;

				// 錯誤處理
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front/realtor/realtor_forgot.jsp");
				failureView.forward(req, res);
			}
		} // 房仲忘記密碼結束

		// 房仲FB登入
		if ("FBLogin".equals(action)) {
			String rtr_area = req.getParameter("rtr_area");
			String rtr_intro = req.getParameter("rtr_intro");
			String rtr_idno = req.getParameter("rtr_idno");
			String re_no = req.getParameter("re_no");
			String name = req.getParameter("name");
			String name2 = new String(name.getBytes("ISO-8859-1"), "UTF-8");
			String email = req.getParameter("email");
			String id = req.getParameter("id");
			HttpSession session = req.getSession();
			PrintWriter out = res.getWriter();
			
			//判斷是否用FB登入過
			RealtorService realtorSvc = new RealtorService();
			List<RealtorVO> list = realtorSvc.getAll();
			for (RealtorVO realtorvo : list) {
				if ((realtorvo.getRtr_id().trim().equals(email)) && realtorvo.getRtr_psw().trim().equals(id)) {
					session.setAttribute("realtorvo", realtorvo);
					//轉交至房仲會員中心
					String url = req.getContextPath()+"/front/realtor/realtor.do?action=realtorLogin&rtr_id="+email+"&rtr_psw="+id+"";
					out.println("<META HTTP-EQUIV='Refresh' content='1;URL=" + url + "'>");
					return;
				}
			}
			
			//沒有登入過走這
			String picUrl = "https://graph.facebook.com/" + id + "/picture?type=large";
			// InputStream fin=GetURLPic.GetPic(picUrl);
			InputStream fin = GetURLPic.GetPic(picUrl);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			byte[] temp = new byte[4096];
			int read;
			try {
				while ((read = fin.read(temp)) >= 0) {
					buffer.write(temp, 0, read);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] data = buffer.toByteArray();
//			realtorSvc.add(email, id, name2, data, rtr_area, rtr_intro, rtr_idno, re_no);
			realtorSvc.FBAdd(email, id, name2, data, rtr_area, rtr_intro, rtr_idno, re_no);
			System.out.println(email);
			System.out.println(id);
			System.out.println(name2);
			RealtorVO realtorVO = new RealtorVO();
			realtorVO.setRtr_id(email);
			realtorVO.setRtr_psw(id);
			realtorVO.setRtr_name(name2);
			realtorVO.setRtr_area(rtr_area);
			realtorVO.setRtr_intro(rtr_intro);
			realtorVO.setRtr_idno(rtr_idno);
			realtorVO.setRe_no(re_no);
			realtorVO.setRtr_photo(data);
			System.out.println("下:" + email);
			System.out.println("下:" + id);
			System.out.println("下:" + name2);
			fin.close();
			session.setAttribute("realtorVO", realtorVO);
			
			out.println("<META HTTP-EQUIV='Refresh' content='1;URL=" + req.getContextPath()+"/front/realtor/realtor_login.jsp" + "'>");
			return;

		}
		
		
		//來自 ReportRealtor.jsp '停權' 按鈕的請求
		if ("stopRealtor".equals(action)) {
			String requestURL = req.getParameter("requestURL");
			String rtr_no = req.getParameter("rtr_no");
			
			RealtorService realtorSvc = new RealtorService();
			realtorSvc.changeRealtorState("OFF", rtr_no);
			
			RequestDispatcher successView = req.getRequestDispatcher(requestURL);
			String stopDone = "已停權!";
			req.setAttribute("stopDone", stopDone);
			req.setAttribute("rtr_no", rtr_no);
			successView.forward(req, res);	
			
		}

	}
}

package com.slr.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SlrJDBCDAO implements SlrDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104G5";
	String passwd = "ba104g5";
	
	private static final String INSERT = 
			"INSERT INTO Seller (slr_no,slr_name,slr_taxid,slr_id,slr_psw,slr_contact,slr_phone,slr_state,slr_intro) "
			+ "VALUES('SL'||(LPAD(to_char(SLR_SEQ.NEXTVAL),8,'0')),?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE Seller SET slr_contact=?, slr_phone=?, slr_intro=? WHERE slr_no=?";
	private static final String SET_SLR_STATE = "UPDATE Seller SET slr_state=? WHERE slr_no=?";
	private static final String CHANGE_PASSWORD = "UPDATE Seller SET slr_psw=? WHERE slr_no=?";
	private static final String GET_ONE_BY_NO = 
			"SELECT slr_no,slr_name,slr_taxid,slr_id,slr_contact,slr_phone,slr_state,slr_intro "
	        + "FROM Seller WHERE slr_no=?";
	private static final String GET_ONE_BY_NAME = 
			"SELECT slr_no,slr_name,slr_taxid,slr_id,slr_contact,slr_phone,slr_state,slr_intro "
			+ "FROM Seller WHERE slr_name=?";
	private static final String GET_ONE_BY_ID = 
			"SELECT slr_no,slr_name,slr_taxid,slr_id,slr_psw,slr_contact,slr_phone,slr_state,slr_intro "
			+ "FROM Seller WHERE slr_id=?";
	private static final String GET_ALL = "SELECT * FROM Seller ORDER BY slr_no";
	private static final String GET_ID_LIST = "SELECT slr_id FROM Seller";
	
	@Override
	public void insert(SlrVO slrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, slrVO.getSlr_name());
			pstmt.setString(2, slrVO.getSlr_taxid());
			pstmt.setString(3, slrVO.getSlr_id());
			pstmt.setString(4, slrVO.getSlr_psw());
			pstmt.setString(5, slrVO.getSlr_contact());
			pstmt.setString(6, slrVO.getSlr_phone());
			pstmt.setString(7, "OFF"); // 此為 lock_state，新加入為 OFF，通過後台驗證才會改為 ON
			pstmt.setString(8, slrVO.getSlr_intro());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(insert出問題了) " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}



	@Override
	public void update(SlrVO slrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, slrVO.getSlr_contact());
			pstmt.setString(2, slrVO.getSlr_phone());
			pstmt.setString(3, slrVO.getSlr_intro());
			pstmt.setString(4, slrVO.getSlr_no());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(update出問題了) " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}



	@Override
	public void changeSlrState(SlrVO slrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SET_SLR_STATE);
			
			pstmt.setString(1, slrVO.getSlr_state());
			pstmt.setString(2, slrVO.getSlr_no());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(changeSlrState出問題了) " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}



	@Override
	public void changePassword(SlrVO slrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(CHANGE_PASSWORD);
			
			pstmt.setString(1, slrVO.getSlr_psw());
			pstmt.setString(2, slrVO.getSlr_no());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(changePassword出問題了) " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}



	@Override
	public SlrVO findByNo(String slr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SlrVO slrVO = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BY_NO);

			pstmt.setString(1, slr_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				slrVO = new SlrVO();
				slrVO.setSlr_no(rs.getString("slr_no"));
				slrVO.setSlr_name(rs.getString("slr_name"));
				slrVO.setSlr_taxid(rs.getString("slr_taxid"));
				slrVO.setSlr_id(rs.getString("slr_id"));
				slrVO.setSlr_contact(rs.getString("slr_contact"));
				slrVO.setSlr_phone(rs.getString("slr_phone"));
				slrVO.setSlr_state(rs.getString("slr_state"));
				slrVO.setSlr_intro(rs.getString("slr_intro"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(findByNo) " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return slrVO;
	}



	@Override
	public SlrVO findByName(String slr_name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SlrVO slrVO = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BY_NAME);

			pstmt.setString(1, slr_name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				slrVO = new SlrVO();
				slrVO.setSlr_no(rs.getString("slr_no"));
				slrVO.setSlr_name(rs.getString("slr_name"));
				slrVO.setSlr_taxid(rs.getString("slr_taxid"));
				slrVO.setSlr_id(rs.getString("slr_id"));
				slrVO.setSlr_contact(rs.getString("slr_contact"));
				slrVO.setSlr_phone(rs.getString("slr_phone"));
				slrVO.setSlr_state(rs.getString("slr_state"));
				slrVO.setSlr_intro(rs.getString("slr_intro"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(findByName) "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return slrVO;
	}
	
	@Override
	public SlrVO findById(String slr_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SlrVO slrVO = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BY_ID);

			pstmt.setString(1, slr_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				slrVO = new SlrVO();
				slrVO.setSlr_no(rs.getString("slr_no"));
				slrVO.setSlr_name(rs.getString("slr_name"));
				slrVO.setSlr_taxid(rs.getString("slr_taxid"));
				slrVO.setSlr_id(rs.getString("slr_id"));
				slrVO.setSlr_psw(rs.getString("slr_psw"));
				slrVO.setSlr_contact(rs.getString("slr_contact"));
				slrVO.setSlr_phone(rs.getString("slr_phone"));
				slrVO.setSlr_state(rs.getString("slr_state"));
				slrVO.setSlr_intro(rs.getString("slr_intro"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(findById) "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return slrVO;
	}



	@Override
	public List<SlrVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<SlrVO> list = new ArrayList<SlrVO>();
		SlrVO slrVO = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				slrVO = new SlrVO();
				slrVO.setSlr_no(rs.getString("slr_no"));
				slrVO.setSlr_name(rs.getString("slr_name"));
				slrVO.setSlr_taxid(rs.getString("slr_taxid"));
				slrVO.setSlr_id(rs.getString("slr_id"));
				slrVO.setSlr_contact(rs.getString("slr_contact"));
				slrVO.setSlr_phone(rs.getString("slr_phone"));
				slrVO.setSlr_state(rs.getString("slr_state"));
				slrVO.setSlr_intro(rs.getString("slr_intro"));
				list.add(slrVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(getAll) "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}


	@Override
	public List<SlrVO> getIdList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<SlrVO> list = new ArrayList<SlrVO>();
		SlrVO slrVO = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ID_LIST);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				slrVO = new SlrVO();
				slrVO.setSlr_id(rs.getString("slr_id"));
				list.add(slrVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(getIdList) "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}



	public static void main(String[] args) {
		SlrJDBCDAO dao = new SlrJDBCDAO();
		SlrVO slrVO1 = new SlrVO();
		
		//廠商加入會員
//		slrVO1.setSlr_name("詩肯柚木");
//		slrVO1.setSlr_taxid("89403884");
//		slrVO1.setSlr_id("sales@scanteak.com");
//		slrVO1.setSlr_psw("123456");
//		slrVO1.setSlr_contact("林福勤");
//		slrVO1.setSlr_phone("03-3180555");
//		slrVO1.setSlr_intro("柚木材質的家具已經成爲不少現代人用來布置家居的首選。柚木向來被視為最優質的木材，尤其以它那堅固耐用的特質，以及金黃色的光澤見稱。早期，柚木主要用於船運和鐵路工業範圍。但近幾十年來，柚木已經開始走進家居裝飾的領域。現在，對於那些愛好溫馨、自然純樸居住環境的人來說，柚木家俱已經成了打造理想家居的不二選擇。\n在詩肯柚木，我們力求家俱設計精美而又經久耐用。我們的創始靈感來自北歐丹麥，注重簡約的設計以及家俱的實用性。詩肯柚木一貫的理念，是要為繁忙的都市人營造一個舒適的居住空間。我們深信，擁有自然優美光澤和紋理的柚木，配搭簡約的家具設計，可為現代都市人打造出最完美的居家環境。\n詩肯柚木的足跡遍布世界各地，我們在德國、新加坡、台灣、汶萊、日本和美國等國家共有超過50間零售分店和旗艦店。在台灣，我們擁有最大的傢俱連鎖門市，全國有超過70間門市。無論在哪個門市，我們都將竭力為來訪的貴賓提供最溫馨、舒適的參觀經驗，讓客人踏進門市的第一步就有賓至如歸的感覺。");
//		dao.insert(slrVO1);
		
		//廠商更新資料
//		slrVO1.setSlr_contact("李協達");
//		slrVO1.setSlr_phone("84128869");
//		slrVO1.setSlr_intro("IKEA不但擁有多樣化的商品，更提供全方位的貼心服務，為你省時又省力。從運送、組裝、廚房安裝或分期付款等，都能助你一臂之力。當然，如果你喜歡自己動手做，就能省下更多錢；反之，如果讓我們為你服務，你就能有更多時間放鬆囉！");
//		slrVO1.setSlr_no("SL00000001");
//		dao.update(slrVO1);
		
		//廠商更新資料2
//		slrVO1.setSlr_contact("何湯雄");
//		slrVO1.setSlr_phone("89390488");
//		slrVO1.setSlr_intro("施工中！");
//		slrVO1.setSlr_no("SL00000003");
//		dao.update(slrVO1);
//		System.out.println("done");
		
		//更改廠商帳號狀態
//		slrVO1.setSlr_state("OFF");
//		slrVO1.setSlr_no("SL00000005");
//		dao.changeSlrState(slrVO1);
//		
		//更改密碼
//		slrVO1.setSlr_psw("8888");
//		slrVO1.setSlr_no("SL00000005");
//		dao.changePassword(slrVO1);
		
		//findByNo 以廠商編號 slr_no 搜尋
//		SlrVO slrVO2 = dao.findByNo("SL00000005");
//		System.out.println("slr_no : " + slrVO2.getSlr_no());
//		System.out.println("slr_name : " + slrVO2.getSlr_name());
//		System.out.println("slr_taxid : " + slrVO2.getSlr_taxid());
//		System.out.println("slr_id : " + slrVO2.getSlr_id());
//		System.out.println("slr_contact : " + slrVO2.getSlr_contact());
//		System.out.println("slr_phone : " + slrVO2.getSlr_phone());
//		System.out.println("slr_state : " + slrVO2.getSlr_state());
//		System.out.println("slr_intro : " + slrVO2.getSlr_intro());
		
		//findByName 以廠商名稱 slr_name 搜尋
//		SlrVO slrVO2 = dao.findByName("宜家家居");
//		System.out.println("slr_no : " + slrVO2.getSlr_no());
//		System.out.println("slr_name : " + slrVO2.getSlr_name());
//		System.out.println("slr_taxid : " + slrVO2.getSlr_taxid());
//		System.out.println("slr_id : " + slrVO2.getSlr_id());
//		System.out.println("slr_contact : " + slrVO2.getSlr_contact());
//		System.out.println("slr_phone : " + slrVO2.getSlr_phone());
//		System.out.println("slr_state : " + slrVO2.getSlr_state());
//		System.out.println("slr_intro : " + slrVO2.getSlr_intro());
		
		//findById 以廠商帳號 slr_id 搜尋
		SlrVO slrVO2 = dao.findById("sales@ikea.com");
		System.out.println("slr_no : " + slrVO2.getSlr_no());
		System.out.println("slr_name : " + slrVO2.getSlr_name());
		System.out.println("slr_taxid : " + slrVO2.getSlr_taxid());
		System.out.println("slr_id : " + slrVO2.getSlr_id());
		System.out.println("slr_psw : " + slrVO2.getSlr_psw());
		System.out.println("slr_contact : " + slrVO2.getSlr_contact());
		System.out.println("slr_phone : " + slrVO2.getSlr_phone());
		System.out.println("slr_state : " + slrVO2.getSlr_state());
		System.out.println("slr_intro : " + slrVO2.getSlr_intro());
//		
//		List<SlrVO> list = dao.getAll();
//		for(SlrVO slrvo : list) {
//			System.out.print(slrvo.getSlr_no() + "\t");
//			System.out.print(slrvo.getSlr_name() + "\t");
//			System.out.print(slrvo.getSlr_taxid() + "\t");
//			System.out.print(slrvo.getSlr_id() + "\t");
//			System.out.print(slrvo.getSlr_contact() + "\t");
//			System.out.print(slrvo.getSlr_phone() + "\t");
//			System.out.print(slrvo.getSlr_state() + "\t");
//			System.out.println(slrvo.getSlr_intro());
//		}
		
		//取得所有廠商的 e-mail 用來讓新註冊比對
//		List<SlrVO> idList = dao.getIdList();
//		for(SlrVO id : idList){
//			System.out.println(id.getSlr_id());
//		}
	}

}

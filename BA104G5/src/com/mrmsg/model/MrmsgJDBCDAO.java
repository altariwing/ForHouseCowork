package com.mrmsg.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MrmsgJDBCDAO implements MrmsgDAO_interface{
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104G5";
	String passwd = "ba104g5";

	private static final String INSERT_STMT = 
		"INSERT INTO mrmsg (mem_no,rtr_no,mr_msg) VALUES ( ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT mem_no,rtr_no,mr_msg,mr_time FROM mrmsg order by mem_no";
	private static final String GET_ONE_STMT = 
		"SELECT mem_no,rtr_no,mr_msg,mr_time FROM mrmsg where mem_no = ? and rtr_no = ?";
//	private static final String DELETE = 
//		"DELETE FROM mrmsg where mem_no = ? and rtr_no = ?";
	private static final String UPDATE = 
		"UPDATE mrmsg set mr_msg=?,mr_time=CURRENT_TIMESTAMP where mem_no = ? and rtr_no = ?";

	
	
	
	
	@Override
	public void insert(MrmsgVO mrmsgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, mrmsgVO.getMem_no());
			pstmt.setString(2, mrmsgVO.getRtr_no());
			pstmt.setString(3, mrmsgVO.getMr_msg());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void update(MrmsgVO mrmsgVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, mrmsgVO.getMr_msg());
			pstmt.setString(2, mrmsgVO.getMem_no());
			pstmt.setString(3, mrmsgVO.getRtr_no());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void delete(String mem_no, String rtr_no) {

		
	}

	@Override
	public MrmsgVO findByPrimaryKey(String mem_no, String rtr_no) {

		MrmsgVO mrmsgVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1,mem_no);
			pstmt.setString(2, rtr_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// mrmsgVo 也稱為 Domain objects
				mrmsgVO = new MrmsgVO();
				mrmsgVO.setMem_no(rs.getString("mem_no"));
				mrmsgVO.setRtr_no(rs.getString("rtr_no"));
				mrmsgVO.setMr_msg(rs.getString("mr_msg"));
				mrmsgVO.setMr_time(rs.getTimestamp("mr_time"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
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
		return mrmsgVO;
	}

	@Override
	public List<MrmsgVO> getAll() {
		List<MrmsgVO> list = new ArrayList<MrmsgVO>();
		MrmsgVO mrmsgVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// mrmsgVO 也稱為 Domain objects
				mrmsgVO = new MrmsgVO();
				mrmsgVO.setMem_no(rs.getString("mem_no"));
				mrmsgVO.setRtr_no(rs.getString("rtr_no"));
				mrmsgVO.setMr_msg(rs.getString("mr_msg"));
				mrmsgVO.setMr_time(rs.getTimestamp("mr_time"));
	
				list.add(mrmsgVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
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
		return list;
	}
	public static void main(String[] args) {

		MrmsgJDBCDAO dao = new MrmsgJDBCDAO();
		SimpleDateFormat sdfromat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		// 新增
//		MrmsgVO mrmsgVO1 = new MrmsgVO();
//		mrmsgVO1.setMem_no("MB00000002");
//		mrmsgVO1.setRtr_no("RT00000003");
//		mrmsgVO1.setMr_msg("MVC指的是Model/View/Controller， 也就是將Web應用程式的組成劃分為模型、畫面與控制器三 個角色，"
//				+ "最原始的MVC定義是指桌面應用程式上的架構，這邊不予探討，Web應用程式借鏡桌面應用程式MVC架構，取其 Model/View/Controller的職責劃分，"
//				+ "並修改流程為適用於HTTP請求/回應特性，基本上你也可以稱這個修改後的架構為 MVC，或者是 Model 2（是的！還有一個架構稱之為Model 1，之後會看到）"
//				+ "，或併稱為MVC/Model 2。");
//		
//		dao.insert(mrmsgVO1);
//		System.out.println("新增成功");
		
		// 修改
//		MrmsgVO mrmsgVO2 = new MrmsgVO();
//		mrmsgVO2.setMem_no("MB00000002");
//		mrmsgVO2.setRtr_no("RT00000003");
//		mrmsgVO2.setMr_msg("在 Web 應用程式的領域中，包括了網頁的呈現與程式碼的行為，網頁的呈現可交由網頁美術人員執行，"
//				+ "程式碼的撰寫則由程式設計人員負責，各司其職，為了 不在網頁中添 加不必要的程式碼來干擾網頁美術人員的設計，"
//				+ "也為了不在程式碼中安插麻煩的字串來輸出HTML等內容，Web應用程式世界常使用的架構是 MVC/Model 2。");
//		dao.update(mrmsgVO2);
//		System.out.println("更新成功");
		// 刪除
//		dao.delete(" ");

		// 查詢		
//		MrmsgVO mrmsgVO3 = dao.findByPrimaryKey("MB00000002" , "RT00000003");
//		System.out.println(mrmsgVO3.getMem_no() + ",");
//		System.out.println(mrmsgVO3.getRtr_no() + ",");
//		System.out.println(mrmsgVO3.getMr_msg() + ",");
//		System.out.println(sdfromat.format(mrmsgVO3.getMr_time()) + ",");
//		System.out.println("---------------------");
//
//		// 查詢
		List<MrmsgVO> list = dao.getAll();
		for (MrmsgVO aMrmsg : list) {
			System.out.println(aMrmsg.getMem_no() + ",");
			System.out.println(aMrmsg.getRtr_no() + ",");
			System.out.println(aMrmsg.getMr_msg() + ",");
			System.out.println(sdfromat.format(aMrmsg.getMr_time()) + ",");
			System.out.println("---------------------");
			System.out.println();
		}
	}
	
}

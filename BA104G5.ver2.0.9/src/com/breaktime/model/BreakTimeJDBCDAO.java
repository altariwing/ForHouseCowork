package com.breaktime.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BreakTimeJDBCDAO implements BreakTimeDAO_interface{

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104G5";
	String passwd = "ba104g5";

	private static final String INSERT_STMT = 
		"INSERT INTO breakTime (brk_no,rtr_no,brk_date,brk_period) VALUES ('BR'||(LPAD(TO_CHAR(BRK_SEQ.NEXTVAL),8,'0')), ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT brk_no,rtr_no,brk_date,brk_period FROM breakTime order by rtr_no";
	private static final String GET_ONE_STMT = 
		"SELECT brk_no,rtr_no,brk_date,brk_period FROM breakTime where brk_no = ? ";
	private static final String DELETE = 
		"DELETE FROM breakTime where brk_no = ?";
	private static final String UPDATE = 
		"UPDATE breakTime set rtr_no=?,brk_date=?,brk_period=? where brk_no = ?";
	private static final String GET_ONE_RTR = 
			"SELECT brk_no,rtr_no,brk_date,brk_period FROM breakTime where rtr_no = ? order by rtr_no";
	
	
	
	
	@Override
	public String insert(BreakTimeVO breakTimeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String brk_no = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String cols[] = {"brk_no"};
			pstmt = con.prepareStatement(INSERT_STMT,cols);
			
			pstmt.setString(1, breakTimeVO.getRtr_no());
			pstmt.setDate(2, breakTimeVO.getBrk_date());
			pstmt.setString(3, breakTimeVO.getBrk_period());
			
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				do {
					for (int i = 1; i <= columnCount; i++) {
						 brk_no = rs.getString(i);				 
					}
				} while (rs.next());
			} else {
				System.out.println("NO KEYS WERE GENERATED.");
			}
			rs.close();

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
		return brk_no;
		
	}

	@Override
	public void update(BreakTimeVO breakTimeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, breakTimeVO.getRtr_no());
			pstmt.setDate(2, breakTimeVO.getBrk_date());
			pstmt.setString(3, breakTimeVO.getBrk_period());
			pstmt.setString(4, breakTimeVO.getBrk_no());
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
	public void delete(String brk_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, brk_no);
		
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
	public BreakTimeVO findByPrimaryKey(String brk_no) {

		BreakTimeVO breakTimeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1,brk_no);


			rs = pstmt.executeQuery();

			while (rs.next()) {
				// breakTimeVo 也稱為 Domain objects
				breakTimeVO = new BreakTimeVO();
				breakTimeVO.setBrk_no(rs.getString("brk_no"));
				breakTimeVO.setRtr_no(rs.getString("rtr_no"));
				breakTimeVO.setBrk_date(rs.getDate("brk_date"));
				breakTimeVO.setBrk_period(rs.getString("brk_period"));
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
		return breakTimeVO;
	}

	@Override
	public List<BreakTimeVO> getAll() {
		List<BreakTimeVO> list = new ArrayList<BreakTimeVO>();
		BreakTimeVO breakTimeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// breakTimeVO 也稱為 Domain objects
				breakTimeVO = new BreakTimeVO();
				breakTimeVO.setBrk_no(rs.getString("brk_no"));
				breakTimeVO.setRtr_no(rs.getString("rtr_no"));
				breakTimeVO.setBrk_date(rs.getDate("brk_date"));
				breakTimeVO.setBrk_period(rs.getString("brk_period"));
	
				list.add(breakTimeVO); // Store the row in the list
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
	
	public List<BreakTimeVO> getRtr_bt_kuei(String rtr_no){
		List<BreakTimeVO> list = new ArrayList<BreakTimeVO>();
		BreakTimeVO breakTimeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_RTR);
			
			pstmt.setString(1,rtr_no);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// breakTimeVO 也稱為 Domain objects
				breakTimeVO = new BreakTimeVO();
				breakTimeVO.setBrk_no(rs.getString("brk_no"));
				breakTimeVO.setRtr_no(rs.getString("rtr_no"));
				breakTimeVO.setBrk_date(rs.getDate("brk_date"));
				breakTimeVO.setBrk_period(rs.getString("brk_period"));
	
				list.add(breakTimeVO); // Store the row in the list
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

		BreakTimeJDBCDAO dao = new BreakTimeJDBCDAO();

		// 新增
//		BreakTimeVO breakTimeVO1 = new BreakTimeVO();
//		breakTimeVO1.setRtr_no("RT00000003");
//		breakTimeVO1.setBrk_date(java.sql.Date.valueOf("2019-12-15"));
//		breakTimeVO1.setBrk_period("08:00-12:00");
//		
//		dao.insert(breakTimeVO1);
//		System.out.println("新增成功");
		
		// 修改
//		BreakTimeVO breakTimeVO2 = new BreakTimeVO();
//		breakTimeVO2.setRtr_no("RT00000003");
//		breakTimeVO2.setBrk_date(java.sql.Date.valueOf("2017-12-25"));
//		breakTimeVO2.setBrk_period("08:00-12:00");
//		breakTimeVO2.setBrk_no("BR00000027");
//		dao.update(breakTimeVO2);
//		System.out.println("更新成功");
		// 刪除
//		dao.delete("RT00000003",java.sql.Date.valueOf("2019-11-05"),"17:00-20:00");

		// 查詢單一房仲		
		List<BreakTimeVO> list1 = dao.getRtr_bt_kuei("RT00000010");
		for (BreakTimeVO aBreakTime : list1) {
			System.out.println(aBreakTime.getBrk_no());
			System.out.println(aBreakTime.getRtr_no());
			System.out.println(aBreakTime.getBrk_date());
			System.out.println(aBreakTime.getBrk_period());
			System.out.println("---------------------");
			System.out.println();
		}
//
//		// 查詢
//		List<BreakTimeVO> list = dao.getAll();
//		for (BreakTimeVO aBreakTime : list) {
//			System.out.println(aBreakTime.getBrk_no());
//			System.out.println(aBreakTime.getRtr_no());
//			System.out.println(aBreakTime.getBrk_date());
//			System.out.println(aBreakTime.getBrk_period());
//			System.out.println("---------------------");
//			System.out.println();
//		}
	}
}

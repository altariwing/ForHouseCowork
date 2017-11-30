package com.resrec.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResRecJDBCDAO implements ResRecDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104G5";
	String passwd = "ba104g5";

	private static final String INSERT_STMT = "INSERT INTO RESREC (RESR_NO, MEM_NO, HOUSE_NO, RTR_NO, RESR_STATES, RESR_DATE, RESR_PERIOD,RESR_HPRIC,RESR_HSIZE,RESR_MGT,RESR_PUC,RESR_STR,RESR_WALL,RESR_LTG,RESR_VEN,RESR_TC,RESR_LC,HSE_REV,MEM_RATE,MEM_REV,RTR_RATE)"
			+ "VALUES ('RR'||TO_CHAR(SYSDATE,'RRMMDD')||'-'||(LPAD(TO_CHAR(RESR_SEQ.NEXTVAL),5,'0')),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT RESR_NO,MEM_NO,HOUSE_NO,RTR_NO,TO_CHAR(RESR_ORD,'YYYY-MM-DD HH:MM:SS') RESR_ORD,RESR_STATES,TO_CHAR(RESR_DATE,'YYYY-MM-DD')RESR_DATE,RESR_PERIOD,RESR_HPRIC,RESR_HSIZE,RESR_MGT,RESR_PUC,RESR_STR,RESR_WALL,RESR_LTG,RESR_VEN,RESR_TC,RESR_LC,HSE_REV,MEM_RATE,MEM_REV,RTR_RATE FROM RESREC ORDER BY RESR_NO";
	private static final String GET_ONE_STMT = "SELECT RESR_NO,MEM_NO,HOUSE_NO,RTR_NO,TO_CHAR(RESR_ORD,'YYYY-MM-DD HH:MM:SS') RESR_ORD,RESR_STATES,TO_CHAR(RESR_DATE,'YYYY-MM-DD')RESR_DATE,RESR_PERIOD,RESR_HPRIC,RESR_HSIZE,RESR_MGT,RESR_PUC,RESR_STR,RESR_WALL,RESR_LTG,RESR_VEN,RESR_TC,RESR_LC,HSE_REV,MEM_RATE,MEM_REV,RTR_RATE FROM RESREC WHERE RESR_NO = ?";
	private static final String DELETE = "DELETE FROM RESREC WHERE RESR_NO = ?";
	private static final String UPDATE = "UPDATE RESREC SET MEM_NO=?, HOUSE_NO=?, RTR_NO=?,RESR_STATES=?,RESR_DATE=?,RESR_PERIOD=?,RESR_HPRIC=?,RESR_HSIZE=?,RESR_MGT=?,RESR_PUC=?,RESR_STR=?,RESR_WALL=?,RESR_LTG=?,RESR_VEN=?,RESR_TC=?,RESR_LC=?,HSE_REV=?,MEM_RATE=?,MEM_REV=?,RTR_RATE=? WHERE RESR_NO = ?";
	private static final String INSERT_KUEI1 = "INSERT INTO RESREC (RESR_NO, MEM_NO, HOUSE_NO, RTR_NO, RESR_STATES, RESR_DATE, RESR_PERIOD)VALUES ('RR'||TO_CHAR(SYSDATE,'RRMMDD')||'-'||(LPAD(TO_CHAR(RESR_SEQ.NEXTVAL),5,'0')), ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_KUEI1 = "UPDATE RESREC SET MEM_NO=?, HOUSE_NO=?, RTR_NO=?,RESR_STATES=?,RESR_DATE=?,RESR_PERIOD=? WHERE RESR_NO = ?";
	private static final String UPDATE_KUEI2 = "UPDATE RESREC SET RESR_STATES=?,RESR_HPRIC=?,RESR_HSIZE=?,RESR_MGT=?,RESR_PUC=?,RESR_STR=?,RESR_WALL=?,RESR_LTG=?,RESR_VEN=?,RESR_TC=?,RESR_LC=?,HSE_REV=?,MEM_RATE=?,MEM_REV=?,RTR_RATE=? WHERE RESR_NO = ?";
	private static final String UPDATE_KUEI3 = "UPDATE RESREC SET RESR_STATES=?,RESR_DATE=?,RESR_PERIOD=? WHERE RESR_NO = ?";	
	private static final String GET_MEM_KUEI ="SELECT RESR_NO,HOUSE_NO,RTR_NO,TO_CHAR(RESR_ORD,'YYYY-MM-DD HH:MM:SS') RESR_ORD,RESR_STATES,TO_CHAR(RESR_DATE,'YYYY-MM-DD')RESR_DATE,RESR_PERIOD,RESR_HPRIC,RESR_HSIZE,RESR_MGT,RESR_PUC,RESR_STR,RESR_WALL,RESR_LTG,RESR_VEN,RESR_TC,RESR_LC,HSE_REV,MEM_RATE,MEM_REV,RTR_RATE FROM RESREC WHERE MEM_NO = ? ORDER BY RESR_NO";
	private static final String GET_RTR_KUEI ="SELECT RESR_NO,MEM_NO,HOUSE_NO,RTR_NO,TO_CHAR(RESR_ORD,'YYYY-MM-DD HH:MM:SS') RESR_ORD,RESR_STATES,TO_CHAR(RESR_DATE,'YYYY-MM-DD')RESR_DATE,RESR_PERIOD,RESR_HPRIC,RESR_HSIZE,RESR_MGT,RESR_PUC,RESR_STR,RESR_WALL,RESR_LTG,RESR_VEN,RESR_TC,RESR_LC,HSE_REV,MEM_RATE,MEM_REV,RTR_RATE FROM RESREC WHERE RTR_NO = ? ORDER BY RESR_NO";
	
	@Override
	public void insert(ResRecVO resRecVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, resRecVO.getMem_no());
			pstmt.setString(2, resRecVO.getHouse_no());
			pstmt.setString(3, resRecVO.getRtr_no());
			pstmt.setString(4, resRecVO.getResr_states());
			pstmt.setDate(5, resRecVO.getResr_date());
			pstmt.setString(6, resRecVO.getResr_period());
			pstmt.setDouble(7, resRecVO.getResr_hpric());
			pstmt.setDouble(8, resRecVO.getResr_hsize());
			pstmt.setDouble(9, resRecVO.getResr_mgt());
			pstmt.setDouble(10, resRecVO.getResr_puc());
			pstmt.setDouble(11, resRecVO.getResr_str());
			pstmt.setDouble(12, resRecVO.getResr_wall());
			pstmt.setDouble(13, resRecVO.getResr_ltg());
			pstmt.setDouble(14, resRecVO.getResr_ven());
			pstmt.setDouble(15, resRecVO.getResr_tc());
			pstmt.setDouble(16, resRecVO.getResr_lc());
			pstmt.setString(17, resRecVO.getHse_rev());
			pstmt.setDouble(18, resRecVO.getMem_rate());
			pstmt.setString(19, resRecVO.getMem_rev());
			pstmt.setDouble(20, resRecVO.getRtr_rate());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public void insert_kuei1(ResRecVO resRecVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_KUEI1);

			pstmt.setString(1, resRecVO.getMem_no());
			pstmt.setString(2, resRecVO.getHouse_no());
			pstmt.setString(3, resRecVO.getRtr_no());
			pstmt.setString(4, resRecVO.getResr_states());
			pstmt.setDate(5, resRecVO.getResr_date());
			pstmt.setString(6, resRecVO.getResr_period());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(ResRecVO resRecVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, resRecVO.getMem_no());
			pstmt.setString(2, resRecVO.getHouse_no());
			pstmt.setString(3, resRecVO.getRtr_no());
			pstmt.setString(4, resRecVO.getResr_states());
			pstmt.setDate(5, resRecVO.getResr_date());
			pstmt.setString(6, resRecVO.getResr_period());
			pstmt.setDouble(7, resRecVO.getResr_hpric());
			pstmt.setDouble(8, resRecVO.getResr_hsize());
			pstmt.setDouble(9, resRecVO.getResr_mgt());
			pstmt.setDouble(10, resRecVO.getResr_puc());
			pstmt.setDouble(11, resRecVO.getResr_str());
			pstmt.setDouble(12, resRecVO.getResr_wall());
			pstmt.setDouble(13, resRecVO.getResr_ltg());
			pstmt.setDouble(14, resRecVO.getResr_ven());
			pstmt.setDouble(15, resRecVO.getResr_tc());
			pstmt.setDouble(16, resRecVO.getResr_lc());
			pstmt.setString(17, resRecVO.getHse_rev());
			pstmt.setDouble(18, resRecVO.getMem_rate());
			pstmt.setString(19, resRecVO.getMem_rev());
			pstmt.setDouble(20, resRecVO.getRtr_rate());
			pstmt.setString(21, resRecVO.getResr_no());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public void update_kuei1(ResRecVO resRecVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_KUEI1);

			pstmt.setString(1, resRecVO.getMem_no());
			pstmt.setString(2, resRecVO.getHouse_no());
			pstmt.setString(3, resRecVO.getRtr_no());
			pstmt.setString(4, resRecVO.getResr_states());
			pstmt.setDate(5, resRecVO.getResr_date());
			pstmt.setString(6, resRecVO.getResr_period());
			pstmt.setString(7, resRecVO.getResr_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public void update_kuei2(ResRecVO resRecVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			pstmt = con.prepareStatement(UPDATE_KUEI2);

			pstmt.setString(1, resRecVO.getResr_states());
			pstmt.setDouble(2, resRecVO.getResr_hpric());
			pstmt.setDouble(3, resRecVO.getResr_hsize());
			pstmt.setDouble(4, resRecVO.getResr_mgt());
			pstmt.setDouble(5, resRecVO.getResr_puc());
			pstmt.setDouble(6, resRecVO.getResr_str());
			pstmt.setDouble(7, resRecVO.getResr_wall());
			pstmt.setDouble(8, resRecVO.getResr_ltg());
			pstmt.setDouble(9, resRecVO.getResr_ven());
			pstmt.setDouble(10, resRecVO.getResr_tc());
			pstmt.setDouble(11, resRecVO.getResr_lc());
			pstmt.setString(12, resRecVO.getHse_rev());
			pstmt.setDouble(13, resRecVO.getMem_rate());
			pstmt.setString(14, resRecVO.getMem_rev());
			pstmt.setDouble(15, resRecVO.getRtr_rate());
			pstmt.setString(16, resRecVO.getResr_no());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
	public void update_kuei3(ResRecVO resRecVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_KUEI3);

			pstmt.setString(1, resRecVO.getResr_states());
			pstmt.setDate(2, resRecVO.getResr_date());
			pstmt.setString(3, resRecVO.getResr_period());
			pstmt.setString(4, resRecVO.getResr_no());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(String resr_no) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, resr_no);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public ResRecVO findByPrimaryKey(String resr_no) {

		ResRecVO resRecVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, resr_no);

			rs = pstmt.executeQuery();
			// SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd
			// HH:mm:ss");
			while (rs.next()) {
				resRecVO = new ResRecVO();
				resRecVO.setResr_no(rs.getString("resr_no"));
				resRecVO.setMem_no(rs.getString("mem_no"));
				resRecVO.setHouse_no(rs.getString("house_no"));
				resRecVO.setRtr_no(rs.getString("rtr_no"));
				resRecVO.setResr_ord(rs.getTimestamp("resr_ord"));
				resRecVO.setResr_states(rs.getString("resr_states"));
				resRecVO.setResr_date(rs.getDate("resr_date"));
				resRecVO.setResr_period(rs.getString("resr_period"));
				resRecVO.setResr_hpric(rs.getDouble("resr_hpric"));
				resRecVO.setResr_hsize(rs.getDouble("resr_hsize"));
				resRecVO.setResr_mgt(rs.getDouble("resr_mgt"));
				resRecVO.setResr_puc(rs.getDouble("resr_puc"));
				resRecVO.setResr_str(rs.getDouble("resr_str"));
				resRecVO.setResr_wall(rs.getDouble("resr_wall"));
				resRecVO.setResr_ltg(rs.getDouble("resr_ltg"));
				resRecVO.setResr_ven(rs.getDouble("resr_ven"));
				resRecVO.setResr_tc(rs.getDouble("resr_tc"));
				resRecVO.setResr_lc(rs.getDouble("resr_lc"));
				resRecVO.setHse_rev(rs.getString("hse_rev"));
				resRecVO.setMem_rate(rs.getDouble("mem_rate"));
				resRecVO.setMem_rev(rs.getString("mem_rev"));
				resRecVO.setRtr_rate(rs.getDouble("rtr_rate"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return resRecVO;
	}

	public List<ResRecVO> getMem_kuei(String mem_no){
		List<ResRecVO> list = new ArrayList<ResRecVO>();
		ResRecVO resRecVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_MEM_KUEI);
			
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				resRecVO = new ResRecVO();
				resRecVO.setResr_no(rs.getString("resr_no"));

				resRecVO.setHouse_no(rs.getString("house_no"));
				resRecVO.setRtr_no(rs.getString("rtr_no"));
				resRecVO.setResr_ord(rs.getTimestamp("resr_ord"));
				resRecVO.setResr_states(rs.getString("resr_states"));
				resRecVO.setResr_date(rs.getDate("resr_date"));
				resRecVO.setResr_period(rs.getString("resr_period"));
				resRecVO.setResr_hpric(rs.getDouble("resr_hpric"));
				resRecVO.setResr_hsize(rs.getDouble("resr_hsize"));
				resRecVO.setResr_mgt(rs.getDouble("resr_mgt"));
				resRecVO.setResr_puc(rs.getDouble("resr_puc"));
				resRecVO.setResr_str(rs.getDouble("resr_str"));
				resRecVO.setResr_wall(rs.getDouble("resr_wall"));
				resRecVO.setResr_ltg(rs.getDouble("resr_ltg"));
				resRecVO.setResr_ven(rs.getDouble("resr_ven"));
				resRecVO.setResr_tc(rs.getDouble("resr_tc"));
				resRecVO.setResr_lc(rs.getDouble("resr_lc"));
				resRecVO.setHse_rev(rs.getString("hse_rev"));
				resRecVO.setMem_rate(rs.getDouble("mem_rate"));
				resRecVO.setMem_rev(rs.getString("mem_rev"));
				resRecVO.setRtr_rate(rs.getDouble("rtr_rate"));

				list.add(resRecVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
	public List<ResRecVO> getRtr_kuei(String rtr_no){
		List<ResRecVO> list = new ArrayList<ResRecVO>();
		ResRecVO resRecVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_RTR_KUEI);
			
			pstmt.setString(1, rtr_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				resRecVO = new ResRecVO();
				resRecVO.setResr_no(rs.getString("resr_no"));
				resRecVO.setMem_no(rs.getString("mem_no"));
				resRecVO.setHouse_no(rs.getString("house_no"));
				resRecVO.setRtr_no(rs.getString("rtr_no"));
				resRecVO.setResr_states(rs.getString("resr_states"));
				resRecVO.setResr_date(rs.getDate("resr_date"));
				resRecVO.setResr_period(rs.getString("resr_period"));

				list.add(resRecVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	@Override
	public List<ResRecVO> getAll() {
		List<ResRecVO> list = new ArrayList<ResRecVO>();
		ResRecVO resRecVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				resRecVO = new ResRecVO();
				resRecVO.setResr_no(rs.getString("resr_no"));
				resRecVO.setMem_no(rs.getString("mem_no"));
				resRecVO.setHouse_no(rs.getString("house_no"));
				resRecVO.setRtr_no(rs.getString("rtr_no"));
				resRecVO.setResr_ord(rs.getTimestamp("resr_ord"));
				resRecVO.setResr_states(rs.getString("resr_states"));
				resRecVO.setResr_date(rs.getDate("resr_date"));
				resRecVO.setResr_period(rs.getString("resr_period"));
				resRecVO.setResr_hpric(rs.getDouble("resr_hpric"));
				resRecVO.setResr_hsize(rs.getDouble("resr_hsize"));
				resRecVO.setResr_mgt(rs.getDouble("resr_mgt"));
				resRecVO.setResr_puc(rs.getDouble("resr_puc"));
				resRecVO.setResr_str(rs.getDouble("resr_str"));
				resRecVO.setResr_wall(rs.getDouble("resr_wall"));
				resRecVO.setResr_ltg(rs.getDouble("resr_ltg"));
				resRecVO.setResr_ven(rs.getDouble("resr_ven"));
				resRecVO.setResr_tc(rs.getDouble("resr_tc"));
				resRecVO.setResr_lc(rs.getDouble("resr_lc"));
				resRecVO.setHse_rev(rs.getString("hse_rev"));
				resRecVO.setMem_rate(rs.getDouble("mem_rate"));
				resRecVO.setMem_rev(rs.getString("mem_rev"));
				resRecVO.setRtr_rate(rs.getDouble("rtr_rate"));

				list.add(resRecVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

		ResRecJDBCDAO dao = new ResRecJDBCDAO();
		SimpleDateFormat sdfromat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat upsdfromat = new SimpleDateFormat("yyMMdd");

//		 ResRecVO resRecVO = new ResRecVO();
//		 resRecVO.setMem_no("MB00000002");
//		 resRecVO.setHouse_no("HSE00000003");
//		 resRecVO.setRtr_no("RT00000005");
//		 resRecVO.setResr_states("CONFIRM");
//		 resRecVO.setResr_date(java.sql.Date.valueOf("2017-11-30"));
//		 resRecVO.setResr_period("15:00-18:00");
//		 resRecVO.setResr_hpric(2);
//		 resRecVO.setResr_hsize(3);
//		 resRecVO.setResr_mgt(2);
//		 resRecVO.setResr_puc(3);
//		 resRecVO.setResr_str(5);
//		 resRecVO.setResr_wall(2);
//		 resRecVO.setResr_ltg(3);
//		 resRecVO.setResr_ven(2);
//		 resRecVO.setResr_tc(1);
//		 resRecVO.setResr_lc(2);
//		 resRecVO.setHse_rev("超完美");
//		 resRecVO.setMem_rate(5);
//		 resRecVO.setMem_rev("房仲待人親切");
//		 resRecVO.setRtr_rate(5);
//		
//		 dao.insert(resRecVO);
//		 System.out.println("insert成功");
		// --------------------------------------------------------------------------------------------
//		 ResRecVO resRecVO1 = new ResRecVO();
//		 resRecVO1.setMem_no("MB00000004");
//		 resRecVO1.setHouse_no("HSE00000002");
//		 resRecVO1.setRtr_no("RT00000008");
//		 resRecVO1.setResr_states("ESTABLISH");
//		 resRecVO1.setResr_date(java.sql.Date.valueOf("2017-11-13"));
//		 resRecVO1.setResr_period("17:00-18:00");
//		
//		 dao.insert_kuei1(resRecVO1);
//		 System.out.println("insert_ekuei 成功");
		// --------------------------------------------------------------------------------------------
		// String resr_no2 ="RR"+upsdfromat.format(new Date())+"-00005";
		// ResRecVO resRecVO2 = new ResRecVO();
		// resRecVO2.setResr_no(resr_no2);
		// resRecVO2.setMem_no("MB00000003");
		// resRecVO2.setHouse_no("HSE00000001");
		// resRecVO2.setRtr_no("RT00000011");
		// resRecVO2.setResr_states("CONFIRM");
		// resRecVO2.setResr_date(java.sql.Date.valueOf("2017-10-30"));
		// resRecVO2.setResr_period("14:00-16:00");
		// resRecVO2.setResr_hpric(3);
		// resRecVO2.setResr_hsize(3);
		// resRecVO2.setResr_mgt(3);
		// resRecVO2.setResr_puc(5);
		// resRecVO2.setResr_str(5);
		// resRecVO2.setResr_wall(4);
		// resRecVO2.setResr_ltg(3);
		// resRecVO2.setResr_ven(3);
		// resRecVO2.setResr_tc(5);
		// resRecVO2.setResr_lc(3);
		// resRecVO2.setHse_rev("太不通風了");
		// resRecVO2.setMem_rate(2);
		// resRecVO2.setMem_rev("房仲待人親切");
		// resRecVO2.setRtr_rate(5);
		// dao.update(resRecVO2);
		// System.out.println("update 成功");
		// --------------------------------------------------------------------------------------------
		// ResRecVO resRecVO3 = new ResRecVO();
		// String resr_no3 ="RR"+upsdfromat.format(new Date())+"-00002";
		// resRecVO3.setResr_no(resr_no3);
		// resRecVO3.setMem_no("MB00000003");
		// resRecVO3.setHouse_no("HSE00000001");
		// resRecVO3.setRtr_no("RT00000011");
		// resRecVO3.setResr_date(java.sql.Date.valueOf("2017-10-30"));
		// resRecVO3.setResr_period("14:00-16:00");
		//
		// resRecVO3.setResr_states("CANCEL");
		//
		// dao.update_kuei1(resRecVO3);
		// System.out.println("update_ckuei成功");
		// --------------------------------------------------------------------------------------------
		// ResRecVO resRecVO4 = new ResRecVO();
		// String resr_no4 ="RR"+upsdfromat.format(new Date())+"-00006";
		// resRecVO4.setResr_no(resr_no4);
		// resRecVO4.setResr_states("CONFIRM");
		// resRecVO4.setResr_hpric(2);
		// resRecVO4.setResr_hsize(5);
		// resRecVO4.setResr_mgt(3);
		// resRecVO4.setResr_puc(4);
		// resRecVO4.setResr_str(3);
		// resRecVO4.setResr_wall(4);
		// resRecVO4.setResr_ltg(3);
		// resRecVO4.setResr_ven(2);
		// resRecVO4.setResr_tc(5);
		// resRecVO4.setResr_lc(2);
		// resRecVO4.setHse_rev("普普通通");
		// resRecVO4.setMem_rate(4);
		// resRecVO4.setMem_rev("這房仲不錯喔");
		// resRecVO4.setRtr_rate(4);
		//
		// dao.update_kuei2(resRecVO4);
		// System.out.println("update_ekuei 成功");
		// --------------------------------------------------------------------------------------------
		// dao.delete("RR171030-00005");
		// dao.delete("RR171028-00007");

		// --------------------------------------------------------------------------------------------
//		ResRecVO resRecVO5 = dao.findByPrimaryKey("RR" + upsdfromat.format(new Date()) + "-00001");
//		System.out.println("-------findByPrimaryKey-------");
//		System.out.println(resRecVO5.getResr_no());
//		System.out.println(resRecVO5.getMem_no());
//		System.out.println(resRecVO5.getHouse_no());
//		System.out.println(resRecVO5.getRtr_no());
//		System.out.println(sdfromat.format(resRecVO5.getResr_ord()));
//		System.out.println(resRecVO5.getResr_states());
//		System.out.println(resRecVO5.getResr_date());
//		System.out.println(resRecVO5.getResr_period());
//		System.out.println(resRecVO5.getResr_hpric());
//		System.out.println(resRecVO5.getResr_hsize());
//		System.out.println(resRecVO5.getResr_mgt());
//		System.out.println(resRecVO5.getResr_puc());
//		System.out.println(resRecVO5.getResr_str());
//		System.out.println(resRecVO5.getResr_wall());
//		System.out.println(resRecVO5.getResr_ltg());
//		System.out.println(resRecVO5.getResr_ven());
//		System.out.println(resRecVO5.getResr_tc());
//		System.out.println(resRecVO5.getResr_lc());
//		System.out.println(resRecVO5.getHse_rev());
//		System.out.println(resRecVO5.getMem_rate());
//		System.out.println(resRecVO5.getMem_rev());
//		System.out.println(resRecVO5.getRtr_rate());
//		System.out.println("---------------------");
		//
		// --------------------------------------------------------------------------------------------
//		 List<ResRecVO> list = dao.getMem_kuei("MB00000002");
//		 for (ResRecVO aResR : list) {
//		 System.out.println("--------getMem_kuei---------");
//		 System.out.println(aResR.getResr_no());
//		 System.out.println(aResR.getMem_no());
//		 System.out.println(aResR.getHouse_no());
//		 System.out.println(aResR.getRtr_no());
//		 System.out.println(aResR.getResr_states());
//		 System.out.println(aResR.getResr_date());
//		 System.out.println(aResR.getResr_period());
//		
//		 System.out.println("---------------------");
//		 System.out.println();
//		 }
		//
		// --------------------------------------------------------------------------------------------
		// List<ResRecVO> list = dao.getAll();
		// int count = 0;
		// for (ResRecVO aResR : list) {
		// System.out.println("--------getAll" + ++count + "---------");
		// System.out.println(aResR.getResr_no());
		// System.out.println(aResR.getMem_no());
		// System.out.println(aResR.getHouse_no());
		// System.out.println(aResR.getRtr_no());
		// System.out.println(sdfromat.format(aResR.getResr_ord()));
		// System.out.println(aResR.getResr_states());
		// System.out.println(aResR.getResr_date());
		// System.out.println(aResR.getResr_period());
		// System.out.println(aResR.getResr_hpric());
		// System.out.println(aResR.getResr_hsize());
		// System.out.println(aResR.getResr_mgt());
		// System.out.println(aResR.getResr_puc());
		// System.out.println(aResR.getResr_str());
		// System.out.println(aResR.getResr_wall());
		// System.out.println(aResR.getResr_ltg());
		// System.out.println(aResR.getResr_ven());
		// System.out.println(aResR.getResr_tc());
		// System.out.println(aResR.getResr_lc());
		// System.out.println(aResR.getHse_rev());
		// System.out.println(aResR.getMem_rate());
		// System.out.println(aResR.getMem_rev());
		// System.out.println(aResR.getRtr_rate());
		//
		// System.out.println("---------------------");
		// System.out.println();
		// }
	}

}

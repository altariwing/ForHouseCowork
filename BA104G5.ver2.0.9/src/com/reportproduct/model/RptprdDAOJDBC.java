package com.reportproduct.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RptprdDAOJDBC implements RptprdDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104G5";
	String passwd = "ba104g5";
	
	private static final String INSERT = 
			"INSERT INTO ReportProduct (rp_no,mem_no,prd_no,rp_reason,rp_state)"
			+ "VALUES ('RP'||(LPAD(TO_CHAR(RP_SEQ.NEXTVAL),8,'0')),?,?,?,?)";
	private static final String UPDATE = 
			"UPDATE ReportProduct SET emp_no=?, rp_result=?, rp_state=? WHERE rp_no=?";
	private static final String GET_BY_STATE = "SELECT * FROM ReportProduct WHERE rp_state=?";
	private static final String GET_ALL = "SELECT * FROM ReportProduct";
	
	@Override
	public void insert(RptprdVO rptprdVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, rptprdVO.getMem_no());
			pstmt.setString(2, rptprdVO.getPrd_no());
			pstmt.setString(3, rptprdVO.getRp_reason());
			pstmt.setString(4, "處理中");
			
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
	public void update(RptprdVO rptprdVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, rptprdVO.getEmp_no());
			pstmt.setString(2, rptprdVO.getRp_result());
			pstmt.setString(3, rptprdVO.getRp_state());
			pstmt.setString(4, rptprdVO.getRp_no());
			
			pstmt.executeUpdate();
		
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public List<RptprdVO> getByState(String rp_state) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RptprdVO> list = new ArrayList<RptprdVO>();
		RptprdVO rptprdVO = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_STATE);
			
			pstmt.setString(1, rp_state);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				rptprdVO = new RptprdVO();
				rptprdVO.setRp_no(rs.getString("rp_no"));
				rptprdVO.setMem_no(rs.getString("mem_no"));
				rptprdVO.setPrd_no(rs.getString("prd_no"));
				rptprdVO.setRp_reason(rs.getString("rp_reason"));
				rptprdVO.setRp_result(rs.getString("rp_result"));
				rptprdVO.setRp_state(rs.getString("rp_state"));
				rptprdVO.setRp_time(rs.getTimestamp("rp_time"));
				list.add(rptprdVO);
			}
		
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(getByState出問題了) " + se.getMessage());
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
		return list;
	}

	@Override
	public List<RptprdVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RptprdVO> list = new ArrayList<RptprdVO>();
		RptprdVO rptprdVO = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				rptprdVO = new RptprdVO();
				rptprdVO.setRp_no(rs.getString("rp_no"));
				rptprdVO.setMem_no(rs.getString("mem_no"));
				rptprdVO.setPrd_no(rs.getString("prd_no"));
				rptprdVO.setRp_reason(rs.getString("rp_reason"));
				rptprdVO.setRp_result(rs.getString("rp_result"));
				rptprdVO.setRp_state(rs.getString("rp_state"));
				rptprdVO.setRp_time(rs.getTimestamp("rp_time"));
				list.add(rptprdVO);
			}
		
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(getAll出問題了) " + se.getMessage());
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
		return list;
	}
	
	
	public static void main(String[] args) {
		RptprdDAOJDBC dao = new RptprdDAOJDBC();
		RptprdVO rptprdVO = new RptprdVO();
		
		//insert
//		rptprdVO.setMem_no("MB00000003");
//		rptprdVO.setPrd_no("PD00000036");
//		rptprdVO.setRp_reason("就是要檢舉");
//		dao.insert(rptprdVO);
		
		//update
//		rptprdVO.setEmp_no("EM00000002");
//		rptprdVO.setRp_result("就是爽");
//		rptprdVO.setRp_state("已結案");
//		rptprdVO.setRp_no("RP00000003");
//		dao.update(rptprdVO);
		
		//getByState
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd 'at' hh:mm:ss a");
		SimpleDateFormat ft2 = new SimpleDateFormat ("E yyyy-MM-dd 'at' hh:mm:ss a");
		SimpleDateFormat ft3 = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss a E");
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		List<RptprdVO> list = dao.getByState("已結案");
		for (RptprdVO rptprdVO1 : list) {
			System.out.println(rptprdVO1.getRp_no());
			System.out.println(rptprdVO1.getMem_no());
			System.out.println(rptprdVO1.getPrd_no());
			System.out.println(rptprdVO1.getEmp_no());
			System.out.println(rptprdVO1.getRp_reason());
			System.out.println(rptprdVO1.getRp_result());
			System.out.println(rptprdVO1.getRp_time());
			System.out.println(ft.format(rptprdVO1.getRp_time()));
			System.out.println(ft2.format(rptprdVO1.getRp_time()));
			System.out.println(ft3.format(rptprdVO1.getRp_time()));
			System.out.println(sdf.format(rptprdVO1.getRp_time()));
		}
	}
	
}

package com.reportrealtor.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.reportproduct.model.RptprdDAOJDBC;
import com.reportproduct.model.RptprdVO;

public class RptrtrDAOJDBC implements RptrtrDAO_interface {
	
	private static DataSource ds = null;
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104G5";
	String passwd = "ba104g5";
	
	private static final String INSERT = 
			"INSERT INTO ReportRealtor (rr_no,mem_no,rtr_no,rr_reason,rr_state)"
			+ "VALUES ('RP'||(LPAD(TO_CHAR(RP_SEQ.NEXTVAL),8,'0')),?,?,?,?)";
	private static final String UPDATE = 
			"UPDATE ReportRealtor SET emp_no=?, rr_result=?, rr_state=? WHERE rr_no=?";
	private static final String GET_BY_STATE = "SELECT * FROM ReportRealtor WHERE rr_state=?";
	private static final String GET_ALL = "SELECT * FROM ReportRealtor";

	@Override
	public void insert(RptrtrVO rptrtrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, rptrtrVO.getMem_no());
			pstmt.setString(2, rptrtrVO.getRtr_no());
			pstmt.setString(3, rptrtrVO.getRr_reason());
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
	public void update(RptrtrVO rptrtrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, rptrtrVO.getEmp_no());
			pstmt.setString(2, rptrtrVO.getRr_result());
			pstmt.setString(3, rptrtrVO.getRr_state());
			pstmt.setString(4, rptrtrVO.getRr_no());
			
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
	public List<RptrtrVO> getByState(String rr_state) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RptrtrVO> list = new ArrayList<RptrtrVO>();
		RptrtrVO rptrtrVO = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_STATE);
			
			pstmt.setString(1, rr_state);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				rptrtrVO = new RptrtrVO();
				rptrtrVO.setRr_no(rs.getString("rr_no"));
				rptrtrVO.setMem_no(rs.getString("mem_no"));
				rptrtrVO.setEmp_no(rs.getString("emp_no"));
				rptrtrVO.setRtr_no(rs.getString("rtr_no"));
				rptrtrVO.setRr_reason(rs.getString("rr_reason"));
				rptrtrVO.setRr_result(rs.getString("rr_result"));
				rptrtrVO.setRr_state(rs.getString("rr_state"));
				rptrtrVO.setRr_time(rs.getTimestamp("rr_time"));
				list.add(rptrtrVO);
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
	public List<RptrtrVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RptrtrVO> list = new ArrayList<RptrtrVO>();
		RptrtrVO rptrtrVO = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				rptrtrVO = new RptrtrVO();
				rptrtrVO.setRr_no(rs.getString("rr_no"));
				rptrtrVO.setMem_no(rs.getString("mem_no"));
				rptrtrVO.setEmp_no(rs.getString("emp_no"));
				rptrtrVO.setRtr_no(rs.getString("rtr_no"));
				rptrtrVO.setRr_reason(rs.getString("rr_reason"));
				rptrtrVO.setRr_result(rs.getString("rr_result"));
				rptrtrVO.setRr_state(rs.getString("rr_state"));
				rptrtrVO.setRr_time(rs.getTimestamp("rr_time"));
				list.add(rptrtrVO);
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
		RptrtrDAOJDBC dao = new RptrtrDAOJDBC();
		RptrtrVO rptrtrVO = new RptrtrVO();
		
		//insert
		rptrtrVO.setMem_no("MB00000003");
		rptrtrVO.setRtr_no("RT00000012");
		rptrtrVO.setRr_reason("就是要檢舉");
		dao.insert(rptrtrVO);
		
		//update
		rptrtrVO.setEmp_no("EM00000002");
		rptrtrVO.setRr_result("就是爽");
		rptrtrVO.setRr_state("已結案");
		rptrtrVO.setRr_no("RR00000003");
		dao.update(rptrtrVO);
		
		//getByState
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd 'at' hh:mm:ss a");
		SimpleDateFormat ft2 = new SimpleDateFormat ("E yyyy-MM-dd 'at' hh:mm:ss a");
		SimpleDateFormat ft3 = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss a E");
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
		List<RptrtrVO> list = dao.getByState("已結案");
		for (RptrtrVO rptrtrVO1 : list) {
			System.out.println(rptrtrVO1.getRr_no());
			System.out.println(rptrtrVO1.getMem_no());
			System.out.println(rptrtrVO1.getRtr_no());
			System.out.println(rptrtrVO1.getEmp_no());
			System.out.println(rptrtrVO1.getRr_reason());
			System.out.println(rptrtrVO1.getRr_result());
			System.out.println(rptrtrVO1.getRr_time());
			System.out.println(ft.format(rptrtrVO1.getRr_time()));
			System.out.println(ft2.format(rptrtrVO1.getRr_time()));
			System.out.println(ft3.format(rptrtrVO1.getRr_time()));
			System.out.println(sdf.format(rptrtrVO1.getRr_time()));
		}
	}
	
	

}

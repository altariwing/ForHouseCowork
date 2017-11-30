package com.reportrealtor.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.reportproduct.model.RptprdVO;

public class RptrtrDAO implements RptrtrDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, rptrtrVO.getMem_no());
			pstmt.setString(2, rptrtrVO.getRtr_no());
			pstmt.setString(3, rptrtrVO.getRr_reason());
			pstmt.setString(4, "處理中");
			
			pstmt.executeUpdate();
		
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
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, rptrtrVO.getEmp_no());
			pstmt.setString(2, rptrtrVO.getRr_result());
			pstmt.setString(3, rptrtrVO.getRr_state());
			pstmt.setString(4, rptrtrVO.getRr_no());
			
			pstmt.executeUpdate();
		
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
			con = ds.getConnection();
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
			con = ds.getConnection();
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

}

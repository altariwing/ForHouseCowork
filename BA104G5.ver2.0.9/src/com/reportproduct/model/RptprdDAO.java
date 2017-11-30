package com.reportproduct.model;

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

public class RptprdDAO implements RptprdDAO_interface {

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, rptprdVO.getMem_no());
			pstmt.setString(2, rptprdVO.getPrd_no());
			pstmt.setString(3, rptprdVO.getRp_reason());
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
	public void update(RptprdVO rptprdVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, rptprdVO.getEmp_no());
			pstmt.setString(2, rptprdVO.getRp_result());
			pstmt.setString(3, rptprdVO.getRp_state());
			pstmt.setString(4, rptprdVO.getRp_no());
			
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
	public List<RptprdVO> getByState(String rp_state) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RptprdVO> list = new ArrayList<RptprdVO>();
		RptprdVO rptprdVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_STATE);
			
			pstmt.setString(1, rp_state);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				rptprdVO = new RptprdVO();
				rptprdVO.setRp_no(rs.getString("rp_no"));
				rptprdVO.setMem_no(rs.getString("mem_no"));
				rptprdVO.setEmp_no(rs.getString("emp_no"));
				rptprdVO.setPrd_no(rs.getString("prd_no"));
				rptprdVO.setRp_reason(rs.getString("rp_reason"));
				rptprdVO.setRp_result(rs.getString("rp_result"));
				rptprdVO.setRp_state(rs.getString("rp_state"));
				rptprdVO.setRp_time(rs.getTimestamp("rp_time"));
				list.add(rptprdVO);
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
	public List<RptprdVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RptprdVO> list = new ArrayList<RptprdVO>();
		RptprdVO rptprdVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				rptprdVO = new RptprdVO();
				rptprdVO.setRp_no(rs.getString("rp_no"));
				rptprdVO.setMem_no(rs.getString("mem_no"));
				rptprdVO.setEmp_no(rs.getString("emp_no"));
				rptprdVO.setPrd_no(rs.getString("prd_no"));
				rptprdVO.setRp_reason(rs.getString("rp_reason"));
				rptprdVO.setRp_result(rs.getString("rp_result"));
				rptprdVO.setRp_state(rs.getString("rp_state"));
				rptprdVO.setRp_time(rs.getTimestamp("rp_time"));
				list.add(rptprdVO);
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

package com.prdcategory.model;

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

public class PcDAO implements PcDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = "INSERT INTO PRD_Category VALUES('PC'||(LPAD(to_char(PRDT_CATE_SEQ.NEXTVAL),8,'0')),?)";
	private static final String UPDATE = "UPDATE PRD_Category SET cate_name=? WHERE cate_no=?";
	private static final String FIND_BY_NO = "SELECT * FROM PRD_Category WHERE cate_no=?";
	private static final String FIND_BY_NAME = "SELECT * FROM PRD_Category WHERE cate_name=?";
	private static final String GET_ALL = "SELECT * FROM PRD_Category";

	@Override
	public void insert(String cate_name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, cate_name);
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
	public void update(PcVO pcVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, pcVO.getCate_name());
			pstmt.setString(2, pcVO.getCate_no());
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(update出問題!) " + se.getMessage());
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
	public PcVO findByNo(String cate_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PcVO pcVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_NO);

			pstmt.setString(1, cate_no);
			rs = pstmt.executeQuery();
			
			pcVO = new PcVO();
			while (rs.next()) {
				pcVO.setCate_no(rs.getString("cate_no"));
				pcVO.setCate_name(rs.getString("cate_name"));
				
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(findByNo出問題!) " + se.getMessage());
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
		return pcVO;
	}

	@Override
	public PcVO findByName(String cate_name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PcVO pcVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_NAME);

			pstmt.setString(1, cate_name);
			rs = pstmt.executeQuery();
			
			pcVO = new PcVO();
			while (rs.next()) {
				pcVO.setCate_no(rs.getString("cate_no"));
				pcVO.setCate_name(rs.getString("cate_name"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(findByName出問題!) " + se.getMessage());
			
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
		return pcVO;
	}

	@Override
	public List<PcVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<PcVO> list = new ArrayList<PcVO>();
		PcVO pcVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				pcVO = new PcVO();
				pcVO.setCate_no(rs.getString("cate_no"));
				pcVO.setCate_name(rs.getString("cate_name"));
				list.add(pcVO);
			}


		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(findByName出問題!) " + se.getMessage());
			
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

}

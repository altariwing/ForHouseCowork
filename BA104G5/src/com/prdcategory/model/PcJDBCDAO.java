package com.prdcategory.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PcJDBCDAO implements PcDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104G5";
	String passwd = "ba104g5";
	
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, cate_name);
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
	public void update(PcVO pcVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, pcVO.getCate_name());
			pstmt.setString(2, pcVO.getCate_no());
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_NO);

			pstmt.setString(1, cate_no);
			rs = pstmt.executeQuery();
			
			pcVO = new PcVO();
			while (rs.next()) {
				pcVO.setCate_no(rs.getString("cate_no"));
				pcVO.setCate_name(rs.getString("cate_name"));
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_NAME);

			pstmt.setString(1, cate_name);
			rs = pstmt.executeQuery();
			
			pcVO = new PcVO();
			while (rs.next()) {
				pcVO.setCate_no(rs.getString("cate_no"));
				pcVO.setCate_name(rs.getString("cate_name"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				pcVO = new PcVO();
				pcVO.setCate_no(rs.getString("cate_no"));
				pcVO.setCate_name(rs.getString("cate_name"));
				list.add(pcVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	
	public static void main(String[] args) {
		PcJDBCDAO dao = new PcJDBCDAO();
		PcVO pcVO1 = new PcVO();
		
		//建立新類別
//		dao.insert("床墊");
		
		//更新類別名稱
//		pcVO1.setCate_name("床床");
//		pcVO1.setCate_no("PC00000004");
//		dao.update(pcVO1);
		
		//findByNo
//		pcVO1 = dao.findByNo("PC00000003");
//		System.out.println("No: " + pcVO1.getCate_no());
//		System.out.println("Name: " + pcVO1.getCate_name());
		
		//findByName
//		pcVO1 = dao.findByName("櫃子");
//		System.out.println("No: " + pcVO1.getCate_no());
//		System.out.println("Name: " + pcVO1.getCate_name());
		
		//getALL
		List<PcVO> list = dao.getAll();
		for (PcVO pcVO2 : list) {
			System.out.print("cate_no:" + pcVO2.getCate_no() + "\t");
			System.out.println("cate_name:" + pcVO2.getCate_name());
		}
	}
}

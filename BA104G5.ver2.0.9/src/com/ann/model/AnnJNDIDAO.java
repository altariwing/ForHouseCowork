package com.ann.model;

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

public class AnnJNDIDAO implements AnnDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G5");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
	}

	private static final String INSERT_STMT = 
			"INSERT INTO ANN (ANN_No, ANN_Title, ANN_Content, ANN_State, EMP_No) VALUES(seq_ann.NEXTVAL, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
			"UPDATE ANN SET ANN_Title=?, ANN_Content=?, ANN_State=?, EMP_No=? WHERE ANN_No = ?";
	private static final String DELETE_STMT = 
			"DELETE FROM ANN WHERE ANN_No= ?";
	private static final String GET_ONE_STMT = 
			"SELECT ANN_No, ANN_Title, ANN_Content, ANN_State, to_char(ANN_Date,'yyyy-mm-dd')ANN_Date, EMP_No FROM ANN WHERE ANN_No = ?";
	private static final String GET_ALL_STMT = 
			"SELECT ANN_No, ANN_Title, ANN_Content, ANN_State, to_char(ANN_Date,'yyyy-mm-dd')ANN_Date, EMP_No FROM ANN ORDER BY ANN_No";

	// 新增
	@Override
	public void insert(AnnVO annVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			con.setAutoCommit(false);

			pstmt.setString(1, annVO.getAnn_title());
			pstmt.setString(2, annVO.getAnn_content());
			pstmt.setString(3, annVO.getAnn_state());
			pstmt.setString(4, annVO.getEmp_no());

			int rowCount = pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("ANN_INSERT_STMT: " + rowCount + "筆");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	// 修改以Ann_No
	@Override
	public void update(AnnVO annVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			con.setAutoCommit(false);

			pstmt.setString(1, annVO.getAnn_title());
			pstmt.setString(2, annVO.getAnn_content());
			pstmt.setString(3, annVO.getAnn_state());
			pstmt.setString(4, annVO.getEmp_no());
			pstmt.setInt(5, annVO.getAnn_no());

			int rowCount = pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("ANN_UPDATE_STMT: " + rowCount + "筆");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	// 刪除以Ann_No
	@Override
	public void delete(Integer ann_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);

			con.setAutoCommit(false);

			pstmt.setInt(1, ann_no);

			int rowCount = pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("ANN_DELETE_STMT: " + rowCount + "筆");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	// 查以Ann_No
	@Override
	public AnnVO findByPrimaryKey(Integer ann_no) {

		AnnVO annvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, ann_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				annvo = new AnnVO();
				annvo.setAnn_no(rs.getInt("Ann_No"));
				annvo.setAnn_title(rs.getString("ANN_Title"));
				annvo.setAnn_content(rs.getString("ANN_Content"));
				annvo.setAnn_state(rs.getString("ANN_State"));
				annvo.setAnn_date(rs.getDate("ANN_Date"));
				annvo.setEmp_no(rs.getString("EMP_No"));
			}
			System.out.println("ANN_findByPrimaryKey");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException re) {
					re.printStackTrace(System.err);
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

		return annvo;
	}

	// 查全部
	@Override
	public List<AnnVO> getAll() {
		List<AnnVO> annList = new ArrayList<AnnVO>();
		AnnVO annvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int counts = 0;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				annvo = new AnnVO();
				annvo.setAnn_no(rs.getInt("ANN_No"));
				annvo.setAnn_title(rs.getString("ANN_Title"));
				annvo.setAnn_content(rs.getString("ANN_Content"));
				annvo.setAnn_state(rs.getString("ANN_State"));
				annvo.setAnn_date(rs.getDate("ANN_Date"));
				annvo.setEmp_no(rs.getString("EMP_No"));
				annList.add(annvo);
				counts++;
			}

			System.out.println("ANN_ANN_findAll: " + counts + "筆");

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException re) {
					re.printStackTrace(System.err);
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

		return annList;
	}
}

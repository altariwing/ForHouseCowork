package com.bk_function.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Bk_functionJDBCDAO implements Bk_functionDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104";
	String passwd = "123456";
	private static final String INSERT = "INSERT INTO BK_FUNCTION(BKF_NO, BKF_NAME, BKF_DSB, BKF_STATE)"
			+ "VALUES(BKF_NO_SEQ.NEXTVAL, ?, ? ,?)";
	private static final String UPDATE = "UPDATE BK_FUNCTION set BKF_NAME=?, BKF_DSB=?, BKF_STATE=? where BKF_NO = ?";
	private static final String DELETE = "DELETE FROM BK_FUNCTION where BKF_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM BK_FUNCTION WHERE BKF_NO = ?";
	private static final String GET_ALL = "SELECT * FROM BK_FUNCTION";

	@Override
	public void insert(Bk_functionVO Bk_functionVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, Bk_functionVO.getBkf_name());
			pstmt.setString(2, Bk_functionVO.getBkf_dsb());
			pstmt.setString(3, Bk_functionVO.getBkf_state());

			updateCount = pstmt.executeUpdate();
			System.out.println("成功新增" + updateCount + "筆資料");

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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} // end finally
	}

	@Override
	public void update(Bk_functionVO Bk_functionVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, Bk_functionVO.getBkf_name());
			pstmt.setString(2, Bk_functionVO.getBkf_dsb());
			pstmt.setString(3, Bk_functionVO.getBkf_state());
			pstmt.setInt(4, Bk_functionVO.getBkf_no());

			updateCount = pstmt.executeUpdate();
			System.out.println("成功修改" + updateCount + "筆資料");

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
		} // end finally

	}

	@Override
	public void delete(Integer bkf_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, bkf_no);

			updateCount = pstmt.executeUpdate();
			System.out.println("成功刪除" + updateCount + "筆資料");

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
		} // end finally

	}

	@Override
	public Bk_functionVO findByPK(Integer bkf_no) {
		Bk_functionVO bk_functionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setInt(1, bkf_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bk_functionVO = new Bk_functionVO();
				bk_functionVO.setBkf_no(rs.getInt("bkf_no"));
				bk_functionVO.setBkf_name(rs.getString("bkf_name"));
				bk_functionVO.setBkf_dsb(rs.getString("bkf_dsb"));
				bk_functionVO.setBkf_state(rs.getString("bkf_state"));
				bk_functionVO.setBkf_update(rs.getTimestamp("bkf_update"));

			}

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
		} // end finally

		return bk_functionVO;

	}

	@Override
	public List<Bk_functionVO> getAll() {
		List<Bk_functionVO> list = new ArrayList<Bk_functionVO>();
		Bk_functionVO bk_functionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				bk_functionVO = new Bk_functionVO();
				bk_functionVO.setBkf_no(rs.getInt("bkf_no"));
				bk_functionVO.setBkf_name(rs.getString("bkf_name"));
				bk_functionVO.setBkf_dsb(rs.getString("bkf_dsb"));
				bk_functionVO.setBkf_state(rs.getString("bkf_state"));
				bk_functionVO.setBkf_update(rs.getTimestamp("bkf_update"));

				list.add(bk_functionVO); // Store the row in the list
			}
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
		} // end finally

		return list;
	}

}

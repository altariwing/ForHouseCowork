package com.ann.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AnnJDBCDAO implements AnnDAO_interface {

	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String userid = "BA104G5";
	private static final String password = "ba104g5";

	private static final String INSERT_STMT = "INSERT INTO ANN (ANN_No, ANN_Title, ANN_Content, ANN_State, EMP_NO) VALUES(seq_ann.NEXTVAL, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE ANN SET ANN_Title=?, ANN_Content=?, ANN_State=?, EMP_NO=? WHERE ANN_No = ?";
	private static final String DELETE_STMT = "DELETE FROM ANN WHERE ANN_No= ?";
	private static final String GET_ONE_STMT = "SELECT ANN_No, ANN_Title, ANN_Content, ANN_State, to_char(ANN_Date,'yyyy-mm-dd')ANN_Date, EMP_NO FROM ANN WHERE ANN_No = ?";
	private static final String GET_ALL_STMT = "SELECT ANN_No, ANN_Title, ANN_Content, ANN_State, to_char(ANN_Date,'yyyy-mm-dd')ANN_Date, EMP_NO FROM ANN ORDER BY ANN_No";

	// 新增
	@Override
	public void insert(AnnVO annVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(DELETE_STMT);

			con.setAutoCommit(false);

			pstmt.setInt(1, ann_no);

			int rowCount = pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			System.out.println("ANN_DELETE_STMT: " + rowCount + "筆");

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
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

			System.out.println("ANN_findAll: " + counts + "筆");

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
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

	// 測試
	public static void main(String[] args) {
		AnnJDBCDAO dao = new AnnJDBCDAO();

		// 新增
//		AnnVO annVO1 = new AnnVO();
//		annVO1.setAnn_title("新增公告標題");
//		annVO1.setAnn_content("新增的公告內容");
//		annVO1.setAnn_state("公告中");
//		annVO1.setEmp_no("EM00000001");
//		dao.insert(annVO1);
//		System.out.println("---------------------------------");
//
//		// 修改
//		AnnVO annVO2 = new AnnVO();
//		annVO2.setAnn_title("修改公告標題");
//		annVO2.setAnn_content("修改的公告內容");
//		annVO2.setAnn_state("已撤銷");
//		annVO2.setEmp_no("EM00000001");
//		annVO2.setAnn_no(3001);
//		dao.update(annVO2);
//		System.out.println("---------------------------------");
//
//		// 刪除
//		dao.delete(3009);
//		System.out.println("---------------------------------");

		// 查ANN_No
		AnnVO annVO3 = dao.findByPrimaryKey(3001);
		System.out.println(annVO3.getAnn_no() + ",");
		System.out.println(annVO3.getAnn_title() + ",");
		System.out.println(annVO3.getAnn_content() + ",");
		System.out.println(annVO3.getAnn_state() + ",");
		System.out.println(annVO3.getAnn_date() + ",");
		System.out.println(annVO3.getEmp_no() + ",");
		System.out.println("---------------------------------");

		// 查全部
		List<AnnVO> annList = dao.getAll();
		for (AnnVO aAnn : annList) {
			System.out.println(aAnn.getAnn_no() + ",");
			System.out.println(aAnn.getAnn_title() + ",");
			System.out.println(aAnn.getAnn_content() + ",");
			System.out.println(aAnn.getAnn_state() + ",");
			System.out.println(aAnn.getAnn_date() + ",");
			System.out.println(aAnn.getEmp_no() + ",");
			System.out.println();
		}

	}

}

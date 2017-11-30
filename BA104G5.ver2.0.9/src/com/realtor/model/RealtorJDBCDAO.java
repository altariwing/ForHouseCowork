package com.realtor.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.article.model.ArticleVO;

import jdbc.util.CompositeQuery.RealtorFindByKeyWord;
import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Realtor;

public class RealtorJDBCDAO implements RealtorDAO_interface {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String userid = "BA104G5";
	private static final String password = "ba104g5";

	private static final String INSERT_STMT = "INSERT INTO Realtor(RTR_NO, RTR_ID, RTR_PSW, RTR_NAME, RTR_PHOTO, RTR_AREA, RTR_INTRO, RTR_IDNO, RE_NO, Rtr_State) "
			+ " VALUES('RT'||(LPAD(to_char(RTR_SEQ.NEXTVAL),8,'0')), ?, ?, ?, ?, ?, ?, ?, ?, 'OFF')";
	// private static final String UPDATE_STMT = "UPDATE Realtor SET RTR_ID=?,
	// RTR_NAME=?, RTR_PHOTO=?, RTR_AREA=?, RTR_INTRO=? WHERE RTR_NO =
	// ?";//預留給之後可能要改服務地區，還有emailID帳號
	private static final String UPDATE_STMT = "UPDATE Realtor SET RTR_NAME=?, RTR_PHOTO=?, RTR_PSW=?, RTR_INTRO=? WHERE RTR_NO = ?";// 修改姓名、照片、簡介用
	private static final String GET_ONE_STMT = "SELECT RTR_NO, RTR_ID, RTR_PSW, RTR_NAME, RTR_PHOTO, RTR_AREA, RTR_INTRO, RTR_IDNO, RE_NO, RTR_STATE FROM Realtor WHERE RTR_NO = ?";
	private static final String GET_ALL_STMT = "SELECT RTR_NO, RTR_ID, RTR_PSW, RTR_NAME, RTR_PHOTO, RTR_AREA, RTR_INTRO, RTR_IDNO, RE_NO, RTR_STATE FROM Realtor ORDER BY RTR_NO";
	private static final String UPDATE_FOR_PHOTO = "UPDATE Realtor SET RTR_PHOTO=?, RTR_INTRO=? WHERE RTR_NO=?";

	// 找房仲ID
	private static final String GET_ONE_BY_ID = "SELECT RTR_NO, RTR_ID, RTR_PSW, RTR_NAME, RTR_PHOTO, RTR_AREA, RTR_INTRO, RTR_IDNO, RE_NO, RTR_STATE FROM Realtor WHERE RTR_ID = ? ORDER BY RTR_ID";
	// 更換密碼
	private static final String CHANGE_PASSWORD = "UPDATE Realtor SET RTR_PSW=? WHERE RTR_NO= ?";
	// 更換房仲狀態
	private static final String SET_RTR_STATE = "UPDATE Realtor SET RTR_STATE=? WHERE RTR_NO= ?";
	// 找房仲名稱
	private static final String GET_ONE_BY_NAME = "SELECT RTR_NO, RTR_ID, RTR_PSW, RTR_NAME, RTR_PHOTO, RTR_AREA, RTR_INTRO, RTR_IDNO, RE_NO, RTR_STATE FROM Realtor WHERE RTR_NAME = ? ORDER BY RTR_NO";
	// 列出所有房仲ID(email)
	private static final String GET_ID_LIST = "SELECT RTR_ID FROM Realtor";
	// 房仲FB登入的特別insert
	private static final String FB_INSERT = "INSERT INTO Realtor(RTR_NO, RTR_ID, RTR_PSW, RTR_NAME, RTR_PHOTO, RTR_AREA, RTR_INTRO, RTR_IDNO, RE_NO, Rtr_State) "
			+ " VALUES('RT'||(LPAD(to_char(RTR_SEQ.NEXTVAL),8,'0')), ?, ?, ?, ?, ?, ?, ?, ?, 'ON')";

	// 找房仲文章
	private static final String GET_ART_BY_RTRNO = "SELECT Article_No, Rtr_No, Article_body, to_char(Post_Date, 'yyyy-mm-dd hh:mi:ss')Post_Date, Update_date, Article_State FROM Article WHERE Rtr_No=? ORDER BY Post_date DESC";
	// 找房仲文章沒用到
	// private static final String GET_ART_BY_RTRNO = "SELECT Article_No,
	// Rtr_No, Article_body, to_char(Post_Date, 'yyyy-mm-dd hh:mi:ss')Post_Date,
	// Update_date, Article_State FROM Article WHERE Rtr_no='RT00000005' ORDER
	// BY POST_DATE DESC";

	// 新增
	@Override
	public void insert(RealtorVO realtorVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			con.setAutoCommit(false);

			pstmt.setString(1, realtorVO.getRtr_id());
			pstmt.setString(2, realtorVO.getRtr_psw());
			pstmt.setString(3, realtorVO.getRtr_name());
			pstmt.setBytes(4, realtorVO.getRtr_photo());
			pstmt.setString(5, realtorVO.getRtr_area());
			pstmt.setString(6, realtorVO.getRtr_intro());
			pstmt.setString(7, realtorVO.getRtr_idno());
			pstmt.setString(8, realtorVO.getRe_no());

			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);

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

	// 修改，預留給之後可能要改服務地區，還有emailID帳號
	// @Override
	// public void update(RealtorVO realtorVO) {
	// Connection con = null;
	// PreparedStatement pstmt = null;
	//
	// try {
	// Class.forName(driver);
	// con = DriverManager.getConnection(url, userid, password);
	// pstmt = con.prepareStatement(UPDATE_STMT);
	//
	// con.setAutoCommit(false);
	//
	// pstmt.setString(1, realtorVO.getRtr_id());
	// pstmt.setString(2, realtorVO.getRtr_psw());
	// pstmt.setString(3, realtorVO.getRtr_name());
	// pstmt.setBytes(4, realtorVO.getRtr_photo());
	// pstmt.setString(5, realtorVO.getRtr_area());
	// pstmt.setString(6, realtorVO.getRtr_intro());
	// pstmt.setString(7, realtorVO.getRtr_idno());
	// pstmt.setString(8, realtorVO.getRe_no());
	// pstmt.setString(9, realtorVO.getRtr_state());
	// pstmt.setString(10, realtorVO.getRtr_no());
	//
	// pstmt.executeUpdate();
	// con.commit();
	// con.setAutoCommit(true);
	//
	// } catch (ClassNotFoundException ce) {
	// throw new RuntimeException("Couldn't load database driver. " +
	// ce.getMessage());
	// } catch (SQLException se) {
	// throw new RuntimeException("A database error occured. " +
	// se.getMessage());
	// } finally {
	// if (pstmt != null) {
	// try {
	// pstmt.close();
	// } catch (SQLException se) {
	// se.printStackTrace(System.err);
	// }
	// }
	// if (con != null) {
	// try {
	// con.close();
	// } catch (Exception e) {
	// e.printStackTrace(System.err);
	// }
	// }
	// }
	// }

	// 修改姓名，照片，簡介
	@Override
	public void update(RealtorVO realtorVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE_STMT);

			con.setAutoCommit(false);

			pstmt.setString(1, realtorVO.getRtr_name());
			pstmt.setBytes(2, realtorVO.getRtr_photo());
			pstmt.setString(3, realtorVO.getRtr_psw());
			pstmt.setString(4, realtorVO.getRtr_intro());
			pstmt.setString(5, realtorVO.getRtr_no());

			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);

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

	// 查單一
	@Override
	public RealtorVO findByPrimaryKey(String rtr_no) {
		RealtorVO realtorvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, rtr_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				realtorvo = new RealtorVO();
				realtorvo.setRtr_no(rs.getString("RTR_NO"));
				realtorvo.setRtr_id(rs.getString("RTR_ID"));
				realtorvo.setRtr_psw(rs.getString("RTR_PSW"));
				realtorvo.setRtr_name(rs.getString("RTR_NAME"));
				realtorvo.setRtr_photo(rs.getBytes("RTR_PHOTO"));
				realtorvo.setRtr_area(rs.getString("RTR_AREA"));
				realtorvo.setRtr_intro(rs.getString("RTR_INTRO"));
				realtorvo.setRtr_idno(rs.getString("RTR_IDNO"));
				realtorvo.setRe_no(rs.getString("RE_NO"));
				realtorvo.setRtr_state(rs.getString("RTR_STATE"));
			}

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

		return realtorvo;
	}

	// 查全部
	@Override
	public List<RealtorVO> getAll() {
		List<RealtorVO> realtorlist = new ArrayList<RealtorVO>();
		RealtorVO realtorvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				realtorvo = new RealtorVO();
				realtorvo.setRtr_no(rs.getString("RTR_NO"));
				realtorvo.setRtr_id(rs.getString("RTR_ID"));
				realtorvo.setRtr_psw(rs.getString("RTR_PSW"));
				realtorvo.setRtr_name(rs.getString("RTR_NAME"));
				realtorvo.setRtr_photo(rs.getBytes("RTR_PHOTO"));
				realtorvo.setRtr_area(rs.getString("RTR_AREA"));
				realtorvo.setRtr_intro(rs.getString("RTR_INTRO"));
				realtorvo.setRtr_idno(rs.getString("RTR_IDNO"));
				realtorvo.setRe_no(rs.getString("RE_NO"));
				realtorvo.setRtr_state(rs.getString("RTR_STATE"));
				realtorlist.add(realtorvo);
			}

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
		return realtorlist;
	}

	// 專門塞房仲照片以及簡介
	@Override
	public void updatePhoto(RealtorVO realtorVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(UPDATE_FOR_PHOTO);

			con.setAutoCommit(false);

			pstmt.setBytes(1, realtorVO.getRtr_photo());
			pstmt.setString(2, realtorVO.getRtr_intro());
			pstmt.setString(3, realtorVO.getRtr_no());

			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);

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

	// 找房仲ID
	@Override
	public RealtorVO findById(String rtr_id) {
		RealtorVO realtorvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_BY_ID);

			pstmt.setString(1, rtr_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				realtorvo = new RealtorVO();
				realtorvo.setRtr_no(rs.getString("RTR_NO"));
				realtorvo.setRtr_id(rs.getString("RTR_ID"));
				realtorvo.setRtr_psw(rs.getString("RTR_PSW"));
				realtorvo.setRtr_name(rs.getString("RTR_NAME"));
				realtorvo.setRtr_photo(rs.getBytes("RTR_PHOTO"));
				realtorvo.setRtr_area(rs.getString("RTR_AREA"));
				realtorvo.setRtr_intro(rs.getString("RTR_INTRO"));
				realtorvo.setRtr_idno(rs.getString("RTR_IDNO"));
				realtorvo.setRe_no(rs.getString("RE_NO"));
				realtorvo.setRtr_state(rs.getString("RTR_STATE"));
			}

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

		return realtorvo;
	}

	// 修改房仲密碼
	@Override
	public void changePassword(RealtorVO realtorVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(CHANGE_PASSWORD);

			con.setAutoCommit(false);

			pstmt.setString(1, realtorVO.getRtr_psw());
			pstmt.setString(2, realtorVO.getRtr_no());

			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);

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

	// 修改房仲狀態
	@Override
	public void changeRealtorState(RealtorVO realtorVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(SET_RTR_STATE);

			con.setAutoCommit(false);

			pstmt.setString(1, realtorVO.getRtr_state());
			pstmt.setString(2, realtorVO.getRtr_no());

			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);

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

	// 找單一房仲透過名字
	@Override
	public RealtorVO findByName(String rtr_name) {
		RealtorVO realtorvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_BY_NAME);

			pstmt.setString(1, rtr_name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				realtorvo = new RealtorVO();
				realtorvo.setRtr_no(rs.getString("RTR_NO"));
				realtorvo.setRtr_id(rs.getString("RTR_ID"));
				realtorvo.setRtr_psw(rs.getString("RTR_PSW"));
				realtorvo.setRtr_name(rs.getString("RTR_NAME"));
				realtorvo.setRtr_photo(rs.getBytes("RTR_PHOTO"));
				realtorvo.setRtr_area(rs.getString("RTR_AREA"));
				realtorvo.setRtr_intro(rs.getString("RTR_INTRO"));
				realtorvo.setRtr_idno(rs.getString("RTR_IDNO"));
				realtorvo.setRe_no(rs.getString("RE_NO"));
				realtorvo.setRtr_state(rs.getString("RTR_STATE"));
			}

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
		return realtorvo;
	}

	// 得到房仲Id(email)
	@Override
	public List<RealtorVO> getIdList() {
		List<RealtorVO> realtorlist = new ArrayList<RealtorVO>();
		RealtorVO realtorvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ID_LIST);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				realtorvo = new RealtorVO();
				realtorvo.setRtr_id(rs.getString("RTR_ID"));
				realtorlist.add(realtorvo);
			}

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
		return realtorlist;
	}// 得到房仲Id(email)結束

	// 房仲萬用複合查詢
	@Override
	public List<RealtorVO> getAll(Map<String, String[]> map) {

		List<RealtorVO> list = new ArrayList<RealtorVO>();
		RealtorVO realtorvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			String finalSQL = "SELECT * FROM REALTOR " + jdbcUtil_CompositeQuery_Realtor.get_WhereCondition(map)
					+ "ORDER BY RTR_NO";
			pstmt = con.prepareStatement(finalSQL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				realtorvo = new RealtorVO();
				realtorvo.setRtr_no(rs.getString("RTR_NO"));
				realtorvo.setRtr_id(rs.getString("RTR_ID"));
				realtorvo.setRtr_psw(rs.getString("RTR_PSW"));
				realtorvo.setRtr_name(rs.getString("RTR_NAME"));
				realtorvo.setRtr_photo(rs.getBytes("RTR_PHOTO"));
				realtorvo.setRtr_area(rs.getString("RTR_AREA"));
				realtorvo.setRtr_intro(rs.getString("RTR_INTRO"));
				realtorvo.setRtr_idno(rs.getString("RTR_IDNO"));
				realtorvo.setRe_no(rs.getString("RE_NO"));
				realtorvo.setRtr_state(rs.getString("RTR_STATE"));
				list.add(realtorvo);
			}
			System.out.println("●●finalSQL = " + finalSQL);

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
		return list;
	} // 房仲萬用複合查詢結束

	// 房仲萬用複合查詢ByKeyword
	@Override
	public List<RealtorVO> findByKeyword(String keyword) {

		List<RealtorVO> list = new ArrayList<RealtorVO>();
		RealtorVO realtorvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			String finalSQL = "select * from Realtor " + RealtorFindByKeyWord.getKeyWordSQL(keyword)
					+ " ORDER BY RTR_NO";
			pstmt = con.prepareStatement(finalSQL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				realtorvo = new RealtorVO();
				realtorvo.setRtr_no(rs.getString("RTR_NO"));
				realtorvo.setRtr_id(rs.getString("RTR_ID"));
				realtorvo.setRtr_psw(rs.getString("RTR_PSW"));
				realtorvo.setRtr_name(rs.getString("RTR_NAME"));
				realtorvo.setRtr_photo(rs.getBytes("RTR_PHOTO"));
				realtorvo.setRtr_area(rs.getString("RTR_AREA"));
				realtorvo.setRtr_intro(rs.getString("RTR_INTRO"));
				realtorvo.setRtr_idno(rs.getString("RTR_IDNO"));
				realtorvo.setRe_no(rs.getString("RE_NO"));
				realtorvo.setRtr_state(rs.getString("RTR_STATE"));
				list.add(realtorvo);
			}
			System.out.println("getKeywordSQL: " + finalSQL);

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
		return list;
	} // 房仲萬用複合查詢結束 ByKeyword

	// 房仲FB登入的特別insert
	@Override
	public void FBInsert(RealtorVO realtorVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(FB_INSERT);

			con.setAutoCommit(false);

			pstmt.setString(1, realtorVO.getRtr_id());
			pstmt.setString(2, realtorVO.getRtr_psw());
			pstmt.setString(3, realtorVO.getRtr_name());
			pstmt.setBytes(4, realtorVO.getRtr_photo());
			pstmt.setString(5, realtorVO.getRtr_area());
			pstmt.setString(6, realtorVO.getRtr_intro());
			pstmt.setString(7, realtorVO.getRtr_idno());
			pstmt.setString(8, realtorVO.getRe_no());

			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);

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
	}// 房仲FB登入的特別insert結束

	// 找房仲文章
	@Override
	public Set<ArticleVO> getArtByRtrNo(String rtr_no) {

		Set<ArticleVO> set = new LinkedHashSet<ArticleVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArticleVO articlevo = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ART_BY_RTRNO);

			pstmt.setString(1, rtr_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articlevo = new ArticleVO();
				articlevo.setArticle_no(rs.getString("Article_no"));
				articlevo.setRtr_no(rs.getString("Rtr_no"));
				articlevo.setPost_date(rs.getTimestamp("Post_date"));
				articlevo.setArticle_body(rs.getString("Article_body"));
				articlevo.setArticle_state(rs.getString("Article_State"));
				articlevo.setUpdate_date(rs.getDate("Update_Date"));
				set.add(articlevo);
			}

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
		} catch (SQLException se) {
			se.printStackTrace();
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
		return set;
	}// 找房仲文章結束

	public static void main(String[] args) throws IOException {
		RealtorJDBCDAO dao = new RealtorJDBCDAO();

		// 專門塞房仲照片以及簡介
		 for (int i = 1; i <= 12; i++) {
		 if (i <= 9) {
		 FileInputStream in = new
		 FileInputStream("WebContent/images/realtorphoto/realtor" + i +
		 ".jpg");
		 String reader = getLongString("WebContent/txt/realtor_txt/realtor" +
		 i + ".txt");
		 String rtr_no = "RT0000000" + i;
		 byte[] realtorpic = new byte[in.available()];
		 in.read(realtorpic);
		 RealtorVO vo4 = new RealtorVO();
		 vo4.setRtr_photo(realtorpic);
		 vo4.setRtr_intro(reader);
		 vo4.setRtr_no(rtr_no);
		 dao.updatePhoto(vo4);
		 in.close();
		 } else {
		 FileInputStream in = new
		 FileInputStream("WebContent/images/realtorphoto/realtor" + i +
		 ".jpg");
		 String reader = getLongString("WebContent/txt/realtor_txt/realtor" +
		 i + ".txt");
		 String rtr_no = "RT000000" + i;
		 byte[] realtorpic = new byte[in.available()];
		 in.read(realtorpic);
		 RealtorVO vo4 = new RealtorVO();
		 vo4.setRtr_photo(realtorpic);
		 vo4.setRtr_intro(reader);
		 vo4.setRtr_no(rtr_no);
		 dao.updatePhoto(vo4);
		 in.close();
		 }
		 }
		 System.out.println("-----------------------------------------------------------------------------------");

		FileInputStream in = new FileInputStream("WebContent/images/realtorphoto/realtor1.jpg");
		byte[] realtorpic = new byte[in.available()];
		in.read(realtorpic);
		in.close();

		// 新增
		// RealtorVO vo = new RealtorVO();
		// vo.setRtr_id("u9813019@yuntech.edu.tw");
		// vo.setRtr_psw("e124217388");
		// vo.setRtr_name("Cooper Kai");
		// vo.setRtr_photo(realtorpic);
		// vo.setRtr_area("中正區");
		// vo.setRtr_intro("大家好!!");
		// vo.setRtr_idno("E124217388");
		// vo.setRe_no("RE00000002");
		// dao.insert(vo);
		// System.out.println("===============================================");

		// 修改
//		RealtorVO vo2 = new RealtorVO();
//		vo2.setRtr_id("BA104G5@gmail.com");
//		vo2.setRtr_psw("cccccccccccccc");
//		vo2.setRtr_name("ccccccc");
//		vo2.setRtr_photo(realtorpic);
//		vo2.setRtr_area("中正區");
//		vo2.setRtr_intro("大家好!!");
//		vo2.setRtr_idno("A147845895");
//		vo2.setRe_no("RE00000001");
//		vo2.setRtr_state("ON");
//		vo2.setRtr_no("RT00000013");
//		dao.update(vo2);
//		System.out.println("===============================================");

		// 修改姓名，照片，簡介
		// RealtorVO vo2 = new RealtorVO();
		// vo2.setRtr_name("ccccccc");
		// vo2.setRtr_photo(realtorpic);
		// vo2.setRtr_intro("大家好!!");
		// vo2.setRtr_no("RT00000001");
		// dao.update(vo2);
		// System.out.println("===============================================");

		// 查單一
		// RealtorVO vo3 = dao.findByPrimaryKey("RT00000002");
		// System.out.println(vo3.getRtr_no());
		// System.out.println(vo3.getRtr_id());
		// System.out.println(vo3.getRtr_psw());
		// System.out.println(vo3.getRtr_name());
		// System.out.println(vo3.getRtr_photo());
		// System.out.println(vo3.getRtr_area());
		// System.out.println(vo3.getRtr_intro());
		// System.out.println(vo3.getRtr_idno());
		// System.out.println(vo3.getRe_no());
		// System.out.println(vo3.getRtr_state());
		// System.out.println("===============================================");

		// 查全部
		// List<RealtorVO> list = dao.getAll();
		// for (RealtorVO arealtor : list) {
		// System.out.println(arealtor.getRtr_no());
		// System.out.println(arealtor.getRtr_id());
		// System.out.println(arealtor.getRtr_psw());
		// System.out.println(arealtor.getRtr_name());
		// System.out.println(arealtor.getRtr_photo());
		// System.out.println(arealtor.getRtr_area());
		// System.out.println(arealtor.getRtr_intro());
		// System.out.println(arealtor.getRtr_idno());
		// System.out.println(arealtor.getRe_no());
		// System.out.println(arealtor.getRtr_state());
		// System.out.println();
		// }

		// 查房仲ID
		// RealtorVO vo3 = dao.findById("realtor002@gmail.com");
		// System.out.println(vo3.getRtr_no());
		// System.out.println(vo3.getRtr_id());
		// System.out.println(vo3.getRtr_psw());
		// System.out.println(vo3.getRtr_name());
		// System.out.println(vo3.getRtr_photo());
		// System.out.println(vo3.getRtr_area());
		// System.out.println(vo3.getRtr_intro());
		// System.out.println(vo3.getRtr_idno());
		// System.out.println(vo3.getRe_no());
		// System.out.println(vo3.getRtr_state());
		// System.out.println("===============================================");

		// 修改房仲密碼
		// RealtorVO vo4 = new RealtorVO();
		// vo4.setRtr_psw("123456");
		// vo4.setRtr_no("RT00000001");
		// dao.changePassword(vo4);
		// System.out.println("===============================================");

		// 修改房仲狀態
		// RealtorVO vo5 = new RealtorVO();
		// vo5.setRtr_state("ON");
		// vo5.setRtr_no("RT00000002");
		// dao.changeRealtorState(vo5);
		// System.out.println("===============================================");

		// 找單一房仲透過名字
		// RealtorVO vo6 = dao.findByName("張三豐");
		// System.out.println(vo6.getRtr_no());
		// System.out.println(vo6.getRtr_id());
		// System.out.println(vo6.getRtr_psw());
		// System.out.println(vo6.getRtr_name());
		// System.out.println(vo6.getRtr_photo());
		// System.out.println(vo6.getRtr_area());
		// System.out.println(vo6.getRtr_intro());
		// System.out.println(vo6.getRtr_idno());
		// System.out.println(vo6.getRe_no());
		// System.out.println(vo6.getRtr_state());
		// System.out.println("===============================================");

		// 得到房仲Id(email)
		// List<RealtorVO> list2 = dao.getIdList();
		// for (RealtorVO arealtorId : list2) {
		// System.out.println(arealtorId.getRtr_id());
		// System.out.println();
		// }

		// map萬用查詢
		// Map<String, String[]> map = new TreeMap<String, String[]>();
		// map.put("RTR_NO", new String[] { "RT00000001" });
		// map.put("RTR_ID", new String[] { "realtor001@gmail.com" });
		// map.put("RTR_NAME", new String[] { "葦小寶" });
		// map.put("RTR_AREA", new String[] { "區" });
		// map.put("RE_NO", new String[] { "RE00000001" });
		// List<RealtorVO> list = dao.getAll(map);
		// for (RealtorVO arealtor : list) {
		// System.out.println(arealtor.getRtr_no());
		// System.out.println(arealtor.getRtr_id());
		// System.out.println(arealtor.getRtr_psw());
		// System.out.println(arealtor.getRtr_name());
		// System.out.println(arealtor.getRtr_photo());
		// System.out.println(arealtor.getRtr_area());
		// System.out.println(arealtor.getRtr_intro());
		// System.out.println(arealtor.getRtr_idno());
		// System.out.println(arealtor.getRe_no());
		// System.out.println(arealtor.getRtr_state());
		// System.out.println();
		// }

		// 查詢房仲ByKeyword
		// String keyword = "森";
		// List<RealtorVO> list2 = dao.findByKeyword(keyword);
		// for (RealtorVO arealtor : list2) {
		// System.out.println(arealtor.getRtr_no());
		// System.out.println(arealtor.getRtr_id());
		// System.out.println(arealtor.getRtr_psw());
		// System.out.println(arealtor.getRtr_name());
		// System.out.println(arealtor.getRtr_photo());
		// System.out.println(arealtor.getRtr_area());
		// System.out.println(arealtor.getRtr_intro());
		// System.out.println(arealtor.getRtr_idno());
		// System.out.println(arealtor.getRe_no());
		// System.out.println(arealtor.getRtr_state());
		// System.out.println();
		// } // 查詢房仲ByKeyword結束

		// 找房仲文章
//		Set<ArticleVO> set = dao.getArtByRtrNo("RT00000005");
//		for (ArticleVO art : set) {
//			System.out.println(art.getArticle_no());
//			System.out.println(art.getRtr_no());
//			System.out.println(art.getArticle_body());
//			System.out.println(art.getPost_date());
//			System.out.println(art.getPost_date());
//			System.out.println(art.getArticle_state());
//		}

	}// 測試結束

	// 使用String 上傳房仲簡介用
	public static String getLongString(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		StringBuilder sb = new StringBuilder(); // StringBuffer is thread-safe!
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);
			sb.append("\n");
		}
		br.close();

		return sb.toString();
	}

}

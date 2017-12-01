package com.slr.model;

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

public class SlrDAO implements SlrDAO_interface {

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
			"INSERT INTO Seller (slr_no,slr_name,slr_taxid,slr_id,slr_psw,slr_contact,slr_phone,slr_state,slr_intro) "
			+ "VALUES('SL'||(LPAD(to_char(SLR_SEQ.NEXTVAL),8,'0')),?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE Seller SET slr_contact=?, slr_phone=?, slr_intro=? WHERE slr_no=?";
	private static final String SET_SLR_STATE = "UPDATE Seller SET slr_state=? WHERE slr_no=?";
	private static final String CHANGE_PASSWORD = "UPDATE Seller SET slr_psw=? WHERE slr_no=?";
	private static final String GET_ONE_BY_NO = 
			"SELECT slr_no,slr_name,slr_taxid,slr_id,slr_contact,slr_phone,slr_state,slr_intro "
	        + "FROM Seller WHERE slr_no=?";
	private static final String GET_ONE_BY_NAME = 
			"SELECT slr_no,slr_name,slr_taxid,slr_id,slr_contact,slr_phone,slr_state,slr_intro "
			+ "FROM Seller WHERE slr_name=?";
	private static final String GET_ONE_BY_ID = 
			"SELECT slr_no,slr_name,slr_taxid,slr_id,slr_psw,slr_contact,slr_phone,slr_state,slr_intro "
			+ "FROM Seller WHERE slr_id=?";
	private static final String GET_ALL = "SELECT * FROM Seller ORDER BY slr_no";
	private static final String GET_ID_LIST = "SELECT slr_id FROM Seller";
		
	@Override
	public void insert(SlrVO slrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT);
			
			pstmt.setString(1, slrVO.getSlr_name());
			pstmt.setString(2, slrVO.getSlr_taxid());
			pstmt.setString(3, slrVO.getSlr_id());
			pstmt.setString(4, slrVO.getSlr_psw());
			pstmt.setString(5, slrVO.getSlr_contact());
			pstmt.setString(6, slrVO.getSlr_phone());
			pstmt.setString(7, "OFF"); // 此為 lock_state，新加入為 OFF，通過後台驗證才會改為 ON
			pstmt.setString(8, slrVO.getSlr_intro());
			
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
	public void update(SlrVO slrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, slrVO.getSlr_contact());
			pstmt.setString(2, slrVO.getSlr_phone());
			pstmt.setString(3, slrVO.getSlr_intro());
			pstmt.setString(4, slrVO.getSlr_no());
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
	public void changeSlrState(SlrVO slrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SET_SLR_STATE);
			
			pstmt.setString(1, slrVO.getSlr_state());
			pstmt.setString(2, slrVO.getSlr_no());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(changeSlrState出問題了) " + se.getMessage());
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
	public void changePassword(SlrVO slrVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHANGE_PASSWORD);
			
			pstmt.setString(1, slrVO.getSlr_psw());
			pstmt.setString(2, slrVO.getSlr_no());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(changePassword出問題了) " + se.getMessage());
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
	public SlrVO findByNo(String slr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SlrVO slrVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_NO);

			pstmt.setString(1, slr_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				slrVO = new SlrVO();
				slrVO.setSlr_no(rs.getString("slr_no"));
				slrVO.setSlr_name(rs.getString("slr_name"));
				slrVO.setSlr_taxid(rs.getString("slr_taxid"));
				slrVO.setSlr_id(rs.getString("slr_id"));
				slrVO.setSlr_contact(rs.getString("slr_contact"));
				slrVO.setSlr_phone(rs.getString("slr_phone"));
				slrVO.setSlr_state(rs.getString("slr_state"));
				slrVO.setSlr_intro(rs.getString("slr_intro"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(findByNo) " + se.getMessage());
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
		return slrVO;
	}

	@Override
	public SlrVO findByName(String slr_name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SlrVO slrVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_NAME);

			pstmt.setString(1, slr_name);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				slrVO = new SlrVO();
				slrVO.setSlr_no(rs.getString("slr_no"));
				slrVO.setSlr_name(rs.getString("slr_name"));
				slrVO.setSlr_taxid(rs.getString("slr_taxid"));
				slrVO.setSlr_id(rs.getString("slr_id"));
				slrVO.setSlr_contact(rs.getString("slr_contact"));
				slrVO.setSlr_phone(rs.getString("slr_phone"));
				slrVO.setSlr_state(rs.getString("slr_state"));
				slrVO.setSlr_intro(rs.getString("slr_intro"));
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(findByName) "
					+ se.getMessage());
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
		return slrVO;
	}
	
	@Override
	public SlrVO findById(String slr_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SlrVO slrVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_ID);

			pstmt.setString(1, slr_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				slrVO = new SlrVO();
				slrVO.setSlr_no(rs.getString("slr_no"));
				slrVO.setSlr_name(rs.getString("slr_name"));
				slrVO.setSlr_taxid(rs.getString("slr_taxid"));
				slrVO.setSlr_id(rs.getString("slr_id"));
				slrVO.setSlr_psw(rs.getString("slr_psw"));
				slrVO.setSlr_contact(rs.getString("slr_contact"));
				slrVO.setSlr_phone(rs.getString("slr_phone"));
				slrVO.setSlr_state(rs.getString("slr_state"));
				slrVO.setSlr_intro(rs.getString("slr_intro"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(findById) "
					+ se.getMessage());
			
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
		return slrVO;
	}

	@Override
	public List<SlrVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<SlrVO> list = new ArrayList<SlrVO>();
		SlrVO slrVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				slrVO = new SlrVO();
				slrVO.setSlr_no(rs.getString("slr_no"));
				slrVO.setSlr_name(rs.getString("slr_name"));
				slrVO.setSlr_taxid(rs.getString("slr_taxid"));
				slrVO.setSlr_id(rs.getString("slr_id"));
				slrVO.setSlr_contact(rs.getString("slr_contact"));
				slrVO.setSlr_phone(rs.getString("slr_phone"));
				slrVO.setSlr_state(rs.getString("slr_state"));
				slrVO.setSlr_intro(rs.getString("slr_intro"));
				list.add(slrVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(getAll) " + se.getMessage());
		
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

	//取得所有廠商的 e-mail 用來讓新註冊比對
	@Override
	public List<SlrVO> getIdList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<SlrVO> list = new ArrayList<SlrVO>();
		SlrVO slrVO = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ID_LIST);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				slrVO = new SlrVO();
				slrVO.setSlr_id(rs.getString("slr_id"));
				list.add(slrVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(getIdList) "
					+ se.getMessage());
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
		return list;
	}

}

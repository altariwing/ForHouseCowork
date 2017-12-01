package com.odrdetail.model;

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

import com.order.model.PrdOdrVO;


public class OdrDetailDAO implements OdrDetailDAO_interface {
			
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = "INSERT INTO ORDER_DETAIL VALUES (?,?,?,?)";
	private static final String DEL_ONE_PRD = "DELETE FROM ORDER_DETAIL WHERE pdo_no=? and prd_no=?";
	private static final String DEL_ONE_ORDER = "DELETE FROM ORDER_DETAIL WHERE pdo_no=?";
	private static final String GET_BY_PDO = "SELECT * FROM ORDER_DETAIL WHERE pdo_no=?";
	private static final String GET_BY_PRD = "SELECT * FROM ORDER_DETAIL WHERE prd_no=?";

	@Override
	public void insert(OdrDetailVO odrDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT); //insert時要順便拿自增PK
			
			pstmt.setString(1, odrDetailVO.getPdo_no());
			pstmt.setString(2, odrDetailVO.getPrd_no());
			pstmt.setInt(3, odrDetailVO.getUnit_price());
			pstmt.setInt(4, odrDetailVO.getQuantity());
						
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

	
	//依據訂單編號 pdo_no 及商品編號 prd_no 刪除整張訂單的某筆明細
	@Override
	public void delOnePrd(String pdo_no, String prd_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DEL_ONE_PRD); //insert時要順便拿自增PK
			
			pstmt.setString(1, pdo_no);
			pstmt.setString(2, prd_no);
						
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(delOnePrd出問題了) " + se.getMessage());
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

	
	//依據訂單編號 pdo_no 刪除所有關聯的訂單明細
	@Override
	public void delOneOrder(String pdo_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DEL_ONE_ORDER); //insert時要順便拿自增PK
			
			pstmt.setString(1, pdo_no);
						
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(delOneOrder出問題了) " + se.getMessage());
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

	//查詢某訂單的所有明細
	@Override
	public List<OdrDetailVO> getListByPdoNo(String pdo_no) {
		List<OdrDetailVO> list = new ArrayList<OdrDetailVO>();
		OdrDetailVO odrDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_PDO);
			pstmt.setString(1, pdo_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				odrDetailVO = new OdrDetailVO();
				odrDetailVO.setPdo_no(rs.getString("pdo_no"));
				odrDetailVO.setPrd_no(rs.getString("prd_no"));
				odrDetailVO.setUnit_price(rs.getInt("unit_price"));
				odrDetailVO.setQuantity(rs.getInt("quantity"));
				list.add(odrDetailVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(getListByPdoNo出問題了) "
					+ se.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("A database error occured.(getListByPdoNo出問題了) "
					+ e.getMessage());

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

	//查詢某商品的訂單明細
	@Override
	public List<OdrDetailVO> getListByPrdNo(String prd_no) {
		List<OdrDetailVO> list = new ArrayList<OdrDetailVO>();
		OdrDetailVO odrDetailVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_PRD);
			pstmt.setString(1, prd_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				odrDetailVO = new OdrDetailVO();
				odrDetailVO.setPdo_no(rs.getString("pdo_no"));
				odrDetailVO.setPrd_no(rs.getString("prd_no"));
				odrDetailVO.setUnit_price(rs.getInt("unit_price"));
				odrDetailVO.setQuantity(rs.getInt("quantity"));
				list.add(odrDetailVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(getListByPdoNo出問題了) "
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
		return list;
	}
	
	
}

package com.order_detail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Order_detailDAO implements Order_detailDAO_interface{

	private static DataSource ds = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
		}catch(NamingException e){
			e.printStackTrace();
		}
		
		
	}
	private static final String INSERT = "INSERT INTO ORDER_DETAIL(PDO_NO, PRD_NO, ODER_UNI_PRICE, ODER_QUANTITY)"
			+ "VALUES(?, ?, ? ,?)";
	private static final String UPDATE = "UPDATE ORDER_DETAIL set ODER_QUANTITY=? where PDO_NO = ? and PRD_NO = ?";
	private static final String DELETE = "DELETE FROM ORDER_DETAIL where PDO_NO = ? and PRD_NO = ?";
	private static final String FIND_BY_PK = "SELECT PDO_NO, PRD_NO, ODER_UNI_PRICE, ODER_QUANTITY FROM ORDER_DETAIL where PDO_NO = ? and PRD_NO = ?";
	private static final String GET_ALL = "SELECT * FROM ORDER_DETAIL";
	private static final String BY_PDO_NO = "SELECT * FROM ORDER_DETAIL WHERE PDO_NO=?";

	
	@Override
	public void insert(Order_detailVO Order_detailVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con =ds.getConnection();
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, Order_detailVO.getPdo_no());
			pstmt.setString(2, Order_detailVO.getPrd_no());
			pstmt.setInt(3, Order_detailVO.getOder_uni_price());
			pstmt.setInt(4, Order_detailVO.getOder_quantity());
			
			pstmt.executeUpdate();
			
			
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
	public void update(Order_detailVO Order_detailVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, Order_detailVO.getOder_quantity());
			pstmt.setString(2, Order_detailVO.getPdo_no());
			pstmt.setString(3, Order_detailVO.getPrd_no());

			pstmt.executeUpdate();
			
			// Handle any driver errors
		}  catch (SQLException se) {
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
	public void delete(String pdo_no, String prd_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pdo_no);
			pstmt.setString(2, prd_no);
		
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
	public Order_detailVO findByPK(String pdo_no, String prd_no) {
		Order_detailVO order_detailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setString(1, pdo_no);
			pstmt.setString(2, prd_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_detailVO = new Order_detailVO();
				order_detailVO.setPdo_no(rs.getString("pdo_no"));
				order_detailVO.setPrd_no(rs.getString("prd_no"));
				order_detailVO.setOder_uni_price(rs.getInt("oder_uni_price"));
				order_detailVO.setOder_quantity(rs.getInt("oder_quantity"));
			}
			
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
		return order_detailVO;
	}

	@Override
	public List<Order_detailVO> getAll() {
		List<Order_detailVO> list = new ArrayList<Order_detailVO>();
		Order_detailVO order_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_detailVO = new Order_detailVO();
				order_detailVO.setPdo_no(rs.getString("pdo_no"));
				order_detailVO.setPrd_no(rs.getString("prd_no"));
				order_detailVO.setOder_uni_price(rs.getInt("oder_uni_price"));
				order_detailVO.setOder_quantity(rs.getInt("oder_quantity"));

				list.add(order_detailVO); // Store the row in the list
			}
			
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

	@Override
	public List<Order_detailVO> getAllByPDO_NO(String pdo_no) {

		List<Order_detailVO> list = new ArrayList<Order_detailVO>();
		Order_detailVO order_detailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(BY_PDO_NO);
			pstmt.setString(1, pdo_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				order_detailVO = new Order_detailVO();
				order_detailVO.setPdo_no(rs.getString("pdo_no"));
				order_detailVO.setPrd_no(rs.getString("prd_no"));
				order_detailVO.setOder_uni_price(rs.getInt("oder_uni_price"));
				order_detailVO.setOder_quantity(rs.getInt("oder_quantity"));

				list.add(order_detailVO); // Store the row in the list
			}
			
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

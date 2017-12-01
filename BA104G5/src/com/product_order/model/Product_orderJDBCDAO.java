package com.product_order.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Product_orderJDBCDAO implements Product_orderDAO_interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104G5";
	String passwd = "ba104g5";
	private static final String INSERT = "INSERT INTO PRODUCT_ORDER(PDO_NO, MEM_NO, SLR_NO,CP_NO)"
			+ "VALUES('OD'||TO_CHAR(SYSDATE,'RRMMDD')||'-'||(LPAD(TO_CHAR(PDO_NO_SEQ.NEXTVAL),6,'0')), ?, ?, ?)";

	private static final String UPDATE = "UPDATE PRODUCT_ORDER set PDO_STAT=? where PDO_NO =?";
	private static final String DELETE = "DELETE FROM PRODUCT_ORDER WHERE PDO_NO = ?";
	private static final String FIND_BY_PK = "SELECT * FROM PRODUCT_ORDER WHERE PDO_NO = ?";
	private static final String GET_ALL = "SELECT * FROM PRODUCT_ORDER";
	private static final String GET_ALL_BYSLRRATE = "SELECT * FROM PRODUCT_ORDER WHERE SLR_NO = ? AND SLR_RATE IS NOT NULL";
	private static final String GET_ALL_BYMEMRATE = "SELECT * FROM PRODUCT_ORDER WHERE MEM_NO = ? AND MEM_RATE IS NOT NULL";
	private static final String GET_AVG_SLR_RATE = "select AVG(SLR_RATE) from product_order where slr_no = ? AND SLR_RATE is not NULL";
	private static final String GET_AVG_MEM_RATE = "select AVG(MEM_RATE) from product_order where mem_no = ? AND MEM_RATE is not NULL";
	
	@Override
	public void insert(Product_orderVO Product_orderVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT);

			pstmt.setString(1, Product_orderVO.getMem_no());
			pstmt.setString(2, Product_orderVO.getSlr_no());
			pstmt.setString(3, Product_orderVO.getCp_no());

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
	public void update(Product_orderVO Product_orderVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, Product_orderVO.getPdo_stat());
			pstmt.setString(2, Product_orderVO.getPdo_no());

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
	public void delete(String pdo_no) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, pdo_no);

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
	public Product_orderVO findByPK(String pdo_no) {
		Product_orderVO product_orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setString(1, pdo_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_orderVO = new Product_orderVO();
				product_orderVO.setPdo_no(rs.getString("pdo_no"));
				product_orderVO.setMem_no(rs.getString("mem_no"));
				product_orderVO.setSlr_no(rs.getString("slr_no"));
				product_orderVO.setPdo_stat(rs.getString("pdo_stat"));
				product_orderVO.setPdo_resr_date(rs.getTimestamp("pdo_resr_date"));
				product_orderVO.setSlr_rate(rs.getInt("slr_rate"));
				product_orderVO.setMem_rate(rs.getInt("mem_rate"));
				product_orderVO.setMem_review(rs.getString("mem_review"));
				product_orderVO.setPdo_params(rs.getString("pdo_params"));
				product_orderVO.setCp_no(rs.getString("cp_no"));
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

		return product_orderVO;
	}

	@Override
	public List<Product_orderVO> getAll() {
		List<Product_orderVO> list = new ArrayList<Product_orderVO>();
		Product_orderVO product_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				product_orderVO = new Product_orderVO();
				product_orderVO.setPdo_no(rs.getString("pdo_no"));
				product_orderVO.setMem_no(rs.getString("mem_no"));
				product_orderVO.setSlr_no(rs.getString("slr_no"));
				product_orderVO.setPdo_stat(rs.getString("pdo_stat"));
				product_orderVO.setPdo_resr_date(rs.getTimestamp("pdo_resr_date"));
				product_orderVO.setSlr_rate(rs.getInt("slr_rate"));
				product_orderVO.setMem_rate(rs.getInt("mem_rate"));
				product_orderVO.setMem_review(rs.getString("mem_review"));
				product_orderVO.setPdo_params(rs.getString("pdo_params"));
				product_orderVO.setCp_no(rs.getString("cp_no"));

				list.add(product_orderVO); // Store the row in the list
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

	@Override
	public List<Product_orderVO> getAllByMemNo(String mem_no) {
		List<Product_orderVO> list = new ArrayList<Product_orderVO>();
		Product_orderVO product_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BYMEMRATE);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				product_orderVO = new Product_orderVO();
				product_orderVO.setPdo_no(rs.getString("pdo_no"));
				product_orderVO.setMem_no(rs.getString("mem_no"));
				product_orderVO.setSlr_no(rs.getString("slr_no"));
				product_orderVO.setPdo_stat(rs.getString("pdo_stat"));
				product_orderVO.setPdo_resr_date(rs.getTimestamp("pdo_resr_date"));
				product_orderVO.setSlr_rate(rs.getInt("slr_rate"));
				product_orderVO.setMem_rate(rs.getInt("mem_rate"));
				product_orderVO.setMem_review(rs.getString("mem_review"));
				product_orderVO.setPdo_params(rs.getString("pdo_params"));
				product_orderVO.setCp_no(rs.getString("cp_no"));

				list.add(product_orderVO); // Store the row in the list
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
	};

	@Override
	public List<Product_orderVO> getAllBySlrNo(String slr_no) {
		List<Product_orderVO> list = new ArrayList<Product_orderVO>();
		Product_orderVO product_orderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_BYSLRRATE);
			pstmt.setString(1, slr_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				product_orderVO = new Product_orderVO();
				product_orderVO.setPdo_no(rs.getString("pdo_no"));
				product_orderVO.setMem_no(rs.getString("mem_no"));
				product_orderVO.setSlr_no(rs.getString("slr_no"));
				product_orderVO.setPdo_stat(rs.getString("pdo_stat"));
				product_orderVO.setPdo_resr_date(rs.getTimestamp("pdo_resr_date"));
				product_orderVO.setSlr_rate(rs.getInt("slr_rate"));
				product_orderVO.setMem_rate(rs.getInt("mem_rate"));
				product_orderVO.setMem_review(rs.getString("mem_review"));
				product_orderVO.setPdo_params(rs.getString("pdo_params"));
				product_orderVO.setCp_no(rs.getString("cp_no"));

				list.add(product_orderVO); // Store the row in the list
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

	@Override
	public Double getMemAvgRate(String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Double avg = 0.0;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_AVG_MEM_RATE);
			pstmt.setString(1, mem_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				avg = (Double) rs.getDouble(1);
			}
						
		}catch (ClassNotFoundException e) {
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
		return avg;
	}

	@Override
	public Double getSlrAvgRate(String slr_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Double avg = 0.0;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_AVG_SLR_RATE);
			pstmt.setString(1, slr_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				avg = rs.getDouble(1);
			}
		}catch (ClassNotFoundException e) {
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
		return avg;
	};

	
	
}

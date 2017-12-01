package com.product.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PrdDAO implements PrdDAO_interface {
	
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
			"INSERT INTO Product (prd_no, slr_no, cate_no, prd_name, prd_desc, prd_stock, prd_price) "
			+ "VALUES ('PD'||(LPAD(TO_CHAR(PD_SEQ.NEXTVAL),8,'0')),?,?,?,?,?,?)";
	private static final String UPDATE = 
			"UPDATE Product SET cate_no=?, prd_name=?, prd_desc=?, prd_stock=?, prd_price=? WHERE prd_no=?";
	private static final String CHANGESTATE = "UPDATE Product SET prd_state=? WHERE prd_no=?";
	private static final String GET_ON = "SELECT * FROM Product WHERE prd_state='ON'";
	private static final String FIND_BY_SLR_OFF = "SELECT * FROM Product WHERE prd_state='OFF' and slr_no=?";
	private static final String FIND_BY_SLR_ON = "SELECT * FROM Product WHERE prd_state='ON' and slr_no=?";
	private static final String FIND_BY_SLR_CATE = "SELECT * FROM Product WHER cate_no=? and slr_no=?";
	private static final String GET_ONE = "SELECT * FROM Product WHERE prd_no=?";

	@Override
	public String insert(PrdVO prdVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String keyPrdNo = null;
		
		try {
			con = ds.getConnection();
			String[] col = { "prd_no" };
			pstmt = con.prepareStatement(INSERT,col); //insert時要順便拿自增PK
			
			pstmt.setString(1, prdVO.getSlr_no());
			pstmt.setString(2, prdVO.getCate_no());
			pstmt.setString(3, prdVO.getPrd_name());
			pstmt.setString(4, prdVO.getPrd_desc());
			pstmt.setInt(5, prdVO.getPrd_stock());
			pstmt.setInt(6, prdVO.getPrd_price());
			
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys(); //從 結果取出PK
			rs.next();
			keyPrdNo = rs.getString(1);
			
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
		return keyPrdNo;
	}

	@Override
	public void update(PrdVO prdVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, prdVO.getCate_no());
			pstmt.setString(2, prdVO.getPrd_name());
			pstmt.setString(3, prdVO.getPrd_desc());
			pstmt.setInt(4, prdVO.getPrd_stock());
			pstmt.setInt(5, prdVO.getPrd_price());
			pstmt.setString(6, prdVO.getPrd_no());
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
	public void changeState(String prd_state, String prd_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(CHANGESTATE);
			
			pstmt.setString(1, prd_state);
			pstmt.setString(2, prd_no);
			
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(changeState出問題了) " + se.getMessage());
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

	
	//取得上架的商品 prd_state=ON
	@Override
	public List<PrdVO> getStateOn() {
		List<PrdVO> list = new ArrayList<PrdVO>();
		PrdVO prdVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ON);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prdVO = new PrdVO();
				prdVO.setPrd_no(rs.getString("prd_no"));
				prdVO.setSlr_no(rs.getString("slr_no"));
				prdVO.setCate_no(rs.getString("cate_no"));
				prdVO.setPrd_name(rs.getString("prd_name"));
				prdVO.setPrd_desc(rs.getString("prd_desc"));
				prdVO.setPrd_stock(rs.getInt("prd_stock"));
				prdVO.setPrd_price(rs.getInt("prd_price"));
				prdVO.setPrd_state(rs.getString("prd_state"));
				list.add(prdVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(getStateOn出問題了) "
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
	
	
	//取得下架的商品 prd_state=OFF
	@Override
	public List<PrdVO> findBySlrOff(String slr_no) {
		List<PrdVO> list = new ArrayList<PrdVO>();
		PrdVO prdVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_SLR_OFF);
			pstmt.setString(1, slr_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prdVO = new PrdVO();
				prdVO.setPrd_no(rs.getString("prd_no"));
				prdVO.setSlr_no(rs.getString("slr_no"));
				prdVO.setCate_no(rs.getString("cate_no"));
				prdVO.setPrd_name(rs.getString("prd_name"));
				prdVO.setPrd_desc(rs.getString("prd_desc"));
				prdVO.setPrd_stock(rs.getInt("prd_stock"));
				prdVO.setPrd_price(rs.getInt("prd_price"));
				prdVO.setPrd_state(rs.getString("prd_state"));
				list.add(prdVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(findBySlr出問題了) "
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
	

	@Override
	public List<PrdVO> findBySlrOn(String slr_no) {
		List<PrdVO> list = new ArrayList<PrdVO>();
		PrdVO prdVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_SLR_ON);
			pstmt.setString(1, slr_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prdVO = new PrdVO();
				prdVO.setPrd_no(rs.getString("prd_no"));
				prdVO.setSlr_no(rs.getString("slr_no"));
				prdVO.setCate_no(rs.getString("cate_no"));
				prdVO.setPrd_name(rs.getString("prd_name"));
				prdVO.setPrd_desc(rs.getString("prd_desc"));
				prdVO.setPrd_stock(rs.getInt("prd_stock"));
				prdVO.setPrd_price(rs.getInt("prd_price"));
				prdVO.setPrd_state(rs.getString("prd_state"));
				list.add(prdVO);
			}

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(findBySlr出問題了) "
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

	@Override
	public List<PrdVO> findBySlrCate(String cate_no, String slr_no) {
		List<PrdVO> list = new ArrayList<PrdVO>();
		PrdVO prdVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_SLR_CATE);
			pstmt.setString(1, cate_no);
			pstmt.setString(2, slr_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prdVO = new PrdVO();
				prdVO.setPrd_no(rs.getString("prd_no"));
				prdVO.setSlr_no(rs.getString("slr_no"));
				prdVO.setCate_no(rs.getString("cate_no"));
				prdVO.setPrd_name(rs.getString("prd_name"));
				prdVO.setPrd_desc(rs.getString("prd_desc"));
				prdVO.setPrd_stock(rs.getInt("prd_stock"));
				prdVO.setPrd_price(rs.getInt("prd_price"));
				prdVO.setPrd_state(rs.getString("prd_state"));
				list.add(prdVO);
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(findBySlrCate出問題了) "
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

	
	@Override
	public PrdVO getOnByPrdNo(String prd_no) {
		PrdVO prdVO = new PrdVO();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setString(1, prd_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prdVO.setPrd_no(rs.getString("prd_no"));
				prdVO.setSlr_no(rs.getString("slr_no"));
				prdVO.setCate_no(rs.getString("cate_no"));
				prdVO.setPrd_name(rs.getString("prd_name"));
				prdVO.setPrd_desc(rs.getString("prd_desc"));
				prdVO.setPrd_stock(rs.getInt("prd_stock"));
				prdVO.setPrd_price(rs.getInt("prd_price"));
				prdVO.setPrd_state(rs.getString("prd_state"));
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured.(getOnByPrdNo出問題了) "
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
		return prdVO;
	}
	
	
	@Override
	public List<PrdVO> getStateOnBymap(Map<String, String[]> map) {
		List<PrdVO> list = new ArrayList<PrdVO>();
		PrdVO prdVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "SELECT * FROM Product WHERE prd_state='ON' "
		          + jdbcUtil_CompositeQuery_Prd.get_WhereCondition(map)
		          + "order by PRD_NO";
			System.out.println(finalSQL);
			pstmt = con.prepareStatement(finalSQL);
//			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				prdVO = new PrdVO();
				prdVO.setPrd_no(rs.getString("prd_no"));
				prdVO.setSlr_no(rs.getString("slr_no"));
				prdVO.setCate_no(rs.getString("cate_no"));
				prdVO.setPrd_name(rs.getString("prd_name"));
				prdVO.setPrd_desc(rs.getString("prd_desc"));
				prdVO.setPrd_stock(rs.getInt("prd_stock"));
				prdVO.setPrd_price(rs.getInt("prd_price"));
				prdVO.setPrd_state(rs.getString("prd_state"));
				list.add(prdVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
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

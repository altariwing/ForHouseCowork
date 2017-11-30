package com.advertisement.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdJDBCDAO implements Ad_interface {
	String name = "BA104G5";
	String password = "ba104g5";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String driver = "oracle.jdbc.driver.OracleDriver";

	static final String INSERT = "insert into advertisement (ad_no,prd_no,emp_no,ad_type,ad_img,appling_date,audit_date,expiration_date) values('AD'||LPAD(to_char(advertisement_sequance.nextval),8,'0'),?,?,?,?,current_timestamp,null,null)";
	static final String UPDATE = "update advertisement set prd_no=?,emp_no=?,ad_type=?,ad_img=?,audit_date=current_timestamp,expiration_date=? where ad_no=? ";
	static final String DELETE = "delete from advertisement where ad_no=?";
	static final String GET_ONE = "select * from advertisement where ad_no=?";
	static final String GET_ALL = "select * from advertisement";

	@Override
	public void insert(AdVO adVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, name, password);
			pstmt = con.prepareStatement(INSERT);
			pstmt.setString(1, adVO.getPrd_no());
			pstmt.setString(2, adVO.getEmp_no());
			pstmt.setString(3, adVO.getAd_type());
			pstmt.setBytes(4, adVO.getAd_img());
			pstmt.executeUpdate();
			System.out.println("成功上傳");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(AdVO adVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, name, password);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, adVO.getPrd_no());
			pstmt.setString(2, adVO.getEmp_no());
			pstmt.setString(3, adVO.getAd_type());
			pstmt.setBytes(4, adVO.getAd_img());
			pstmt.setTimestamp(5, adVO.getExpiration_date());
			pstmt.setString(6, adVO.getAd_no());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void delete(String ad_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, name, password);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ad_no);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public AdVO findByPrimaryKey(String ad_no) {
		AdVO adVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, name, password);
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setString(1, ad_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adVO = new AdVO();
				adVO.setAd_no(rs.getString("ad_no"));
				adVO.setPrd_no(rs.getString("prd_no"));
				adVO.setEmp_no(rs.getString("emp_no"));
				adVO.setAd_type(rs.getString("ad_type"));
				adVO.setAd_img(rs.getBytes("ad_img"));
				adVO.setAppling_date(rs.getTimestamp("appling_date"));
				adVO.setAudit_date(rs.getTimestamp("audit_date"));
				adVO.setExpiration_date(rs.getTimestamp("expiration_date"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return adVO;
	}

	@Override
	public List<AdVO> getAll() {
		List<AdVO> list = new ArrayList<AdVO>();
		AdVO adVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DriverManager.getConnection(url, name, password);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				adVO = new AdVO();
				adVO.setAd_no(rs.getString("ad_no"));
				adVO.setPrd_no(rs.getString("prd_no"));
				adVO.setEmp_no(rs.getString("emp_no"));
				adVO.setAd_type(rs.getString("ad_type"));
				adVO.setAd_img(rs.getBytes("ad_img"));
				adVO.setAppling_date(rs.getTimestamp("appling_date"));
				adVO.setAudit_date(rs.getTimestamp("audit_date"));
				adVO.setExpiration_date(rs.getTimestamp("expiration_date"));
				list.add(adVO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

}

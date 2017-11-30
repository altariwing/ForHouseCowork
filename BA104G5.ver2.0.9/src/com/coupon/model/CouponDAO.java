package com.coupon.model;

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

public class CouponDAO implements CouponDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G5");
		} catch (NamingException ne) {
			ne.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO COUPON (CP_No, CP_From, CP_To, CP_Content, CP_discount, PDO_No, CP_State, MEM_No, PROMO_No) VALUES('COU'||LPAD(TO_CHAR(SEQ_COU.NEXTVAL), 7, '0'), ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = "UPDATE Coupon SET CP_From=?, CP_To=?, CP_Content=?, CP_discount=?, PDO_No=?, CP_State=?, MEM_No=?, PROMO_No=? WHERE CP_No = ?";
	private static final String GET_ONE_STMT = "SELECT CP_No, to_char(CP_From, 'yyyy-mm-dd')CP_From, to_char(CP_To, 'yyyy-mm-dd')CP_To, CP_Content, CP_discount, PDO_No, CP_State, to_char(CP_Date, 'yyyy-mm-dd')CP_Date, MEM_No, PROMO_No FROM Coupon WHERE CP_No = ?";
	private static final String GET_ALL_STMT = "SELECT CP_No, to_char(CP_From, 'yyyy-mm-dd')CP_From, to_char(CP_To, 'yyyy-mm-dd')CP_To, CP_Content, CP_discount, PDO_No, CP_State, to_char(CP_Date, 'yyyy-mm-dd')CP_Date, MEM_No, PROMO_No FROM Coupon ORDER BY CP_No";
	private static final String GET_ALL_BY_TIME = "SELECT * FROM Promo ORDER BY CP_To DESC";

	// 新增
	@Override
	public void insert(CouponVO couponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			con.setAutoCommit(false);

			pstmt.setDate(1, couponVO.getCp_from());
			pstmt.setDate(2, couponVO.getCp_to());
			pstmt.setString(3, couponVO.getCp_content());
			pstmt.setString(4, couponVO.getCp_discount());
			pstmt.setString(5, couponVO.getPdo_no());
			pstmt.setString(6, couponVO.getCp_state());
			pstmt.setString(7, couponVO.getMem_no());
			pstmt.setString(8, couponVO.getPromo_no());

			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);

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
	}// 新增結束

	// 修改
	@Override
	public void update(CouponVO couponVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);

			con.setAutoCommit(false);

			pstmt.setDate(1, couponVO.getCp_from());
			pstmt.setDate(2, couponVO.getCp_to());
			pstmt.setString(3, couponVO.getCp_content());
			pstmt.setString(4, couponVO.getCp_discount());
			pstmt.setString(5, couponVO.getPdo_no());
			pstmt.setString(6, couponVO.getCp_state());
			pstmt.setString(7, couponVO.getMem_no());
			pstmt.setString(8, couponVO.getPromo_no());
			pstmt.setString(9, couponVO.getCp_no());

			pstmt.executeUpdate();
			con.commit();
			con.setAutoCommit(true);

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
	}// 修改結束

	// 查單一
	@Override
	public CouponVO findByPrimaryKey(String cp_no) {

		CouponVO couponvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, cp_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				couponvo = new CouponVO();
				couponvo.setCp_no(rs.getString("CP_No"));
				couponvo.setCp_from(rs.getDate("CP_From"));
				couponvo.setCp_to(rs.getDate("CP_To"));
				couponvo.setCp_content(rs.getString("CP_Content"));
				couponvo.setCp_discount(rs.getString("CP_Discount"));
				couponvo.setPdo_no(rs.getString("PDO_No"));
				couponvo.setCp_state(rs.getString("CP_State"));
				couponvo.setCp_date(rs.getDate("CP_Date"));
				couponvo.setMem_no(rs.getString("MEM_No"));
				couponvo.setPromo_no(rs.getString("PROMO_No"));
			}

		} catch (SQLException se) {
			se.printStackTrace();
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
		return couponvo;
	}// 查單一結束

	// 查全部
	@Override
	public List<CouponVO> getAll() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				couponvo = new CouponVO();
				couponvo.setCp_no(rs.getString("CP_No"));
				couponvo.setCp_from(rs.getDate("CP_From"));
				couponvo.setCp_to(rs.getDate("CP_To"));
				couponvo.setCp_content(rs.getString("CP_Content"));
				couponvo.setCp_state(rs.getString("CP_State"));
				couponvo.setCp_discount(rs.getString("CP_Discount"));
				couponvo.setCp_date(rs.getDate("CP_Date"));
				couponvo.setPdo_no(rs.getString("PDO_No"));
				couponvo.setMem_no(rs.getString("MEM_No"));
				couponvo.setPromo_no(rs.getString("PROMO_No"));
				list.add(couponvo);
			}

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
	}// 查全部結束

	// 查全部照時間
	@Override
	public List<CouponVO> getAllByTime() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_BY_TIME);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				couponvo = new CouponVO();
				couponvo.setCp_no(rs.getString("CP_No"));
				couponvo.setCp_from(rs.getDate("CP_From"));
				couponvo.setCp_to(rs.getDate("CP_To"));
				couponvo.setCp_content(rs.getString("CP_Content"));
				couponvo.setCp_state(rs.getString("CP_State"));
				couponvo.setCp_discount(rs.getString("CP_Discount"));
				couponvo.setCp_date(rs.getDate("CP_Date"));
				couponvo.setPdo_no(rs.getString("PDO_No"));
				couponvo.setMem_no(rs.getString("MEM_No"));
				couponvo.setPromo_no(rs.getString("PROMO_No"));
				list.add(couponvo);
			}

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
	}// 查全部照時間結束
}
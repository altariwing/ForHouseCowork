package com.realestate.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.realtor.model.RealtorVO;

public class RealEstateJDBCDAO implements RealEstateDAO_interface {

	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String userid = "BA104G5";
	private static final String password = "ba104g5";

	private static final String INSERT_STMT = "INSERT INTO RealEstate (RE_No, RE_Name) VALUES('RE'||(LPAD(to_char(RE_SEQ.NEXTVAL),8,'0')), ?)";
	private static final String GET_ONE_STMT = "SELECT RE_No, RE_Name FROM RealEstate WHERE RE_No = ?";
	private static final String GET_ALL_STMT = "SELECT RE_No, RE_Name FROM RealEstate ORDER BY RE_No";
	private static final String Get_Realtor_ByRTR_NO_STMT = "SELECT RTR_NO, RTR_ID, RTR_PSW, RTR_NAME, RTR_PHOTO, RTR_AREA, RTR_INTRO, RTR_IDNO, RE_NO, RTR_STATE FROM Realtor WHERE RE_NO = ? ORDER BY RTR_NO";

	// 新增
	@Override
	public void insert(RealEstateVO realestateVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(INSERT_STMT);

			con.setAutoCommit(false);

			pstmt.setString(1, realestateVO.getRe_name());

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
	public RealEstateVO findByPrimaryKey(String re_no) {
		RealEstateVO realestateVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, re_no);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				realestateVO = new RealEstateVO();
				realestateVO.setRe_no(rs.getString("RE_No"));
				realestateVO.setRe_name(rs.getString("RE_Name"));

			}
			System.out.println("RealEstate_findByPrimaryKey");

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
		return realestateVO;
	}

	// 查全部
	@Override
	public List<RealEstateVO> getAll() {
		List<RealEstateVO> realestatelist = new ArrayList<RealEstateVO>();
		RealEstateVO realestateVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(GET_ALL_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				realestateVO = new RealEstateVO();
				realestateVO.setRe_no(rs.getString("RE_No"));
				realestateVO.setRe_name(rs.getString("RE_Name"));
				realestatelist.add(realestateVO);
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
		return realestatelist;
	}

	// 查房仲公司對應的房仲
	@Override
	public Set<RealtorVO> getRealtorByRTR_NO(String re_no) {
		Set<RealtorVO> set = new LinkedHashSet<RealtorVO>();
		RealtorVO realtorvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, password);
			pstmt = con.prepareStatement(Get_Realtor_ByRTR_NO_STMT);
			pstmt.setString(1, re_no);
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
				set.add(realtorvo);
			}

		} catch (ClassNotFoundException ce) {
			throw new RuntimeException("Couldn't load database driver. " + ce.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return set;
	}

	public static void main(String[] args) {
		RealEstateJDBCDAO dao = new RealEstateJDBCDAO();

		// 新增
		// RealEstateVO vo = new RealEstateVO();
		// vo.setRe_name("GGGG");
		// dao.insert(vo);
		// System.out.println("=====================================");

		// 查單一
		// RealEstateVO vo2 = dao.findByPrimaryKey("RE00000001");
		// System.out.println(vo2.getRe_no());
		// System.out.println(vo2.getRe_name());
		// System.out.println("=====================================");

		// 查全部
		 List<RealEstateVO> list = dao.getAll();
		 for (RealEstateVO arealestate : list) {
		 System.out.println(arealestate.getRe_no());
		 System.out.println(arealestate.getRe_name());
		 System.out.println();
		 }

		// 查房仲公司對應的房仲
//		Set<RealtorVO> set = dao.getRealtorByRTR_NO("RE00000001");
//		for (RealtorVO arealtor : set) {
//			System.out.println(arealtor.getRtr_no());
//			System.out.println(arealtor.getRtr_id());
//			System.out.println(arealtor.getRtr_psw());
//			System.out.println(arealtor.getRtr_name());
//			System.out.println(arealtor.getRtr_photo());
//			System.out.println(arealtor.getRtr_area());
//			System.out.println(arealtor.getRtr_intro());
//			System.out.println(arealtor.getRtr_idno());
//			System.out.println(arealtor.getRe_no());
//			System.out.println(arealtor.getRtr_state());
//			System.out.println();
//		}
	}
}

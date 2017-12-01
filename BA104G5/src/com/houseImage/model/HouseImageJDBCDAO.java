package com.houseImage.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HouseImageJDBCDAO implements HouseImage_interface {
	String name = "BA104G5";
	String password = "ba104g5";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String driver = "oracle.jdbc.driver.OracleDriver";

	private static final String INSERT = "INSERT INTO HOUSEIMAGES VALUES('IMG'||LPAD(to_char(houseimages_sequence.nextval),7,'0'),?,?,?,current_timestamp)";
	private static final String UPDATE = "UPDATE HOUSEIMAGES SET HOUSE_NO=?,IMG=?,STATE=? WHERE IMG_NO=?";
	private static final String DELETE = "DELETE FROM HOUSEIMAGES WHERE IMG_NO = ?";
	private static final String GET_ONE = "SELECT * FROM HOUSEIMAGES WHERE IMG_NO = ?";
	private static final String GET_ALL = "SELECT * FROM HOUSEIMAGES ";
	private static final String GET_BY_HOUSE_NO = " SELECT * FROM HOUSEIMAGES WHERE house_no = ? ";
												

	@Override
	public void addImg(HouseImageVO houseImageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(url, name, password);
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(INSERT);	
			
			pstmt.setString(1, houseImageVO.getHouse_no());
			pstmt.setBytes(2, houseImageVO.getImg());
			pstmt.setString(3, houseImageVO.getState());
			pstmt.addBatch();
			pstmt.executeBatch();
			con.commit();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("新增失敗");
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
	public void updateImg(HouseImageVO houseImageVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, name, password);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, houseImageVO.getHouse_no());
			pstmt.setBytes(2, houseImageVO.getImg());
			pstmt.setString(3, houseImageVO.getState());
			pstmt.setString(4, houseImageVO.getImg_no());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("修改失敗");
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
	public void delete(String img_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(url, name, password);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, img_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public HouseImageVO findByPrimaryKey(String img_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HouseImageVO houseImageVO = null;
		try {
			con = DriverManager.getConnection(url, name, password);
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setString(1, img_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseImageVO = new HouseImageVO();
				houseImageVO.setImg_no(rs.getString("img_no"));
				houseImageVO.setHouse_no(rs.getString("house_no"));
				houseImageVO.setImg(rs.getBytes("img"));
				houseImageVO.setState(rs.getString("state"));
				houseImageVO.setInsert_time(rs.getTimestamp("insert_time"));
			}

		} catch (SQLException e) {
			System.out.println("查詢失敗");
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
		return houseImageVO;
	}

	@Override
	public List<HouseImageVO> getall() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HouseImageVO houseImageVO = null;
		List<HouseImageVO> houseImageVOlist = new ArrayList<>();

		try {
			con = DriverManager.getConnection(url, name, password);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseImageVO = new HouseImageVO();
				houseImageVO.setImg_no(rs.getString("img_no"));
				houseImageVO.setHouse_no(rs.getString("house_no"));
				houseImageVO.setImg(rs.getBytes("img"));
				houseImageVO.setState(rs.getString("state"));
				houseImageVO.setInsert_time(rs.getTimestamp("insert_time"));
				houseImageVOlist.add(houseImageVO);
			}

		} catch (SQLException e) {
			System.out.println("查詢失敗");
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
		return houseImageVOlist;
	}

	@Override
	public List<HouseImageVO> findByHouseNo(String house_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HouseImageVO houseImageVO = null;
		List<HouseImageVO> houseImageVOlist = new ArrayList<>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			pstmt = con.prepareStatement(GET_BY_HOUSE_NO);
			pstmt.setString(1, house_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseImageVO = new HouseImageVO();
				houseImageVO.setImg_no(rs.getString("img_no"));
				houseImageVO.setHouse_no(rs.getString("house_no"));
				houseImageVO.setImg(rs.getBytes("img"));
				houseImageVO.setState(rs.getString("state"));
				houseImageVO.setInsert_time(rs.getTimestamp("insert_time"));
				houseImageVOlist.add(houseImageVO);
			}

		} catch (SQLException e) {
			System.out.println("查詢失敗");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
		return houseImageVOlist;
	}

}

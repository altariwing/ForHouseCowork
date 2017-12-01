package com.hsecoll.model;

import java.sql.Connection;
import com.house.model.HouseVO;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HseCollJDBCDAO implements HseCollDAO_interface {

	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String userid = "BA104G5";
	String passwd = "ba104g5";

	private static final String INSERT_STMT = "INSERT INTO HSECOLL VALUES ( ?, ?)";
	private static final String GET_ALL_STMT = "SELECT mem_no,house_no FROM HSECOLL order by mem_no";
	private static final String GET_ONE_STMT = "SELECT mem_no,house_no FROM HSECOLL where mem_no = ? and house_no = ?";
	private static final String GET_ONE_MEM = "SELECT mem_no,house_no FROM HSECOLL where mem_no = ? order by house_no";
	private static final String DELETE = "DELETE FROM HSECOLL where mem_no = ? and house_no = ?";

	private static final String GET_ONE_MEM_WITH_HOUSEINFO = "SELECT mem_no, H.HOUSE_NO,re_no,house_serial_number,title,location,house_type,price,total_pings,main_pings,amenity_pings,accessory_pings,floor,age,pattern,orientation,building_materials,parking_space,classification_of_land,land_pings,data_info,lat,lng,house_states,insert_time,final_update_time FROM HSECOLL H JOIN HOUSEINFO HF ON H.HOUSE_NO = HF.HOUSE_NO where mem_no = ? order by house_no";

	// private static final String UPDATE =
	// "UPDATE HSECOLL set mem_no=?, house_no=? where mem_no = ? and house_no =
	// ?";

	@Override
	public void insert(HseCollVO hseCollVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, hseCollVO.getMem_no());
			pstmt.setString(2, hseCollVO.getHouse_no());

			pstmt.executeUpdate();

			// Handle any driver errors
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
		}

	}

	@Override
	public void update(HseCollVO hseCollVO) {
		// Connection con = null;
		// PreparedStatement pstmt = null;
		//
		// try {
		//
		// Class.forName(driver);
		// con = DriverManager.getConnection(url, userid, passwd);
		// pstmt = con.prepareStatement(UPDATE);
		//
		// pstmt.setString(1, hseCollVO.getMem_no());
		// pstmt.setString(2, hseCollVO.getHouse_no());
		// pstmt.setString(3, hseCollVO.getMem_no());
		// pstmt.setString(4, hseCollVO.getHouse_no());
		//
		// pstmt.executeUpdate();
		//
		// // Handle any driver errors
		// } catch (ClassNotFoundException e) {
		// throw new RuntimeException("Couldn't load database driver. "
		// + e.getMessage());
		// // Handle any SQL errors
		// } catch (SQLException se) {
		// throw new RuntimeException("A database error occured. "
		// + se.getMessage());
		// // Clean up JDBC resources
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

	}

	@Override
	public void delete(String mem_no, String house_no) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setString(1, mem_no);
			pstmt.setString(2, house_no);

			pstmt.executeUpdate();

			// Handle any driver errors
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
		}

	}

	@Override
	public HseCollVO findByPrimaryKey(String mem_no, String house_no) {

		HseCollVO hseCollVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setString(1, mem_no);
			pstmt.setString(2, house_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				hseCollVO = new HseCollVO();
				hseCollVO.setMem_no(rs.getString("mem_no"));
				hseCollVO.setHouse_no(rs.getString("house_no"));

			}

			// Handle any driver errors
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
		}
		return hseCollVO;
	}

	@Override
	public List<HseCollVO> getAll_kuei(String mem_no) {
		List<HseCollVO> list = new ArrayList<HseCollVO>();
		HseCollVO hseCollVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_MEM);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				hseCollVO = new HseCollVO();
				hseCollVO.setMem_no(rs.getString("mem_no"));
				hseCollVO.setHouse_no(rs.getString("house_no"));

				list.add(hseCollVO); // Store the row in the list
			}

			// Handle any driver errors
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
		}
		return list;
	}

	@Override
	public List<HseCollVO> getAll() {
		List<HseCollVO> list = new ArrayList<HseCollVO>();
		HseCollVO hseCollVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				hseCollVO = new HseCollVO();
				hseCollVO.setMem_no(rs.getString("mem_no"));
				hseCollVO.setHouse_no(rs.getString("house_no"));

				list.add(hseCollVO); // Store the row in the list
			}

			// Handle any driver errors
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
		}
		return list;
	}

	@Override
	public List<HouseVO> getAllByMemNoWithHouseInfo(String mem_no) {
		List<HouseVO> list = new ArrayList<HouseVO>();
		HseCollVO hseCollVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_MEM_WITH_HOUSEINFO);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				hseCollVO = new HseCollVO();
				hseCollVO.setMem_no(rs.getString("mem_no"));
				hseCollVO.setHouse_no(rs.getString("house_no"));
				hseCollVO.setTitle(rs.getString("title"));
				hseCollVO.setRe_no(rs.getString("re_no"));
				hseCollVO.setHouse_serial_number(rs.getString("house_serial_number"));
				hseCollVO.setLocation(rs.getString("location"));
				hseCollVO.setHouse_type(rs.getString("house_type"));
				hseCollVO.setPrice(rs.getInt("price"));
				hseCollVO.setTotal_pings(rs.getDouble("total_pings"));
				hseCollVO.setMain_pings(rs.getDouble("main_pings"));
				hseCollVO.setAmenity_pings(rs.getDouble("amenity_pings"));
				hseCollVO.setAccessory_pings(rs.getDouble("accessory_pings"));
				hseCollVO.setFloor(rs.getString("floor"));
				hseCollVO.setAge(rs.getDouble("age"));
				hseCollVO.setPattern(rs.getString("pattern"));
				hseCollVO.setOrientation(rs.getString("orientation"));
				hseCollVO.setBuilding_materials(rs.getString("building_materials"));
				hseCollVO.setParking_space(rs.getString("parking_space"));
				hseCollVO.setClassification_of_land(rs.getString("classification_of_land"));
				hseCollVO.setLand_pings(rs.getDouble("land_pings"));
				hseCollVO.setData_info(rs.getString("data_info"));
				hseCollVO.setLat(rs.getDouble("lat"));
				hseCollVO.setLng(rs.getDouble("lng"));
				hseCollVO.setHouse_states(rs.getString("house_states"));
				hseCollVO.setInsert_time(rs.getTimestamp("insert_Time"));
				hseCollVO.setFinal_update_time(rs.getTimestamp("final_update_time"));
				list.add(hseCollVO); // Store the row in the list
			}

			// Handle any driver errors
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
		}
		return list;
	}

	@Override
	public List<HouseVO> getAllByMemNoWithHouseInfo(String mem_no, String[] house_nos) {

		List<HouseVO> list = new ArrayList<HouseVO>();
		HseCollVO hseCollVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_MEM_WITH_HOUSEINFO);

			pstmt.setString(1, mem_no);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				String house_no = rs.getString("house_no");
				if (!Arrays.asList(house_nos).contains(house_no)) {
					hseCollVO = new HseCollVO();
					hseCollVO.setMem_no(rs.getString("mem_no"));
					hseCollVO.setHouse_no(rs.getString("house_no"));
					hseCollVO.setTitle(rs.getString("title"));
					hseCollVO.setRe_no(rs.getString("re_no"));
					hseCollVO.setHouse_serial_number(rs.getString("house_serial_number"));
					hseCollVO.setLocation(rs.getString("location"));
					hseCollVO.setHouse_type(rs.getString("house_type"));
					hseCollVO.setPrice(rs.getInt("price"));
					hseCollVO.setTotal_pings(rs.getDouble("total_pings"));
					hseCollVO.setMain_pings(rs.getDouble("main_pings"));
					hseCollVO.setAmenity_pings(rs.getDouble("amenity_pings"));
					hseCollVO.setAccessory_pings(rs.getDouble("accessory_pings"));
					hseCollVO.setFloor(rs.getString("floor"));
					hseCollVO.setAge(rs.getDouble("age"));
					hseCollVO.setPattern(rs.getString("pattern"));
					hseCollVO.setOrientation(rs.getString("orientation"));
					hseCollVO.setBuilding_materials(rs.getString("building_materials"));
					hseCollVO.setParking_space(rs.getString("parking_space"));
					hseCollVO.setClassification_of_land(rs.getString("classification_of_land"));
					hseCollVO.setLand_pings(rs.getDouble("land_pings"));
					hseCollVO.setData_info(rs.getString("data_info"));
					hseCollVO.setLat(rs.getDouble("lat"));
					hseCollVO.setLng(rs.getDouble("lng"));
					hseCollVO.setHouse_states(rs.getString("house_states"));
					hseCollVO.setInsert_time(rs.getTimestamp("insert_Time"));
					hseCollVO.setFinal_update_time(rs.getTimestamp("final_update_time"));
					list.add(hseCollVO); // Store the row in the list
				}
			}
			// Handle any driver errors
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
		}
		return list;

	}

	public static void main(String[] args) {
		HseCollJDBCDAO dao = new HseCollJDBCDAO();

		// HseCollVO hseCollVO1 = new HseCollVO();
		// hseCollVO1.setMem_no("MB00000003");
		// hseCollVO1.setHouse_no("HN00000010");
		//
		// dao.insert(hseCollVO1);

		// HseCollVO hseCollVO2 = new HseCollVO();
		// hseCollVO2.setMem_no("");
		// hseCollVO2.setHouse_no("");
		//
		// dao.update(hseCollVO2);
		//
		//
		// dao.delete("MB00000001","HN00000011");
		//
		//
		// HseCollVO hseCollVO3 =
		// dao.findByPrimaryKey("MB00000003","HN00000001");
		// System.out.print(hseCollVO3.getMem_no() + ",");
		// System.out.print(hseCollVO3.getHouse_no() + ",");
		//
		// System.out.println("---------------------");
		//
		//
		// List<HseCollVO> list = dao.getAll();
		// for (HseCollVO aHseColl : list) {
		// System.out.print(aHseColl.getMem_no() + ",");
		// System.out.print(aHseColl.getHouse_no() + ",");
		//
		// System.out.println();
		// }

	}

}

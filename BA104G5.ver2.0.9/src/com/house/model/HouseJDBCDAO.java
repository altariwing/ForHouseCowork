package com.house.model;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.house.tool.HouseCompositeQuery;

public class HouseJDBCDAO implements House_interface {
	String name = "BA104G5";
	String password = "ba104g5";
	String url = "jdbc:oracle:thin:@localhost:1521:XE";
	String driver = "oracle.jdbc.driver.OracleDriver";

	private static final String INSERT = "INSERT INTO HOUSEINFO (" + "house_No," + "re_no," + "house_serial_number,"
			+ "title," + "location," + "house_type," + "price," + "total_pings," + "main_pings," + "amenity_pings,"
			+ "accessory_pings," + "floor," + "age," + "pattern," + "orientation," + "building_materials,"
			+ "parking_space," + "classification_of_land," + "land_pings," + "data_info," + "main_img," + "lat,"
			+ "lng," + "house_states," + "insert_time,"
			+ "final_update_time)VALUES('HSE'||LPAD(to_char(house_sequence.nextval),8,'0'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,current_timestamp,current_timestamp)";
	private static final String UPDATE = "UPDATE HOUSEINFO SET " + "re_no=?," + "house_serial_number=?," + "title=?,"
			+ "location=?," + "house_type=?," + "price=?," + "total_pings=?," + "main_pings=?," + "amenity_pings=?,"
			+ "accessory_pings=?," + "floor=?," + "age=?," + "pattern=?," + "orientation=?," + "building_materials=?,"
			+ "parking_space=?," + "classification_of_land=?," + "land_pings=?," + "data_info=?," + "main_img=?,"
			+ "lat=?," + "lng=?," + "final_update_time=current_timestamp," + "house_states=? where house_no=?";
	private static final String DELETE = "DELETE FROM HOUSEINFO WHERE House_No=?";
	private static final String GET_ONE = "SELECT * FROM HOUSEINFO WHERE House_No=?";
	private static final String GET_ALL = "SELECT * FROM HOUSEINFO";
	private static final String FIND_BY_CONDITION = "SELECT * FROM HOUSEINFO WHERE";

	@Override
	public String insert(HouseVO houseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String[] cols = { "house_no" };
		ResultSet rs = null;
		String house_no = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);

			pstmt = con.prepareStatement(INSERT, cols);
			pstmt.setString(1, houseVO.getRe_no());
			pstmt.setString(2, houseVO.getHouse_serial_number());
			pstmt.setString(3, houseVO.getTitle());
			pstmt.setString(4, houseVO.getLocation());
			pstmt.setString(5, houseVO.getHouse_type());
			pstmt.setInt(6, houseVO.getPrice());
			pstmt.setDouble(7, houseVO.getTotal_pings());
			pstmt.setDouble(8, houseVO.getMain_pings());
			pstmt.setDouble(9, houseVO.getAmenity_pings());
			pstmt.setDouble(10, houseVO.getAccessory_pings());
			pstmt.setString(11, houseVO.getFloor());
			pstmt.setDouble(12, houseVO.getAge());
			pstmt.setString(13, houseVO.getPattern());
			pstmt.setString(14, houseVO.getOrientation());
			pstmt.setString(15, houseVO.getBuilding_materials());
			pstmt.setString(16, houseVO.getParking_space());
			pstmt.setString(17, houseVO.getClassification_of_land());
			pstmt.setDouble(18, houseVO.getLand_pings());
			pstmt.setString(19, houseVO.getData_info());
			pstmt.setBytes(20, houseVO.getMain_img());
			pstmt.setDouble(21, houseVO.getLat());
			pstmt.setDouble(22, houseVO.getLng());
			pstmt.setString(23, houseVO.getHouse_states());
			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();
			while (rs.next()) {
				house_no = rs.getString(1);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return house_no;
	}

	@Override
	public void update(HouseVO houseVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, houseVO.getRe_no());
			pstmt.setString(2, houseVO.getHouse_serial_number());
			pstmt.setString(3, houseVO.getTitle());
			pstmt.setString(4, houseVO.getLocation());
			pstmt.setString(5, houseVO.getHouse_type());
			pstmt.setInt(6, houseVO.getPrice());
			pstmt.setDouble(7, houseVO.getTotal_pings());
			pstmt.setDouble(8, houseVO.getMain_pings());
			pstmt.setDouble(9, houseVO.getAmenity_pings());
			pstmt.setDouble(10, houseVO.getAccessory_pings());
			pstmt.setString(11, houseVO.getFloor());
			pstmt.setDouble(12, houseVO.getAge());
			pstmt.setString(13, houseVO.getPattern());
			pstmt.setString(14, houseVO.getOrientation());
			pstmt.setString(15, houseVO.getBuilding_materials());
			pstmt.setString(16, houseVO.getParking_space());
			pstmt.setString(17, houseVO.getClassification_of_land());
			pstmt.setDouble(18, houseVO.getLand_pings());
			pstmt.setString(19, houseVO.getData_info());
			pstmt.setBytes(20, houseVO.getMain_img());
			pstmt.setDouble(21, houseVO.getLat());
			pstmt.setDouble(22, houseVO.getLng());
			pstmt.setString(23, houseVO.getHouse_states());
			pstmt.setString(24, houseVO.getHouse_no());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void delete(String house_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, house_no);
			int deleteNumber = pstmt.executeUpdate();
			System.out.println(deleteNumber);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public HouseVO findByPrimaryKey(String house_no) {
		HouseVO houseVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			pstmt = con.prepareStatement(GET_ONE);
			pstmt.setString(1, house_no);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				houseVO = new HouseVO();
				houseVO.setHouse_no(rs.getString("house_No"));
				houseVO.setRe_no(rs.getString("re_no"));
				houseVO.setHouse_serial_number(rs.getString("house_serial_number"));
				houseVO.setTitle(rs.getString("title"));
				houseVO.setLocation(rs.getString("location"));
				houseVO.setHouse_type(rs.getString("house_type"));
				houseVO.setPrice(rs.getInt("price"));
				houseVO.setTotal_pings(rs.getDouble("total_pings"));
				houseVO.setMain_pings(rs.getDouble("main_pings"));
				houseVO.setAmenity_pings(rs.getDouble("amenity_pings"));
				houseVO.setAccessory_pings(rs.getDouble("accessory_pings"));
				houseVO.setFloor(rs.getString("floor"));
				houseVO.setAge(rs.getDouble("age"));
				houseVO.setPattern(rs.getString("pattern"));
				houseVO.setOrientation(rs.getString("orientation"));
				houseVO.setBuilding_materials(rs.getString("building_materials"));
				houseVO.setParking_space(rs.getString("parking_space"));
				houseVO.setClassification_of_land(rs.getString("classification_of_land"));
				houseVO.setLand_pings(rs.getDouble("land_pings"));
				houseVO.setData_info(rs.getString("data_info"));
				houseVO.setMain_img(rs.getBytes("main_img"));
				houseVO.setLat(rs.getDouble("lat"));
				houseVO.setLng(rs.getDouble("lng"));
				houseVO.setHouse_states(rs.getString("house_states"));
				houseVO.setInsert_time(rs.getTimestamp("insert_Time"));
				houseVO.setFinal_update_time(rs.getTimestamp("final_update_time"));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
		return houseVO;
	}

	@Override
	public List<HouseVO> getAll() {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List<HouseVO> list = new ArrayList<>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				HouseVO houseVO = new HouseVO();
				houseVO.setHouse_no(rs.getString("house_No"));
				houseVO.setTitle(rs.getString("title"));
				houseVO.setRe_no(rs.getString("re_no"));
				houseVO.setHouse_serial_number(rs.getString("house_serial_number"));
				houseVO.setLocation(rs.getString("location"));
				houseVO.setHouse_type(rs.getString("house_type"));
				houseVO.setPrice(rs.getInt("price"));
				houseVO.setTotal_pings(rs.getDouble("total_pings"));
				houseVO.setMain_pings(rs.getDouble("main_pings"));
				houseVO.setAmenity_pings(rs.getDouble("amenity_pings"));
				houseVO.setAccessory_pings(rs.getDouble("accessory_pings"));
				houseVO.setFloor(rs.getString("floor"));
				houseVO.setAge(rs.getDouble("age"));
				houseVO.setPattern(rs.getString("pattern"));
				houseVO.setOrientation(rs.getString("orientation"));
				houseVO.setBuilding_materials(rs.getString("building_materials"));
				houseVO.setParking_space(rs.getString("parking_space"));
				houseVO.setClassification_of_land(rs.getString("classification_of_land"));
				houseVO.setLand_pings(rs.getDouble("land_pings"));
				houseVO.setData_info(rs.getString("data_info"));
				houseVO.setMain_img(rs.getBytes("main_img"));
				houseVO.setLat(rs.getDouble("lat"));
				houseVO.setLng(rs.getDouble("lng"));
				houseVO.setHouse_states(rs.getString("house_states"));
				houseVO.setInsert_time(rs.getTimestamp("insert_Time"));
				houseVO.setFinal_update_time(rs.getTimestamp("final_update_time"));
				list.add(houseVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
		return list;
	}

	public List<HouseVO> getAllJson() {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List<HouseVO> list = new ArrayList<>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				HouseVO houseVO = new HouseVO();
				houseVO.setHouse_no(rs.getString("house_No"));
				houseVO.setTitle(rs.getString("title"));
				houseVO.setRe_no(rs.getString("re_no"));
				houseVO.setHouse_serial_number(rs.getString("house_serial_number"));
				houseVO.setLocation(rs.getString("location"));
				houseVO.setHouse_type(rs.getString("house_type"));
				houseVO.setPrice(rs.getInt("price"));
				houseVO.setTotal_pings(rs.getDouble("total_pings"));
				houseVO.setMain_pings(rs.getDouble("main_pings"));
				houseVO.setAmenity_pings(rs.getDouble("amenity_pings"));
				houseVO.setAccessory_pings(rs.getDouble("accessory_pings"));
				houseVO.setFloor(rs.getString("floor"));
				houseVO.setAge(rs.getDouble("age"));
				houseVO.setPattern(rs.getString("pattern"));
				houseVO.setOrientation(rs.getString("orientation"));
				houseVO.setBuilding_materials(rs.getString("building_materials"));
				houseVO.setParking_space(rs.getString("parking_space"));
				houseVO.setClassification_of_land(rs.getString("classification_of_land"));
				houseVO.setLand_pings(rs.getDouble("land_pings"));
				houseVO.setData_info(rs.getString("data_info"));

			
//				String main_img_base64;
//				try {
//					byte[] img = rs.getBytes("main_img");
//					System.out.println("img length = " + img.length);
//					main_img_base64 = new String(Base64.encodeBase64(img), "UTF-8");
//					houseVO.setMain_img_base64(main_img_base64);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
	

				houseVO.setLat(rs.getDouble("lat"));
				houseVO.setLng(rs.getDouble("lng"));
				houseVO.setHouse_states(rs.getString("house_states"));
				houseVO.setInsert_time(rs.getTimestamp("insert_Time"));
				houseVO.setFinal_update_time(rs.getTimestamp("final_update_time"));
				list.add(houseVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
		return list;
	}

	public byte[] getByteArray(String path) {
		byte[] bytes = new byte[8192];
		int length;
		try {
			FileInputStream fis = new FileInputStream(path);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();

			while ((length = fis.read(bytes)) != -1) {
				bos.write(bytes, 0, length);
			}
			bos.close();
			fis.close();
			return bos.toByteArray();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}

	@Override
	public List<HouseVO> findByCondtion(String location) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List<HouseVO> list = new ArrayList<>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			String find_by_location = FIND_BY_CONDITION + " LOCATION LIKE '%" + location + "%'";
			System.out.println(find_by_location);
			pstmt = con.prepareStatement(find_by_location);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				HouseVO houseVO = new HouseVO();
				houseVO.setHouse_no(rs.getString("house_No"));
				houseVO.setTitle(rs.getString("title"));
				houseVO.setRe_no(rs.getString("re_no"));
				houseVO.setHouse_serial_number(rs.getString("house_serial_number"));
				houseVO.setLocation(rs.getString("location"));
				houseVO.setHouse_type(rs.getString("house_type"));
				houseVO.setPrice(rs.getInt("price"));
				houseVO.setTotal_pings(rs.getDouble("total_pings"));
				houseVO.setMain_pings(rs.getDouble("main_pings"));
				houseVO.setAmenity_pings(rs.getDouble("amenity_pings"));
				houseVO.setAccessory_pings(rs.getDouble("accessory_pings"));
				houseVO.setFloor(rs.getString("floor"));
				houseVO.setAge(rs.getDouble("age"));
				houseVO.setPattern(rs.getString("pattern"));
				houseVO.setOrientation(rs.getString("orientation"));
				houseVO.setBuilding_materials(rs.getString("building_materials"));
				houseVO.setParking_space(rs.getString("parking_space"));
				houseVO.setClassification_of_land(rs.getString("classification_of_land"));
				houseVO.setLand_pings(rs.getDouble("land_pings"));
				houseVO.setData_info(rs.getString("data_info"));
				houseVO.setMain_img(rs.getBytes("main_img"));
				houseVO.setLat(rs.getDouble("lat"));
				houseVO.setLng(rs.getDouble("lng"));
				houseVO.setHouse_states(rs.getString("house_states"));
				houseVO.setInsert_time(rs.getTimestamp("insert_Time"));
				houseVO.setFinal_update_time(rs.getTimestamp("final_update_time"));
				list.add(houseVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
		return list;
	}

	@Override
	public List<HouseVO> findByPrice(Integer price_str, Integer price_end) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List<HouseVO> list = new ArrayList<>();
		String find_by_price = "";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);

			if (price_end == 9999) {
				find_by_price = FIND_BY_CONDITION + " PRICE > 5000";
			} else {
				find_by_price = FIND_BY_CONDITION + " PRICE BETWEEN " + price_str + " and " + price_end;
			}
			System.out.println(find_by_price);
			pstmt = con.prepareStatement(find_by_price);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				HouseVO houseVO = new HouseVO();
				houseVO.setHouse_no(rs.getString("house_No"));
				houseVO.setTitle(rs.getString("title"));
				houseVO.setRe_no(rs.getString("re_no"));
				houseVO.setHouse_serial_number(rs.getString("house_serial_number"));
				houseVO.setLocation(rs.getString("location"));
				houseVO.setHouse_type(rs.getString("house_type"));
				houseVO.setPrice(rs.getInt("price"));
				houseVO.setTotal_pings(rs.getDouble("total_pings"));
				houseVO.setMain_pings(rs.getDouble("main_pings"));
				houseVO.setAmenity_pings(rs.getDouble("amenity_pings"));
				houseVO.setAccessory_pings(rs.getDouble("accessory_pings"));
				houseVO.setFloor(rs.getString("floor"));
				houseVO.setAge(rs.getDouble("age"));
				houseVO.setPattern(rs.getString("pattern"));
				houseVO.setOrientation(rs.getString("orientation"));
				houseVO.setBuilding_materials(rs.getString("building_materials"));
				houseVO.setParking_space(rs.getString("parking_space"));
				houseVO.setClassification_of_land(rs.getString("classification_of_land"));
				houseVO.setLand_pings(rs.getDouble("land_pings"));
				houseVO.setData_info(rs.getString("data_info"));
				houseVO.setMain_img(rs.getBytes("main_img"));
				houseVO.setLat(rs.getDouble("lat"));
				houseVO.setLng(rs.getDouble("lng"));
				houseVO.setHouse_states(rs.getString("house_states"));
				houseVO.setInsert_time(rs.getTimestamp("insert_Time"));
				houseVO.setFinal_update_time(rs.getTimestamp("final_update_time"));
				list.add(houseVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
		return list;

	}

	@Override
	public List<HouseVO> findByPattern(Integer roomCount) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List<HouseVO> list = new ArrayList<>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			String find_by_pattern = "";
			if (roomCount == 99) {
				find_by_pattern = FIND_BY_CONDITION
						+ " PATTERN NOT LIKE '%1房%' AND PATTERN NOT LIKE '%2房%' AND PATTERN NOT LIKE '%3房%' AND PATTERN NOT LIKE '%4房%'";
			} else {
				find_by_pattern = FIND_BY_CONDITION + " PATTERN LIKE '%" + roomCount + "房%'";
			}
			System.out.println(find_by_pattern);
			pstmt = con.prepareStatement(find_by_pattern);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HouseVO houseVO = new HouseVO();
				houseVO.setHouse_no(rs.getString("house_No"));
				houseVO.setTitle(rs.getString("title"));
				houseVO.setRe_no(rs.getString("re_no"));
				houseVO.setHouse_serial_number(rs.getString("house_serial_number"));
				houseVO.setLocation(rs.getString("location"));
				houseVO.setHouse_type(rs.getString("house_type"));
				houseVO.setPrice(rs.getInt("price"));
				houseVO.setTotal_pings(rs.getDouble("total_pings"));
				houseVO.setMain_pings(rs.getDouble("main_pings"));
				houseVO.setAmenity_pings(rs.getDouble("amenity_pings"));
				houseVO.setAccessory_pings(rs.getDouble("accessory_pings"));
				houseVO.setFloor(rs.getString("floor"));
				houseVO.setAge(rs.getDouble("age"));
				houseVO.setPattern(rs.getString("pattern"));
				houseVO.setOrientation(rs.getString("orientation"));
				houseVO.setBuilding_materials(rs.getString("building_materials"));
				houseVO.setParking_space(rs.getString("parking_space"));
				houseVO.setClassification_of_land(rs.getString("classification_of_land"));
				houseVO.setLand_pings(rs.getDouble("land_pings"));
				houseVO.setData_info(rs.getString("data_info"));
				houseVO.setMain_img(rs.getBytes("main_img"));
				houseVO.setLat(rs.getDouble("lat"));
				houseVO.setLng(rs.getDouble("lng"));
				houseVO.setHouse_states(rs.getString("house_states"));
				houseVO.setInsert_time(rs.getTimestamp("insert_Time"));
				houseVO.setFinal_update_time(rs.getTimestamp("final_update_time"));
				list.add(houseVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
		return list;
	}

	@Override
	public List<HouseVO> findByHouseType(String house_type) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List<HouseVO> list = new ArrayList<>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			String find_by_house_type = FIND_BY_CONDITION + " HOUSE_TYPE LIKE '%" + house_type + "%'";
			System.out.println(find_by_house_type);
			pstmt = con.prepareStatement(find_by_house_type);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				HouseVO houseVO = new HouseVO();
				houseVO.setHouse_no(rs.getString("house_No"));
				houseVO.setTitle(rs.getString("title"));
				houseVO.setRe_no(rs.getString("re_no"));
				houseVO.setHouse_serial_number(rs.getString("house_serial_number"));
				houseVO.setLocation(rs.getString("location"));
				houseVO.setHouse_type(rs.getString("house_type"));
				houseVO.setPrice(rs.getInt("price"));
				houseVO.setTotal_pings(rs.getDouble("total_pings"));
				houseVO.setMain_pings(rs.getDouble("main_pings"));
				houseVO.setAmenity_pings(rs.getDouble("amenity_pings"));
				houseVO.setAccessory_pings(rs.getDouble("accessory_pings"));
				houseVO.setFloor(rs.getString("floor"));
				houseVO.setAge(rs.getDouble("age"));
				houseVO.setPattern(rs.getString("pattern"));
				houseVO.setOrientation(rs.getString("orientation"));
				houseVO.setBuilding_materials(rs.getString("building_materials"));
				houseVO.setParking_space(rs.getString("parking_space"));
				houseVO.setClassification_of_land(rs.getString("classification_of_land"));
				houseVO.setLand_pings(rs.getDouble("land_pings"));
				houseVO.setData_info(rs.getString("data_info"));
				houseVO.setMain_img(rs.getBytes("main_img"));
				houseVO.setLat(rs.getDouble("lat"));
				houseVO.setLng(rs.getDouble("lng"));
				houseVO.setHouse_states(rs.getString("house_states"));
				houseVO.setInsert_time(rs.getTimestamp("insert_Time"));
				houseVO.setFinal_update_time(rs.getTimestamp("final_update_time"));
				list.add(houseVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
		return list;
	}

	@Override
	public List<HouseVO> findByTotalPings(Double total_pings_str, Double total_pings_end) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List<HouseVO> list = new ArrayList<>();
		String find_by_total_pings = "";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);

			if (total_pings_end == 99) {
				find_by_total_pings = FIND_BY_CONDITION + " total_pings > " + total_pings_str;
			} else {
				find_by_total_pings = FIND_BY_CONDITION + " total_pings BETWEEN " + total_pings_str + " and "
						+ total_pings_end;
			}
			System.out.println(find_by_total_pings);
			pstmt = con.prepareStatement(find_by_total_pings);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				HouseVO houseVO = new HouseVO();
				houseVO.setHouse_no(rs.getString("house_No"));
				houseVO.setTitle(rs.getString("title"));
				houseVO.setRe_no(rs.getString("re_no"));
				houseVO.setHouse_serial_number(rs.getString("house_serial_number"));
				houseVO.setLocation(rs.getString("location"));
				houseVO.setHouse_type(rs.getString("house_type"));
				houseVO.setPrice(rs.getInt("price"));
				houseVO.setTotal_pings(rs.getDouble("total_pings"));
				houseVO.setMain_pings(rs.getDouble("main_pings"));
				houseVO.setAmenity_pings(rs.getDouble("amenity_pings"));
				houseVO.setAccessory_pings(rs.getDouble("accessory_pings"));
				houseVO.setFloor(rs.getString("floor"));
				houseVO.setAge(rs.getDouble("age"));
				houseVO.setPattern(rs.getString("pattern"));
				houseVO.setOrientation(rs.getString("orientation"));
				houseVO.setBuilding_materials(rs.getString("building_materials"));
				houseVO.setParking_space(rs.getString("parking_space"));
				houseVO.setClassification_of_land(rs.getString("classification_of_land"));
				houseVO.setLand_pings(rs.getDouble("land_pings"));
				houseVO.setData_info(rs.getString("data_info"));
				houseVO.setMain_img(rs.getBytes("main_img"));
				houseVO.setLat(rs.getDouble("lat"));
				houseVO.setLng(rs.getDouble("lng"));
				houseVO.setHouse_states(rs.getString("house_states"));
				houseVO.setInsert_time(rs.getTimestamp("insert_Time"));
				houseVO.setFinal_update_time(rs.getTimestamp("final_update_time"));
				list.add(houseVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
		return list;

	}

	@Override
	public List<HouseVO> getAll(String sortedCondition) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List<HouseVO> list = new ArrayList<>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			String get_all_sorted = GET_ALL + " ORDER BY " + sortedCondition;
			pstmt = con.prepareStatement(get_all_sorted);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				HouseVO houseVO = new HouseVO();
				houseVO.setHouse_no(rs.getString("house_No"));
				houseVO.setTitle(rs.getString("title"));
				houseVO.setRe_no(rs.getString("re_no"));
				houseVO.setHouse_serial_number(rs.getString("house_serial_number"));
				houseVO.setLocation(rs.getString("location"));
				houseVO.setHouse_type(rs.getString("house_type"));
				houseVO.setPrice(rs.getInt("price"));
				houseVO.setTotal_pings(rs.getDouble("total_pings"));
				houseVO.setMain_pings(rs.getDouble("main_pings"));
				houseVO.setAmenity_pings(rs.getDouble("amenity_pings"));
				houseVO.setAccessory_pings(rs.getDouble("accessory_pings"));
				houseVO.setFloor(rs.getString("floor"));
				houseVO.setAge(rs.getDouble("age"));
				houseVO.setPattern(rs.getString("pattern"));
				houseVO.setOrientation(rs.getString("orientation"));
				houseVO.setBuilding_materials(rs.getString("building_materials"));
				houseVO.setParking_space(rs.getString("parking_space"));
				houseVO.setClassification_of_land(rs.getString("classification_of_land"));
				houseVO.setLand_pings(rs.getDouble("land_pings"));
				houseVO.setData_info(rs.getString("data_info"));
				houseVO.setMain_img(rs.getBytes("main_img"));
				houseVO.setLat(rs.getDouble("lat"));
				houseVO.setLng(rs.getDouble("lng"));
				houseVO.setHouse_states(rs.getString("house_states"));
				houseVO.setInsert_time(rs.getTimestamp("insert_Time"));
				houseVO.setFinal_update_time(rs.getTimestamp("final_update_time"));
				list.add(houseVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
		return list;

	}

	@Override
	public List<HouseVO> findByCondtion(String location, String sortedCondition) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List<HouseVO> list = new ArrayList<>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			String find_by_location_sorted = FIND_BY_CONDITION + " LOCATION LIKE '%" + location + "%' ORDER BY "
					+ sortedCondition;
			System.out.println(find_by_location_sorted);
			pstmt = con.prepareStatement(find_by_location_sorted);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				HouseVO houseVO = new HouseVO();
				houseVO.setHouse_no(rs.getString("house_No"));
				houseVO.setTitle(rs.getString("title"));
				houseVO.setRe_no(rs.getString("re_no"));
				houseVO.setHouse_serial_number(rs.getString("house_serial_number"));
				houseVO.setLocation(rs.getString("location"));
				houseVO.setHouse_type(rs.getString("house_type"));
				houseVO.setPrice(rs.getInt("price"));
				houseVO.setTotal_pings(rs.getDouble("total_pings"));
				houseVO.setMain_pings(rs.getDouble("main_pings"));
				houseVO.setAmenity_pings(rs.getDouble("amenity_pings"));
				houseVO.setAccessory_pings(rs.getDouble("accessory_pings"));
				houseVO.setFloor(rs.getString("floor"));
				houseVO.setAge(rs.getDouble("age"));
				houseVO.setPattern(rs.getString("pattern"));
				houseVO.setOrientation(rs.getString("orientation"));
				houseVO.setBuilding_materials(rs.getString("building_materials"));
				houseVO.setParking_space(rs.getString("parking_space"));
				houseVO.setClassification_of_land(rs.getString("classification_of_land"));
				houseVO.setLand_pings(rs.getDouble("land_pings"));
				houseVO.setData_info(rs.getString("data_info"));
				houseVO.setMain_img(rs.getBytes("main_img"));
				houseVO.setLat(rs.getDouble("lat"));
				houseVO.setLng(rs.getDouble("lng"));
				houseVO.setHouse_states(rs.getString("house_states"));
				houseVO.setInsert_time(rs.getTimestamp("insert_Time"));
				houseVO.setFinal_update_time(rs.getTimestamp("final_update_time"));
				list.add(houseVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
		return list;
	}

	@Override
	public List<HouseVO> findByPrice(Integer price_str, Integer price_end, String sortedCondition) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List<HouseVO> list = new ArrayList<>();
		String find_by_price = "";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);

			if (price_end == 9999) {
				find_by_price = FIND_BY_CONDITION + " PRICE > 5000 ORDER BY " + sortedCondition;
			} else {
				find_by_price = FIND_BY_CONDITION + " PRICE BETWEEN " + price_str + " and " + price_end + " ORDER BY "
						+ sortedCondition;
			}
			System.out.println(find_by_price);
			pstmt = con.prepareStatement(find_by_price);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				HouseVO houseVO = new HouseVO();
				houseVO.setHouse_no(rs.getString("house_No"));
				houseVO.setTitle(rs.getString("title"));
				houseVO.setRe_no(rs.getString("re_no"));
				houseVO.setHouse_serial_number(rs.getString("house_serial_number"));
				houseVO.setLocation(rs.getString("location"));
				houseVO.setHouse_type(rs.getString("house_type"));
				houseVO.setPrice(rs.getInt("price"));
				houseVO.setTotal_pings(rs.getDouble("total_pings"));
				houseVO.setMain_pings(rs.getDouble("main_pings"));
				houseVO.setAmenity_pings(rs.getDouble("amenity_pings"));
				houseVO.setAccessory_pings(rs.getDouble("accessory_pings"));
				houseVO.setFloor(rs.getString("floor"));
				houseVO.setAge(rs.getDouble("age"));
				houseVO.setPattern(rs.getString("pattern"));
				houseVO.setOrientation(rs.getString("orientation"));
				houseVO.setBuilding_materials(rs.getString("building_materials"));
				houseVO.setParking_space(rs.getString("parking_space"));
				houseVO.setClassification_of_land(rs.getString("classification_of_land"));
				houseVO.setLand_pings(rs.getDouble("land_pings"));
				houseVO.setData_info(rs.getString("data_info"));
				houseVO.setMain_img(rs.getBytes("main_img"));
				houseVO.setLat(rs.getDouble("lat"));
				houseVO.setLng(rs.getDouble("lng"));
				houseVO.setHouse_states(rs.getString("house_states"));
				houseVO.setInsert_time(rs.getTimestamp("insert_Time"));
				houseVO.setFinal_update_time(rs.getTimestamp("final_update_time"));
				list.add(houseVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
		return list;
	}

	@Override
	public List<HouseVO> findByPattern(Integer roomCount, String sortedCondition) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List<HouseVO> list = new ArrayList<>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			String find_by_pattern = "";
			if (roomCount == 99) {
				find_by_pattern = FIND_BY_CONDITION
						+ " PATTERN NOT LIKE '%1房%' AND PATTERN NOT LIKE '%2房%' AND PATTERN NOT LIKE '%3房%' AND PATTERN NOT LIKE '%4房%' ORDER BY "
						+ sortedCondition;
			} else {
				find_by_pattern = FIND_BY_CONDITION + " PATTERN LIKE '%" + roomCount + "房%' ORDER BY "
						+ sortedCondition;
			}
			System.out.println(find_by_pattern);
			pstmt = con.prepareStatement(find_by_pattern);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				HouseVO houseVO = new HouseVO();
				houseVO.setHouse_no(rs.getString("house_No"));
				houseVO.setTitle(rs.getString("title"));
				houseVO.setRe_no(rs.getString("re_no"));
				houseVO.setHouse_serial_number(rs.getString("house_serial_number"));
				houseVO.setLocation(rs.getString("location"));
				houseVO.setHouse_type(rs.getString("house_type"));
				houseVO.setPrice(rs.getInt("price"));
				houseVO.setTotal_pings(rs.getDouble("total_pings"));
				houseVO.setMain_pings(rs.getDouble("main_pings"));
				houseVO.setAmenity_pings(rs.getDouble("amenity_pings"));
				houseVO.setAccessory_pings(rs.getDouble("accessory_pings"));
				houseVO.setFloor(rs.getString("floor"));
				houseVO.setAge(rs.getDouble("age"));
				houseVO.setPattern(rs.getString("pattern"));
				houseVO.setOrientation(rs.getString("orientation"));
				houseVO.setBuilding_materials(rs.getString("building_materials"));
				houseVO.setParking_space(rs.getString("parking_space"));
				houseVO.setClassification_of_land(rs.getString("classification_of_land"));
				houseVO.setLand_pings(rs.getDouble("land_pings"));
				houseVO.setData_info(rs.getString("data_info"));
				houseVO.setMain_img(rs.getBytes("main_img"));
				houseVO.setLat(rs.getDouble("lat"));
				houseVO.setLng(rs.getDouble("lng"));
				houseVO.setHouse_states(rs.getString("house_states"));
				houseVO.setInsert_time(rs.getTimestamp("insert_Time"));
				houseVO.setFinal_update_time(rs.getTimestamp("final_update_time"));
				list.add(houseVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
		return list;
	}

	@Override
	public List<HouseVO> findByHouseType(String house_type, String sortedCondition) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List<HouseVO> list = new ArrayList<>();

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);
			String find_by_house_type = FIND_BY_CONDITION + " HOUSE_TYPE LIKE '%" + house_type + "%' ORDER BY "
					+ sortedCondition;
			System.out.println(find_by_house_type);
			pstmt = con.prepareStatement(find_by_house_type);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				HouseVO houseVO = new HouseVO();
				houseVO.setHouse_no(rs.getString("house_No"));
				houseVO.setTitle(rs.getString("title"));
				houseVO.setRe_no(rs.getString("re_no"));
				houseVO.setHouse_serial_number(rs.getString("house_serial_number"));
				houseVO.setLocation(rs.getString("location"));
				houseVO.setHouse_type(rs.getString("house_type"));
				houseVO.setPrice(rs.getInt("price"));
				houseVO.setTotal_pings(rs.getDouble("total_pings"));
				houseVO.setMain_pings(rs.getDouble("main_pings"));
				houseVO.setAmenity_pings(rs.getDouble("amenity_pings"));
				houseVO.setAccessory_pings(rs.getDouble("accessory_pings"));
				houseVO.setFloor(rs.getString("floor"));
				houseVO.setAge(rs.getDouble("age"));
				houseVO.setPattern(rs.getString("pattern"));
				houseVO.setOrientation(rs.getString("orientation"));
				houseVO.setBuilding_materials(rs.getString("building_materials"));
				houseVO.setParking_space(rs.getString("parking_space"));
				houseVO.setClassification_of_land(rs.getString("classification_of_land"));
				houseVO.setLand_pings(rs.getDouble("land_pings"));
				houseVO.setData_info(rs.getString("data_info"));
				houseVO.setMain_img(rs.getBytes("main_img"));
				houseVO.setLat(rs.getDouble("lat"));
				houseVO.setLng(rs.getDouble("lng"));
				houseVO.setHouse_states(rs.getString("house_states"));
				houseVO.setInsert_time(rs.getTimestamp("insert_Time"));
				houseVO.setFinal_update_time(rs.getTimestamp("final_update_time"));
				list.add(houseVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
		return list;
	}

	@Override
	public List<HouseVO> findByTotalPings(Double total_pings_str, Double total_pings_end, String sortedCondition) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		List<HouseVO> list = new ArrayList<>();
		String find_by_total_pings = "";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);

			if (total_pings_end == 99) {
				find_by_total_pings = FIND_BY_CONDITION + " total_pings > " + total_pings_str + " ORDER BY "
						+ sortedCondition;
			} else {
				find_by_total_pings = FIND_BY_CONDITION + " total_pings BETWEEN " + total_pings_str + " and "
						+ total_pings_end + " ORDER BY " + sortedCondition;
			}
			System.out.println(find_by_total_pings);
			pstmt = con.prepareStatement(find_by_total_pings);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				HouseVO houseVO = new HouseVO();
				houseVO.setHouse_no(rs.getString("house_No"));
				houseVO.setTitle(rs.getString("title"));
				houseVO.setRe_no(rs.getString("re_no"));
				houseVO.setHouse_serial_number(rs.getString("house_serial_number"));
				houseVO.setLocation(rs.getString("location"));
				houseVO.setHouse_type(rs.getString("house_type"));
				houseVO.setPrice(rs.getInt("price"));
				houseVO.setTotal_pings(rs.getDouble("total_pings"));
				houseVO.setMain_pings(rs.getDouble("main_pings"));
				houseVO.setAmenity_pings(rs.getDouble("amenity_pings"));
				houseVO.setAccessory_pings(rs.getDouble("accessory_pings"));
				houseVO.setFloor(rs.getString("floor"));
				houseVO.setAge(rs.getDouble("age"));
				houseVO.setPattern(rs.getString("pattern"));
				houseVO.setOrientation(rs.getString("orientation"));
				houseVO.setBuilding_materials(rs.getString("building_materials"));
				houseVO.setParking_space(rs.getString("parking_space"));
				houseVO.setClassification_of_land(rs.getString("classification_of_land"));
				houseVO.setLand_pings(rs.getDouble("land_pings"));
				houseVO.setData_info(rs.getString("data_info"));
				houseVO.setMain_img(rs.getBytes("main_img"));
				houseVO.setLat(rs.getDouble("lat"));
				houseVO.setLng(rs.getDouble("lng"));
				houseVO.setHouse_states(rs.getString("house_states"));
				houseVO.setInsert_time(rs.getTimestamp("insert_Time"));
				houseVO.setFinal_update_time(rs.getTimestamp("final_update_time"));
				list.add(houseVO);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
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
		return list;
	}

	public List<HouseVO> findBySeveralConds(Map<String, String[]> map) {
		List<HouseVO> list = new ArrayList<HouseVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);

			String finalSQL = "select * from HOUSEINFO " + HouseCompositeQuery.get_WhereCondition(map);
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				HouseVO houseVO = new HouseVO();
				houseVO.setHouse_no(rs.getString("house_No"));
				houseVO.setTitle(rs.getString("title"));
				houseVO.setRe_no(rs.getString("re_no"));
				houseVO.setHouse_serial_number(rs.getString("house_serial_number"));
				houseVO.setLocation(rs.getString("location"));
				houseVO.setHouse_type(rs.getString("house_type"));
				houseVO.setPrice(rs.getInt("price"));
				houseVO.setTotal_pings(rs.getDouble("total_pings"));
				houseVO.setMain_pings(rs.getDouble("main_pings"));
				houseVO.setAmenity_pings(rs.getDouble("amenity_pings"));
				houseVO.setAccessory_pings(rs.getDouble("accessory_pings"));
				houseVO.setFloor(rs.getString("floor"));
				houseVO.setAge(rs.getDouble("age"));
				houseVO.setPattern(rs.getString("pattern"));
				houseVO.setOrientation(rs.getString("orientation"));
				houseVO.setBuilding_materials(rs.getString("building_materials"));
				houseVO.setParking_space(rs.getString("parking_space"));
				houseVO.setClassification_of_land(rs.getString("classification_of_land"));
				houseVO.setLand_pings(rs.getDouble("land_pings"));
				houseVO.setData_info(rs.getString("data_info"));
				houseVO.setMain_img(rs.getBytes("main_img"));
				houseVO.setLat(rs.getDouble("lat"));
				houseVO.setLng(rs.getDouble("lng"));
				houseVO.setHouse_states(rs.getString("house_states"));
				houseVO.setInsert_time(rs.getTimestamp("insert_Time"));
				houseVO.setFinal_update_time(rs.getTimestamp("final_update_time"));
				list.add(houseVO);
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public List<HouseVO> findBySeveralConds(Map<String, String[]> map, String sortedCondition) {
		List<HouseVO> list = new ArrayList<HouseVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);

			String finalSQL = "select * from HOUSEINFO " + HouseCompositeQuery.get_WhereCondition(map) + " ORDER BY "
					+ sortedCondition;
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				HouseVO houseVO = new HouseVO();
				houseVO.setHouse_no(rs.getString("house_No"));
				houseVO.setTitle(rs.getString("title"));
				houseVO.setRe_no(rs.getString("re_no"));
				houseVO.setHouse_serial_number(rs.getString("house_serial_number"));
				houseVO.setLocation(rs.getString("location"));
				houseVO.setHouse_type(rs.getString("house_type"));
				houseVO.setPrice(rs.getInt("price"));
				houseVO.setTotal_pings(rs.getDouble("total_pings"));
				houseVO.setMain_pings(rs.getDouble("main_pings"));
				houseVO.setAmenity_pings(rs.getDouble("amenity_pings"));
				houseVO.setAccessory_pings(rs.getDouble("accessory_pings"));
				houseVO.setFloor(rs.getString("floor"));
				houseVO.setAge(rs.getDouble("age"));
				houseVO.setPattern(rs.getString("pattern"));
				houseVO.setOrientation(rs.getString("orientation"));
				houseVO.setBuilding_materials(rs.getString("building_materials"));
				houseVO.setParking_space(rs.getString("parking_space"));
				houseVO.setClassification_of_land(rs.getString("classification_of_land"));
				houseVO.setLand_pings(rs.getDouble("land_pings"));
				houseVO.setData_info(rs.getString("data_info"));
				houseVO.setMain_img(rs.getBytes("main_img"));
				houseVO.setLat(rs.getDouble("lat"));
				houseVO.setLng(rs.getDouble("lng"));
				houseVO.setHouse_states(rs.getString("house_states"));
				houseVO.setInsert_time(rs.getTimestamp("insert_Time"));
				houseVO.setFinal_update_time(rs.getTimestamp("final_update_time"));
				list.add(houseVO);
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public List<HouseVO> findByKeyword(String keyword, String sortedCondition) {

		List<HouseVO> list = new ArrayList<HouseVO>();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, name, password);

			String finalSQL = "select * from HOUSEINFO " + HouseCompositeQuery.getKeyWordSQL(keyword) + " ORDER BY "
					+ sortedCondition;
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				HouseVO houseVO = new HouseVO();
				houseVO.setHouse_no(rs.getString("house_No"));
				houseVO.setTitle(rs.getString("title"));
				houseVO.setRe_no(rs.getString("re_no"));
				houseVO.setHouse_serial_number(rs.getString("house_serial_number"));
				houseVO.setLocation(rs.getString("location"));
				houseVO.setHouse_type(rs.getString("house_type"));
				houseVO.setPrice(rs.getInt("price"));
				houseVO.setTotal_pings(rs.getDouble("total_pings"));
				houseVO.setMain_pings(rs.getDouble("main_pings"));
				houseVO.setAmenity_pings(rs.getDouble("amenity_pings"));
				houseVO.setAccessory_pings(rs.getDouble("accessory_pings"));
				houseVO.setFloor(rs.getString("floor"));
				houseVO.setAge(rs.getDouble("age"));
				houseVO.setPattern(rs.getString("pattern"));
				houseVO.setOrientation(rs.getString("orientation"));
				houseVO.setBuilding_materials(rs.getString("building_materials"));
				houseVO.setParking_space(rs.getString("parking_space"));
				houseVO.setClassification_of_land(rs.getString("classification_of_land"));
				houseVO.setLand_pings(rs.getDouble("land_pings"));
				houseVO.setData_info(rs.getString("data_info"));
				houseVO.setMain_img(rs.getBytes("main_img"));
				houseVO.setLat(rs.getDouble("lat"));
				houseVO.setLng(rs.getDouble("lng"));
				houseVO.setHouse_states(rs.getString("house_states"));
				houseVO.setInsert_time(rs.getTimestamp("insert_Time"));
				houseVO.setFinal_update_time(rs.getTimestamp("final_update_time"));
				list.add(houseVO);
			}

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	// public static void main(String[] args) {
	//// insert測試OK!
	// HouseJDBCDAO dao = new HouseJDBCDAO();
	//
	// HouseVO houseVO = new HouseVO();
	// houseVO.setRe_no("永慶");
	// houseVO.setHouse_serial_number("2764617");
	// houseVO.setTitle("仁愛電梯兩房車位 - 仁愛路林蔭 15年內電梯二房");
	// houseVO.setLocation("台北市中正區仁愛路二段");
	// houseVO.setHouse_type("公寓");
	// houseVO.setPrice(2498);
	// houseVO.setTotal_pings(50.12);
	// houseVO.setMain_pings(30.12);
	// houseVO.setAmenity_pings(15.0);
	// houseVO.setAccessory_pings(5.0);
	// houseVO.setFloor(" 2~2/6樓");
	// houseVO.setAge(29.5);
	// houseVO.setPattern("2房(室)1廳1衛");
	// houseVO.setOrientation("朝向西南");
	// houseVO.setBuilding_materials("鋼筋混凝土(RC)");
	// houseVO.setParking_space("電腦選號 固定車位");
	// houseVO.setClassification_of_land("第三之二種住宅區");
	// houseVO.setLand_pings(6.02);
	// houseVO.setData_info("國小：東門國小");
	// String imgPath1 = "C:\\project\\tzukai\\imgs\\rechouse1.jpeg";
	// byte[] img1ByteArray = dao.getByteArray(imgPath1);
	// houseVO.setMain_img(img1ByteArray);
	// houseVO.setLat(133.00);
	// houseVO.setLng(50.00);
	// houseVO.setHouse_states("selling");
	// dao.insert(houseVO);
	//
	// houseVO.setRe_no("永慶");
	// houseVO.setHouse_serial_number("YC946209");
	// houseVO.setTitle("博嘉靜巷雅緻三房 - 邊間採光明亮，住家環境安靜");
	// houseVO.setLocation("台北市文山區木柵路四段");
	// houseVO.setHouse_type("電梯");
	// houseVO.setPrice(1380);
	// houseVO.setTotal_pings(37.08);
	// houseVO.setMain_pings(30.36);
	// houseVO.setAmenity_pings(0.0);
	// houseVO.setAccessory_pings(6.72);
	// houseVO.setFloor("3~3/5樓");
	// houseVO.setAge(36.8);
	// houseVO.setPattern("3房(室)2廳2衛");
	// houseVO.setOrientation("朝向北");
	// houseVO.setBuilding_materials("鋼筋混凝土(RC)");
	// houseVO.setParking_space("無車位");
	// houseVO.setClassification_of_land("第二種住宅區、道路用地");
	// houseVO.setLand_pings(10.69);
	// houseVO.setData_info("國中：木柵國中");
	// String imgPath2 = "C:\\project\\tzukai\\imgs\\rechouse2.jpeg";
	// byte[] img2ByteArray = dao.getByteArray(imgPath2);
	// houseVO.setMain_img(img2ByteArray);
	// houseVO.setLat(133.00);
	// houseVO.setLng(50.00);
	// houseVO.setHouse_states("selling");
	// dao.insert(houseVO);
	//
	// houseVO.setRe_no("信義");
	// houseVO.setHouse_serial_number("4546L");
	// houseVO.setTitle("大戶移居河濱生活(34546L)");
	// houseVO.setLocation("台北市文山區樟新街");
	// houseVO.setHouse_type("辦公");
	// houseVO.setPrice(4120);
	// houseVO.setTotal_pings(71.09);
	// houseVO.setMain_pings(41.11);
	// houseVO.setAmenity_pings(7.21);
	// houseVO.setAccessory_pings(22.77);
	// houseVO.setFloor("4/9樓");
	// houseVO.setAge(3.2);
	// houseVO.setPattern("4房/ 2廳/ 2衛/ 0室");
	// houseVO.setOrientation("大樓：東北");
	// houseVO.setBuilding_materials("正面石材");
	// houseVO.setParking_space("無車位");
	// houseVO.setClassification_of_land("無資料");
	// houseVO.setLand_pings(0.0);
	// houseVO.setData_info("雙拼戶戶邊間戶戶大面寬１７米２２．５ＣＭ中空樓板日本ＣｌｅａｎＵｐ停車位另購全熱交換機ｓｔｕｄｏｒ排水系統文山區木新路名軒濡山");
	// String imgPath3 = "C:\\project\\tzukai\\imgs\\rechouse3.jpeg";
	// byte[] img3ByteArray = dao.getByteArray(imgPath3);
	// houseVO.setMain_img(img3ByteArray);
	// houseVO.setLat(133.00);
	// houseVO.setLng(50.00);
	// houseVO.setHouse_states("selling");
	// dao.insert(houseVO);

	// UPDATE測試OK!!
	// HouseVO houseVO = new HouseVO();
	// houseVO.setRe_no("信義");
	// houseVO.setHouse_serial_number("2764617");
	// houseVO.setTitle("仁愛電梯兩房車位 - 仁愛路林蔭 15年內電梯二房");
	// houseVO.setLocation("台北市中正區仁愛路二段");
	// houseVO.setPrice("2,498");
	// houseVO.setTotal_pings(50.12);
	// houseVO.setMain_pings(30.12);
	// houseVO.setAmenity_pings(15.0);
	// houseVO.setAccessory_pings(5.0);
	// houseVO.setFloor(" 2~2/6樓");
	// houseVO.setAge(29.5);
	// houseVO.setPattern("2房(室)1廳1衛");
	// houseVO.setOrientation("朝向西南");
	// houseVO.setBuilding_materials("鋼筋混凝土(RC)");
	// houseVO.setParking_space("電腦選號 固定車位");
	// houseVO.setClassification_of_land("第三之二種住宅區");
	// houseVO.setLand_pings(6.02);
	// houseVO.setData_info("國小：東門國小");
	// String imgPath =
	// "C:/Users/Java/Desktop/Beautiful_World/15065925683.jpg";
	// HouseJDBCDAO dao = new HouseJDBCDAO();
	// byte[] imgByteArray = dao.getByteArray(imgPath);
	// houseVO.setMain_img(imgByteArray);
	// houseVO.setLat(133.00);
	// houseVO.setLng(50.00);
	// houseVO.setHouse_states("selling");
	// houseVO.setHouse_no("HSE00000023");
	// dao.update(houseVO);
	//

	// 刪除測試成功!
	// HouseJDBCDAO dao = new HouseJDBCDAO();
	// dao.delete("HSE00000007");

	// 查詢單一資料,測試成功!
	// HouseJDBCDAO dao = new HouseJDBCDAO();
	// HouseVO houseVO = dao.findByPrimaryKey("HSE00000004");
	// System.out.println(houseVO.getTitle());

	//
	// HouseJDBCDAO dao = new HouseJDBCDAO();
	// List<HouseVO> houseList = dao.getAll();
	// for(HouseVO houseVO:houseList){
	// System.out.println(houseVO.getHouse_no());
	// System.out.println(houseVO.getTitle());
	// }
	//

	// }

}

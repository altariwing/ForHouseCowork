package com.house.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class HouseService {
	private HouseJDBCDAO dao;

	public HouseService() {
		dao = new HouseJDBCDAO();
	}

	public HouseVO addHouse(String re_no, String house_serial_number, String title, String location, String house_type,
			Integer price, Double total_pings, Double main_pings, Double amenity_pings, Double accessory_pings,
			String floor, Double age, String pattern, String orientation, String building_materials,
			String parking_space, String classification_of_land, Double land_pings, String data_info, byte[] main_img,
			Double lat, Double lng, String house_states) {
		HouseVO houseVO = new HouseVO();
		houseVO.setRe_no(re_no);
		houseVO.setHouse_serial_number(house_serial_number);
		houseVO.setTitle(title);
		houseVO.setLocation(location);
		houseVO.setHouse_type(house_type);
		houseVO.setPrice(price);
		houseVO.setTotal_pings(total_pings);
		houseVO.setMain_pings(main_pings);
		houseVO.setAmenity_pings(amenity_pings);
		houseVO.setAccessory_pings(accessory_pings);
		houseVO.setFloor(floor);
		houseVO.setAge(age);
		houseVO.setPattern(pattern);
		houseVO.setOrientation(orientation);
		houseVO.setBuilding_materials(building_materials);
		houseVO.setParking_space(parking_space);
		houseVO.setClassification_of_land(classification_of_land);
		houseVO.setLand_pings(land_pings);
		houseVO.setData_info(data_info);
		houseVO.setMain_img(main_img);
		houseVO.setLat(lat);
		houseVO.setLng(lng);
		houseVO.setHouse_states(house_states);
		String house_no = dao.insert(houseVO);
		houseVO.setHouse_no(house_no);
		return houseVO;
	}

	public HouseVO updateHouse(String re_no, String house_serial_number, String title, String location,
			String house_type, Integer price, Double total_pings, Double main_pings, Double amenity_pings,
			Double accessory_pings, String floor, Double age, String pattern, String orientation,
			String building_materials, String parking_space, String classification_of_land, Double land_pings,
			String data_info, byte[] main_img, Double lat, Double lng, String house_states, Timestamp insert_time,
			Timestamp final_update_time, String house_no) {
		HouseVO houseVO = new HouseVO();
		houseVO.setRe_no(re_no);
		houseVO.setHouse_serial_number(house_serial_number);
		houseVO.setTitle(title);
		houseVO.setLocation(location);
		houseVO.setHouse_type(house_type);
		houseVO.setPrice(price);
		houseVO.setTotal_pings(total_pings);
		houseVO.setMain_pings(main_pings);
		houseVO.setAmenity_pings(amenity_pings);
		houseVO.setAccessory_pings(accessory_pings);
		houseVO.setFloor(floor);
		houseVO.setAge(age);
		houseVO.setPattern(pattern);
		houseVO.setOrientation(orientation);
		houseVO.setBuilding_materials(building_materials);
		houseVO.setParking_space(parking_space);
		houseVO.setClassification_of_land(classification_of_land);
		houseVO.setLand_pings(land_pings);
		houseVO.setData_info(data_info);
		houseVO.setMain_img(main_img);
		houseVO.setLat(lat);
		houseVO.setLng(lng);
		houseVO.setHouse_states(house_states);
		houseVO.setInsert_time(insert_time);
		houseVO.setFinal_update_time(final_update_time);
		houseVO.setHouse_no(house_no);
		dao.update(houseVO);
		return houseVO;
	}

	public void delete(String house_no) {
		dao.delete(house_no);
	}

	public HouseVO getOneHouseInfo(String house_no) {
		return dao.findByPrimaryKey(house_no);
	}

	public List<HouseVO> getAll() {
		return dao.getAll();
	}

	public List<HouseVO> getAllJson() {
		return dao.getAllJson();
	}

	public List<HouseVO> findByCondition(String location) {
		return dao.findByCondtion(location);
	}

	public List<HouseVO> findByPrice(Integer price_str, Integer price_end) {
		return dao.findByPrice(price_str, price_end);
	}

	public List<HouseVO> findByPattern(Integer roomCount) {
		return dao.findByPattern(roomCount);
	}

	public List<HouseVO> findByHouseType(String house_type) {
		return dao.findByHouseType(house_type);
	}

	public List<HouseVO> findByTotalPings(Double total_pings_str, Double total_pings_end) {
		return dao.findByTotalPings(total_pings_str, total_pings_end);
	}

	// 條件篩選
	public List<HouseVO> getAll(String sortedCondition) {
		return dao.getAll(sortedCondition);
	}

	public List<HouseVO> findByCondition(String location, String sortedCondition) {
		return dao.findByCondtion(location, sortedCondition);
	}

	public List<HouseVO> findByPrice(Integer price_str, Integer price_end, String sortedCondition) {
		return dao.findByPrice(price_str, price_end, sortedCondition);
	}

	public List<HouseVO> findByPattern(Integer roomCount, String sortedCondition) {
		return dao.findByPattern(roomCount, sortedCondition);
	}

	public List<HouseVO> findByHouseType(String house_type, String sortedCondition) {
		return dao.findByHouseType(house_type, sortedCondition);
	}

	public List<HouseVO> findByTotalPings(Double total_pings_str, Double total_pings_end, String sortedCondition) {
		return dao.findByTotalPings(total_pings_str, total_pings_end, sortedCondition);
	}

	public List<HouseVO> findBySeveralConds(Map<String, String[]> map) {
		return dao.findBySeveralConds(map);
	}

	public List<HouseVO> findBySeveralConds(Map<String, String[]> map, String sortedCondition) {
		return dao.findBySeveralConds(map, sortedCondition);
	}

	public List<HouseVO> findByKeyword(String keyword, String sortedCondition) {
		return dao.findByKeyword(keyword, sortedCondition);
	}

	// public static void main(String[] args){
	// FileInputStream fis;
	// try {
	// fis = new FileInputStream("C:\\project\\tzukai\\imgs\\rechouse1.jpeg");
	//
	// byte[] main_img = new byte[fis.available()];
	// fis.read(main_img);
	// HouseService svc = new HouseService();
	// svc.addHouse("永慶", "2764617", "仁愛電梯兩房車位 - 仁愛路林蔭 15年內電梯二房", "台北市中正區仁愛路二段",
	// "電梯大樓", 2498, 50.00, 00.00, 0.0,
	// 0.0, " 2~2/6樓", 29.5, "2房(室)1廳1衛", "朝向西南", "鋼筋混凝土(RC)", "電腦選號 固定車位",
	// "第三之二種住宅區", 0.0, "國小：東門國小",
	// main_img, 11.31, 120.11, "selling");
	// } catch (FileNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

}

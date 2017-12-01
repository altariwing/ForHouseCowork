package com.house.tool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.google.gson.Gson;
import com.house.model.HouseVO;

import sun.misc.BASE64Encoder;

public class HouseDataFormat {

	public Integer priceToInteger(String string) {
		Integer priceInt = null;
		StringBuilder sb = new StringBuilder();
		char[] priceChar = string.toCharArray();

		for (int i = 0; i < priceChar.length; i++) {
			if (Character.isDigit(priceChar[i]) || priceChar[i] == '.') {
				sb.append(priceChar[i]);
			}
		}
		priceInt = Integer.valueOf(sb.toString());
		return priceInt;
	}

	public Double StringToDouble(String string) {
		Double pingsDouble = null;
		StringBuilder sb = new StringBuilder();
		char[] priceChar = string.toCharArray();

		for (int i = 0; i < priceChar.length; i++) {

			if (Character.isDigit(priceChar[i]) || priceChar[i] == '.') {
				sb.append(priceChar[i]);
			}
		}
		pingsDouble = Double.valueOf(sb.toString());
		return pingsDouble;
	}

	public String dataInfoAddTokenizer(List<String> strings) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strings.size() - 2; i++) {
			sb.append(strings.get(i));
			if (i != strings.size() - 3) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	// 讀取圖片->轉成Bytes->轉String by base64
	public String getImageStr(String path) {
		String imgFile = path;
		InputStream in = null;
		byte[] data = null;
		try {
			in = new FileInputStream(imgFile);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);//
	}

	public String houseTypeForSinyi(String houseTypeOrigin) {
		String house_type = "";
		if (houseTypeOrigin.contains("華廈")) {
			houseTypeOrigin = houseTypeOrigin.replaceAll("華廈、", "");
			house_type = "電梯大樓、" + houseTypeOrigin;
		} else if (houseTypeOrigin.contains("大樓")) {
			houseTypeOrigin = houseTypeOrigin.replaceAll("大樓、", "");
			house_type = "電梯大樓、" + houseTypeOrigin;
		} else {
			house_type = houseTypeOrigin;
		}
		return house_type;
	}

	public String patternForSinyi(String patternOrigin) {
		char[] arr = patternOrigin.toCharArray();
		StringBuilder pattern = new StringBuilder();
		for (int i = 9; i < arr.length; i++) {
			if (arr[i] != ' ' && arr[i] != '：') {
				pattern.append(arr[i]);
			}

		}

		return pattern.toString();
	}


	
	public String dataInfoToJson(String re_no, String house_serial_number, String title, String location,
			String house_type, Integer price, Double total_pings, Double main_pings, Double amenity_pings,
			Double accessory_pings, String floor, Double age, String pattern, String orientation,
			String building_materials, String parking_space, String classification_of_land, Double land_pings,
			String data_info, String main_img_path, Double lat, Double lng, String house_states) {

		HouseDataFormat hdf = new HouseDataFormat();
		String main_img_base64 = hdf.getImageStr(main_img_path);

		HouseVO houseVO = new HouseVO();
		houseVO.setTitle(title);
		houseVO.setRe_no(re_no);
		houseVO.setHouse_serial_number(house_serial_number);
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
		houseVO.setMain_img_base64(main_img_base64);
		houseVO.setLat(lat);
		houseVO.setLng(lng);
		houseVO.setHouse_states(house_states);

		Gson gson = new Gson();
		String hosueJson = gson.toJson(houseVO);
		System.out.println(hosueJson);

		return hosueJson;
	}

}

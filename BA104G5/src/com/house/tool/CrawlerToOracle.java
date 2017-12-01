package com.house.tool;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import com.house.model.HouseService;
import com.house.model.HouseVO;
import com.houseImage.model.*;

public class CrawlerToOracle {

	private static CrawlerToOracle instance = null;

	private CrawlerToOracle() {
	}

	public static CrawlerToOracle getInstance() {
		synchronized (CrawlerToOracle.class) {
			if (instance == null) {
				instance = new CrawlerToOracle();
			}
		}
		return instance;
	}

	public HouseVO insertToHouseInfo(String re_no, String house_serial_number, String title, String location,
			String house_type, Integer price, Double total_pings, Double main_pings, Double amenity_pings,
			Double accessory_pings, String floor, Double age, String pattern, String orientation,
			String building_materials, String parking_space, String classification_of_land, Double land_pings,
			String data_info, String main_img_path, Double lat, Double lng, String house_states) {

		InputStream fis = null;
		byte[] main_img = null;
		byte[] tempBytes = new byte[8192];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int size = 0;

		try {
			fis = new FileInputStream(main_img_path);
			while ((size = fis.read(tempBytes)) != -1) {
				bos.write(tempBytes, 0, size);
			}
			fis.close();
			main_img = bos.toByteArray();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HouseService houseSvc = new HouseService();
		HouseVO returnedHouseVo = houseSvc.addHouse(re_no, house_serial_number, title, location, house_type, price,
				total_pings, main_pings, amenity_pings, accessory_pings, floor, age, pattern, orientation,
				building_materials, parking_space, classification_of_land, land_pings, data_info, main_img, lat, lng,
				house_states);

		return returnedHouseVo;
	}

	public void insertToHouseImage(String house_no, String img_path, String state) {
		HouseImageService houseImageService = new HouseImageService();

		InputStream in = null;
		byte[] imgBytes = null;
		try {
			in = new FileInputStream(img_path);
			imgBytes = new byte[in.available()];
			in.read(imgBytes);
			houseImageService.addHouseImage(house_no, imgBytes, state);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void insertToHouseImage(String house_no, Collection<String> img_paths, String state) {
		HouseImageService houseImageService = new HouseImageService();

		InputStream in = null;
		byte[] imgBytes = null;

		for (String img_path : img_paths) {
			try {
				in = new FileInputStream(img_path);
				imgBytes = new byte[in.available()];
				in.read(imgBytes);
				houseImageService.addHouseImage(house_no, imgBytes, state);
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

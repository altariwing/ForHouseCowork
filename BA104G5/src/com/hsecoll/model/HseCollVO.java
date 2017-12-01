package com.hsecoll.model;

import java.io.Serializable;
import java.sql.Timestamp;
import com.house.model.HouseVO;

public class HseCollVO extends HouseVO implements Serializable {

	private String mem_no;
	private String house_no;
	private String re_no;
	private String house_serial_number;
	private String title;
	private String location;
	private String house_type;
	private Integer price;
	private Double total_pings;
	private Double main_pings;
	private Double amenity_pings;
	private Double accessory_pings;
	private String floor;
	private Double age;
	private String pattern;
	private String orientation;
	private String building_materials;
	private String parking_space;
	private String classification_of_land;
	private Double land_pings;
	private String data_info;// clob
	private String main_img_base64;
	private Double lat;
	private Double lng;
	private String house_states;
	private Timestamp insert_time;
	private Timestamp final_update_time;

	public HseCollVO() {
		super();
	}

	public String getHouse_no() {
		return house_no;
	}

	public void setHouse_no(String house_no) {
		this.house_no = house_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getRe_no() {
		return re_no;
	}

	public void setRe_no(String re_no) {
		this.re_no = re_no;
	}

	public String getHouse_serial_number() {
		return house_serial_number;
	}

	public void setHouse_serial_number(String house_serial_number) {
		this.house_serial_number = house_serial_number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHouse_type() {
		return house_type;
	}

	public void setHouse_type(String house_type) {
		this.house_type = house_type;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Double getTotal_pings() {
		return total_pings;
	}

	public void setTotal_pings(Double total_pings) {
		this.total_pings = total_pings;
	}

	public Double getMain_pings() {
		return main_pings;
	}

	public void setMain_pings(Double main_pings) {
		this.main_pings = main_pings;
	}

	public Double getAmenity_pings() {
		return amenity_pings;
	}

	public void setAmenity_pings(Double amenity_pings) {
		this.amenity_pings = amenity_pings;
	}

	public Double getAccessory_pings() {
		return accessory_pings;
	}

	public void setAccessory_pings(Double accessory_pings) {
		this.accessory_pings = accessory_pings;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}

	public String getBuilding_materials() {
		return building_materials;
	}

	public void setBuilding_materials(String building_materials) {
		this.building_materials = building_materials;
	}

	public String getParking_space() {
		return parking_space;
	}

	public void setParking_space(String parking_space) {
		this.parking_space = parking_space;
	}

	public String getClassification_of_land() {
		return classification_of_land;
	}

	public void setClassification_of_land(String classification_of_land) {
		this.classification_of_land = classification_of_land;
	}

	public Double getLand_pings() {
		return land_pings;
	}

	public void setLand_pings(Double land_pings) {
		this.land_pings = land_pings;
	}

	public String getData_info() {
		return data_info;
	}

	public void setData_info(String data_info) {
		this.data_info = data_info;
	}

	public String getMain_img_base64() {
		return main_img_base64;
	}

	public void setMain_img_base64(String main_img_base64) {
		this.main_img_base64 = main_img_base64;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getHouse_states() {
		return house_states;
	}

	public void setHouse_states(String house_states) {
		this.house_states = house_states;
	}

	public Timestamp getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(Timestamp insert_time) {
		this.insert_time = insert_time;
	}

	public Timestamp getFinal_update_time() {
		return final_update_time;
	}

	public void setFinal_update_time(Timestamp final_update_time) {
		this.final_update_time = final_update_time;
	}

}

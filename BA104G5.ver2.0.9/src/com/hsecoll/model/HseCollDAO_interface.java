package com.hsecoll.model;

import java.util.List;
import com.house.model.HouseVO;

public interface HseCollDAO_interface {
	void insert(HseCollVO hseCollVO);

	void update(HseCollVO hseCollVO);

	void delete(String mem_no , String house_no);

	HseCollVO findByPrimaryKey(String mem_no , String house_no);
	
	List<HseCollVO> getAll_kuei(String mem_no);
	
	List<HouseVO> getAllByMemNoWithHouseInfo(String mem_no);
	
	List<HouseVO> getAllByMemNoWithHouseInfo(String mem_no,String[] house_nos);
	
	List<HseCollVO> getAll();
}

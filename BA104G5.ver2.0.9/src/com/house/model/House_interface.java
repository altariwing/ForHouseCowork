package com.house.model;

import java.util.List;
import java.util.Map;

public interface House_interface {

	public String insert(HouseVO houseVO);

	public void update(HouseVO houseVO);

	public void delete(String house_No);

	public HouseVO findByPrimaryKey(String house_No);

	
	
	public List<HouseVO> getAll();
	
	public List<HouseVO> findByCondtion(String location);
	
	public List<HouseVO> findByPrice(Integer price_str,Integer price_end); 

	public List<HouseVO> findByPattern(Integer roomCount);
	
	public List<HouseVO> findByHouseType(String house_type);
	
	public List<HouseVO> findByTotalPings(Double total_pings_str,Double total_pings_end);
	
	
	//for排序
	public List<HouseVO> getAll(String sortedCondition);
	
	public List<HouseVO> findByCondtion(String location,String sortedCondition);
	
	public List<HouseVO> findByPrice(Integer price_str,Integer price_end,String sortedCondition); 

	public List<HouseVO> findByPattern(Integer roomCount,String sortedCondition);
	
	public List<HouseVO> findByHouseType(String house_type,String sortedCondition);
	
	public List<HouseVO> findByTotalPings(Double total_pings_str,Double total_pings_end,String sortedCondition);
	
	public List<HouseVO> findBySeveralConds(Map<String, String[]> map);
	
	public List<HouseVO> findBySeveralConds(Map<String, String[]> map,String sortedCondition);
	
	
	public List<HouseVO> findByKeyword(String keyword,String sortedCondition);
}

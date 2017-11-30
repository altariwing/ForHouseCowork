package com.houseImage.model;

import java.util.List;

public interface HouseImage_interface {

	public void addImg(HouseImageVO houseImageVO);

	public void updateImg(HouseImageVO houseImageVO);

	public void delete(String img_no);

	public HouseImageVO findByPrimaryKey(String img_no);
	
	public List<HouseImageVO> getall();
	
	public List<HouseImageVO> findByHouseNo(String house_no);

}

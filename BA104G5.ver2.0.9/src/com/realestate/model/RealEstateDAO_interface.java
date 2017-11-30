package com.realestate.model;

import java.util.List;
import java.util.Set;

import com.realtor.model.RealtorVO;

public interface RealEstateDAO_interface {
	
	public void insert(RealEstateVO realestateVO);
	public RealEstateVO findByPrimaryKey(String re_no);
	public List<RealEstateVO> getAll();
	public Set<RealtorVO> getRealtorByRTR_NO(String re_no); //查房仲公司對應的房仲
	
}

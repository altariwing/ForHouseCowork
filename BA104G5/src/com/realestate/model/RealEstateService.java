package com.realestate.model;

import java.util.List;
import java.util.Set;

import com.realtor.model.RealtorVO;

public class RealEstateService {

	private RealEstateDAO_interface dao;

	public RealEstateService() {
		dao = new RealEstateDAO();
	}

	public RealEstateVO add(String re_no, String re_name) {
		RealEstateVO realestateVO = new RealEstateVO();
		realestateVO.setRe_no(re_no);
		realestateVO.setRe_name(re_name);
		dao.insert(realestateVO);
		return realestateVO;
	}

	public RealEstateVO getOne(String re_no) {
		return dao.findByPrimaryKey(re_no);
	}

	public List<RealEstateVO> getAll() {
		return dao.getAll();
	}

	public Set<RealtorVO> getRealtorByRTR_NO(String re_no) {
		return dao.getRealtorByRTR_NO(re_no);
	}

}

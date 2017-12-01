package com.reportproduct.model;

import java.util.List;

public class RptprdService {
	private RptprdDAO_interface dao;
	public RptprdService() {
		dao = new RptprdDAO();
	}
	
	public void addNewReport(RptprdVO rptprdVO) {
		dao.insert(rptprdVO);
	}
	
	public void updateReport(RptprdVO rptprdVO) {
		dao.update(rptprdVO);
	}
	
	public List<RptprdVO> getByState(String rp_state) {
		return dao.getByState(rp_state);
	}
	
	public List<RptprdVO> getAll() {
		return dao.getAll();
	}
	
}

package com.reportrealtor.model;

import java.util.List;

import com.reportproduct.model.RptprdVO;
import com.reportrealtor.model.*;

public class RptrtrService {
	private RptrtrDAO_interface dao;
	public RptrtrService() {
		dao = new RptrtrDAO();
	}
	
	public void addNewReport(RptrtrVO rptrtrVO) {
		dao.insert(rptrtrVO);
	}
	
	public void updateReport(RptrtrVO rptrtrVO) {
		dao.update(rptrtrVO);
	}
	
	public List<RptrtrVO> getByState(String rr_state) {
		return dao.getByState(rr_state);
	}
	
	public List<RptrtrVO> getAll() {
		return dao.getAll();
	}
	
}

package com.reportrealtor.model;

import java.util.List;

import com.reportproduct.model.RptprdVO;

public interface RptrtrDAO_interface {
	public void insert(RptrtrVO rptrtrVO);
	public void update(RptrtrVO rptrtrVO);
	public List<RptrtrVO> getByState(String rr_state);
	public List<RptrtrVO> getAll();
}

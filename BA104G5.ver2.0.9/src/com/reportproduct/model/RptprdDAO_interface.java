package com.reportproduct.model;

import java.util.List;

public interface RptprdDAO_interface {
	public void insert(RptprdVO rptprdVO);
	public void update(RptprdVO rptprdVO);
	public List<RptprdVO> getByState(String rp_state);
	public List<RptprdVO> getAll();
}

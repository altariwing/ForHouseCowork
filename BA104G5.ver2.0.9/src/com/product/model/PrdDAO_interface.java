package com.product.model;

import java.util.List;
import java.util.Map;

public interface PrdDAO_interface {
	public String insert(PrdVO prdVO);
	public void update(PrdVO prdVO);
	public void changeState(String prd_state, String prd_no);
	public List<PrdVO> getStateOn();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
	public List<PrdVO> getStateOnBymap(Map<String, String[]> map);
	public List<PrdVO> findBySlrOff(String slr_no);
	public List<PrdVO> findBySlrOn(String slr_no);
	public List<PrdVO> findBySlrCate(String cate_no, String slr_no);
	public PrdVO getOnByPrdNo(String prd_no);
}

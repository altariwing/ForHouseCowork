package com.product.model;

import java.util.List;
import java.util.Map;

public class PrdService {
	private PrdDAO_interface dao;
	public PrdService() {
		dao = new PrdDAO();
	}
	
	public String addPrd(PrdVO prdVO) {
		String keyPrdNo = dao.insert(prdVO);
		return keyPrdNo;
	}
	
	public void updatePrd(PrdVO prdVO) {
		dao.update(prdVO);
	}
	
	public void changeState(String prd_no, String prd_state) {
		dao.changeState(prd_state, prd_no);
	}
	
	//取得上架的商品 prd_state=ON
	public List<PrdVO> getAll() {
		return dao.getStateOn();
	}
	
	// 取得上架的商品 prd_state=ON?? by map
	public List<PrdVO> getAll(Map<String, String[]> map) {
		return dao.getStateOnBymap(map);
	}
	
	//取得下架的商品 prd_state=OFF
	public List<PrdVO> findBySlrOff(String slr_no) {
		return dao.findBySlrOff(slr_no);
	}
	
	public List<PrdVO> findBySlrNo(String slr_no) {
		return dao.findBySlrOn(slr_no);
	}
	
	public List<PrdVO> findBySlrCate(String slr_no, String cate_no) {
		return dao.findBySlrCate(cate_no, slr_no);
	}
	
	public PrdVO getOnByPrdNo(String prd_no) {
		return dao.getOnByPrdNo(prd_no);
	}
	
}

package com.odrdetail.model;

import java.util.List;

public class OdrDetailService {
	private OdrDetailDAO_interface dao;
	public OdrDetailService() {
		dao = new OdrDetailDAO();
	}
	
	public void insert(OdrDetailVO odrDetailVO) {
		dao.insert(odrDetailVO);
	}
	
	public void delOnePrd(String pdo_no, String prd_no) {
		dao.delOnePrd(pdo_no, prd_no);
	}
	
	public void delOneOrder(String pdo_no) {
		dao.delOneOrder(pdo_no);
	}
	
	public List<OdrDetailVO> getListByPdoNo(String pdo_no) {
		return dao.getListByPdoNo(pdo_no);
	}
	
	public List<OdrDetailVO> getListByPrdNo(String prd_no) {
		return dao.getListByPrdNo(prd_no);
	}
}

package com.prdcategory.model;

import java.util.List;

public class PcService {
	private PcDAO_interface dao;
	public PcService() {
		dao = new PcDAO();
	}
	
	public void addPc(String cate_name) {
		dao.insert(cate_name);
	}
	
	public void updatePc(String cate_no, String cate_name) {
		PcVO pcVO = new PcVO();
		pcVO.setCate_no(cate_no);
		pcVO.setCate_name(cate_name);
		dao.update(pcVO);
	}
	
	public PcVO findByNo(String cate_no) {
		return dao.findByNo(cate_no);
	}
	
	public PcVO findByName(String cate_name) {
		return dao.findByName(cate_name);
	}
	
	public List<PcVO> getAll() {
		return dao.getAll();
	}
}

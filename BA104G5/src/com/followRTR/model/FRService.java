package com.followRTR.model;

import java.util.List;

public class FRService {
	private FRJDBCDAO dao;

	public FRService() {
		dao = new FRJDBCDAO();
	}

	public void addFollowRTR(String rtr_no, String mem_no, String state) {
		FRVO frVO = new FRVO();
		frVO.setRtr_no(rtr_no);
		frVO.setMem_no(mem_no);
		frVO.setState(state);
		dao.insert(frVO);
	}

	public void updateFollowRTR(String rtr_no, String mem_no, String state) {
		FRVO frVO = new FRVO();
		frVO.setRtr_no(rtr_no);
		frVO.setMem_no(mem_no);
		frVO.setState(state);
		dao.update(frVO);
	}

	public void deleteFollowRTR(String rtr_no, String mem_no) {
		dao.delete(rtr_no, mem_no);
	}

	public List<FRVO> findByNumber(String mem_no) {
		return dao.findByMemNumber(mem_no);
	}

	public List<FRVO> getAll() {
		return dao.getAll();
	}

}

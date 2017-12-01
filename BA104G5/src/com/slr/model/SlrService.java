package com.slr.model;

import java.util.List;

public class SlrService {
	private SlrDAO_interface dao;
	public SlrService() {
		dao = new SlrDAO();
	}
	
	public SlrVO addSlr(String slr_name, String slr_taxid, String slr_id, String slr_psw,
			String slr_contact, String slr_phone, String slr_intro) {
		SlrVO slrVO = new SlrVO();
		slrVO.setSlr_name(slr_name);
		slrVO.setSlr_taxid(slr_taxid);
		slrVO.setSlr_id(slr_id);
		slrVO.setSlr_psw(slr_psw);
		slrVO.setSlr_contact(slr_contact);
		slrVO.setSlr_phone(slr_phone);
		slrVO.setSlr_intro(slr_intro);
		dao.insert(slrVO);
		return slrVO;
	}
	
	public SlrVO updateSlr(String slr_contact, String slr_phone, String slr_intro, String slr_no) {
		SlrVO slrVO = new SlrVO();
		slrVO.setSlr_contact(slr_contact);
		slrVO.setSlr_phone(slr_phone);
		slrVO.setSlr_intro(slr_intro);
		slrVO.setSlr_no(slr_no);
		dao.update(slrVO);
		return slrVO;
	}
	
	public SlrVO changeSlrState(String slr_state, String slr_no) {
		SlrVO slrVO = new SlrVO();
		slrVO.setSlr_state(slr_state);
		slrVO.setSlr_no(slr_no);
		dao.changeSlrState(slrVO);
		return slrVO;
	}
	
	public SlrVO changePassword(String slr_psw, String slr_no) {
		SlrVO slrVO = new SlrVO();
		slrVO.setSlr_psw(slr_psw);
		slrVO.setSlr_no(slr_no);
		dao.changePassword(slrVO);
		return slrVO;
	}
	
	public SlrVO findByNo(String slr_no) {
		SlrVO slrVO = dao.findByNo(slr_no);
		return slrVO;
	}
	
	public SlrVO findByName(String slr_name) {
		SlrVO slrVO = dao.findByNo(slr_name);
		return slrVO;
	}
	
	public SlrVO findById(String slr_id) {
		SlrVO slrVO = dao.findById(slr_id);
		return slrVO;
	}
	
	public List<SlrVO> getAllSlr() {
		return dao.getAll();
	}
	
	public List<SlrVO> getIdList() {
		return dao.getIdList();
	}
}

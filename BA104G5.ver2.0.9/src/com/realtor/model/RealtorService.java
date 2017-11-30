package com.realtor.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.article.model.ArticleVO;

public class RealtorService {
	private RealtorDAO_interface dao;

	public RealtorService() {
		dao = new RealtorDAO();
	}

	public RealtorVO add(String rtr_id, String rtr_psw, String rtr_name, byte[] rtr_photo, String rtr_area,
			String rtr_intro, String rtr_idno, String re_no) {

		RealtorVO realtorVO = new RealtorVO();
		realtorVO.setRtr_id(rtr_id);
		realtorVO.setRtr_psw(rtr_psw);
		realtorVO.setRtr_name(rtr_name);
		realtorVO.setRtr_photo(rtr_photo);
		realtorVO.setRtr_area(rtr_area);
		realtorVO.setRtr_intro(rtr_intro);
		realtorVO.setRtr_idno(rtr_idno);
		realtorVO.setRe_no(re_no);

		dao.insert(realtorVO);
		return realtorVO;
	}

	// public RealtorVO update(String rtr_id,String rtr_name, byte[] rtr_photo,
	// String rtr_area, String rtr_intro) {
	//
	// RealtorVO realtorVO = new RealtorVO();
	// realtorVO.setRtr_id(rtr_id);
	// realtorVO.setRtr_name(rtr_name);
	// realtorVO.setRtr_photo(rtr_photo);
	// realtorVO.setRtr_area(rtr_area);
	// realtorVO.setRtr_intro(rtr_intro);
	// dao.update(realtorVO);
	// return realtorVO;
	// }

	// 修改照片、簡介、照片
	public RealtorVO update(String rtr_no, String rtr_name, byte[] rtr_photo, String rtr_intro, String rtr_psw) {

		RealtorVO realtorVO = new RealtorVO();
		realtorVO.setRtr_no(rtr_no);
		realtorVO.setRtr_name(rtr_name);
		realtorVO.setRtr_photo(rtr_photo);
		realtorVO.setRtr_intro(rtr_intro);
		realtorVO.setRtr_psw(rtr_psw);
		dao.update(realtorVO);
		return realtorVO;
	}

	public RealtorVO getOne(String rtr_no) {
		return dao.findByPrimaryKey(rtr_no);
	}

	public List<RealtorVO> getAll() {
		return dao.getAll();
	}

	// 專門塞房仲照片以及簡介
	public RealtorVO updatePhoto(String rtr_no, byte[] rtr_photo, String rtr_intro) {
		RealtorVO realtorVO = new RealtorVO();
		realtorVO.setRtr_intro(rtr_intro);
		realtorVO.setRtr_photo(rtr_photo);
		realtorVO.setRtr_intro(rtr_intro);
		dao.updatePhoto(realtorVO);
		return realtorVO;
	}

	// 找房仲ID
	public RealtorVO findById(String rtr_id) {
		RealtorVO realtorVO = new RealtorVO();
		realtorVO = dao.findById(rtr_id);
		return realtorVO;
	}

	// 更換密碼
	public void changePassword(String rtr_psw, String rtr_no) {
		RealtorVO realtorVO = new RealtorVO();
		realtorVO.setRtr_psw(rtr_psw);
		realtorVO.setRtr_no(rtr_no);
		dao.changePassword(realtorVO);
	}

	// 更換房仲狀態
	public void changeRealtorState(String rtr_state, String rtr_no) {
		RealtorVO realtorVO = new RealtorVO();
		realtorVO.setRtr_state(rtr_state);
		realtorVO.setRtr_no(rtr_no);
		dao.changeRealtorState(realtorVO);
	}

	// 找房仲名稱
	public RealtorVO findByName(String rtr_name) {
		RealtorVO realtorVO = new RealtorVO();
		realtorVO = dao.findByName(rtr_name);
		return realtorVO;
	}

	// 列出所有房仲ID(email)
	public List<RealtorVO> getIdList() {
		return dao.getIdList();
	}

	// 房仲萬用複合查詢
	public List<RealtorVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

	// 關鍵字查詢
	public List<RealtorVO> findByKeyword(String keyword) {
		return dao.findByKeyword(keyword);
	}

	// 房仲FB登入的特別insert
	public RealtorVO FBAdd(String rtr_id, String rtr_psw, String rtr_name, byte[] rtr_photo, String rtr_area,
			String rtr_intro, String rtr_idno, String re_no) {

		RealtorVO realtorVO = new RealtorVO();
		realtorVO.setRtr_id(rtr_id);
		realtorVO.setRtr_psw(rtr_psw);
		realtorVO.setRtr_name(rtr_name);
		realtorVO.setRtr_photo(rtr_photo);
		realtorVO.setRtr_area(rtr_area);
		realtorVO.setRtr_intro(rtr_intro);
		realtorVO.setRtr_idno(rtr_idno);
		realtorVO.setRe_no(re_no);
		dao.FBInsert(realtorVO);
		return realtorVO;
	}
	
	// 找房仲文章
	public Set<ArticleVO> getArtByRtrNo(String rtr_no) {
		return dao.getArtByRtrNo(rtr_no);
	}

}

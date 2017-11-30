package com.mem.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.coupon.model.CouponVO;

public class MemService {
	private MemDAO_interface dao;
	public MemService() {
		dao = new MemDAO();
	}
	
	public MemVO addMem(String mem_id, String mem_psw, String mem_name, String mem_addr, String search_state, byte[] mem_img) {
		MemVO memVO = new MemVO();
		
		memVO.setMem_id(mem_id);
		memVO.setMem_psw(mem_psw);
		memVO.setMem_name(mem_name);
		memVO.setMem_addr(mem_addr);
		memVO.setSearch_state(search_state);
		memVO.setMem_img(mem_img);
		dao.insert(memVO);
		return memVO;
	}
	
	public MemVO updateMem(MemVO memVO) {
		
		dao.update(memVO);
		return memVO;
	}
	
	public MemVO changeLockState(String lock_state, String mem_no) {
		MemVO memVO = new MemVO();

		memVO.setLock_state(lock_state);
		memVO.setMem_no(mem_no);
		dao.changeLockState(memVO);
		return memVO;
	}
	
	public MemVO changePassword(String mem_psw, String mem_no) {
		MemVO memVO = new MemVO();

		memVO.setMem_psw(mem_psw);
		memVO.setMem_no(mem_no);
		dao.changePassword(memVO);
		return memVO;
	}
	
	public MemVO getOneByNo(String mem_no) {
		return dao.findByPrimaryKey(mem_no);
	}
	
	public MemVO getOneById(String mem_id) {
		return dao.findById(mem_id);
	}
	
	public List<MemVO> getAllMem() {
		return dao.getAll();
	}
	
	public List<MemVO> getAllIdList() {
		return dao.getIdList();
	}
	
	// 會員找房狀態open list阿蓋
		public List<MemVO> getOpenList() {
			return dao.getOpenList();
		}

		// 會員找房狀態open map阿蓋
		public List<MemVO> getOpenMap(Map<String, String[]> map) {
			return dao.getOpenMap(map);
		}

		// 查詢促銷資訊對應的優惠卷阿蓋
		public Set<CouponVO> getCPByMemno(String mem_no) {
			return dao.getCPByMemno(mem_no);
		}

}
package com.order.model;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PrdOdrService {
	private PrdOdrDAO_interface dao;
	public PrdOdrService() {
		dao = new PrdOdrDAO();
	}
	
	public String addOrder(PrdOdrVO prdOdrVO) {
		return dao.insert(prdOdrVO);
	}
	
	public void update(PrdOdrVO prdOdrVO) {
		dao.update(prdOdrVO);
	}
	
	public void setPdoStat(String pdo_stat,String pdo_no) {
		dao.setPdoStat(pdo_stat, pdo_no);
	}
	
	public void setSlrRate(Integer slr_rate,String pdo_no) {
		dao.setSlrRate(slr_rate, pdo_no);
	}
	
	public void setMem(Integer mem_rate, String mem_review) {
		dao.setMem(mem_rate, mem_review);
	}
	
	public void setPayment(String pdo_params,String pdo_no) {
		dao.setPayment(pdo_params, pdo_no);
	}
	
	public Map<String, List<PrdOdrVO>> memGetByNew(String mem_no) {
		List<PrdOdrVO> list = dao.memGetByNew(mem_no);
		Map<String, List<PrdOdrVO>> odrByDateMap = null;
		odrByDateMap = list.stream().collect(Collectors.groupingBy(PrdOdrVO::getOdrTime));
		return odrByDateMap;
	}
	
	public Map<String, List<PrdOdrVO>> memGetByDeliver(String mem_no) {
		List<PrdOdrVO> list = dao.memGetByDeliver(mem_no);
		Map<String, List<PrdOdrVO>> odrHistoryMap = null;
		odrHistoryMap = list.stream().collect(Collectors.groupingBy(PrdOdrVO::getOdrTime));
		return odrHistoryMap;
	}
	
	public Map<String, List<PrdOdrVO>> Cancel(String mem_no) {
		List<PrdOdrVO> list = dao.memGetByCancel(mem_no);
		Map<String, List<PrdOdrVO>> odrCancelledMap = null;
		odrCancelledMap = list.stream().collect(Collectors.groupingBy(PrdOdrVO::getOdrTime));
		return odrCancelledMap;
	}
	
	public List<PrdOdrVO> slrGetByNew(String slr_no) {
		return dao.slrGetByNew(slr_no);
	}
	
	public List<PrdOdrVO> slrGetByDeliver(String slr_no) {
		return dao.slrGetByDeliver(slr_no);
	}
	
	public List<PrdOdrVO> slrGetByCancel(String slr_no) {
		return dao.slrGetByCancel(slr_no);
	}
	
	public PrdOdrVO findByPdoNo(String pdo_no) {
		return dao.findByPdoNo(pdo_no);
	}
}

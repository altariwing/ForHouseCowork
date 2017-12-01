package com.order.model;

import java.util.List;

public interface PrdOdrDAO_interface {
	public String insert(PrdOdrVO prdOdrVO);
	public void update(PrdOdrVO prdOdrVO);
	public void setPdoStat(String pdo_stat,String pdo_no);
	public void setSlrRate(Integer slr_rate,String pdo_no);
	public void setMem(Integer mem_rate, String mem_review);
	public void setPayment(String pdo_params,String pdo_no);
	public List<PrdOdrVO> memGetByNew(String mem_no);
	public List<PrdOdrVO> memGetByDeliver(String mem_no);
	public List<PrdOdrVO> memGetByCancel(String mem_no);
	public List<PrdOdrVO> slrGetByNew(String slr_no);
	public List<PrdOdrVO> slrGetByDeliver(String slr_no);
	public List<PrdOdrVO> slrGetByCancel(String slr_no);
	public PrdOdrVO findByPdoNo(String pdo_no);
}

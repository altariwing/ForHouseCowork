package com.prdcategory.model;

import java.util.List;

public interface PcDAO_interface {
	public void insert(String cate_name);
	public void update(PcVO pcVO);
	public PcVO findByNo(String cate_no);
	public PcVO findByName(String cate_name);
	public List<PcVO> getAll();
}

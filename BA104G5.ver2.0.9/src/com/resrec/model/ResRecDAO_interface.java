package com.resrec.model;

import java.util.List;

public interface ResRecDAO_interface {
	void insert(ResRecVO resRecVO);
	
	void insert_kuei1(ResRecVO resRecVO);
	
	void update(ResRecVO resRecVO);
	
	void update_kuei1(ResRecVO resRecVO);
	
	void update_kuei2(ResRecVO resRecVO);
	
	void update_kuei3(ResRecVO resRecVO);

	void delete(String resr_no);

	ResRecVO findByPrimaryKey(String resr_no);
	
	List<ResRecVO> getMem_kuei(String mem_no);
	
	List<ResRecVO> getRtr_kuei(String rtr_no);

	List<ResRecVO> getAll();
}

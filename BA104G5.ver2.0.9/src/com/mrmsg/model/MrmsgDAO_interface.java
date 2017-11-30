package com.mrmsg.model;

import java.util.List;

public interface MrmsgDAO_interface {
	void insert(MrmsgVO mrmsgVO);

	void update(MrmsgVO mrmsgVO);

	void delete(String mem_no , String rtr_no);

	MrmsgVO findByPrimaryKey(String mem_no , String rtr_no);

	List<MrmsgVO> getAll();
}

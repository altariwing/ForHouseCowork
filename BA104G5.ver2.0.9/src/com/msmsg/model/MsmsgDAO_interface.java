package com.msmsg.model;

import java.util.List;


public interface MsmsgDAO_interface {
	void insert(MsmsgVO msmsgVO);

	void update(MsmsgVO msmsgVO);

	void delete(String mem_no , String slr_no);

	MsmsgVO findByPrimaryKey(String mem_no , String slr_no);

	List<MsmsgVO> getAll();
}

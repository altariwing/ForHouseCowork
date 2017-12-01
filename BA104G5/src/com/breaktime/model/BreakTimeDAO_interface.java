package com.breaktime.model;

import java.sql.Date;
import java.util.List;


public interface BreakTimeDAO_interface {
	String insert(BreakTimeVO breakTimeVO);

	void update(BreakTimeVO breakTimeVO);

	void delete(String brk_no );

	BreakTimeVO findByPrimaryKey(String brk_no);
	
	List<BreakTimeVO> getRtr_bt_kuei(String rtr_no);

	List<BreakTimeVO> getAll();
}

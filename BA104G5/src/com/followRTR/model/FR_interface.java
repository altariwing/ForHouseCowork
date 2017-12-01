package com.followRTR.model;

import java.util.List;

public interface FR_interface {

	public void insert(FRVO frVO);

	public void update(FRVO frVO);

	public void delete(String rtr_no, String mem_no);

	public List<FRVO> findByMemNumber(String mem_no);

	public List<FRVO> getAll();

}

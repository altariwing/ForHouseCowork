package com.slr.model;

import java.util.List;

public interface SlrDAO_interface {
	public void insert(SlrVO slrVO);
	public void update(SlrVO slrVO);
	public void changeSlrState(SlrVO slrVO);
	public void changePassword(SlrVO slrVO);
	public SlrVO findByNo(String slr_no);
	public SlrVO findByName(String slr_name);
	public SlrVO findById(String slr_id);
	public List<SlrVO> getAll();
	public List<SlrVO> getIdList();
}

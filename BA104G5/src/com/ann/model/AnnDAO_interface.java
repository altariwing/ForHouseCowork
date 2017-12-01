package com.ann.model;

import java.util.List;

public interface AnnDAO_interface {
	
	public void insert (AnnVO annVO);
	public void update (AnnVO annVO);
	public void delete (Integer ann_no);
	public AnnVO findByPrimaryKey(Integer ann_no);
	public List<AnnVO> getAll();
	
	// 依照新增時間做排序(只取前三筆)
	public List<AnnVO> getAllByTime();

}

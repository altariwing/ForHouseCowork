package com.news.model;

import java.util.List;

public interface NewsDAO_interface {

	public void insert(NewsVO newsVO);
	public void update(NewsVO newsVO);
	public NewsVO findByPrimaryKey(String news_no);
	public List<NewsVO> getAll();
	
	// 依照新增時間做排序(只取前三筆)
	public List<NewsVO> getAllByTime();
	//專門塞照片內文用
	public void updatePhoto(NewsVO newsVO);
	 

}

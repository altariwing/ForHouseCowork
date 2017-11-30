package com.news.model;

import java.util.List;

public interface NewsDAO_interface {

	public void insert(NewsVO newsVO);
	public void update(NewsVO newsVO);
	public NewsVO findByPrimaryKey(String news_no);
	public List<NewsVO> getAll();
	
	//依照新聞發布時間做排序
	public List<NewsVO> getAllByTime();
	

}

package com.newstype.model;

import java.util.List;
import java.util.Set;

import com.news.model.NewsVO;

public interface NewsTypeDAO_interface {

	public void insert(NewsTypeVO newstypeVO);
	public NewsTypeVO findByPrimaryKey(String ntype_no);
	public List<NewsTypeVO> getAll();
	public Set<NewsVO> getNewsByNtype_no(String ntype_no);//一對多的地方

}

package com.newstype.model;

import java.util.List;
import java.util.Set;

import com.news.model.NewsVO;

public class NewsTypeService {

	private NewsTypeDAO_interface dao;

	public NewsTypeService() {
		dao = new NewsTypeDAO();
	}

	public NewsTypeVO add(String ntype_no, String news_type) {
		NewsTypeVO newstypeVO = new NewsTypeVO();
		newstypeVO.setNtype_no(ntype_no);
		newstypeVO.setNews_type(news_type);
		dao.insert(newstypeVO);
		return newstypeVO;
	}

	public NewsTypeVO getOne(String ntype_no) {
		return dao.findByPrimaryKey(ntype_no);
	}

	public List<NewsTypeVO> getAll() {
		return dao.getAll();
	}

	public Set<NewsVO> getNewsByNtype_no(String ntype_no) {
		return dao.getNewsByNtype_no(ntype_no);
	}

}

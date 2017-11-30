package com.news.model;

import java.util.List;

public class NewsService {
	private NewsDAO_interface dao;

	public NewsService() {
		dao = new NewsDAO();
	}

	public NewsVO add(String ntype_no, String news_title, String news_content, byte[] news_photo, String news_state,
			String emp_no) {
		NewsVO newsVO = new NewsVO();
		newsVO.setNtype_no(ntype_no);
		newsVO.setNews_title(news_title);
		newsVO.setNews_content(news_content);
		newsVO.setNews_photo(news_photo);
		newsVO.setNews_state(news_state);
		newsVO.setEmp_no(emp_no);
		dao.insert(newsVO);
		return newsVO;
	}

	public NewsVO update(String news_no, String ntype_no, String news_title, String news_content, byte[] news_photo,
			String news_state, String emp_no) {
		NewsVO newsVO = new NewsVO();
		newsVO.setNews_no(news_no);
		newsVO.setNtype_no(ntype_no);
		newsVO.setNews_title(news_title);
		newsVO.setNews_content(news_content);
		newsVO.setNews_photo(news_photo);
		newsVO.setNews_state(news_state);
		newsVO.setEmp_no(emp_no);
		dao.update(newsVO);
		return newsVO;
	}

	public NewsVO getOne(String news_no) {
		return dao.findByPrimaryKey(news_no);
	}

	public List<NewsVO> getAll() {
		return dao.getAll();
	}
	
	//查詢全部照時間排序
	public List<NewsVO> getAllByTime() {
		return dao.getAllByTime();
	}
	
}

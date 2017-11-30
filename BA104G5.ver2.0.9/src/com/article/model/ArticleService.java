package com.article.model;

import java.sql.Timestamp;
import java.util.List;

public class ArticleService {

	private ArticleDAO_interface dao;

	public ArticleService() {
		dao = new ArticleDAO();
	}

	// 新增
	public ArticleVO add(String rtr_no, String article_body, Timestamp post_date, String article_state) {
		ArticleVO articleVO = new ArticleVO();
		articleVO.setRtr_no(rtr_no);
		articleVO.setArticle_body(article_body);
		articleVO.setPost_date(post_date);
		articleVO.setArticle_state(article_state);
		dao.insert(articleVO);
		return articleVO;
	}

	// 修改
	public ArticleVO update(String article_no, String rtr_no, String article_body, Timestamp post_date,
			String article_state) {
		ArticleVO articleVO = new ArticleVO();
		articleVO.setArticle_no(article_no);
		articleVO.setRtr_no(rtr_no);
		articleVO.setArticle_body(article_body);
		articleVO.setPost_date(post_date);
		articleVO.setArticle_state(article_state);
		dao.update(articleVO);
		return articleVO;
	}

	// 查單一
	public ArticleVO getOne(String articles_no) {
		return dao.findByPrimaryKey(articles_no);
	}

	// 查全部
	public List<ArticleVO> getAll() {
		return dao.getAll();
	}

	// 查詢全部照時間排序
	public List<ArticleVO> getAllByTime() {
		return dao.getAllByTime();
	}
}

package com.article.model;

import java.util.List;


public interface ArticleDAO_interface {
	
	public void insert(ArticleVO articleVO);
	public void update(ArticleVO articleVO);
	public ArticleVO findByPrimaryKey(String rtr_no);
	public List<ArticleVO> getAll();
	
	//查詢發布時間做排序
	public List<ArticleVO> getAllByTime();
	
	//新增留言
	public void updateComm(ArticleVO articleVO);
	
	//刪除文章
	public void delete(String article_no);

}

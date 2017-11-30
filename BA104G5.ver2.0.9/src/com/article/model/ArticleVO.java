package com.article.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class ArticleVO implements Serializable {

	private String article_no;
	private String rtr_no;
	private String article_body;
	private Timestamp post_date;
	private Date update_date;
	private String article_state;

	public String getArticle_no() {
		return article_no;
	}

	public void setArticle_no(String article_no) {
		this.article_no = article_no;
	}

	public String getRtr_no() {
		return rtr_no;
	}

	public void setRtr_no(String rtr_no) {
		this.rtr_no = rtr_no;
	}

	public String getArticle_body() {
		return article_body;
	}

	public void setArticle_body(String article_body) {
		this.article_body = article_body;
	}

	public Timestamp getPost_date() {
		return post_date;
	}

	public void setPost_date(Timestamp post_date) {
		this.post_date = post_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getArticle_state() {
		return article_state;
	}

	public void setArticle_state(String article_state) {
		this.article_state = article_state;
	}

}

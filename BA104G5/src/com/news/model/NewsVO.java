package com.news.model;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class NewsVO implements Serializable {
	
	private String news_no;
	private String ntype_no;
	private String news_title;
	private String news_content;
	private byte[] news_photo;
	private String news_state;
	private Date news_date;
	private String emp_no;

	public String getNews_no() {
		return news_no;
	}

	public void setNews_no(String news_no) {
		this.news_no = news_no;
	}

	public String getNtype_no() {
		return ntype_no;
	}

	public void setNtype_no(String ntype_no) {
		this.ntype_no = ntype_no;
	}

	public String getNews_title() {
		return news_title;
	}

	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}

	public String getNews_content() {
		return news_content;
	}

	public void setNews_content(String news_content) {
		this.news_content = news_content;
	}

	public byte[] getNews_photo() {
		return news_photo;
	}

	public void setNews_photo(byte[] news_photo) {
		this.news_photo = news_photo;
	}

	public String getNews_state() {
		return news_state;
	}

	public void setNews_state(String news_state) {
		this.news_state = news_state;
	}

	public Date getNews_date() {
		return news_date;
	}

	public void setNews_date(Date news_date) {
		this.news_date = news_date;
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

}

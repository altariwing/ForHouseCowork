package com.newstype.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class NewsTypeVO implements Serializable {

	private String ntype_no;
	private String news_type;

	public String getNtype_no() {
		return ntype_no;
	}

	public void setNtype_no(String ntype_no) {
		this.ntype_no = ntype_no;
	}

	public String getNews_type() {
		return news_type;
	}

	public void setNews_type(String news_type) {
		this.news_type = news_type;
	}

}

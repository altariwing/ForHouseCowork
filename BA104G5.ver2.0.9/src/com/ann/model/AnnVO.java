package com.ann.model;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class AnnVO implements Serializable {

	private Integer ann_no;
	private String ann_title;
	private String ann_content;
	private String ann_state;
	private Date ann_date;
	private String emp_no;



	public Integer getAnn_no() {
		return ann_no;
	}

	public void setAnn_no(Integer ann_no) {
		this.ann_no = ann_no;
	}

	public String getAnn_title() {
		return ann_title;
	}

	public void setAnn_title(String ann_title) {
		this.ann_title = ann_title;
	}

	public String getAnn_content() {
		return ann_content;
	}

	public void setAnn_content(String ann_content) {
		this.ann_content = ann_content;
	}

	public String getAnn_state() {
		return ann_state;
	}

	public void setAnn_state(String ann_state) {
		this.ann_state = ann_state;
	}

	public Date getAnn_date() {
		return ann_date;
	}

	public void setAnn_date(Date ann_date) {
		this.ann_date = ann_date;
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

}

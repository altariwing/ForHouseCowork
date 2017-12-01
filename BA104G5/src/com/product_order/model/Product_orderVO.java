package com.product_order.model;

import java.sql.Timestamp;

public class Product_orderVO {

	private String pdo_no;
	private String mem_no;
	private String slr_no;
	private String pdo_stat;
	private Timestamp pdo_resr_date;
	private Integer slr_rate;
	private Integer mem_rate;
	private String mem_review;
	private String pdo_params;
	private String cp_no;
	
	public Product_orderVO() {
		//empty
	}

	public String getPdo_no() {
		return pdo_no;
	}

	public void setPdo_no(String pdo_no) {
		this.pdo_no = pdo_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getSlr_no() {
		return slr_no;
	}

	public void setSlr_no(String slr_no) {
		this.slr_no = slr_no;
	}

	public String getPdo_stat() {
		return pdo_stat;
	}

	public void setPdo_stat(String pdo_stat) {
		this.pdo_stat = pdo_stat;
	}

	public Timestamp getPdo_resr_date() {
		return pdo_resr_date;
	}

	public void setPdo_resr_date(Timestamp pdo_resr_date) {
		this.pdo_resr_date = pdo_resr_date;
	}

	public Integer getSlr_rate() {
		return slr_rate;
	}

	public void setSlr_rate(Integer slr_rate) {
		this.slr_rate = slr_rate;
	}

	public Integer getMem_rate() {
		return mem_rate;
	}

	public void setMem_rate(Integer mem_rate) {
		this.mem_rate = mem_rate;
	}

	public String getMem_review() {
		return mem_review;
	}

	public void setMem_review(String mem_review) {
		this.mem_review = mem_review;
	}

	public String getPdo_params() {
		return pdo_params;
	}

	public void setPdo_params(String pdo_params) {
		this.pdo_params = pdo_params;
	}

	public String getCp_no() {
		return cp_no;
	}

	public void setCp_no(String cp_no) {
		this.cp_no = cp_no;
	}
	
	
}

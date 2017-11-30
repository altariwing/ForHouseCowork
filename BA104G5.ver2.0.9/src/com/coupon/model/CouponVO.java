package com.coupon.model;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class CouponVO implements Serializable {

	private String cp_no;
	private Date cp_from;
	private Date cp_to;
	private String cp_content;
	private String cp_discount;
	private String pdo_no;
	private String cp_state;
	private Date cp_date;
	private String mem_no;
	private String promo_no;

	public Date getCp_from() {
		return cp_from;
	}

	public void setCp_from(Date cp_from) {
		this.cp_from = cp_from;
	}

	public Date getCp_to() {
		return cp_to;
	}

	public void setCp_to(Date cp_to) {
		this.cp_to = cp_to;
	}

	public String getCp_content() {
		return cp_content;
	}

	public void setCp_content(String cp_content) {
		this.cp_content = cp_content;
	}

	public String getCp_discount() {
		return cp_discount;
	}

	public void setCp_discount(String cp_discount) {
		this.cp_discount = cp_discount;
	}

	public String getPdo_no() {
		return pdo_no;
	}

	public void setPdo_no(String pdo_no) {
		this.pdo_no = pdo_no;
	}

	public String getCp_state() {
		return cp_state;
	}

	public void setCp_state(String cp_state) {
		this.cp_state = cp_state;
	}

	public Date getCp_date() {
		return cp_date;
	}

	public void setCp_date(Date cp_date) {
		this.cp_date = cp_date;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getPromo_no() {
		return promo_no;
	}

	public void setPromo_no(String promo_no) {
		this.promo_no = promo_no;
	}

	public String getCp_no() {
		return cp_no;
	}

	public void setCp_no(String cp_no) {
		this.cp_no = cp_no;
	}

}

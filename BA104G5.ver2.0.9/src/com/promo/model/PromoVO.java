package com.promo.model;

import java.io.Serializable;
import java.sql.Date;

public class PromoVO implements Serializable {

	private String promo_no;
	private Date promo_from;
	private Date promo_to;
	private String promo_name;
	private String promo_content;
	private byte[] promo_photo;
	private String promo_state;
	private Date promo_date;
	private String emp_no;

	public String getPromo_no() {
		return promo_no;
	}

	public void setPromo_no(String promo_no) {
		this.promo_no = promo_no;
	}

	public Date getPromo_from() {
		return promo_from;
	}

	public void setPromo_from(Date promo_from) {
		this.promo_from = promo_from;
	}

	public Date getPromo_to() {
		return promo_to;
	}

	public void setPromo_to(Date promo_to) {
		this.promo_to = promo_to;
	}

	public String getPromo_name() {
		return promo_name;
	}

	public void setPromo_name(String promo_name) {
		this.promo_name = promo_name;
	}

	public String getPromo_content() {
		return promo_content;
	}

	public void setPromo_content(String promo_content) {
		this.promo_content = promo_content;
	}

	public byte[] getPromo_photo() {
		return promo_photo;
	}

	public void setPromo_photo(byte[] promo_photo) {
		this.promo_photo = promo_photo;
	}

	public String getPromo_state() {
		return promo_state;
	}

	public void setPromo_state(String promo_state) {
		this.promo_state = promo_state;
	}

	public Date getPromo_date() {
		return promo_date;
	}

	public void setPromo_date(Date promo_date) {
		this.promo_date = promo_date;
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

}

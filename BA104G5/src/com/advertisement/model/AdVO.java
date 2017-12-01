package com.advertisement.model;

import java.sql.Timestamp;

public class AdVO {
	
	private String ad_no;
	private String prd_no;
	private String emp_no;
	private String ad_type;
	private byte[] ad_img;
	private Timestamp appling_date;
	private Timestamp audit_date;
	private Timestamp expiration_date;
	
	
	public String getAd_no() {
		return ad_no;
	}
	public void setAd_no(String ad_no) {
		this.ad_no = ad_no;
	}
	public String getPrd_no() {
		return prd_no;
	}
	public void setPrd_no(String prd_no) {
		this.prd_no = prd_no;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getAd_type() {
		return ad_type;
	}
	public void setAd_type(String ad_type) {
		this.ad_type = ad_type;
	}
	public byte[] getAd_img() {
		return ad_img;
	}
	public void setAd_img(byte[] ad_img) {
		this.ad_img = ad_img;
	}
	public Timestamp getAppling_date() {
		return appling_date;
	}
	public void setAppling_date(Timestamp appling_date) {
		this.appling_date = appling_date;
	}
	public Timestamp getAudit_date() {
		return audit_date;
	}
	public void setAudit_date(Timestamp audit_date) {
		this.audit_date = audit_date;
	}
	public Timestamp getExpiration_date() {
		return expiration_date;
	}
	public void setExpiration_date(Timestamp expiration_date) {
		this.expiration_date = expiration_date;
	}
	
}

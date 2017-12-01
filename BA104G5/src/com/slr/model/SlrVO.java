package com.slr.model;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class SlrVO implements java.io.Serializable {
	private String slr_no;
	private String slr_name;
	private String slr_taxid;
	private String slr_id;
	private String slr_psw;
	private String slr_contact;
	private String slr_phone;
	private String slr_state;
	private String slr_intro;
	private String aggrement;
	
	public String getSlr_no() {
		return slr_no;
	}
	public void setSlr_no(String slr_no) {
		this.slr_no = slr_no;
	}
	public String getSlr_name() {
		return slr_name;
	}
	public void setSlr_name(String slr_name) {
		this.slr_name = slr_name;
	}
	public String getSlr_taxid() {
		return slr_taxid;
	}
	public void setSlr_taxid(String slr_taxid) {
		this.slr_taxid = slr_taxid;
	}
	public String getSlr_id() {
		return slr_id;
	}
	public void setSlr_id(String slr_id) {
		this.slr_id = slr_id;
	}
	public String getSlr_psw() {
		return slr_psw;
	}
	public void setSlr_psw(String slr_psw) {
		this.slr_psw = slr_psw;
	}
	public String getSlr_contact() {
		return slr_contact;
	}
	public void setSlr_contact(String slr_contact) {
		this.slr_contact = slr_contact;
	}
	public String getSlr_phone() {
		return slr_phone;
	}
	public void setSlr_phone(String slr_phone) {
		this.slr_phone = slr_phone;
	}
	public String getSlr_state() {
		return slr_state;
	}
	public void setSlr_state(String slr_state) {
		this.slr_state = slr_state;
	}
	public String getSlr_intro() {
		return slr_intro;
	}
	public void setSlr_intro(String slr_intro) {
		this.slr_intro = slr_intro;
	}
	public String getAggrement() {
		return aggrement;
	}
	public void setAggrement(String aggrement) {
		this.aggrement = aggrement;
	}
	
}

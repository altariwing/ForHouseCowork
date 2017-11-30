package com.realtor.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RealtorVO implements Serializable{

	private String rtr_no;
	private String rtr_id;
	private String rtr_psw;
	private String rtr_name;
	private byte[] rtr_photo;
	private String rtr_area;
	private String rtr_intro;
	private String rtr_idno;
	private String re_no;
	private String rtr_state;
	private String aggrement;

	public String getAggrement() {
		return aggrement;
	}

	public void setAggrement(String aggrement) {
		this.aggrement = aggrement;
	}

	public String getRtr_no() {
		return rtr_no;
	}

	public void setRtr_no(String rtr_no) {
		this.rtr_no = rtr_no;
	}

	public String getRtr_id() {
		return rtr_id;
	}

	public void setRtr_id(String rtr_id) {
		this.rtr_id = rtr_id;
	}

	public String getRtr_psw() {
		return rtr_psw;
	}

	public void setRtr_psw(String rtr_psw) {
		this.rtr_psw = rtr_psw;
	}

	public String getRtr_name() {
		return rtr_name;
	}

	public void setRtr_name(String rtr_name) {
		this.rtr_name = rtr_name;
	}

	public byte[] getRtr_photo() {
		return rtr_photo;
	}

	public void setRtr_photo(byte[] rtr_photo) {
		this.rtr_photo = rtr_photo;
	}

	public String getRtr_area() {
		return rtr_area;
	}

	public void setRtr_area(String rtr_area) {
		this.rtr_area = rtr_area;
	}

	public String getRtr_intro() {
		return rtr_intro;
	}

	public void setRtr_intro(String rtr_intro) {
		this.rtr_intro = rtr_intro;
	}

	public String getRtr_idno() {
		return rtr_idno;
	}

	public void setRtr_idno(String rtr_idno) {
		this.rtr_idno = rtr_idno;
	}

	public String getRe_no() {
		return re_no;
	}

	public void setRe_no(String re_no) {
		this.re_no = re_no;
	}

	public String getRtr_state() {
		return rtr_state;
	}

	public void setRtr_state(String rtr_state) {
		this.rtr_state = rtr_state;
	}

}

package com.realestate.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RealEstateVO implements Serializable{

	private String re_no;
	private String re_name;

	public String getRe_no() {
		return re_no;
	}

	public void setRe_no(String re_no) {
		this.re_no = re_no;
	}

	public String getRe_name() {
		return re_name;
	}

	public void setRe_name(String re_name) {
		this.re_name = re_name;
	}

}

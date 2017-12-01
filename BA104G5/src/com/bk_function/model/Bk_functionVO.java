package com.bk_function.model;

import java.sql.Timestamp;

/**
 * Bk_function Value Object
 *
 */
public class Bk_functionVO implements java.io.Serializable{
	
	private Integer bkf_no;
	private String bkf_name;
	private String bkf_dsb;
	private String bkf_state;
	private Timestamp bkf_update;
		
	public Bk_functionVO() {
		//empty
	}
	
	public Integer getBkf_no() {
		return bkf_no;
	}
	public void setBkf_no(Integer bkf_no) {
		this.bkf_no = bkf_no;
	}
	public String getBkf_name() {
		return bkf_name;
	}
	public void setBkf_name(String bkf_name) {
		this.bkf_name = bkf_name;
	}
	public String getBkf_dsb() {
		return bkf_dsb;
	}
	public void setBkf_dsb(String bkf_dsb) {
		this.bkf_dsb = bkf_dsb;
	}
	public String getBkf_state() {
		return bkf_state;
	}
	public void setBkf_state(String bkf_state) {
		this.bkf_state = bkf_state;
	}
	public Timestamp getBkf_update() {
		return bkf_update;
	}
	public void setBkf_update(Timestamp bkf_update) {
		this.bkf_update = bkf_update;
	}
	
	
	
}

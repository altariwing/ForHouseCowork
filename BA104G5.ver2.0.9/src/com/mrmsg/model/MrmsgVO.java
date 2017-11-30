package com.mrmsg.model;

import java.sql.Timestamp;

public class MrmsgVO {

	private String mem_no;
	
	private String rtr_no;
	
	private String mr_msg;
	
	private Timestamp mr_time;


	public MrmsgVO(){
		
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getRtr_no() {
		return rtr_no;
	}

	public void setRtr_no(String rtr_no) {
		this.rtr_no = rtr_no;
	}
	public String getMr_msg() {
		return mr_msg;
	}

	public void setMr_msg(String mr_msg) {
		this.mr_msg = mr_msg;
	}
	
	public Timestamp getMr_time() {
		return mr_time;
	}

	public void setMr_time(Timestamp mr_time) {
		this.mr_time = mr_time;
	}
	
}

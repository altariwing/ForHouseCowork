package com.msmsg.model;

import java.sql.Timestamp;

public class MsmsgVO {
	
	private String mem_no;
	
	private String slr_no;
	
	private String ms_msg;
	
	private Timestamp ms_time;


	public MsmsgVO(){
		
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
	public String getMs_msg() {
		return ms_msg;
	}

	public void setMs_msg(String ms_msg) {
		this.ms_msg = ms_msg;
	}
	
	public Timestamp getMs_time() {
		return ms_time;
	}

	public void setMs_time(Timestamp ms_time) {
		this.ms_time = ms_time;
	}
	
	
}

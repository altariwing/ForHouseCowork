package com.breaktime.model;

import java.sql.Date;

public class BreakTimeVO {
	private String brk_no;
	public String getBrk_no() {
		return brk_no;
	}

	public void setBrk_no(String brk_no) {
		this.brk_no = brk_no;
	}

	private String rtr_no;
	private Date brk_date;
	private String brk_period;
	
	public BreakTimeVO(){
		
	}

	public String getRtr_no() {
		return rtr_no;
	}

	public void setRtr_no(String rtr_no) {
		this.rtr_no = rtr_no;
	}

	public Date getBrk_date() {
		return brk_date;
	}

	public void setBrk_date(Date brk_date) {
		this.brk_date = brk_date;
	}

	public String getBrk_period() {
		return brk_period;
	}

	public void setBrk_period(String brk_period) {
		this.brk_period = brk_period;
	}
	
	
}

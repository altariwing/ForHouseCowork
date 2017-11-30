package com.reportrealtor.model;

import java.sql.Timestamp;

public class RptrtrVO implements java.io.Serializable {
	private String rr_no;
	private String mem_no;
	private String rtr_no;
	private String emp_no;
	private String rr_reason;
	private String rr_result;
	private String rr_state;
	private Timestamp rr_time;
	
	public String getRr_no() {
		return rr_no;
	}
	public void setRr_no(String rr_no) {
		this.rr_no = rr_no;
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
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getRr_reason() {
		return rr_reason;
	}
	public void setRr_reason(String rr_reason) {
		this.rr_reason = rr_reason;
	}
	public String getRr_result() {
		return rr_result;
	}
	public void setRr_result(String rr_result) {
		this.rr_result = rr_result;
	}
	public String getRr_state() {
		return rr_state;
	}
	public void setRr_state(String rr_state) {
		this.rr_state = rr_state;
	}
	public Timestamp getRr_time() {
		return rr_time;
	}
	public void setRr_time(Timestamp rr_time) {
		this.rr_time = rr_time;
	}
	
}

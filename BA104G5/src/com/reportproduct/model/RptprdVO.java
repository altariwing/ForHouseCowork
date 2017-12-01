package com.reportproduct.model;

import java.sql.Timestamp;

public class RptprdVO implements java.io.Serializable {
	private String rp_no;
	private String mem_no;
	private String prd_no;
	private String emp_no;
	private String rp_reason;
	private String rp_result;
	private String rp_state;
	private Timestamp rp_time;
	public String getRp_no() {
		return rp_no;
	}
	public void setRp_no(String rp_no) {
		this.rp_no = rp_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
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
	public String getRp_reason() {
		return rp_reason;
	}
	public void setRp_reason(String rp_reason) {
		this.rp_reason = rp_reason;
	}
	public String getRp_result() {
		return rp_result;
	}
	public void setRp_result(String rp_result) {
		this.rp_result = rp_result;
	}
	public String getRp_state() {
		return rp_state;
	}
	public void setRp_state(String rp_state) {
		this.rp_state = rp_state;
	}
	public Timestamp getRp_time() {
		return rp_time;
	}
	public void setRp_time(Timestamp rp_time) {
		this.rp_time = rp_time;
	}
	
}

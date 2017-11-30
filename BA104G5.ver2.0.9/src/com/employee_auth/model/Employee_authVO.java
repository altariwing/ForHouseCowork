package com.employee_auth.model;

import java.sql.Timestamp;

public class Employee_authVO implements java.io.Serializable{

	private String emp_no;
	private Integer bkf_no;
	private Timestamp emau_date;
	private String emau_editor;
	
	public Employee_authVO(){
		//empty
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public Integer getBkf_no() {
		return bkf_no;
	}

	public void setBkf_no(Integer bkf_no) {
		this.bkf_no = bkf_no;
	}

	public Timestamp getEmau_date() {
		return emau_date;
	}

	public void setEmau_date(Timestamp emau_date) {
		this.emau_date = emau_date;
	}

	public String getEmau_editor() {
		return emau_editor;
	}

	public void setEmau_editor(String emau_editor) {
		this.emau_editor = emau_editor;
	}
	
	
}

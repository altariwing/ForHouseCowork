package com.employee.model;

import java.sql.Timestamp;

/**
 * Employee Value Object
 */
public class EmployeeVO implements java.io.Serializable {

	private String emp_no;
	private String emp_id;
	private String emp_psw;
	private String emp_name;
	private Timestamp emp_lstlog;
	private Timestamp emp_badlog;
	private Integer emp_badlogtry;
	private byte[] emp_photo;
	private String emp_state;
	private Timestamp emp_newdate;
		
	public EmployeeVO() {
	    //empty	
	}

	public String getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_psw() {
		return emp_psw;
	}

	public void setEmp_psw(String emp_psw) {
		this.emp_psw = emp_psw;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public Timestamp getEmp_lstlog() {
		return emp_lstlog;
	}

	public void setEmp_lstlog(Timestamp emp_lstlog) {
		this.emp_lstlog = emp_lstlog;
	}

	public Timestamp getEmp_badlog() {
		return emp_badlog;
	}

	public void setEmp_badlog(Timestamp emp_badlog) {
		this.emp_badlog = emp_badlog;
	}

	public Integer getEmp_badlogtry() {
		return emp_badlogtry;
	}

	public void setEmp_badlogtry(Integer emp_badlogtry) {
		this.emp_badlogtry = emp_badlogtry;
	}

	public byte[] getEmp_photo() {
		return emp_photo;
	}

	public void setEmp_photo(byte[] emp_photo) {
		this.emp_photo = emp_photo;
	}

	public String getEmp_state() {
		return emp_state;
	}

	public void setEmp_state(String emp_state) {
		this.emp_state = emp_state;
	}

	public Timestamp getEmp_newdate() {
		return emp_newdate;
	}

	public void setEmp_newdate(Timestamp emp_newdate) {
		this.emp_newdate = emp_newdate;
	}
	
	
	
	
	
	
	
}

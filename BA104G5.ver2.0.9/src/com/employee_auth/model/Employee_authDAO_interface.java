package com.employee_auth.model;

import java.util.List;


/**
 * Employee_authDAO_interface
 * insert
 * update
 * delete
 * findByPK
 * getAll
 */
public interface Employee_authDAO_interface {

	/**
	 * insert a row 
	 * */
	public void insert(Employee_authVO Employee_authVO);
	
	/**
	 * update that (EMP_NO+BKF_NO)'s row 
	 * */
	public void update(Employee_authVO Employee_authVO);
	
	/**
	 * delete that (EMP_NO+BKF_NO)'s row  
	 * */
	public void delete(String emp_no);
	
	/**
	 * select PK from table
	 * no used
	 * */
	Employee_authVO findByPK(String emp_no);
	
	
	/**
	 * select all from table
	 * */
	List<Employee_authVO> getByemp_no();
	
	/**
	 * select all from table
	 * */
	List<Employee_authVO> getBybkf_no();
	
	/**
	 * select all from table
	 * dunno ITS USE
	 * */
	List<Employee_authVO> getAll();
	
	
	
}

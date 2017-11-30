package com.employee.model;

import java.util.List;

/**
 * EmployeeDAO_interface
 * insert
 * update
 * delete
 * findByPK
 * findByID
 * getAll
 * getCountByID
 */
public interface EmployeeDAO_interface {
	/**
	 * insert a row 
	 * */
	public void insert(EmployeeVO EmployeeVO);
	
	/**
	 * update that emp_no's row 
	 * */
	public void update(EmployeeVO EmployeeVO);
	
	/**
	 * delete that emp_no's row 
	 * */
	public void delete(String emp_no);
	
	/**
	 * select a row from table by PK
	 * */
	public EmployeeVO findByPK(String emp_no);
	
	/**
	 * select a row from table by ID
	 * */
	public EmployeeVO findByID(String emp_id);
	
	/**
	 * select all from table
	 * */
	public List<EmployeeVO> getAll();
	
	
	public Integer getCountByID (String emp_id);
	
}

package com.employee.model;

import java.util.List;

import com.employee.model.EmployeeVO;

public class EmployeeService {

	private EmployeeDAO_interface dao;
	
	public EmployeeService(){
		dao = new EmployeeJNDIDAO();
	}
	
	public EmployeeVO addEmp(String id, String psw,String name) {

		EmployeeVO empVO = new EmployeeVO();
		empVO.setEmp_id(id);
		empVO.setEmp_psw(psw);
		empVO.setEmp_name(name);			
		
		dao.insert(empVO);

		return empVO;
	}

	//預留給 Struts 2 用的
	public void addEmp(EmployeeVO empVO) {
		dao.insert(empVO);
	}
	
	public EmployeeVO updateEmp(String id, String psw,String name, byte[] photo, String state,String no) {

		EmployeeVO empVO = new EmployeeVO();

		empVO.setEmp_id(id);
		empVO.setEmp_psw(psw);
		empVO.setEmp_name(name);		
		empVO.setEmp_photo(photo);
		empVO.setEmp_state(state);
		empVO.setEmp_no(no);
		
		dao.update(empVO);

		return dao.findByPK(no);
	}
	
	//預留給 Struts 2 用的
	public void updateEmp(EmployeeVO empVO) {
		dao.update(empVO);
	}
	
	
/*=====================*/	
	
	public void deleteEmp(String empno) {
		dao.delete(empno);
	}

	public EmployeeVO getOneEmp(String empno) {
		return dao.findByPK(empno);
	}
	
	public EmployeeVO getOneEmpbyID(String empid) {
		return dao.findByID(empid);
	}

	public List<EmployeeVO> getAll() {
		return dao.getAll();
	}
		
	public Integer getCountByID(String emp_id) {
		return dao.getCountByID(emp_id);
	}
	
}

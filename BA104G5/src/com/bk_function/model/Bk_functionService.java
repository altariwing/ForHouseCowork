package com.bk_function.model;

import java.util.List;

import com.employee.model.EmployeeDAO_interface;
import com.employee.model.EmployeeVO;

public class Bk_functionService {

	private Bk_functionDAO_interface dao;
	
	public Bk_functionService(){
		dao = new Bk_functionDAO();
	}
	
	public Bk_functionVO addBkf(String name,String dsb,String state){
		Bk_functionVO bkfVO = new Bk_functionVO();
		bkfVO.setBkf_name(name);
		bkfVO.setBkf_dsb(dsb);
		bkfVO.setBkf_state(state);
		
		dao.insert(bkfVO);
		
		return bkfVO;
	}
	
	//預留給 Struts 2 用的
	public void addBkf(Bk_functionVO bkfVO) {
		dao.insert(bkfVO);
	}	
	
	public Bk_functionVO updateBkf(String bkf_name,String bkf_dsb,String bkf_state,Integer bkf_no){
		
		Bk_functionVO bkfVO = new Bk_functionVO();
		
		bkfVO.setBkf_name(bkf_name);
		bkfVO.setBkf_dsb(bkf_dsb);
		bkfVO.setBkf_state(bkf_state);
		bkfVO.setBkf_no(bkf_no);
		
		dao.update(bkfVO);
		
		return dao.findByPK(bkf_no);
		
	}
	
	//預留給 Struts 2 用的
	public void updateBkf(Bk_functionVO bkfVO){
		dao.update(bkfVO);
	}	
	
	public void deleteBkf(Integer bkf_no){
		dao.delete(bkf_no);
	}
	
	public void getOneBkf(Integer bkf_no){
		dao.findByPK(bkf_no);
	}
	
	public List<Bk_functionVO> getAll(){
		return dao.getAll();
	}
	
}

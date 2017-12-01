package com.mrmsg.model;

import java.util.List;


public class MrmsgService {
	
	private MrmsgDAO_interface dao;
	
	public MrmsgService(){
		dao = new MrmsgJDBCDAO();
	}
	
	public MrmsgVO addMrmsg(String mem_no, String rtr_no, String mr_msg){
		
		MrmsgVO mrmsgVO =new MrmsgVO();
		
		mrmsgVO.setMem_no(mem_no);
		mrmsgVO.setRtr_no(rtr_no);
		mrmsgVO.setMr_msg(mr_msg);
		
		dao.insert(mrmsgVO);
		
		return mrmsgVO;
	}
	
	public void addMrmsg(MrmsgVO mrmsgVO){
		dao.insert(mrmsgVO);
	}
	
	public MrmsgVO updateMrmsg(String mem_no, String rtr_no, String mr_msg){
		
		MrmsgVO mrmsgVO =new MrmsgVO();
		
		mrmsgVO.setMem_no(mem_no);
		mrmsgVO.setRtr_no(rtr_no);
		mrmsgVO.setMr_msg(mr_msg);
		
		dao.update(mrmsgVO);
		
		return dao.findByPrimaryKey(mem_no,rtr_no);
	}
	
	public void updateMrmsg(MrmsgVO mrmsgVO){
		dao.update(mrmsgVO);
	}
	
//	public void deletMrmsg(String mem_no,String rtr_no){
//		dao.delete(mem_no,rtr_no);
//	}
	
	public MrmsgVO getOneResR(String mem_no,String rtr_no){
		return dao.findByPrimaryKey(mem_no,rtr_no);
	}
	
	public List<MrmsgVO> getAll(){
		return dao.getAll();
	}
	
}

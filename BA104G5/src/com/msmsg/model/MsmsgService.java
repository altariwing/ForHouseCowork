package com.msmsg.model;


import java.util.List;


public class MsmsgService {
	private MsmsgDAO_interface dao;
	
	public MsmsgService(){
		dao = new MsmsgJDBCDAO();
	}
	
	public MsmsgVO addMsmsg(String mem_no, String slr_no, String ms_msg){
		
		MsmsgVO msmsgVO =new MsmsgVO();
		
		msmsgVO.setMem_no(mem_no);
		msmsgVO.setSlr_no(slr_no);
		msmsgVO.setMs_msg(ms_msg);
		
		dao.insert(msmsgVO);
		
		return msmsgVO;
	}
	
	public void addMsmsg(MsmsgVO msmsgVO){
		dao.insert(msmsgVO);
	}
	
	public MsmsgVO updateMsmsg(String mem_no, String slr_no, String ms_msg){
		
		MsmsgVO msmsgVO =new MsmsgVO();
		
		msmsgVO.setMem_no(mem_no);
		msmsgVO.setSlr_no(slr_no);
		msmsgVO.setMs_msg(ms_msg);
		
		dao.update(msmsgVO);
		
		return dao.findByPrimaryKey(mem_no,slr_no);
	}
	
	public void updateMsmsg(MsmsgVO msmsgVO){
		dao.update(msmsgVO);
	}
	
//	public void deletMsmsg(String mem_no,String slr_no){
//		dao.delete(mem_no,slr_no);
//	}
	
	public MsmsgVO getOneResR(String mem_no,String slr_no){
		return dao.findByPrimaryKey(mem_no,slr_no);
	}
	
	public List<MsmsgVO> getAll(){
		return dao.getAll();
	}
	
}

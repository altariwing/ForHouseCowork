package com.breaktime.model;

import java.sql.Date;
import java.util.List;


public class BreakTimeService {

	private BreakTimeDAO_interface dao;
	
	public BreakTimeService(){
		dao = new BreakTimeJDBCDAO();
	}
	
	public BreakTimeVO addBreakTime(String rtr_no , Date brk_date , String brk_period){
		
		BreakTimeVO breakTimeVO =new BreakTimeVO();
		
		breakTimeVO.setRtr_no(rtr_no);
		breakTimeVO.setBrk_date(brk_date);
		breakTimeVO.setBrk_period(brk_period);
		
		dao.insert(breakTimeVO);
		
		return breakTimeVO;
	}
	
	public BreakTimeVO addBreakTime(BreakTimeVO breakTimeVO){
		String brk_no = dao.insert(breakTimeVO);		
		return dao.findByPrimaryKey(brk_no);
	}
	
	public BreakTimeVO updateBreakTime(String brk_no , String rtr_no , Date brk_date , String brk_period){
		
		BreakTimeVO breakTimeVO =new BreakTimeVO();
		breakTimeVO.setBrk_no(brk_no);
		breakTimeVO.setRtr_no(rtr_no);
		breakTimeVO.setBrk_date(brk_date);
		breakTimeVO.setBrk_period(brk_period);
		
		dao.update(breakTimeVO);
		
		return dao.findByPrimaryKey(brk_no);
	}
	
	public void updateBreakTime(BreakTimeVO breakTimeVO){
		dao.update(breakTimeVO);
	}
	
	public void deletBreakTime(String brk_no ){
		dao.delete(brk_no);
	}
	
	public BreakTimeVO getOneResR(String brk_no){
		return dao.findByPrimaryKey(brk_no);
	}
	
	public List<BreakTimeVO> getAll(){
		return dao.getAll();
	}
	
	public List<BreakTimeVO> getRtr_bt_kuei(String rtr_no){
		return dao.getRtr_bt_kuei(rtr_no);
	}
}

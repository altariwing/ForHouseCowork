package com.resrec.model;

import java.sql.Date;
import java.util.List;

public class ResRecService {

	private ResRecDAO_interface dao;

	public ResRecService() {
		dao = new ResRecJDBCDAO();
	}

	public ResRecVO addResRec(String mem_no, String house_no, String rtr_no, String resr_states, Date resr_date,
			String resr_period, Double resr_hpric, Double resr_hsize, Double resr_mgt, Double resr_puc,
			Double resr_str, Double resr_wall, Double resr_ltg, Double resr_ven, Double resr_tc, Double resr_lc,
			String hse_rev, Double mem_rate, String mem_rev, Double rtr_rate) {

		ResRecVO resrecVO = new ResRecVO();

		resrecVO.setMem_no(mem_no);
		resrecVO.setHouse_no(house_no);
		resrecVO.setRtr_no(rtr_no);
		resrecVO.setResr_states(resr_states);
		resrecVO.setResr_date(resr_date);
		resrecVO.setResr_period(resr_period);
		resrecVO.setResr_hpric(resr_hpric);
		resrecVO.setResr_hsize(resr_hsize);
		resrecVO.setResr_mgt(resr_mgt);
		resrecVO.setResr_puc(resr_puc);
		resrecVO.setResr_str(resr_str);
		resrecVO.setResr_wall(resr_wall);
		resrecVO.setResr_ltg(resr_ltg);
		resrecVO.setResr_ven(resr_ven);
		resrecVO.setResr_tc(resr_tc);
		resrecVO.setResr_lc(resr_lc);
		resrecVO.setHse_rev(hse_rev);
		resrecVO.setMem_rate(mem_rate);
		resrecVO.setMem_rev(mem_rev);
		resrecVO.setRtr_rate(rtr_rate);

		dao.insert(resrecVO);

		return resrecVO;
	}

	public ResRecVO addResRec_kuei1(String mem_no, String house_no, String rtr_no, String resr_states, Date resr_date,
			String resr_period) {
		ResRecVO resrecVO = new ResRecVO();

		resrecVO.setMem_no(mem_no);
		resrecVO.setHouse_no(house_no);
		resrecVO.setRtr_no(rtr_no);
		resrecVO.setResr_states(resr_states);
		resrecVO.setResr_date(resr_date);
		resrecVO.setResr_period(resr_period);

		dao.insert_kuei1(resrecVO);

		return resrecVO;

	}

	public void addResRec(ResRecVO resrecVO) {
		dao.insert(resrecVO);
	}

	public void addResRec_kuei1(ResRecVO resrecVO) {
		dao.insert_kuei1(resrecVO);
	}

	public ResRecVO updateResRec(String resr_no, String mem_no, String house_no, String rtr_no, String resr_states,
			Date resr_date, String resr_period, Double resr_hpric, Double resr_hsize, Double resr_mgt,
			Double resr_puc, Double resr_str, Double resr_wall, Double resr_ltg, Double resr_ven, Double resr_tc,
			Double resr_lc, String hse_rev, Double mem_rate, String mem_rev, Double rtr_rate) {

		ResRecVO resrecVO = new ResRecVO();

		resrecVO.setResr_no(resr_no);
		resrecVO.setMem_no(mem_no);
		resrecVO.setHouse_no(house_no);
		resrecVO.setRtr_no(rtr_no);
		resrecVO.setResr_states(resr_states);
		resrecVO.setResr_date(resr_date);
		resrecVO.setResr_period(resr_period);
		resrecVO.setResr_hpric(resr_hpric);
		resrecVO.setResr_hsize(resr_hsize);
		resrecVO.setResr_mgt(resr_mgt);
		resrecVO.setResr_puc(resr_puc);
		resrecVO.setResr_str(resr_str);
		resrecVO.setResr_wall(resr_wall);
		resrecVO.setResr_ltg(resr_ltg);
		resrecVO.setResr_ven(resr_ven);
		resrecVO.setResr_tc(resr_tc);
		resrecVO.setResr_lc(resr_lc);
		resrecVO.setHse_rev(hse_rev);
		resrecVO.setMem_rate(mem_rate);
		resrecVO.setMem_rev(mem_rev);
		resrecVO.setRtr_rate(rtr_rate);

		dao.update(resrecVO);

		return dao.findByPrimaryKey(resr_no);
	}

	public ResRecVO updateResRec_kuei1(String resr_no, String mem_no, String house_no, String rtr_no,
			String resr_states, Date resr_date, String resr_period) {
		ResRecVO resrecVO = new ResRecVO();

		resrecVO.setResr_no(resr_no);
		resrecVO.setMem_no(mem_no);
		resrecVO.setHouse_no(house_no);
		resrecVO.setRtr_no(rtr_no);
		resrecVO.setResr_states(resr_states);
		resrecVO.setResr_date(resr_date);
		resrecVO.setResr_period(resr_period);

		dao.update_kuei1(resrecVO);

		return resrecVO;
	}

	public ResRecVO updateResRec_kuei2(String resr_no, String resr_states, Double resr_hpric, Double resr_hsize,
			Double resr_mgt, Double resr_puc, Double resr_str, Double resr_wall, Double resr_ltg, Double resr_ven,
			Double resr_tc, Double resr_lc, String hse_rev, Double mem_rate, String mem_rev, Double rtr_rate) {

		ResRecVO resrecVO = new ResRecVO();

		resrecVO.setResr_no(resr_no);
		resrecVO.setResr_states(resr_states);
		resrecVO.setResr_hpric(resr_hpric);
		resrecVO.setResr_hsize(resr_hsize);
		resrecVO.setResr_mgt(resr_mgt);
		resrecVO.setResr_puc(resr_puc);
		resrecVO.setResr_str(resr_str);
		resrecVO.setResr_wall(resr_wall);
		resrecVO.setResr_ltg(resr_ltg);
		resrecVO.setResr_ven(resr_ven);
		resrecVO.setResr_tc(resr_tc);
		resrecVO.setResr_lc(resr_lc);
		resrecVO.setHse_rev(hse_rev);
		resrecVO.setMem_rate(mem_rate);
		resrecVO.setMem_rev(mem_rev);
		resrecVO.setRtr_rate(rtr_rate);

		dao.update_kuei2(resrecVO);

		return dao.findByPrimaryKey(resr_no);
	}
	
	public ResRecVO updateResRec_kuei3( String resr_states, Date resr_date, String resr_period,String resr_no) {
		ResRecVO resrecVO = new ResRecVO();

		resrecVO.setResr_no(resr_no);
		resrecVO.setResr_states(resr_states);
		resrecVO.setResr_date(resr_date);
		resrecVO.setResr_period(resr_period);

		dao.update_kuei3(resrecVO);

		return resrecVO;
	}
	
	public void updateResRec(ResRecVO resrecVO) {
		dao.update(resrecVO);
	}

	public void updateResRec_kuei1(ResRecVO resrecVO) {
		dao.update_kuei1(resrecVO);
	}
	
	public void updateResRec_kuei2(ResRecVO resrecVO) {
		dao.update_kuei2(resrecVO);
	}
	
	public void updateResRec_kuei3(ResRecVO resrecVO) {
		dao.update_kuei3(resrecVO);
	}

	public void deleteResRec(String resr_no) {
		dao.delete(resr_no);
	}

	public ResRecVO getOneResR(String resr_no) {
		return dao.findByPrimaryKey(resr_no);
	}
	
	public List<ResRecVO> getMem_kuei(String mem_no){
		return dao.getMem_kuei(mem_no);
	}
	
	public List<ResRecVO> getRtr_kuei(String rtr_no){
		return dao.getRtr_kuei(rtr_no);
	}
	
	public List<ResRecVO> getAll() {
		return dao.getAll();
	}

}

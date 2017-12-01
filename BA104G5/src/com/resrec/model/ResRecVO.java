package com.resrec.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class ResRecVO implements Serializable , Comparable<ResRecVO>{
	private String resr_no;
	private String mem_no;
	private String house_no;
	private String rtr_no;
	private Timestamp resr_ord;
	private String resr_states;
	private Date resr_date;
	private String resr_period;
	private Double resr_hpric;
	private Double resr_hsize;
	private Double resr_mgt;
	private Double resr_puc;
	private Double resr_str;
	private Double resr_wall;
	private Double resr_ltg;
	private Double resr_ven;
	private Double resr_tc;
	private Double resr_lc;	
	private String hse_rev;
	private Double mem_rate;
	private String mem_rev;
	private Double rtr_rate;
	
	
	public ResRecVO() {
		super();
	}
	public String getResr_no() {
		return resr_no;
	}
	public void setResr_no(String resr_no) {
		this.resr_no = resr_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getHouse_no() {
		return house_no;
	}
	public void setHouse_no(String house_no) {
		this.house_no = house_no;
	}
	public String getRtr_no() {
		return rtr_no;
	}
	public void setRtr_no(String rtr_no) {
		this.rtr_no = rtr_no;
	}
	public Timestamp getResr_ord() {
		return resr_ord;
	}
	public void setResr_ord(Timestamp resr_ord) {
		this.resr_ord = resr_ord;
	}
	public String getResr_states() {
		return resr_states;
	}
	public void setResr_states(String resr_states) {
		this.resr_states = resr_states;
	}
	public Date getResr_date() {
		return resr_date;
	}
	public void setResr_date(Date resr_date) {
		this.resr_date = resr_date;
	}
	public String getResr_period() {
		return resr_period;
	}
	public void setResr_period(String resr_period) {
		this.resr_period = resr_period;
	}
	public Double getResr_hpric() {
		return resr_hpric;
	}
	public void setResr_hpric(Double resr_hpric) {
		this.resr_hpric = resr_hpric;
	}
	public Double getResr_hsize() {
		return resr_hsize;
	}
	public void setResr_hsize(Double resr_hsize) {
		this.resr_hsize = resr_hsize;
	}
	public Double getResr_mgt() {
		return resr_mgt;
	}
	public void setResr_mgt(Double resr_mgt) {
		this.resr_mgt = resr_mgt;
	}
	public Double getResr_puc() {
		return resr_puc;
	}
	public void setResr_puc(Double resr_puc) {
		this.resr_puc = resr_puc;
	}
	public Double getResr_str() {
		return resr_str;
	}
	public void setResr_str(Double resr_str) {
		this.resr_str = resr_str;
	}
	public Double getResr_wall() {
		return resr_wall;
	}
	public void setResr_wall(Double resr_wall) {
		this.resr_wall = resr_wall;
	}
	public Double getResr_ltg() {
		return resr_ltg;
	}
	public void setResr_ltg(Double resr_ltg) {
		this.resr_ltg = resr_ltg;
	}
	public Double getResr_ven() {
		return resr_ven;
	}
	public void setResr_ven(Double resr_ven) {
		this.resr_ven = resr_ven;
	}
	public Double getResr_tc() {
		return resr_tc;
	}
	public void setResr_tc(Double resr_tc) {
		this.resr_tc = resr_tc;
	}
	public Double getResr_lc() {
		return resr_lc;
	}
	public void setResr_lc(Double resr_lc) {
		this.resr_lc = resr_lc;
	}
	public String getHse_rev() {
		return hse_rev;
	}
	public void setHse_rev(String hse_rev) {
		this.hse_rev = hse_rev;
	}
	public Double getMem_rate() {
		return mem_rate;
	}
	public void setMem_rate(Double mem_rate) {
		this.mem_rate = mem_rate;
	}
	public String getMem_rev() {
		return mem_rev;
	}
	public void setMem_rev(String mem_rev) {
		this.mem_rev = mem_rev;
	}
	public Double getRtr_rate() {
		return rtr_rate;
	}
	public void setRtr_rate(Double rtr_rate) {
		this.rtr_rate = rtr_rate;
	}
	@Override
	public int compareTo(ResRecVO resrecVO) {
		Integer thisperiod = Integer.valueOf(this.resr_period.substring(0, this.resr_period.indexOf(":")));
		Integer periodVO = Integer.valueOf(resrecVO.resr_period.substring(0, resrecVO.resr_period.indexOf(":")));
				if ((Long) this.resr_date.getTime()+thisperiod < (Long) resrecVO.resr_date.getTime()+periodVO){
					
					return -1;
				}
			    if (this.resr_date.getTime()+thisperiod==resrecVO.resr_date.getTime()+periodVO){
			    	
			    	return 0;
			    }
			    return 1;
	}
}

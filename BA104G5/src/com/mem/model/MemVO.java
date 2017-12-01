package com.mem.model;

public class MemVO implements java.io.Serializable {
	private String mem_no;
	private String mem_id;
	private String mem_psw;
	private String mem_name;
	private String mem_addr;
	private byte[] mem_img;
	private Integer mem_game;
	private String search_state;
	
	private String lock_state;
	private String aggrement;
	private String twcity;
	private String twdist;
	private String twcitytext;
	
	
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_psw() {
		return mem_psw;
	}
	public void setMem_psw(String mem_psw) {
		this.mem_psw = mem_psw;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_addr() {
		return mem_addr;
	}
	public void setMem_addr(String mem_addr) {
		this.mem_addr = mem_addr;
	}
	public String getSearch_state() {
		return search_state;
	}
	public void setSearch_state(String search_state) {
		this.search_state = search_state;
	}
	public String getLock_state() {
		return lock_state;
	}
	public void setLock_state(String lock_state) {
		this.lock_state = lock_state;
	}
	public String getAggrement() {
		return aggrement;
	}
	public void setAggrement(String aggrement) {
		this.aggrement = aggrement;
	}
	public String getTwcity() {
		return twcity;
	}
	public void setTwcity(String twcity) {
		this.twcity = twcity;
	}
	public String getTwdist() {
		return twdist;
	}
	public void setTwdist(String twdist) {
		this.twdist = twdist;
	}
	public String getTwcitytext() {
		return twcitytext;
	}
	public void setTwcitytext(String twcitytext) {
		this.twcitytext = twcitytext;
	}
	public byte[] getMem_img() {
		return mem_img;
	}
	public void setMem_img(byte[] mem_img) {
		this.mem_img = mem_img;
	}
	public Integer getMem_game() {
		return mem_game;
	}
	public void setMem_game(Integer mem_game) {
		this.mem_game = mem_game;
	}

}
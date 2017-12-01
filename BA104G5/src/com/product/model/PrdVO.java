package com.product.model;

public class PrdVO implements java.io.Serializable {
	private String prd_no;
	private String slr_no;
	private String cate_no;
	private String prd_name;
	private String prd_desc;
	private Integer prd_stock;
	private Integer prd_price;
	private String prd_state;
	private Integer quantity;
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getPrd_no() {
		return prd_no;
	}
	public void setPrd_no(String prd_no) {
		this.prd_no = prd_no;
	}
	public String getSlr_no() {
		return slr_no;
	}
	public void setSlr_no(String slr_no) {
		this.slr_no = slr_no;
	}
	public String getCate_no() {
		return cate_no;
	}
	public void setCate_no(String cate_no) {
		this.cate_no = cate_no;
	}
	public String getPrd_name() {
		return prd_name;
	}
	public void setPrd_name(String prd_name) {
		this.prd_name = prd_name;
	}
	public String getPrd_desc() {
		return prd_desc;
	}
	public void setPrd_desc(String prd_desc) {
		this.prd_desc = prd_desc;
	}
	public Integer getPrd_stock() {
		return prd_stock;
	}
	public void setPrd_stock(Integer prd_stock) {
		this.prd_stock = prd_stock;
	}
	public Integer getPrd_price() {
		return prd_price;
	}
	public void setPrd_price(Integer prd_price) {
		this.prd_price = prd_price;
	}
	public String getPrd_state() {
		return prd_state;
	}
	public void setPrd_state(String prd_state) {
		this.prd_state = prd_state;
	}
	
}

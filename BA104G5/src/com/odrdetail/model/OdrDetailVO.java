package com.odrdetail.model;

public class OdrDetailVO {
	private String pdo_no;
	private String prd_no;
	private Integer unit_price;
	private Integer quantity;
	
	public String getPdo_no() {
		return pdo_no;
	}
	public void setPdo_no(String pdo_no) {
		this.pdo_no = pdo_no;
	}
	public String getPrd_no() {
		return prd_no;
	}
	public void setPrd_no(String prd_no) {
		this.prd_no = prd_no;
	}
	public Integer getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(Integer unit_price) {
		this.unit_price = unit_price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}

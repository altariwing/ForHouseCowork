package com.order_detail.model;

public class Order_detailVO {
	private String pdo_no;
	private String prd_no;
	private Integer oder_uni_price;
	private Integer oder_quantity;
	
	public Order_detailVO(){
		//empty
	}

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

	public Integer getOder_uni_price() {
		return oder_uni_price;
	}

	public void setOder_uni_price(Integer oder_uni_price) {
		this.oder_uni_price = oder_uni_price;
	}

	public Integer getOder_quantity() {
		return oder_quantity;
	}

	public void setOder_quantity(Integer oder_quantity) {
		this.oder_quantity = oder_quantity;
	}
	
	
}

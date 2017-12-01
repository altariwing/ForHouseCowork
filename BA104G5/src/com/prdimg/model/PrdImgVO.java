package com.prdimg.model;


public class PrdImgVO implements java.io.Serializable {
	private Integer img_no;
	private String prd_no;
	private byte[] prd_img;
	
	public Integer getImg_no() {
		return img_no;
	}
	public void setImg_no(Integer img_no) {
		this.img_no = img_no;
	}
	public String getPrd_no() {
		return prd_no;
	}
	public void setPrd_no(String prd_no) {
		this.prd_no = prd_no;
	}
	public byte[] getPrd_img() {
		return prd_img;
	}
	public void setPrd_img(byte[] prd_img) {
		this.prd_img = prd_img;
	}
}

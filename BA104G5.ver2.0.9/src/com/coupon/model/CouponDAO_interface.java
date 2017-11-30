package com.coupon.model;

import java.util.List;

public interface CouponDAO_interface {
	public void insert(CouponVO couponVO); 
	public void update(CouponVO couponVO); 
	public CouponVO findByPrimaryKey(String coupon_no);
	public List<CouponVO> getAll();

	public List<CouponVO> getAllByTime();//依照開始時間做排序
}

package com.promo.model;

import java.util.List;
import java.util.Set;

import com.coupon.model.CouponVO;


public interface PromoDAO_interface {
	
	public void insert(PromoVO promoVO); 
	public void update(PromoVO promoVO); 
	public PromoVO findByPrimaryKey(String promo_no);
	public List<PromoVO> getAll();
	
	//依照開始時間做排序
	public List<PromoVO> getAllByTime();
	// 專門塞促銷照片，內容
	public void updatePhoto(PromoVO promoVO);
	//查詢促銷資訊對應的優惠卷
	public Set<CouponVO> getCPsByPromono(String promo_no);
}

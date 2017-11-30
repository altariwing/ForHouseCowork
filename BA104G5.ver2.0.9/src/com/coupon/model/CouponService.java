package com.coupon.model;

import java.util.List;

public class CouponService {
	CouponDAO_interface dao;

	public CouponService() {
		dao = new CouponDAO();
	}

	// 新增
	public CouponVO add(java.sql.Date cp_from, java.sql.Date cp_to, String cp_content, String cp_discount,
			String pdo_no, String cp_state, String mem_no, String promo_no) {
		CouponVO couponVO = new CouponVO();
		couponVO.setCp_from(cp_from);
		couponVO.setCp_to(cp_to);
		couponVO.setCp_content(cp_content);
		couponVO.setCp_discount(cp_discount);
		couponVO.setPdo_no(pdo_no);
		couponVO.setCp_state(cp_state);
		couponVO.setMem_no(mem_no);
		couponVO.setPromo_no(promo_no);
		dao.insert(couponVO);
		return couponVO;

	}

	// 修改
	public CouponVO update(java.sql.Date cp_from, java.sql.Date cp_to, String cp_content, String cp_discount,
			String pdo_no, String cp_state, String mem_no, String promo_no, String cp_no) {
		CouponVO couponVO = new CouponVO();
		couponVO.setCp_from(cp_from);
		couponVO.setCp_to(cp_to);
		couponVO.setCp_content(cp_content);
		couponVO.setCp_discount(cp_discount);
		couponVO.setPdo_no(pdo_no);
		couponVO.setCp_state(cp_state);
		couponVO.setMem_no(mem_no);
		couponVO.setPromo_no(promo_no);
		couponVO.setCp_no(cp_no);
		dao.update(couponVO);
		return couponVO;
	}

	// 查單一
	public CouponVO getOne(String cp_no) {
		return dao.findByPrimaryKey(cp_no);
	}

	// 查全部
	public List<CouponVO> getAll() {
		return dao.getAll();
	}

	// 查期限排序
	public List<CouponVO> getAllByTime() {
		return dao.getAllByTime();
	}

}

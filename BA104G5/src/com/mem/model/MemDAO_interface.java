package com.mem.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.coupon.model.CouponVO;

public interface MemDAO_interface {
	public void insert(MemVO memVO);
    public void update(MemVO memVO);
    public void changeLockState(MemVO memVO);
    public void changePassword(MemVO memVO);
    public MemVO findByPrimaryKey(String mem_no);
    public MemVO findById(String mem_id);
    public List<MemVO> getAll();
    public List<MemVO> getIdList();
    public void setGame(Integer mem_game, String mem_no);

    //專門找開放找房狀態的會員BY阿蓋
    public List<MemVO> getOpenList();
    //專門找開放找房狀態的會員BY阿蓋Map
    public List<MemVO> getOpenMap(Map<String, String[]> map);
    //專門找會員擁有的優惠卷BY阿蓋
    public Set<CouponVO> getCPByMemno(String mem_no);
}

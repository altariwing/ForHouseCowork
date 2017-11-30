package com.realtor.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.article.model.ArticleVO;


public interface RealtorDAO_interface {

	public void insert(RealtorVO realtorVO);
	public void update(RealtorVO realtorVO);//目前更改簡介，照片，還有姓名，我寫了兩個方法，但其中一個現在沒有使用
	public RealtorVO findByPrimaryKey(String rtr_no);
	public List<RealtorVO> getAll();
	public void updatePhoto(RealtorVO realtorVO);// 專門塞房仲照片以及簡介
	
	public RealtorVO findById(String rtr_id);//找房仲ID
	public void changePassword(RealtorVO realtorVO);//更換密碼
	
	
//	public List<RealtorVO> getNameList();//列出所有房仲姓名
//	public List<RealtorVO> getAreaList();//列出所有房仲所屬地區

	public RealtorVO findByName(String rtr_name);//找房仲名稱
	public List<RealtorVO> getIdList();//列出所有房仲ID(email)
	public List<RealtorVO> getAll(Map<String, String[]> map);//萬用複合查詢(傳入參數型態Map)(回傳 List)
	public List<RealtorVO> findByKeyword(String keyword);//複合查詢ByKeyword
	public void changeRealtorState(RealtorVO realtorVO);//更換房仲狀態
	public void FBInsert(RealtorVO realtorVO);//房仲FB登入的特別insert
	public Set<ArticleVO> getArtByRtrNo(String rtr_no);// 找房仲文章
	
 
}

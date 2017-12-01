package com.odrdetail.model;

import java.util.List;

public interface OdrDetailDAO_interface {
	public void insert(OdrDetailVO odrDetailVO);
	public void delOnePrd(String pdo_no, String prd_no);    //依據訂單編號 pdo_no 及商品編號 prd_no 刪除整張訂單的某筆明細
	public void delOneOrder(String pdo_no);                 //依據訂單編號 pdo_no 刪除所有關聯的訂單明細
	public List<OdrDetailVO> getListByPdoNo(String pdo_no); //查詢某訂單的所有明細
	public List<OdrDetailVO> getListByPrdNo(String prd_no); //查詢某商品的訂單明細
}

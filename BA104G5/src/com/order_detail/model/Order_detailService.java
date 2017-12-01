package com.order_detail.model;

import java.util.List;

public class Order_detailService {
	private Order_detailDAO_interface dao;
	
	public Order_detailService(){
		dao= new Order_detailDAO();
	}
	
	public Order_detailVO addOdt(String pdo_no, String prd_no,Integer oder_uni_price,Integer oder_quantity){
		
		Order_detailVO order_detailVO = new Order_detailVO();
		order_detailVO.setPdo_no(pdo_no);
		order_detailVO.setPrd_no(prd_no);
		order_detailVO.setOder_uni_price(oder_uni_price);
		order_detailVO.setOder_quantity(oder_quantity);
		dao.insert(order_detailVO);
		return order_detailVO;
	}
	public Order_detailVO updateOdt(String pdo_no, String prd_no,Integer oder_quantity){
		Order_detailVO order_detailVO = new Order_detailVO();
		order_detailVO.setPdo_no(pdo_no);
		order_detailVO.setPrd_no(prd_no);
		order_detailVO.setOder_quantity(oder_quantity);
		dao.update(order_detailVO);
		return order_detailVO;
	}
	
	public void deleteOdt(String pdo_no, String prd_no){
		dao.delete(pdo_no, pdo_no);
	}
	
	public Order_detailVO getOneOdt(String pdo_no, String prd_no){
		return dao.findByPK(pdo_no, prd_no);
	}
	
	public List<Order_detailVO> getAll(){
		return dao.getAll();
	}
	
	public List<Order_detailVO> getByPDO_NO(String pdo_no){
		return dao.getAllByPDO_NO(pdo_no);
	}
}

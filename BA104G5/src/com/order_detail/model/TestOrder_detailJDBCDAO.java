package com.order_detail.model;

import java.util.List;

public class TestOrder_detailJDBCDAO {

	public static void main(String[] args) {
		System.out.println("hello world!");

		Order_detailJDBCDAO dao = new Order_detailJDBCDAO();
		
//		//☁Test  insert(Order_detailVO Order_detailVO); 
//		System.out.println("====☁Test insert(Order_detailVO Order_detailVO)====");
//		Order_detailVO odtVO1 = new Order_detailVO();
//		odtVO1.setPdo_no("OD171109-000001");
//		odtVO1.setPrd_no("PD00000001");
//		odtVO1.setOder_uni_price(599);
//		odtVO1.setOder_quantity(3);
//        dao.insert(odtVO1);
        
//		//☁Test   update(Order_detailVO Order_detailVO); 
//		System.out.println("====☁Test update(Order_detailVO Order_detailVO)====");
//		Order_detailVO odtVO2 = new Order_detailVO();
//		odtVO2.setOder_quantity(100);
//		odtVO2.setPdo_no("OD171109-000001");
//		odtVO2.setPrd_no("PD00000020");
//		dao.update(odtVO2);
		
//		//☁Test   delete(String pdo_no,String prd_no); 
//		System.out.println("====☁Test delete(String pdo_no,String prd_no)====");
//		dao.delete("OD171109-000001", "PD00000011");
		
//		//☁Test   findByPK(String pdo_no,String prd_no); 
//		System.out.println("====☁Test findByPK(String pdo_no,String prd_no)====");
//		Order_detailVO odtVO3 = dao.findByPK("OD171109-000001","PD00000002");
//		System.out.print(odtVO3.getPdo_no()+", ");
//		System.out.print(odtVO3.getPrd_no()+", ");
//		System.out.print(odtVO3.getOder_uni_price()+", ");
//		System.out.println(odtVO3.getOder_quantity()+", ");
//		
		//☁Test   getAll(); 
		System.out.println("====☁Test getAll()====");
		List<Order_detailVO> list1 = dao.getAll();
		for(Order_detailVO list:list1){
			System.out.print(list.getPdo_no()+", ");
			System.out.print(list.getPdo_no()+", ");
			System.out.print(list.getOder_uni_price()+", ");
			System.out.println(list.getOder_quantity()+", ");
		}
		
		//☁Test   getAllByPDO_NO(String pdo_no); 
		System.out.println("====☁Test getAllByPDO_NO(String pdo_no)====");
		List<Order_detailVO> list2 = dao.getAllByPDO_NO("OD171109-000002");
		for(Order_detailVO list:list2){
			System.out.print(list.getPdo_no()+", ");
			System.out.print(list.getPdo_no()+", ");
			System.out.print(list.getOder_uni_price()+", ");
			System.out.println(list.getOder_quantity()+", ");
		}	
		
	}//end of main

}

package com.product_order.model;

import java.util.Calendar;
import java.util.List;

public class TEST_Product_orderJDBCDAO {

	
	public static void main(String[] args){
		
		System.out.println("hello World!");
		
		Product_orderJDBCDAO dao = new Product_orderJDBCDAO();
		
		//"java.sql.Timestamp" test inputs
    	Calendar calendar = Calendar.getInstance();
    	java.util.Date now = calendar.getTime();
    	java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
    	
//    	//♥Test insert(Product_orderVO Product_orderVO)
//    	System.out.println("======♥Test insert(Product_orderVO Product_orderVO)======");  	
//    	Product_orderVO pdoVO1 = new Product_orderVO();
//    	pdoVO1.setMem_no("MB00000001");
//    	pdoVO1.setSlr_no("SL00000001");
//    	pdoVO1.setCp_no("YYYYYY");
//    	dao.insert(pdoVO1);
    	
//    	//♥Test update(Product_orderVO Product_orderVO)
//    	System.out.println("======♥Test update(Product_orderVO Product_orderVO)======");
//    	Product_orderVO pdoVO2 = new Product_orderVO();
//    	pdoVO2.setPdo_stat("cancel");
//    	pdoVO2.setPdo_no("OD171108-000002");
//    	dao.update(pdoVO2);
        
//    	//♥Test delete(String pdo_no) 
//    	System.out.println("======♥Test delete(String pdo_no) ======");
//    	dao.delete("OD171109-000005");
    	       
//    	//♥Test findByPK(String pdo_no)
//    	System.out.println("======♥Test findByPK(String pdo_no)======");
//    	Product_orderVO pdoVO3 = dao.findByPK("OD171109-000006");
//    	System.out.print(pdoVO3.getPdo_no()+", ");
//    	System.out.print(pdoVO3.getMem_no()+", ");
//    	System.out.print(pdoVO3.getSlr_no()+", ");
//    	System.out.print(pdoVO3.getPdo_stat()+", ");
//    	System.out.print(pdoVO3.getPdo_resr_date()+", ");
//    	System.out.print(pdoVO3.getSlr_rate()+", ");
//    	System.out.print(pdoVO3.getMem_rate()+", ");
//    	System.out.print(pdoVO3.getMem_review()+", ");
//    	System.out.print(pdoVO3.getMem_review()+", ");
//    	System.out.print(pdoVO3.getCp_no()+", ");
    	
//    	//♥Test getAll()
//    	System.out.println("======♥Test getAll()======");
//   	    List<Product_orderVO> list =dao.getAll();
//   	    for(Product_orderVO pdo:list){
//   	    	System.out.print(pdo.getPdo_no()+", ");
//   	    	System.out.print(pdo.getMem_no()+", ");
//   	    	System.out.print(pdo.getSlr_no()+", ");
//   	    	System.out.print(pdo.getPdo_stat()+", ");
//   	    	System.out.print(pdo.getPdo_resr_date()+", ");
//   	    	System.out.print(pdo.getSlr_rate()+", ");
//   	    	System.out.print(pdo.getMem_rate()+", ");
//   	    	System.out.print(pdo.getMem_review()+", ");
//   	    	System.out.print(pdo.getMem_review()+", ");
//   	    	System.out.println(pdo.getCp_no()+", ");
//   	    }
 
    	
//    	//♥Test getAllByMemNo()
//    	System.out.println("======♥Test getAllByMemNo()======");   	
//    	List<Product_orderVO> list =dao.getAllByMemNo("MB00000003");
//	    for(Product_orderVO pdo:list){
//	    	System.out.print(pdo.getPdo_no()+", ");
//	    	System.out.print(pdo.getMem_no()+", ");
//	    	System.out.print(pdo.getSlr_no()+", ");
//	    	System.out.print(pdo.getPdo_stat()+", ");
//	    	System.out.print(pdo.getPdo_resr_date()+", ");
//	    	System.out.print(pdo.getSlr_rate()+", ");
//	    	System.out.print(pdo.getMem_rate()+", ");
//	    	System.out.print(pdo.getMem_review()+", ");
//	    	System.out.print(pdo.getMem_review()+", ");
//	    	System.out.println(pdo.getCp_no()+", ");
//	    }
	    
	    List<Product_orderVO> list =dao.getAllBySlrNo("SL00000002");
	    for(Product_orderVO pdo:list){
	    	System.out.print(pdo.getPdo_no()+", ");
	    	System.out.print(pdo.getMem_no()+", ");
	    	System.out.print(pdo.getSlr_no()+", ");
	    	System.out.print(pdo.getPdo_stat()+", ");
	    	System.out.print(pdo.getPdo_resr_date()+", ");
	    	System.out.print(pdo.getSlr_rate()+", ");
	    	System.out.print(pdo.getMem_rate()+", ");
	    	System.out.print(pdo.getMem_review()+", ");
	    	System.out.print(pdo.getMem_review()+", ");
	    	System.out.println(pdo.getCp_no()+", ");
	    }
	    
	    
	    
	}//end main
	
}

package com.employee.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.*;

public class TEST_EmployeeJDBCDAO {
    public static void main(String[] args) throws IOException{
    	System.out.println("hello World!");
    	
    	EmployeeJDBCDAO dao = new EmployeeJDBCDAO();
    	
    	//"java.sql.Timestamp" test inputs
    	Calendar calendar = Calendar.getInstance();
    	java.util.Date now = calendar.getTime();
    	java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
    	//"byte[]" test inputs
    	byte[] testByteArray = "abc".getBytes();
    	//File f=new File("D:/BA104_WebAPP/eclipse_WTP_workSpace1/ThisIsASite/src/com/employee/model/img/poke4.JPG");   	    	
    	//byte[] fileContent = Files.readAllBytes(f.toPath());
    	
//    	//✈Test insert(EmployeeVO EmployeeVO);
//		EmployeeVO empVO1 = new EmployeeVO();
//    	empVO1.setEmp_id("imageup@forhouse.com");
//    	empVO1.setEmp_psw("###");
//    	empVO1.setEmp_name("CCC");
//    	//empVO1.setEmp_photo(fileContent);
//    	empVO1.setEmp_state("Active");    	
//    	dao.insert(empVO1);

  	
//    	System.out.println("======✈Test update(EmployeeVO EmployeeVO)======");
//   	
//    	//✈Test update(EmployeeVO EmployeeVO)
//    	EmployeeVO empVO2 = new EmployeeVO();
//    	empVO2.setEmp_no("EM00000028");
//    	empVO2.setEmp_id("byRose@forhouse.com");
//    	empVO2.setEmp_psw("rrrrrrr");
//    	empVO2.setEmp_name("RubyRose");
//    	empVO2.setEmp_photo(fileContent);
//    	empVO2.setEmp_state("Active");
//    	
//    	dao.update(empVO2);

    	
//    	System.out.println("======✈Test delete(String emp_no)======");
//    	
//    	//✈Test delete(String emp_no)
//    	dao.delete("EM00000025");

//
//    	
//		System.out.println("======✈Test findByPK(String emp_no)======");
//    	
//   	
//    	//✈Test findByPK(String emp_no)
//    	EmployeeVO empVO3 = dao.findByPK("EM00000001");
//    	System.out.print(empVO3.getEmp_no()+", "); 
//		System.out.print(empVO3.getEmp_id()+", ");
//		System.out.print(empVO3.getEmp_psw()+", ");
//		System.out.print(empVO3.getEmp_name()+", ");
//		System.out.print(empVO3.getEmp_lstlog()+", ");
//		System.out.print(empVO3.getEmp_badlog()+", ");
//		System.out.print(empVO3.getEmp_badlogtry()+", ");
//		System.out.print(empVO3.getEmp_photo()+", ");
//		System.out.print(empVO3.getEmp_state()+", ");
//		System.out.println(empVO3.getEmp_newdate()+", ");
    	
		System.out.println("======✈Test findByID(String emp_id)======");
    	
	   	
//    	//✈Test findByID(String emp_id)
//    	EmployeeVO empVO3 = dao.findByID("peter@forhouse.com");
//    	System.out.print(empVO3.getEmp_no()+", "); 
//		System.out.print(empVO3.getEmp_id()+", ");
//		System.out.print(empVO3.getEmp_psw()+", ");
//		System.out.print(empVO3.getEmp_name()+", ");
//		System.out.print(empVO3.getEmp_lstlog()+", ");
//		System.out.print(empVO3.getEmp_badlog()+", ");
//		System.out.print(empVO3.getEmp_badlogtry()+", ");
//		System.out.print(empVO3.getEmp_photo()+", ");
//		System.out.print(empVO3.getEmp_state()+", ");
//		System.out.println(empVO3.getEmp_newdate()+", ");
		
//		System.out.println("======✈Test getAll() ======");
    	
//    	
//		//✈Test getAll()  	
//		List<EmployeeVO> list = dao.getAll();
//    	for(EmployeeVO emp:list){
//    		System.out.print(emp.getEmp_no()+", "); 
//    		System.out.print(emp.getEmp_id()+", ");
//    		System.out.print(emp.getEmp_psw()+", ");
//    		System.out.print(emp.getEmp_name()+", ");
//    		System.out.print(emp.getEmp_lstlog()+", ");
//    		System.out.print(emp.getEmp_badlog()+", ");
//    		System.out.print(emp.getEmp_badlogtry()+", ");
//    		System.out.print(emp.getEmp_photo()+", ");
//    		System.out.print(emp.getEmp_state()+", ");
//    		System.out.println(emp.getEmp_newdate()+", ");
//    	}
 
		
		//✈Test getCountByID(String emp_id)
    	System.out.print(dao.getCountByID("yyy@forhouse.com")); 
		
    }
}

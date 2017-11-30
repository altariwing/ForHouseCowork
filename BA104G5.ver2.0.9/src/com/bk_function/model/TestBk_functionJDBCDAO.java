package com.bk_function.model;

import java.util.List;

public class TestBk_functionJDBCDAO {

	public static void main(String[] args) {
		System.out.println("hello world!");

		Bk_functionJDBCDAO dao = new Bk_functionJDBCDAO();
		
//		System.out.println("====❄Test insert(Bk_functionVO Bk_functionVO)====");
//		//❄Test insert(Bk_functionVO Bk_functionVO);
//		Bk_functionVO bkfVO1 = new Bk_functionVO();
//		bkfVO1.setBkf_name("管理老虎");
//		bkfVO1.setBkf_dsb("管理後台帳號、管理後台帳號權限");
//		bkfVO1.setBkf_state("Test");
//		dao.insert(bkfVO1);
//
//		System.out.println("====❄Test update(Bk_functionVO Bk_functionVO)====");
//		//❄Test update(Bk_functionVO Bk_functionVO);
//		Bk_functionVO bkfVO2 = new Bk_functionVO();
//		bkfVO2.setBkf_name("管理獅子");
//		bkfVO2.setBkf_dsb("管理後台帳號、管理後台帳號權限");
//		bkfVO2.setBkf_state("Off");
//		bkfVO2.setBkf_no(107);
//		dao.update(bkfVO2);		
		
//		System.out.println("====❄Test delete(Integer bkf_no)====");
//		//❄Test delete(Integer bkf_no);
//		dao.delete(109);
		
//		System.out.println("====❄Test findByPK(Integer bkf_no)====");
//		//❄Test findByPK(Integer bkf_no);
//		Bk_functionVO bkfVO3 = dao.findByPK(106);
//		System.out.print(bkfVO3.getBkf_no()+", ");
//		System.out.print(bkfVO3.getBkf_name()+", ");
//		System.out.print(bkfVO3.getBkf_dsb()+", ");
//		System.out.print(bkfVO3.getBkf_state()+", ");
//		System.out.println(bkfVO3.getBkf_update()+", ");
		
		System.out.println("====❄Test List<Bk_functionVO> getAll()====");
		//❄Test List<Bk_functionVO> getAll();
		List<Bk_functionVO> list = dao.getAll();
		for(Bk_functionVO bkf:list){
			System.out.print(bkf.getBkf_no()+", ");
			System.out.print(bkf.getBkf_name()+", ");
			System.out.print(bkf.getBkf_dsb()+", ");
			System.out.print(bkf.getBkf_state()+", ");
			System.out.println(bkf.getBkf_update()+", ");
		}
		
		
	}

}

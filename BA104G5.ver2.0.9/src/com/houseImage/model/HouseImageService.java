package com.houseImage.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class HouseImageService {
	private HouseImageJDBCDAO dao;

	public HouseImageService() {
		dao = new HouseImageJDBCDAO();
	}

	public void addHouseImage(String house_no,byte[] img, String state) {
		HouseImageVO houseImageVO = new HouseImageVO();
		houseImageVO.setHouse_no(house_no);
		houseImageVO.setImg(img);
		houseImageVO.setState(state);
		dao.addImg(houseImageVO);
	}

	public void updateHouseImage(String house_no, byte[] img, String state,String img_no) {
		HouseImageVO houseImageVO = new HouseImageVO();
		houseImageVO.setHouse_no(house_no);
		houseImageVO.setImg(img);
		houseImageVO.setState(state);
		houseImageVO.setImg_no(img_no);
		dao.updateImg(houseImageVO);
	}

	public void delete(String img_no) {
		dao.delete(img_no);
	}

	public HouseImageVO findByPrimaryKey(String img_no) {
		return dao.findByPrimaryKey(img_no);
	}

	public List<HouseImageVO> getAll() {
		return dao.getall();
	}
	
	public List<HouseImageVO> findByHouseNo(String house_no) {
		return dao.findByHouseNo(house_no);
	}
	

	public static void main(String[] args) {
//		 insert測試OK
		 try {
		 String house_no1 = "HSE00000006";
		 FileInputStream fis1 = new
		 FileInputStream("C:/Users/Java/Desktop/Beautiful_World/15065925683.jpg");
		 byte[] img1 = new byte[fis1.available()];
		 fis1.read(img1);
		 String state1 = "using";
		 HouseImageService srv1 = new HouseImageService();
		 srv1.addHouseImage(house_no1, img1, state1);
		 fis1.close();
		
		 String house_no2 = "HSE00000006";
		 FileInputStream fis2 = new
		 FileInputStream("C:/Users/Java/Desktop/Beautiful_World/1506592556627.jpg");
		 byte[] img2 = new byte[fis2.available()];
		 fis2.read(img2);
		 String state2 = "not_using";
		 HouseImageService srv2 = new HouseImageService();
		 srv2.addHouseImage(house_no2, img2, state2);
		 fis2.close();
		
		 String house_no3 = "HSE00000006";
		 FileInputStream fis3 = new
		 FileInputStream("C:/Users/Java/Desktop/Beautiful_World/1506592556627.jpg");
		 byte[] img3 = new byte[fis3.available()];
		 fis3.read(img3);
		 String state3 = "not_using";
		 HouseImageService srv3 = new HouseImageService();
		 srv3.addHouseImage(house_no3, img3, state3);
		 fis3.close();
		
		 } catch (FileNotFoundException e) {
		 System.out.println("上傳失敗啦!");
		 e.printStackTrace();
		 } catch (IOException e) {
		 // TODO Auto-generated catch block
		 e.printStackTrace();
		 }
		
		
		// UPDATE測試OK
//		try {
//			String house_no1 = "HSE00000024";
//			FileInputStream fis1 = new FileInputStream("C:/Users/Java/Documents/GitHub/itisagame/project_final/tzukai/imgs/bedRoom.jpg");
//			byte[] img1 = new byte[fis1.available()];
//			fis1.read(img1);
//			String state1 = "not_using";
//			String img_no1 = "IMG0000001";
//			HouseImageService srv1 = new HouseImageService();
//			srv1.updateHouseImage(house_no1, img1, state1, img_no1);
//			fis1.close();
//
//			String house_no2 = "HSE00000024";
//			FileInputStream fis2 = new FileInputStream("C:\\Users\\Java\\Documents\\GitHub\\itisagame\\project_final\\tzukai\\imgs\\house.jpg");
//			byte[] img2 = new byte[fis2.available()];
//			fis2.read(img2);
//			String state2 = "using";
//			String img_no2 = "IMG0000002";
//			HouseImageService srv2 = new HouseImageService();
//			srv2.updateHouseImage(house_no2, img2, state2, img_no2);
//			fis2.close();
//
//			String house_no3 = "HSE00000024";
//			FileInputStream fis3 = new FileInputStream("C:\\Users\\Java\\Documents\\GitHub\\itisagame\\project_final\\tzukai\\imgs\\kitchen.jpg");
//			byte[] img3 = new byte[fis3.available()];
//			fis3.read(img3);
//			String state3 = "using";
//			String img_no3 = "IMG0000003";
//			HouseImageService srv3 = new HouseImageService();
//			srv3.updateHouseImage(house_no3, img3, state3, img_no3);
//			fis3.close();
//
//		} catch (FileNotFoundException e) {
//			System.out.println("上傳失敗啦!");
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//		刪除測試ok
//		HouseImageService svc = new HouseImageService();
//		String img_no = "IMG0000004";
//		svc.delete(img_no);
//		img_no = "IMG0000005";
//		svc.delete(img_no);
		
//		HouseImageService svc = new HouseImageService();
//		HouseImageVO VO=svc.findByPrimaryKey("IMG0000001");
//		System.out.println(VO.getImg_no());
//		System.out.println(VO.getHouse_no());
//		System.out.println(VO.getState());
//		System.out.println(VO.getInsert_time());
	
//		GETALL  OK!
//		HouseImageService svc = new HouseImageService();
//		List<HouseImageVO> lista = svc.getAll();
//		for(HouseImageVO VO : lista){
//			System.out.println(VO.getImg_no());
//			System.out.println(VO.getHouse_no());
//			System.out.println(VO.getState());
//			System.out.println(VO.getInsert_time());
//		}
	}

}

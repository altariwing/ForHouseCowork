package com.advertisement.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdService {
	private AdJDBCDAO dao;

	public AdService() {
		dao = new AdJDBCDAO();
	}

	public void applyAd(String prd_no, String emp_no, String ad_type, byte[] ad_img) {
		AdVO adVO = new AdVO();
		adVO.setPrd_no(prd_no);
		adVO.setEmp_no(emp_no);
		adVO.setAd_type(ad_type);
		adVO.setAd_img(ad_img);
		dao.insert(adVO);
	}

	public void updateAd(String prd_no, String emp_no, String ad_type, byte[] ad_img, Timestamp expiration_date,
			String ad_no) {
		AdVO adVO = new AdVO();
		adVO.setPrd_no(prd_no);
		adVO.setEmp_no(emp_no);
		adVO.setAd_type(ad_type);
		adVO.setAd_img(ad_img);
		adVO.setExpiration_date(expiration_date);
		adVO.setAd_no(ad_no);
		dao.update(adVO);
	}

	public void deleteAd(String ad_no) {
		dao.delete(ad_no);
	}

	public AdVO findOneByPrimaryKey(String ad_no) {
		return dao.findByPrimaryKey(ad_no);
	}

	public List<AdVO> getAll() {
		return dao.getAll();
	}

	public static void main(String[] args) {
		AdService svc = new AdService();

		// 新增成功ok
		// try {
		// AdService svc = new AdService();
		// String prd_no = "prd0001";
		// String emp_no = "emp0001";
		// String ad_type="main_block";
		// FileInputStream fis;
		// fis = new
		// FileInputStream("C:/Users/Java/Desktop/Beautiful_World/15065925683.jpg");
		// byte[] ad_img = new byte[fis.available()];
		// fis.read(ad_img);
		// svc.applyAd(prd_no, emp_no, ad_type, ad_img);
		// fis.close();
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// update OK!
		// try {
		// AdService svc = new AdService();
		// String prd_no = "prd0001";
		// String emp_no = "emp0001";
		// String ad_type = "main_block";
		// FileInputStream fis;
		// fis = new
		// FileInputStream("C:/Users/Java/Desktop/Beautiful_World/BEAUTY/153_170522103910_3_lit.jpg");
		// byte[] ad_img = new byte[fis.available()];
		// fis.read(ad_img);
		//
		// long currentTime = System.currentTimeMillis();
		//
		// long threeMonth = (long)1000*60 * 60 * 24 * 30 * 3;
		//
		// long expiration_time = currentTime + threeMonth;
		//
		// Timestamp expiration_date = new Timestamp(expiration_time);
		//
		// // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd
		// // HH:mm:ss");
		// // String time = df.format(new Date());
		// // Timestamp ts = Timestamp.valueOf(time);
		//
		// String ad_no = "AD00000001";
		// svc.updateAd(prd_no, emp_no, ad_type, ad_img, expiration_date,
		// ad_no);
		// fis.close();
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// 單一OK
		// AdVO adVO = svc.findOneByPrimaryKey("AD00000001");
		// System.out.println(adVO.getAd_no());
		// System.out.println(adVO.getEmp_no());
		// System.out.println(adVO.getPrd_no());
		// System.out.println(adVO.getAd_type());
		// System.out.println(adVO.getAppling_date());
		// System.out.println(adVO.getAudit_date());
		// System.out.println(adVO.getExpiration_date());

		
		
//		全部OK
//		List<AdVO> list = svc.getAll();
//		for (AdVO adVO : list) {
//			System.out.println(adVO.getAd_no());
//			System.out.println(adVO.getEmp_no());
//			System.out.println(adVO.getPrd_no());
//			System.out.println(adVO.getAd_type());
//			System.out.println(adVO.getAppling_date());
//			System.out.println(adVO.getAudit_date());
//			System.out.println(adVO.getExpiration_date());
//		}
	}

}

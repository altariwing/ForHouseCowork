package com.tool.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class InsertImgData {
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "BA104G5";
	private static final String PASSWORD = "ba104g5";
	private static final String IMG = "INSERT INTO PRDIMG(IMG_NO, PRD_NO, PRD_IMG)" + "VALUES(PRDIMG_SEQ.NEXTVAL, ?, ?)";
	private static final String DESC = "UPDATE PRODUCT SET PRD_DESC=? WHERE PRD_NO=?";
	private static final String MEMIMG = "UPDATE MEMBER SET MEM_IMG = ? WHERE MEM_NO = ?";
	private static final String REALTORIMG = "UPDATE REALTOR SET RTR_PHOTO = ? WHERE RTR_NO = ?";
	private static final String RTRINTRO = "UPDATE REALTOR SET RTR_INTRO = ? WHERE RTR_NO = ?";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;  //會員 member
		PreparedStatement pstmt4 = null;  //房仲 realtor rtr_photo
		PreparedStatement pstmt5 = null;  //房仲 realtor rtr_intro

		// 將 ForHouseCowork 的  product 資料夾放到 D: 下即可執行本程式
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);

			
			// 商品圖片部分
			pstmt = con.prepareStatement(IMG);
			int total = 0;
			for(int prd_no=1 ; prd_no<37 ; prd_no++){
				int pcs = 0 ;
				for(int i=1 ; i<8 ; i++) {
					if (new File("D:/product/img/" + prd_no + i + ".jpg").exists()) {
						byte[] pic = getPictureByteArray("D:/product/img/" + prd_no + i + ".jpg");
						if(prd_no<10){
							pstmt.setString(1, "PD0000000" + prd_no);
						} else {
							pstmt.setString(1, "PD000000" + prd_no);
						}
						pstmt.setBytes(2, pic);
						pstmt.executeUpdate();
						pcs++;
					}
				}
				total = total + pcs;
				System.out.println("prd_no:" + prd_no +" 新增 "+ pcs + " 張成功!");
			}
			System.out.println("商品共新增 " + total + " 張圖片!");
			
			
						
			// 商品詳情部分
			pstmt2 = con.prepareStatement(DESC);
			int i = 1;
			for(i=1 ; i<34 ; i++){
					if (new File("D:/product/info/" + i + ".txt").exists()) {
						String str = getLongString("D:/product/info/" + i + ".txt");
						pstmt2.setString(1, str);
						if(i<10){
							pstmt2.setString(2, "PD0000000" + i);
						} else {
							pstmt2.setString(2, "PD000000" + i);
						}
						pstmt2.executeUpdate();
					}
			}
			System.out.println("商品詳情共新增 " + i + " 筆資料!");
			
			
			// 會員圖片部分
			pstmt3 = con.prepareStatement(MEMIMG);
			String mem_no = "MB0000000";
			int m=0;
			for (m=1 ; m<=4 ; m++) {
				if (new File("D:/product/member/" + mem_no + m + ".jpg").exists()) {
					byte[] pic = getPictureByteArray("D:/product/member/" + mem_no + m + ".jpg");
					pstmt3.setBytes(1,pic);
					pstmt3.setString(2, mem_no+m);
					pstmt3.executeUpdate();
				}
			}
			System.out.println("會員照片共新增 " + (m-1) + " 張!");
			
			
			// 房仲照片部分
			pstmt4 = con.prepareStatement(REALTORIMG);
			String rtr_no = "RT000000";
			int r=0;
			for (r=1 ; r<=12 ; r++) {
				if (new File("D:/product/realtor/" + r + ".jpg").exists()) {
					byte[] pic = getPictureByteArray("D:/product/realtor/" + r + ".jpg");
					pstmt4.setBytes(1,pic);
					if(r>9) {
						pstmt4.setString(2, rtr_no + r);
					} else {
						pstmt4.setString(2, rtr_no + "0" + r);
					}
					pstmt4.executeUpdate();
				}
			}
			System.out.println("房仲照片共新增 " + (r-1) + " 張!");
			
			
			// 房仲intro部分
			pstmt5 = con.prepareStatement(RTRINTRO);
			
			int j=0;
			for (j=1 ; j<=12 ; j++) {
				if (new File("D:/product/realtor_txt/realtor" + j + ".txt").exists()) {
					String intro = getLongString("D:/product/realtor_txt/realtor" + j + ".txt");
					pstmt5.setString(1,intro);
					if(j>9) {
						pstmt5.setString(2, rtr_no + j);
					} else {
						pstmt5.setString(2, rtr_no + "0" + j);
					}
					pstmt5.executeUpdate();
				}
			}
			System.out.println("房仲intro共新增 " + (j-1) + " 筆!");
			

			
			
		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
			if (pstmt4 != null) {
				try {
					pstmt4.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
			
			if (pstmt3 != null) {
				try {
					pstmt3.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
			
			if (pstmt2 != null) {
				try {
					pstmt2.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
			
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
		
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		File file = new File(path);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = fis.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		fis.close();
		return baos.toByteArray();
	}
	
	public static String getLongString(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		StringBuilder sb = new StringBuilder(); // StringBuffer is thread-safe!
		String str;
		while ((str = br.readLine()) != null) {
			sb.append(str);
		}
		br.close();
		return sb.toString();
	}

}

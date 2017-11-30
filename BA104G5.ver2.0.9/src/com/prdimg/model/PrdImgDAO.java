package com.prdimg.model;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.Part;
import javax.sql.DataSource;

public class PrdImgDAO implements PrdImgDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/BA104G5");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT = "INSERT INTO PRDIMG (img_no,prd_no,prd_img) VALUES(PRDIMG_SEQ.NEXTVAL,?,?)";
	private static final String DELETE = "DELETE FROM PRDIMG WHERE img_no=?";
	private static final String UPDATE = "UPDATE PRDIMG SET prd_img=? WHERE img_no=?";
	private static final String FIND_BY_IMGNO = "SELECT * FROM PRDIMG WHERE img_no=?";
	private static final String FIND_BY_PRDNO = "SELECT * FROM PRDIMG WHERE prd_no=?";
	private static final String GET_ALL = "SELECT * FROM PRDIMG";	
	
	@Override
	public void insert(List<byte[]> imgList, String keyPrdNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			
			for(byte[] prdImgVO : imgList) {
				pstmt = con.prepareStatement(INSERT);
				
				pstmt.setString(1 ,keyPrdNo);
				pstmt.setBytes(2, prdImgVO);
				pstmt.executeUpdate();
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void delete(String img_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1 ,img_no);
			
			pstmt.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void update(PrdImgVO prdImgVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setBytes(1, prdImgVO.getPrd_img());
			pstmt.setInt(2 ,prdImgVO.getImg_no());
			
			pstmt.executeUpdate();			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	@Override
	public PrdImgVO findByImgNo(Integer img_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PrdImgVO prdImgVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_IMGNO);
			
			pstmt.setInt(1 ,img_no);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				prdImgVO = new PrdImgVO();
				prdImgVO.setImg_no(rs.getInt("img_no"));
				prdImgVO.setPrd_no(rs.getString("prd_no"));
				prdImgVO.setPrd_img(rs.getBytes("prd_img"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return prdImgVO;
	}
	
	@Override
	public List<PrdImgVO> findByPrdNo(String prd_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<PrdImgVO> list = new ArrayList<PrdImgVO>();
		PrdImgVO prdImgVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_BY_PRDNO);
			
			pstmt.setString(1 ,prd_no);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				prdImgVO = new PrdImgVO();
				prdImgVO.setImg_no(rs.getInt("img_no"));
				prdImgVO.setPrd_no(rs.getString("prd_no"));
				prdImgVO.setPrd_img(rs.getBytes("prd_img"));
				list.add(prdImgVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	@Override
	public List<PrdImgVO> getAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<PrdImgVO> list = new ArrayList<PrdImgVO>();
		PrdImgVO prdImgVO = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL);
			
			ResultSet rs = null;
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				prdImgVO = new PrdImgVO();
				prdImgVO.setImg_no(rs.getInt("img_no"));
				prdImgVO.setPrd_no(rs.getString("prd_no"));
				prdImgVO.setPrd_img(rs.getBytes("prd_img"));
				list.add(prdImgVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	// 取出上傳的檔案名稱 (因為API未提供method,所以必須自行撰寫)
			public String getFileNameFromPart(Part part) {
				String header = part.getHeader("content-disposition");
//				System.out.println("header=" + header); // 測試用
				String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName(); 
//				System.out.println("filename=" + filename); // 測試用
				if (filename.length() == 0) {
					return null;
				}
				return filename;
			}
	
}

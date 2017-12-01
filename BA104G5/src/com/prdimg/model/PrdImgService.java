package com.prdimg.model;

import java.util.List;


public class PrdImgService {
	private PrdImgDAO_interface dao;
	public PrdImgService() {
		dao = new PrdImgDAO();
	}
	
	public void addPrdImg(List<byte[]> imgList, String keyPrdNo) {
		dao.insert(imgList, keyPrdNo);
	}
	
	public void delImg(String img_no) {
		dao.delete(img_no);
	}
	
	public PrdImgVO updateImg(String prd_no, byte[] prd_img) {
		PrdImgVO prdImgVO = new PrdImgVO();
		
		prdImgVO.setPrd_no(prd_no);
		prdImgVO.setPrd_img(prd_img);
		dao.update(prdImgVO);
		
		return prdImgVO;
	}
		
	public PrdImgVO findByImgNo(Integer img_no) {
		return dao.findByImgNo(img_no);
	}
	
	public PrdImgVO findFirstByPrdNo(String prd_no) {
		return dao.findByPrdNo(prd_no).get(0);
	}
	
	public List<PrdImgVO> findByPrdNo(String prd_no) {
		return dao.findByPrdNo(prd_no);
	}
	
	public List<PrdImgVO> getAll() {
		return dao.getAll();
	}
}

package com.prdimg.model;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.Part;

public interface PrdImgDAO_interface {
	public void insert(List<byte[]> imgList, String keyPrdNo);
	public void delete(String img_no);
	public void update(PrdImgVO prdImgVO);
	public PrdImgVO findByImgNo(Integer img_no);
	public List<PrdImgVO> findByPrdNo(String prd_no);
	public List<PrdImgVO> getAll();
}

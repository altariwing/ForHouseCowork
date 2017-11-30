package com.ann.model;

import java.util.List;

public class AnnService {

	private AnnDAO_interface dao;

	public AnnService() {
		dao = new AnnDAO();
	}

	public AnnVO add(String ann_title, String ann_content, String ann_state, String emp_no) {

		AnnVO annVO = new AnnVO();
		annVO.setAnn_title(ann_title);
		annVO.setAnn_content(ann_content);
		annVO.setAnn_state(ann_state);
		annVO.setEmp_no(emp_no);
		dao.insert(annVO);
		return annVO;
	}

	public AnnVO update(Integer ann_no, String ann_title, String ann_content, String ann_state, String emp_no) {

		AnnVO annVO = new AnnVO();
		annVO.setAnn_title(ann_title);
		annVO.setAnn_content(ann_content);
		annVO.setAnn_state(ann_state);
		annVO.setEmp_no(emp_no);
		annVO.setAnn_no(ann_no);
		dao.update(annVO);
		return annVO;
	}

	public void delete(Integer ann_no) {
		dao.delete(ann_no);
	}

	public AnnVO getOne(Integer ann_no) {
		return dao.findByPrimaryKey(ann_no);
	}

	public List<AnnVO> getAll() {
		return dao.getAll();
	}
}

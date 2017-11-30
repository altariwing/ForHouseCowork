package com.product_order.model;

import java.util.List;

/**
 * Product_orderDAO_interface
 * insert
 * update
 * delete
 * findByPK
 * getAll
 * getAllByMemNo
 * getAllBySlrNo
 * getMemAvgRate
 * getSlrAvgRate 
 */
public interface Product_orderDAO_interface {
	
    /**
     * insert a row
     */
    public void insert(Product_orderVO Product_orderVO);
    
    /**
     * update that pdo_no's row
     */
    public void update(Product_orderVO Product_orderVO);
      
    /**
     * delete that pdo_no's row
     */
    public void delete(String pdo_no);
    
    /**
     * select a row from table by pdo_no
     * @return Product_orderVO object
     */
    public Product_orderVO findByPK(String pdo_no);
    
    /**
     * select all from table
     * @return Product_orderVO object
     */
    public List<Product_orderVO> getAll();
    
    /**
     * select all from table which Mem_Rate is NOT NULL
     * @return List<Product_orderVO object>
     * @param mem_no
     */
    public List<Product_orderVO> getAllByMemNo(String mem_no);
    
    /**
     * select all from table which Slr_Rate is NOT NULL
     * @return List<Product_orderVO object>
     * @param slr_no
     */
    public List<Product_orderVO> getAllBySlrNo(String slr_no);
    
    /**
     * get average rate of that member
     * @return Double
     * @param mem_no
     */
    public Double getMemAvgRate(String mem_no);
    
    /**
     * select all from table which slr_no is NOT NULL
     * @return Double
     * @param slr_no
     */
    public Double getSlrAvgRate (String slr_no);
}

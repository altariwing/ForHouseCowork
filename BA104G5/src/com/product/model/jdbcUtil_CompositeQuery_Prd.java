
/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

package com.product.model;

import java.util.*;

public class jdbcUtil_CompositeQuery_Prd {

	/**
	 * 產生單一限制字串
	 * */
	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;

		StringTokenizer tokenizer = null;
		int tokens = 0;
		if ("slr_no".equals(columnName) || "cate_no".equals(columnName)) {
			// 用於產品分類搜尋(varchar)
			aCondition = columnName + "='" + value + "'";
		} else if ("prd_name".equals(columnName)) {
			// 產品名稱搜尋toUpperCase(varchar)
			aCondition = "upper(" + columnName + ")" + " like '%" + value.toUpperCase() + "%'";
		} else if ("price_range".equals(columnName)) {
			// 用於搜尋價格區間 price_range(varchar)
			tokenizer = new StringTokenizer(value);
			tokens = tokenizer.countTokens();
			if (tokens == 1) {
				aCondition = "prd_price BETWEEN " + tokenizer.nextToken() + " AND 99999999";
			} else if (tokens == 2) {
				aCondition = "prd_price BETWEEN " + tokenizer.nextToken() + " AND " + tokenizer.nextToken();
			}
		}
		return aCondition + " "; 
	}
	
	/**
	 * 產生單一限制字串 (checkbox方法)
	 * */
	public static String get_aCondition_For_Oracle(String columnName, String[] value){
		String aCondition = "";
		if ("slr_no".equals(columnName) || "cate_no".equals(columnName)) {						
			aCondition += columnName + "='" + value[0] + "'";
			for(int i =1; i<value.length; i++){
				aCondition += " or "+ columnName + "='" + value[i] + "'";
			}					
		}
			
		return "(" + aCondition + ") "; 
	};	

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			int valueLength = map.get(key).length;
			String value = map.get(key)[0];
			String[] valueAraay = map.get(key);
			if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
				count++;
				
				String aCondition ="";
				if(valueLength>1){
					aCondition = get_aCondition_For_Oracle(key, valueAraay); //假如是checkbox
				}else{
					aCondition = get_aCondition_For_Oracle(key, value.trim()); //假如是其它
				}				

				
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
			
			
		}

		return whereCondition.toString();
	}

	//測試用main方法
	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳
		// java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("prd_name", new String[] { "k" });
		map.put("cate_no", new String[] { "PC00000002" });
		map.put("slr_no", new String[] { "SL00000001","SL00000002" ,"SL00000003"});
		map.put("price_range", new String[] { "5000 50000" });
		map.put("action", new String[] { "listPrd_ByCompositeQuery" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from PRODUCT " + jdbcUtil_CompositeQuery_Prd.get_WhereCondition(map)
				+ "order by PRD_NO";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}

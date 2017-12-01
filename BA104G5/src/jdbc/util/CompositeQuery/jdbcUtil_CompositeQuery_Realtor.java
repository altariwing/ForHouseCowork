/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *        所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

package jdbc.util.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Realtor {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;
		// 用於varchar
		if ("RTR_NO".equals(columnName) || "RTR_ID".equals(columnName) || "RTR_NAME".equals(columnName)
				|| "RTR_AREA".equals(columnName) || "RE_NO".equals(columnName) || "RTR_INTRO".equals(columnName))
			aCondition = columnName + " like '%" + value + "%'";

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_Oracle(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		} 
		
		return whereCondition.toString();
	}
 
	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳
		// java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("RTR_ID", new String[] { "realtor001@gmail.com" });
		map.put("RTR_NAME", new String[] { "葦小寶" });
		map.put("RTR_AREA", new String[] { "北投區" });
		map.put("RE_NO", new String[] { "RE00000001" });
		map.put("RTR_INTRO", new String[] { "g" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "SELECT * FROM REALTOR " + jdbcUtil_CompositeQuery_Realtor.get_WhereCondition(map)
				+ "ORDER BY RTR_NO";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}

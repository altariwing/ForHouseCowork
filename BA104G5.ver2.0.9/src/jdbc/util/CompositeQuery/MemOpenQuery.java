package jdbc.util.CompositeQuery;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MemOpenQuery {

	public static String get_aCondition_For_Oracle(String columnName, String value) {

		String aCondition = null;
		// 用於varchar
		if ("MEM_NO".equals(columnName) || "MEM_NAME".equals(columnName) || "MEM_ADDR".equals(columnName)
				|| "SEARCH_STATE".equals(columnName))
			aCondition = columnName + " like '%" + value + "%'";

		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0 && !"action".equals(key)) {
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
		map.put("MEM_NO", new String[] { "" });
		map.put("MEM_ID", new String[] { "" });
		map.put("MEM_NAME", new String[] { "" });
		map.put("MEM_ADDR", new String[] { "萬里區" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "SELECT * FROM MEMBER " + MemOpenQuery.get_WhereCondition(map) + "ORDER BY MEM_NO";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}
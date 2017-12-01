package com.house.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class HouseCompositeQuery {

	public static String get_aCondition_For_Oracle(String columnName, String value) {
		String aCondition = null;
		System.out.println("欄位是" + columnName);
		System.out.println("值是" + value);
		if ("location".equals(columnName) || "house_Type".equals(columnName)) {
			aCondition = columnName + " like '%" + value + "%'";
		} else if ("price".equals(columnName) || "total_Pings".equals(columnName)) {
			String[] result = value.split("_");
			Integer str = Integer.valueOf(result[0]);
			Integer end = Integer.valueOf(result[1]);

			if (end == 9999) {
				aCondition = columnName + " > " + str;
			} else {
				aCondition = columnName + " BETWEEN " + str + " AND " + end;
			}
		} else if ("pattern".equals(columnName)) {

			if (value.equals("99")) {
				aCondition = columnName
						+ " NOT LIKE '%1房%' AND PATTERN NOT LIKE '%2房%' AND PATTERN NOT LIKE '%3房%' AND PATTERN NOT LIKE '%4房%'";
			} else {
				aCondition = columnName + " like '%" + value + "房%'";
			}
		}
		return aCondition + " ";
	}

	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String[] values = map.get(key);
			if (count != 0) {
				whereCondition.append("and");
			}
			for (int i = 0; i < values.length; i++) {
				if (values[i] != null && values[i].trim().length() != 0 && !"action".equals(key)) {
					count++;
					String aCondition = get_aCondition_For_Oracle(key, values[i].trim());
					if (count == 1)
						whereCondition.append(" where ");
					if (i == 0) {
						whereCondition.append("(" + aCondition);
					} else {
						whereCondition.append("or " + aCondition);
					}
					if (i == values.length - 1) {
						whereCondition.append(")");
					}
				}
				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}

		return whereCondition.toString();
	}

	public static String getKeyWordSQL(String keyword) {
		String[] conditions = null;
		String[] conditions2 = null;
		List<String> conditionList = new ArrayList<String>();
		StringBuffer whereCondition = new StringBuffer();

		if (keyword.contains(" ") || keyword.contains("+")) {

			conditions = keyword.split(" ");

			for (int i = 0; i < conditions.length; i++) {

				if (conditions[i].contains("+")) {

					conditions2 = conditions[i].split("\\+");

					for (int j = 0; j < conditions2.length; j++) {

						conditionList.add(conditions2[j]);
					}
				} else {
					conditionList.add(conditions[i]);
				}
			}
		} else {
			conditionList.add(keyword);
		}

		for (int i = 0; i < conditionList.size(); i++) {
			if (i == 0) {
				whereCondition.append(" where ");
			}
			whereCondition.append("(");

			whereCondition.append(get_aWord_For_Oracle(conditionList.get(i)));

			whereCondition.append(")");

			if (i != conditionList.size() - 1) {
				whereCondition.append(" and ");
			}
		}
		return whereCondition.toString();
	}

	public static String get_aWord_For_Oracle(String condition) {
		String wordForOracle = null;
		if (condition.contains("區")) {
			condition = condition.trim();
			wordForOracle = " location like '%" + condition + "%' ";

		} else if (condition.contains("樓")) {
			condition = condition.replaceAll("樓", "").trim();
			wordForOracle = " floor like '%" + condition + "%' ";

		} else if (condition.matches("[0-9.。]{1,7}(年)")) {
			condition = condition.replaceAll("年", "").trim();
			Double ageNum = Double.valueOf(condition);
			Double lowestAge = 0.0;
			Double highestAge = 0.0;
			if (ageNum > 5) {
				lowestAge = ageNum - 5;
			}
			highestAge = lowestAge + 10;
			wordForOracle = " age between " + lowestAge + " AND " + highestAge + " ";

		} else if (condition.matches("[0-9.。]{1,7}坪")) {
			condition = condition.replaceAll("坪", "").trim();
			Double pingsNum = Double.valueOf(condition);
			System.out.println(pingsNum);
			Double lowestPings = 0.0;
			Double highestPings = 0.0;

			if (pingsNum > 5) {
				lowestPings = pingsNum - 5;
			}
			highestPings = lowestPings + 10;
			wordForOracle = " total_pings between " + lowestPings + " AND " + highestPings + " ";
		} else if (condition.matches("[0-9.。]{1,7}(萬|億|千)")) {
			condition = condition.replaceAll("萬", "").trim();
			Double price = Double.valueOf(condition);
			Double lowestPrice = 0.0;
			Double highestPrice = 0.0;

			if (price > 500) {
				lowestPrice = price - 500;
			}
			highestPrice = lowestPrice + 1000;
			wordForOracle = "  price between " + lowestPrice + " AND " + highestPrice + " ";
		} else {
			condition = condition.trim();
			wordForOracle = " re_no like '%" + condition + "%' or house_serial_number like '%" + condition
					+ "%' or title like '%" + condition + "%' or location like '%" + condition
					+ "%' or house_type like '%" + condition + "%' or price like '%" + condition
					+ "%' or total_pings like'%" + condition + "%' or floor like '%" + condition + "%' or age like '%"
					+ condition + "%' or pattern like '%" + condition + "%' or orientation like '%" + condition
					+ "%' or building_materials like '%" + condition + "%' or parking_space like '%" + condition
					+ "%' or classification_of_land like '%" + condition + "%' or land_pings like '%" + condition
					+ "%' or data_info like '%" + condition + "%' ";
		}
		return wordForOracle;
	}

	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳
		// java.util.Map<java.lang.String,java.lang.String[]> 之測試
		// Map<String, String[]> map = new TreeMap<String, String[]>();
		// map.put("location", new String[] { "中山區", "文山區" });
		// map.put("house_Type", new String[] { "公寓", "大樓", "電梯" });
		// map.put("pattern", new String[] { "1", "99" });
		// map.put("price", new String[] { "0_1000", "5000_9999" });
		// map.put("total_Pings", new String[] { "11_20", "51_9999" });

		// 注意Map裡面會含有action的key

		String finalSQL = "select * from houseinfo " + getKeyWordSQL("永慶+3樓");
		System.out.println(finalSQL);

	}

}

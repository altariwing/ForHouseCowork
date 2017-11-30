package jdbc.util.CompositeQuery;

import java.util.ArrayList;
import java.util.List;

public class RealtorFindByKeyWord {

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
			whereCondition.append(getAWordForOracle(conditionList.get(i)));
			whereCondition.append(")");

			if (i != conditionList.size() - 1) {
				whereCondition.append(" and ");
			}
		}
		return whereCondition.toString();
	}

	private static Object getAWordForOracle(String condition) {
		String wordForOracle = null;
		if (condition.contains(" 區 ")) {
			wordForOracle = " RTR_AREA like '%" + condition + "%'";
		} else if (condition.contains(" 信 ") || condition.contains(" 永 ") || condition.contains(" 東 ")) {
			condition = condition.replaceAll("", "").trim();
			wordForOracle = " RTR_INTRO like '%" + condition + "%'";
		} else if (condition.contains(" 永慶 ") || condition.contains(" 東森 ") || condition.contains(" 信義 ")) {
			condition = condition.replaceAll("", "").trim();
			wordForOracle = " RTR_AREA like '%" + condition + "%'";

		} else {
			condition = condition.trim();
			wordForOracle = "RTR_NAME like '%" + condition + "%' or RTR_AREA like '%" + condition
					+ "%' or RTR_INTRO like '%" + condition + "%' ";
		}
		return wordForOracle;
	}

	public static void main(String[] args) {

		String finalSQL = "select * from Realtor " + RealtorFindByKeyWord.getKeyWordSQL("信") + "ORDER BY RTR_NO";
		System.out.println(finalSQL);

	}
}

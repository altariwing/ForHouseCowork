package com.tool.controller;

import java.util.regex.Pattern;

public class RealtorIDRex {
	
	public static final Pattern TWPID_PATTERN = Pattern.compile("[ABCDEFGHJKLMNPQRSTUVXYWZIO][12]\\d{8}");

	public static boolean isValidTWPID(String twpid) {
		boolean result = false;
		String pattern = "ABCDEFGHJKLMNPQRSTUVXYWZIO";
		if (TWPID_PATTERN.matcher(twpid.toUpperCase()).matches()) {
			int code = pattern.indexOf(twpid.toUpperCase().charAt(0)) + 10;
			int sum = 0;
			sum = (int) (code / 10) + 9 * (code % 10) + 8 * (twpid.charAt(1) - '0') + 7 * (twpid.charAt(2) - '0')
					+ 6 * (twpid.charAt(3) - '0') + 5 * (twpid.charAt(4) - '0') + 4 * (twpid.charAt(5) - '0')
					+ 3 * (twpid.charAt(6) - '0') + 2 * (twpid.charAt(7) - '0') + 1 * (twpid.charAt(8) - '0')
					+ (twpid.charAt(9) - '0');
			if ((sum % 10) == 0) {
				result = true;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		String id = " e124217388";
		System.out.println(RealtorIDRex.isValidTWPID(id.trim()));
	}

}

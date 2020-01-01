package com.sqlite.utils;

public class EPCStringUtils {

	public EPCStringUtils() {
		super();
	}

	public static boolean isHeaderChange(String epcA, String epcB) {
		
		int headerWidth = 15;
		return getHeaderString(epcA, headerWidth).equalsIgnoreCase(getHeaderString(epcB, headerWidth));
	}

	public static String getHeaderString(String epcA, int headerWidth) {
		return epcA.substring(0, headerWidth);
	}

}
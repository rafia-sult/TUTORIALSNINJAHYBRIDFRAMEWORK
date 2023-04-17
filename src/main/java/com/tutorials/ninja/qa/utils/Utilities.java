package com.tutorials.ninja.qa.utils;

import java.util.Date;

public class Utilities {

	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "johnsmith" + timeStamp + "@gmail.com";
	}

	public static String generatePasswordWithTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return timeStamp;
	}

	public static String generateNameforEmailWithTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_").substring(8, 19).replace("_", "");
		;
		return "johnsmith" + timeStamp;

	}

	public static final int implicitWaitTime = 10;
	public static final int pageLoadTime = 10;
	public static final int scriptTime = 2000;

}

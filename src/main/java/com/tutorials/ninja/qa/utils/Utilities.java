package com.tutorials.ninja.qa.utils;

import java.security.SecureRandom;
import java.util.Date;

public class Utilities {

	private static final String CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+|";
	private static final int DEFAULT_LENGTH = 14;
	private static final String char_pool = "0123456789";
	private static final int DEFAULT_PHONE_LENGTH = 11;

	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "selenium" + timeStamp + "@gmail.com";
	}

	public static String generateNameforEmailWithTimeStamp() {
		Date date = new Date();
		String timeStamp = date.toString().replace(" ", "_").replace(":", "_").substring(8, 19).replace("_", "");
		;
		return "selenium" + timeStamp;

	}

	public static String generatePassword(int length) {
		if (length < 2) {
			length = DEFAULT_LENGTH;

		}

		SecureRandom random = new SecureRandom();
		StringBuilder password = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(CHAR_POOL.length());
			password.append(CHAR_POOL.charAt(index));
		}
		return password.toString();
	}

	public static String generatePhoneNumber(int length) {
		if (length < 2) {
			length = DEFAULT_PHONE_LENGTH;

		}

		SecureRandom random = new SecureRandom();
		StringBuilder password = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(char_pool.length());
			password.append(char_pool.charAt(index));
		}
		return password.toString();
	}

	public static final int implicitWaitTime = 10;
	public static final int pageLoadTime = 10;
	public static final int scriptTime = 2000;



}

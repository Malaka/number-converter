package com.aconex.code.challenge.util;

import java.io.InputStream;

/**
 * Commons utils
 *
 * @author: malaka
 * Date: 5/12/17
 * Time: 2:49 AM
 */
public class CommonsUtil {

	private static final String EMPTY_STRING = "";

	private static final String REGEX_DIGITS_ONLY = "\\D+";
	private static final String REGEX_ALPHA_ONLY = "[^a-zA-Z]";

	private static final String REGEX_ALPHA_ONLY_VALIDATION = "^[a-zA-Z]+$";
	private static final String REGEX_DIGITS_ONLY_VALIDATION = "^[0-9]+$";

	public static String filterDigitOnly(String string) {

		if (string == null) {
			return null;
		}
		return string.trim().replaceAll(REGEX_DIGITS_ONLY, EMPTY_STRING);
	}

	public static String filterAlphaOnly(String string) {

		if (string == null) {
			return null;
		}
		return string.trim().replaceAll(REGEX_ALPHA_ONLY, EMPTY_STRING);
	}

	public static boolean isDigit(String string) {
		return string != null && string.matches(REGEX_DIGITS_ONLY_VALIDATION);
	}


	public static InputStream getInputStream(String fileName) {
		return CommonsUtil.class.getClassLoader().getResourceAsStream(fileName);
	}
}

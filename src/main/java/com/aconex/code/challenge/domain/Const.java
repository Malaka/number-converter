package com.aconex.code.challenge.domain;

/**
 * Domain constants of the project
 *
 * @author: malaka
 * Date: 5/12/17
 * Time: 2:26 AM
 */
public final class Const {

	public static final String INTERNAL_DICTIONARY_RESOURCE = "dictionary.txt";
	public static final String NUMBER_ENCODING_RESOURCE = "char_encodings.properties";
	public static final String SEPARATOR = "-";

	// Supported dictionary input date types
	public enum DictionaryType {
		FILE, INTERNAL
	}

	// Supported Telephone data input types
	public enum TelDataType {
		FILE, STDIN
	}

}

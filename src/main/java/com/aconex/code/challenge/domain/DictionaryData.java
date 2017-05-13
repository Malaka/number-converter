package com.aconex.code.challenge.domain;

import com.aconex.code.challenge.domain.Const.DictionaryType;

/**
 * @author: malaka
 * Date: 5/11/17
 * Time: 12:37 PM
 */
public class DictionaryData {

	private DictionaryType dictionaryType;

	private String dataPath;

	public static DictionaryData file(String dataPath) {
		return new DictionaryData(DictionaryType.FILE, dataPath);
	}

	public static DictionaryData internal() {
		return new DictionaryData(DictionaryType.INTERNAL, "");
	}

	private DictionaryData(DictionaryType dictionaryType, String dataPath) {
		this.dictionaryType = dictionaryType;
		this.dataPath = dataPath;
	}

	public DictionaryType getDictionaryType() {
		return dictionaryType;
	}

	public String getDataPath() {
		return dataPath;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("DictionaryData{");
		sb.append("dictionaryType=").append(dictionaryType);
		sb.append(", dataPath='").append(dataPath).append('\'');
		sb.append('}');
		return sb.toString();
	}
}

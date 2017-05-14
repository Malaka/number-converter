package com.aconex.code.challenge.domain;

import com.aconex.code.challenge.domain.Const.TelDataType;

/**
 * @author: malaka
 * Date: 5/11/17
 * Time: 12:30 PM
 */
public class TelephoneData {

	private TelDataType dataInputType;

	private String dataPath;

	private TelephoneData(TelDataType dataInputType, String dataPath) {
		this.dataInputType = dataInputType;
		this.dataPath = dataPath;
	}

	public static TelephoneData file(String dataPath) {
		return new TelephoneData(TelDataType.FILE, dataPath);
	}

	public static TelephoneData stdin() {
		return new TelephoneData(TelDataType.STDIN, "");
	}

	public TelDataType getDataInputType() {
		return dataInputType;
	}

	public String getDataPath() {
		return dataPath;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("TelephoneData{");
		sb.append("dataInputType=").append(dataInputType);
		sb.append(", dataPath='").append(dataPath).append('\'');
		sb.append('}');
		return sb.toString();
	}
}

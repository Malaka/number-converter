package com.aconex.code.challenge.service.telephone;

import com.aconex.code.challenge.domain.Const.TelDataType;
import com.aconex.code.challenge.domain.TelephoneData;

/**
 * @author: malaka
 * Date: 5/11/17
 * Time: 2:43 PM
 */
public class TelephoneLoaderFactory {

	public static TelDataLoader createDateLoader(TelephoneData telephoneData) {
		TelDataLoader dataLoader = null;
		TelDataType dataInputType = telephoneData.getDataInputType();
		switch (dataInputType) {
			case FILE:
				dataLoader = new TelDataFileLoader(telephoneData.getDataPath());
				break;
			case STDIN:
				dataLoader = new TelDataSTDINLoader();
				break;
		}
		return dataLoader;
	}

}

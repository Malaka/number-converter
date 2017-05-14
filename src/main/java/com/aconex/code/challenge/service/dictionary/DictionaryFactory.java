package com.aconex.code.challenge.service.dictionary;

import com.aconex.code.challenge.domain.Const.DictionaryType;
import com.aconex.code.challenge.domain.Dictionary;
import com.aconex.code.challenge.domain.DictionaryData;
import com.aconex.code.challenge.exception.ResourceNotFountException;

/**
 * Factory for creating @{@link DictionaryLoader} based on the user selection
 *
 * @author: malaka
 * Date: 5/12/17
 * Time: 1:58 AM
 */
public class DictionaryFactory {

	public static Dictionary createDictionary(DictionaryData dictionaryData) throws ResourceNotFountException {
		DictionaryLoader loader = null;
		DictionaryType dictionaryType = dictionaryData.getDictionaryType();
		switch (dictionaryType) {
			case FILE:
				loader = new FileDictionaryLoader(dictionaryData.getDataPath());
				break;
			case INTERNAL:
				loader = new InternalDictionaryLoader();
				break;
		}
		return loader.load();
	}

}

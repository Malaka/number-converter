package com.aconex.code.challenge.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.aconex.code.challenge.domain.Dictionary;
import com.aconex.code.challenge.domain.DictionaryData;
import com.aconex.code.challenge.domain.Encoding;
import com.aconex.code.challenge.domain.telnumber.TelephoneNumber;
import com.aconex.code.challenge.exception.ResourceNotFountException;
import com.aconex.code.challenge.exception.UnsupportedOperation;
import com.aconex.code.challenge.service.dictionary.DictionaryFactory;
import com.aconex.code.challenge.service.encoding.EncodeLoader;

/**
 * @author: malaka
 * Date: 5/13/17
 * Time: 1:45 PM
 */
public class ConverterServiceImplTest {
	@Test
	public void convert() throws Exception {

		try {
			String filename = "./src/test/resources/test_dictionary.txt";
			DictionaryData dictionaryData = DictionaryData.file(filename);
			Dictionary dictionary = DictionaryFactory.createDictionary(dictionaryData);

			Encoding encoding = EncodeLoader.loadEncoding();

			ConverterService converterService = new ConverterServiceImpl(dictionary, encoding);
			//List<TelephoneNumber> convert = converterService.convert("2775324861");
			List<TelephoneNumber> convert = converterService.convert("225563");
			System.out.println(convert);
		} catch (ResourceNotFountException | UnsupportedOperation e) {
			Assert.fail();
		}

	}
}
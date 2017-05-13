package com.aconex.code.challenge.service.dictionary;

import org.junit.Assert;
import org.junit.Test;

import com.aconex.code.challenge.domain.Dictionary;
import com.aconex.code.challenge.domain.DictionaryData;
import com.aconex.code.challenge.exception.ResourceNotFountException;

/**
 * @author: malaka
 * Date: 5/13/17
 * Time: 5:07 AM
 */
public class DictionaryFactoryTest {
	@Test
	public void createDictionary() throws Exception {

		try {
			DictionaryData dictionaryData = DictionaryData.internal();
			Dictionary dictionary = DictionaryFactory.createDictionary(dictionaryData);
			Assert.assertTrue(dictionary != null);

			String filename = "./src/test/resources/test_dictionary.txt";
			dictionaryData = DictionaryData.file(filename);
			dictionary = DictionaryFactory.createDictionary(dictionaryData);
			Assert.assertTrue(dictionary != null);
			Assert.assertTrue(dictionary.wordSearch("apple"));
			Assert.assertFalse(dictionary.wordSearch("Test"));
		} catch (ResourceNotFountException e) {
			Assert.fail();
		}
	}

}
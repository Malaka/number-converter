package com.aconex.code.challenge.service.dictionary;

import org.junit.Assert;
import org.junit.Test;

import com.aconex.code.challenge.domain.Dictionary;

/**
 * @author: malaka
 * Date: 5/13/17
 * Time: 4:41 AM
 */
public class FileDictionaryLoaderTest {
	@Test
	public void loadFromFile() throws Exception {
		try {
			String filename = "./src/test/resources/test_dictionary.txt";
			FileDictionaryLoader loader = new FileDictionaryLoader(filename);
			Dictionary dictionary = loader.load();
			Assert.assertNotNull(dictionary);

			Assert.assertTrue(dictionary.wordSearch("A"));
			Assert.assertTrue(dictionary.wordSearch("apple"));
			Assert.assertTrue(dictionary.wordSearch("APPLE"));
			Assert.assertTrue(dictionary.wordSearch("elephant"));
			Assert.assertTrue(dictionary.wordSearch("GREEN"));
			Assert.assertTrue(dictionary.wordSearch("country"));

			Assert.assertFalse(dictionary.wordSearch("Test"));
			Assert.assertFalse(dictionary.wordSearch(""));
			Assert.assertFalse(dictionary.wordSearch(null));

		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void invalidFile() throws Exception {
		try {
			String filename = "./invalid_file.txt";
			FileDictionaryLoader loader = new FileDictionaryLoader(filename);
			loader.load();
			Assert.fail();
		} catch (Exception e) {
			//expected
		}
	}
}
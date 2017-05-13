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

			Assert.assertTrue(dictionary.findWord("A"));
			Assert.assertTrue(dictionary.findWord("apple"));
			Assert.assertTrue(dictionary.findWord("APPLE"));
			Assert.assertTrue(dictionary.findWord("elephant"));
			Assert.assertTrue(dictionary.findWord("GREEN"));
			Assert.assertTrue(dictionary.findWord("country"));

			Assert.assertFalse(dictionary.findWord("Test"));
			Assert.assertFalse(dictionary.findWord(""));
			Assert.assertFalse(dictionary.findWord(null));

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
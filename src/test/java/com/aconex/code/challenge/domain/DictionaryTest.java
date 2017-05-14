package com.aconex.code.challenge.domain;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: malaka
 * Date: 5/14/17
 * Time: 5:07 AM
 */
public class DictionaryTest {

	private List<String> dictionaryData;

	@Before
	public void setUp() throws Exception {
		dictionaryData = Stream.of(
			"LE",
			"EYE",
			"PQR",
			"PQ",
			"QR",
			"P",
			"Q",
			"R",
			"APPLE",
			"APP"
		)
			.collect(Collectors.toList());
	}

	@Test
	public void testSorting() throws Exception {
		Dictionary dictionary = Dictionary.of(dictionaryData);
		List<String> dictionaryData = dictionary.getDictionaryData();
		Assert.assertEquals("APP", dictionaryData.iterator().next());
	}


	@Test
	public void findWord() throws Exception {
		Dictionary dictionary = Dictionary.of(dictionaryData);
		Assert.assertFalse(dictionary.findWord(null));
		Assert.assertFalse(dictionary.findWord(""));
		Assert.assertFalse(dictionary.findWord("RUN"));
		Assert.assertFalse(dictionary.findWord("run"));

		Assert.assertTrue(dictionary.findWord("apple"));
		Assert.assertTrue(dictionary.findWord("APPLE"));
		Assert.assertTrue(dictionary.findWord("eye"));
	}

	@Test
	public void expandSearch() throws Exception {

		Dictionary dictionary = Dictionary.of(dictionaryData);
		Assert.assertTrue(dictionary.expandSearch("BAT").isEmpty());

		Set<List<String>> result = dictionary.expandSearch("EYE");
		Set<String> expected = Stream.of("EYE").collect(Collectors.toSet());
		Assert.assertEquals(expected, transform(result));

		result = dictionary.expandSearch("APPLE");
		expected = Stream.of("APPLE", "APP-LE").collect(Collectors.toSet());
		Assert.assertEquals(expected, transform(result));

		result = dictionary.expandSearch("APPLEEYE");
		expected = Stream.of("APPLE-EYE", "APP-LE-EYE").collect(Collectors.toSet());
		Assert.assertEquals(expected, transform(result));

		result = dictionary.expandSearch("PQR");
		expected = Stream.of("PQR", "PQ-R", "P-QR", "P-Q-R").collect(Collectors.toSet());
		Assert.assertEquals(expected, transform(result));
	}

	private Set<String> transform(Set<List<String>> data) {
		return data
			.stream()
			.map(this::reduce)
			.collect(Collectors.toSet());
	}

	private String reduce(List<String> words) {
		final StringBuilder sb = new StringBuilder();
		Iterator<String> iterator = words.iterator();
		while (iterator.hasNext()) {
			String node = iterator.next();
			sb.append(node);
			if (iterator.hasNext()) {
				sb.append(Const.SEPARATOR);
			}
		}
		return sb.toString();
	}
}
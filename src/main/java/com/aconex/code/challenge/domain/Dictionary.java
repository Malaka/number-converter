package com.aconex.code.challenge.domain;

import java.util.Collections;
import java.util.List;

/**
 * @author: malaka
 * Date: 5/12/17
 * Time: 1:59 AM
 */
public class Dictionary {

	private List<String> dictionaryData;

	public static Dictionary of(List<String> dictionaryData) {
		return new Dictionary(dictionaryData);
	}

	private Dictionary(List<String> dictionaryData) {
		// we sort the list first thus leverage the binary search for finding words
		Collections.sort(dictionaryData);
		this.dictionaryData = dictionaryData;
	}

	public boolean wordSearch(String word) {

		if (word == null || word.isEmpty()) {
			return false;
		}

		return (Collections.binarySearch(this.dictionaryData, word.toUpperCase()) >= 0);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Dictionary{");
		sb.append("size=").append(dictionaryData.size()).append('}');
		return sb.toString();
	}
}

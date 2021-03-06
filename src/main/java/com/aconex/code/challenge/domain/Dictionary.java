package com.aconex.code.challenge.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Represent the dictionary. dictionaryData is always sorted.
 * Thus binary search can be used for word search
 *
 * @author: malaka
 * Date: 5/12/17
 * Time: 1:59 AM
 */
public class Dictionary {

	private List<String> dictionaryData;

	private Dictionary(List<String> dictionaryData) {
		// we sort the list first thus leverage the binary search for finding words
		Collections.sort(dictionaryData);
		this.dictionaryData = dictionaryData;
	}

	public static Dictionary of(List<String> dictionaryData) {
		return new Dictionary(dictionaryData);
	}

	/**
	 * Find a word is present in the dictionary or not
	 *
	 * @param word
	 * 	word to test
	 * @return true if if match is found in dictionary false otherwise. If the word is null or empty it will return false.
	 */
	public boolean findWord(String word) {

		if (word == null || word.isEmpty()) {
			return false;
		}
		return (Collections.binarySearch(this.dictionaryData, word.toUpperCase()) >= 0);
	}

	/**
	 * Given a word this method will gives the all possible valid word sub sets
	 *
	 * @param word
	 * 	word to expand
	 * @return all valid sub sets of words
	 */
	public Set<List<String>> expandSearch(String word) {
		Set<List<String>> expandResults = new HashSet<>();

		if (word == null || word.isEmpty()) {
			return expandResults;
		}

		List<List<String>> subWords = subStrings(word.toUpperCase());
		return subWords
			.stream()
			.filter(this::validWordCombo)
			.collect(Collectors.toSet());
	}

	private boolean validWordCombo(List<String> combo) {
		return combo
			.stream()
			.filter(a -> !this.findWord(a))
			.map(a -> false)
			.findFirst()
			.orElse(true);
	}

	/**
	 * Given a string expand to sub sets which
	 * [abc] -> [[a-bc],[ab-c],[a-b-c],[abc]]
	 */
	private List<List<String>> subStrings(String input) {

		// [x] ==> x
		if (input.length() == 1)
			return Collections.singletonList(Collections.singletonList(input));

		// To hold the result
		List<List<String>> result = new ArrayList<>();

		List<List<String>> subStrings = subStrings(input.substring(1));
		for (List<String> subResult : subStrings) {

			// Case: Don't split
			List<String> l2 = new ArrayList<>(subResult);
			l2.set(0, input.charAt(0) + l2.get(0));
			result.add(l2);

			// Case: Split
			List<String> l = new ArrayList<>(subResult);
			l.add(0, input.substring(0, 1));
			result.add(l);
		}

		return result;
	}

	public List<String> getDictionaryData() {
		return Collections.unmodifiableList(dictionaryData);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Dictionary{");
		sb.append("size=").append(dictionaryData.size()).append('}');
		return sb.toString();
	}
}

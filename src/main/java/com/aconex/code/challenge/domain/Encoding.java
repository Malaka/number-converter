package com.aconex.code.challenge.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toList;

/**
 * Number to Charter encoding
 *
 * @author: malaka
 * Date: 5/12/17
 * Time: 2:51 AM
 */
public class Encoding {

	// key : Number, Value : Set of Char which maps to the Number
	private Map<Character, List<Character>> encodeMapping;

	public static Encoding of(Map<Character, Character> encodeMapping) {
		// transform the map to be key by number
		Map<Character, List<Character>> encoding = encodeMapping.entrySet()
			.stream()
			.collect(Collectors.groupingBy(Map.Entry::getValue, mapping(Map.Entry::getKey, toList())));
		return new Encoding(encoding);
	}

	private Encoding(Map<Character, List<Character>> encodeMapping) {
		this.encodeMapping = encodeMapping;
	}

	public Set<String> encode(String number) {

		if (number == null || number.isEmpty()) {
			return new HashSet<>();
		}

		// TODO validate number

		List<List<Character>> encoded = new ArrayList<>();
		number.chars().forEach(c -> encoded.add(getEncoding((char) c)));

		List<List<Character>> lists = cartesianProduct(encoded);
		return lists
			.stream()
			.map(chars -> chars
				.stream()
				.map(Object::toString)
				.reduce((acc, e) -> acc + e).get())
			.collect(Collectors.toSet());
	}

	private List<Character> getEncoding(Character c) {
		return Optional.ofNullable(encodeMapping.get(c)).orElse(Stream.of(c).collect(Collectors.toList()));
	}

	private <T> List<List<T>> cartesianProduct(List<List<T>> lists) {
		List<List<T>> resultLists = new ArrayList<>();
		if (lists.size() == 0) {
			resultLists.add(new ArrayList<T>());
			return resultLists;
		} else {
			List<T> firstList = lists.get(0);
			List<List<T>> remainingLists = cartesianProduct(lists.subList(1, lists.size()));
			for (T condition : firstList) {
				for (List<T> remainingList : remainingLists) {
					ArrayList<T> resultList = new ArrayList<T>();
					resultList.add(condition);
					resultList.addAll(remainingList);
					resultLists.add(resultList);
				}
			}
		}
		return resultLists;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Encoding{");
		sb.append("encodeMapping=").append(encodeMapping);
		sb.append('}');
		return sb.toString();
	}
}

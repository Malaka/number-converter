package com.aconex.code.challenge.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.aconex.code.challenge.domain.telnumber.NumberNode;
import com.aconex.code.challenge.domain.telnumber.StringNode;
import com.aconex.code.challenge.domain.telnumber.TelNode;
import com.aconex.code.challenge.util.CommonsUtil;

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

	public List<List<TelNode>> encode(String number) {

		if (number == null || number.isEmpty() || !CommonsUtil.isDigit(number)) {
			return new ArrayList<>();
		}

		List<List<TelNode>> encoded = new ArrayList<>();
		number.chars().forEach(c -> encoded.add(getEncoding((char) c)));

		/*List<List<Character>> lists = cartesianProduct(encoded);
		return lists
			.stream()
			.map(chars -> chars
				.stream()
				.map(Object::toString)
				.reduce((acc, e) -> acc + e).get())
			.collect(Collectors.toSet());*/

		return encoded;
	}

	private List<TelNode> getEncoding(Character c) {
		List<TelNode> expansion = new ArrayList<>();
		expansion.add(NumberNode.ofChar(c));
		List<Character> encoding = encodeMapping.get(c);
		if (encoding != null) {
			List<StringNode> stringNodes = encoding
				.stream()
				.map(StringNode::ofChar)
				.collect(Collectors.toList());

			expansion.addAll(stringNodes);
		}
		return expansion;
	}

	/*private List<List<Character>> cartesianProduct(List<List<Character>> lists) {
		List<List<Character>> resultLists = new ArrayList<>();
		if (lists.size() == 0) {
			resultLists.add(new ArrayList<>());
			return resultLists;
		} else {
			List<Character> firstList = lists.get(0);
			List<List<Character>> remainingLists = cartesianProduct(lists.subList(1, lists.size()));
			for (Character condition : firstList) {
				for (List<Character> remainingList : remainingLists) {
					ArrayList<Character> resultList = new ArrayList<>();
					//
					if (!remainingList.isEmpty()) {
						if (!(isDigit(condition.toString()) && isDigit(remainingList.iterator().next().toString()))) {
							resultList.add(condition);
							resultList.addAll(remainingList);
							resultLists.add(resultList);
						}
					} else {
						resultList.add(condition);
						resultLists.add(resultList);
					}
				}
			}
		}
		return resultLists;
	}*/

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Encoding{");
		sb.append("encodeMapping=").append(encodeMapping);
		sb.append('}');
		return sb.toString();
	}
}

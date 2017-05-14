package com.aconex.code.challenge.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.aconex.code.challenge.domain.telnumber.NumberNode;
import com.aconex.code.challenge.domain.telnumber.StringNode;
import com.aconex.code.challenge.domain.telnumber.TelNode;
import com.aconex.code.challenge.domain.telnumber.Termination;
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
	private Map<Character, List<StringNode>> encodeMapping;

	public static Encoding of(Map<Character, Character> encodeMapping) {
		// transform the map to be key by number
		Map<Character, List<StringNode>> encoding = encodeMapping.entrySet()
			.stream()
			.collect(Collectors.groupingBy(
				Map.Entry::getValue,
				mapping((a) -> StringNode.ofChar(a.getKey()), toList())));
		return new Encoding(encoding);
	}

	private Encoding(Map<Character, List<StringNode>> encodeMapping) {
		this.encodeMapping = encodeMapping;
	}

	public List<List<TelNode>> encode(String number) {

		List<List<TelNode>> encoded = new ArrayList<>();
		encoded.add(Stream.of(Termination.of()).collect(Collectors.toList()));

		if (number == null || number.isEmpty() || !CommonsUtil.isDigit(number)) {
			return encoded;
		}

		number.chars().forEach(c -> encoded.add(getEncoding((char) c)));
		return encoded;
	}

	private List<TelNode> getEncoding(Character c) {
		List<TelNode> expansion = new ArrayList<>();
		expansion.add(NumberNode.ofChar(c));
		List<StringNode> encoding = encodeMapping.get(c);
		expansion.addAll(encoding);
		return expansion;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("Encoding{");
		sb.append("encodeMapping=").append(encodeMapping);
		sb.append('}');
		return sb.toString();
	}
}

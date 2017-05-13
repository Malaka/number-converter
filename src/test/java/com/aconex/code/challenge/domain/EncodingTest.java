package com.aconex.code.challenge.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author: malaka
 * Date: 5/12/17
 * Time: 4:36 AM
 */
public class EncodingTest {

	private Map<Character, Character> encodeMapping;

	@Before
	public void setUp() throws Exception {
		this.encodeMapping = new HashMap<>();
		encodeMapping.put('a', '2');
		encodeMapping.put('b', '2');
		encodeMapping.put('c', '2');
		encodeMapping.put('d', '3');
		encodeMapping.put('e', '3');
		encodeMapping.put('f', '3');
		encodeMapping.put('g', '4');
		encodeMapping.put('h', '4');
		encodeMapping.put('i', '4');
		encodeMapping.put('j', '5');
		encodeMapping.put('k', '5');
		encodeMapping.put('l', '5');
		encodeMapping.put('m', '6');
		encodeMapping.put('n', '6');
		encodeMapping.put('o', '6');
		encodeMapping.put('p', '7');
		encodeMapping.put('q', '7');
		encodeMapping.put('r', '7');
		encodeMapping.put('s', '7');
		encodeMapping.put('t', '8');
		encodeMapping.put('u', '8');
		encodeMapping.put('v', '8');
		encodeMapping.put('w', '9');
		encodeMapping.put('x', '9');
		encodeMapping.put('y', '9');
		encodeMapping.put('z', '9');
	}

	@Test
	public void creation() throws Exception {
		Encoding encoding = Encoding.of(encodeMapping);

		Set<String> encode = encoding.encode("2");
		Set<String> expected = Stream.of("a", "c", "b").collect(Collectors.toSet());
		Assert.assertEquals(expected, encode);

		encode = encoding.encode("3");
		expected = Stream.of("d", "e", "f").collect(Collectors.toSet());
		Assert.assertEquals(expected, encode);

		encode = encoding.encode("4");
		expected = Stream.of("g", "h", "i").collect(Collectors.toSet());
		Assert.assertEquals(expected, encode);

		encode = encoding.encode("5");
		expected = Stream.of("j", "k", "l").collect(Collectors.toSet());
		Assert.assertEquals(expected, encode);

		encode = encoding.encode("6");
		expected = Stream.of("m", "n", "o").collect(Collectors.toSet());
		Assert.assertEquals(expected, encode);

		encode = encoding.encode("7");
		expected = Stream.of("p", "q", "r", "s").collect(Collectors.toSet());
		Assert.assertEquals(expected, encode);

		encode = encoding.encode("8");
		expected = Stream.of("t", "u", "v").collect(Collectors.toSet());
		Assert.assertEquals(expected, encode);

		encode = encoding.encode("9");
		expected = Stream.of("w", "x", "y", "z").collect(Collectors.toSet());
		Assert.assertEquals(expected, encode);
	}

	@Test
	public void encode() throws Exception {
		Encoding encoding = Encoding.of(encodeMapping);

		Assert.assertTrue("Empy set for null input", encoding.encode(null).isEmpty());

		Set<String> encode = encoding.encode("1");
		Set<String> expected = Stream.of("1").collect(Collectors.toSet());
		Assert.assertEquals("if no encoding found same should return", expected, encode);

		encode = encoding.encode("#");
		expected = Stream.of("#").collect(Collectors.toSet());
		Assert.assertEquals("non number char should return same", expected, encode);

		encode = encoding.encode("2");
		expected = Stream.of("a", "c", "b").collect(Collectors.toSet());
		Assert.assertEquals(expected, encode);

		encode = encoding.encode("12");
		expected = Stream.of("1a", "1c", "1b").collect(Collectors.toSet());
		Assert.assertEquals(expected, encode);

		encode = encoding.encode("21");
		expected = Stream.of("a1", "c1", "b1").collect(Collectors.toSet());
		Assert.assertEquals(expected, encode);

		encode = encoding.encode("22");
		expected = Stream.of("aa", "ca", "ba", "ab", "cb", "bb", "ac", "cc", "bc").collect(Collectors.toSet());
		Assert.assertEquals(expected, encode);

		encode = encoding.encode("212");
		expected = Stream.of("a1a", "c1a", "b1a", "a1b", "c1b", "b1b", "a1c", "c1c", "b1c").collect(Collectors.toSet());
		Assert.assertEquals(expected, encode);

		// check for larger inputs
		encode = encoding.encode("2398756277");
		Assert.assertEquals((3 * 3 * 4 * 3 * 4 * 3 * 3 * 3 * 4 * 4), encode.size());

		encode = encoding.encode("11239817562771");
		Assert.assertEquals((3 * 3 * 4 * 3 * 4 * 3 * 3 * 3 * 4 * 4), encode.size());
	}

}
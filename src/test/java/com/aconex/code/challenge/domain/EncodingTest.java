package com.aconex.code.challenge.domain;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.aconex.code.challenge.domain.telnumber.TelNode;
import com.aconex.code.challenge.domain.telnumber.Termination;

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


		List<List<TelNode>> encode = encoding.encode(null);
		Iterator<List<TelNode>> iterator = encode.iterator();
		Assert.assertEquals("Only Terminator for null input", 1, encode.size());
		Assert.assertTrue(iterator.next().iterator().next() instanceof Termination);

		encode = encoding.encode("#");
		iterator = encode.iterator();
		Assert.assertEquals("Only Terminator for null input", 1, encode.size());
		Assert.assertTrue(iterator.next().iterator().next() instanceof Termination);

		encode = encoding.encode("1");
		iterator = encode.iterator();
		Assert.assertTrue(iterator.next().iterator().next() instanceof Termination);
		Set<String> expected = Stream.of("1").collect(Collectors.toSet());
		Assert.assertEquals(expected, convert(iterator.next()));

		encode = encoding.encode("2");
		iterator = encode.iterator();
		Assert.assertTrue(iterator.next().iterator().next() instanceof Termination);
		expected = Stream.of("2", "a", "c", "b").collect(Collectors.toSet());
		Assert.assertEquals(expected, convert(iterator.next()));

		encode = encoding.encode("3");
		iterator = encode.iterator();
		Assert.assertTrue(iterator.next().iterator().next() instanceof Termination);
		expected = Stream.of("3", "d", "e", "f").collect(Collectors.toSet());
		Assert.assertEquals(expected, convert(iterator.next()));

		encode = encoding.encode("4");
		iterator = encode.iterator();
		Assert.assertTrue(iterator.next().iterator().next() instanceof Termination);
		expected = Stream.of("4", "g", "h", "i").collect(Collectors.toSet());
		Assert.assertEquals(expected, convert(iterator.next()));

		encode = encoding.encode("5");
		iterator = encode.iterator();
		Assert.assertTrue(iterator.next().iterator().next() instanceof Termination);
		expected = Stream.of("5", "j", "k", "l").collect(Collectors.toSet());
		Assert.assertEquals(expected, convert(iterator.next()));

		encode = encoding.encode("6");
		iterator = encode.iterator();
		Assert.assertTrue(iterator.next().iterator().next() instanceof Termination);
		expected = Stream.of("6", "m", "n", "o").collect(Collectors.toSet());
		Assert.assertEquals(expected, convert(iterator.next()));

		encode = encoding.encode("7");
		iterator = encode.iterator();
		Assert.assertTrue(iterator.next().iterator().next() instanceof Termination);
		expected = Stream.of("7", "p", "q", "r", "s").collect(Collectors.toSet());
		Assert.assertEquals(expected, convert(iterator.next()));

		encode = encoding.encode("8");
		iterator = encode.iterator();
		Assert.assertTrue(iterator.next().iterator().next() instanceof Termination);
		expected = Stream.of("8", "t", "u", "v").collect(Collectors.toSet());
		Assert.assertEquals(expected, convert(iterator.next()));

		encode = encoding.encode("9");
		iterator = encode.iterator();
		Assert.assertTrue(iterator.next().iterator().next() instanceof Termination);
		expected = Stream.of("9", "w", "x", "y", "z").collect(Collectors.toSet());
		Assert.assertEquals(expected, convert(iterator.next()));
	}

	private Set<String> convert(List<TelNode> encoding) {
		return encoding
			.stream()
			.filter(a -> !a.isTerminating())
			.map(TelNode::getVal)
			.collect(Collectors.toSet());
	}

	@Test
	public void encode() throws Exception {
		Encoding encoding = Encoding.of(encodeMapping);



		/*Set<String> encode = encoding.encode("1");
		Set<String> expected = Stream.of("1").collect(Collectors.toSet());
		Assert.assertEquals("if no encoding found same should return", expected, encode);

		encode = encoding.encode("#");
		expected = Stream.of("#").collect(Collectors.toSet());
		Assert.assertEquals("non number char should return same", expected, encode);

		encode = encoding.encode("2");
		expected = Stream.of("2", "a", "c", "b").collect(Collectors.toSet());
		Assert.assertEquals(expected, encode);

		encode = encoding.encode("12");
		expected = Stream.of("1a", "1c", "1b").collect(Collectors.toSet());
		Assert.assertEquals(expected, encode);

		encode = encoding.encode("21");
		expected = Stream.of("a1", "c1", "b1").collect(Collectors.toSet());
		Assert.assertEquals(expected, encode);

		encode = encoding.encode("22");
		expected = Stream.of("aa", "ca", "ba", "ab", "cb", "bb", "ac", "cc", "bc", "a2", "b2", "c2", "2a", "2b",
			"2c").collect(Collectors.toSet());
		Assert.assertEquals(expected, encode);

		encode = encoding.encode("212");
		expected = Stream.of("a1a", "c1a", "b1a", "a1b", "c1b", "b1b", "a1c", "c1c", "b1c").collect(Collectors.toSet());
		Assert.assertEquals(expected, encode);

		// check for larger inputs
		encode = encoding.encode("2398756277");
		Assert.assertEquals((4 * 4 * 5 * 4 * 5 * 4 * 4 * 4 * 5 * 5), encode.size());*/
	}

}
package com.aconex.code.challenge.service.encoding;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.aconex.code.challenge.domain.Encoding;
import com.aconex.code.challenge.domain.telnumber.TelNode;
import com.aconex.code.challenge.domain.telnumber.Termination;
import com.aconex.code.challenge.exception.ResourceNotFountException;

/**
 * @author: malaka
 * Date: 5/13/17
 * Time: 3:23 AM
 */
public class EncodeLoaderTest {
	@Test
	public void load() throws Exception {
		try {
			Encoding encoding = EncodeLoader.loadEncoding();


			List<List<TelNode>> encode = encoding.encode("2");
			Iterator<List<TelNode>> iterator = encode.iterator();
			Assert.assertTrue(iterator.next().iterator().next() instanceof Termination);

			Set<String> expected = Stream.of("2", "A", "B", "C").collect(Collectors.toSet());
			Assert.assertEquals(expected, convert(iterator.next()));

			encode = encoding.encode("9");
			iterator = encode.iterator();
			Assert.assertTrue(iterator.next().iterator().next() instanceof Termination);
			expected = Stream.of("9", "W", "X", "Y", "Z").collect(Collectors.toSet());
			Assert.assertEquals(expected, convert(iterator.next()));

		} catch (ResourceNotFountException e) {
			Assert.fail("should be able to load the encoding file");
		}
	}

	private Set<String> convert(List<TelNode> encoding) {
		return encoding
			.stream()
			.filter(a -> !a.isTerminating())
			.map(TelNode::getVal)
			.collect(Collectors.toSet());
	}

}
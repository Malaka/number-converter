package com.aconex.code.challenge.service.encoding;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.aconex.code.challenge.domain.Encoding;
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

			Set<String> encode = encoding.encode("2");
			Set<String> expected = Stream.of("A", "B", "C").collect(Collectors.toSet());
			Assert.assertEquals(expected, encode);

			encode = encoding.encode("9");
			expected = Stream.of("W", "X", "Y", "Z").collect(Collectors.toSet());
			Assert.assertEquals(expected, encode);
		} catch (ResourceNotFountException e) {
			Assert.fail("should be able to load the encoding file");
		}
	}

}
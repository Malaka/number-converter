package com.aconex.code.challenge.service.telephone;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: malaka
 * Date: 5/13/17
 * Time: 3:36 AM
 */
public class TelDataSTDINLoaderTest {
	@Test
	public void readFromSTDIN() throws Exception {

		String data = "12345\n4564564\n@#$@#$@#$23\n";
		InputStream stdin = System.in;
		try {
			System.setIn(new ByteArrayInputStream(data.getBytes()));
			TelDataSTDINLoader stdinLoader = new TelDataSTDINLoader();
			Stream<String> inputStream = stdinLoader.load();
			List<String> collect = inputStream.collect(Collectors.toList());

			Assert.assertFalse(collect.isEmpty());
			Assert.assertEquals("12345", collect.get(0));
			Assert.assertEquals("4564564", collect.get(1));
			Assert.assertEquals("@#$@#$@#$23", collect.get(2));

		} catch (Exception e) {
			Assert.fail();
		} finally {
			System.setIn(stdin);
		}
	}

}
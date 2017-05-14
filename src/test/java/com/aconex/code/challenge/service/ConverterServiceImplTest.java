package com.aconex.code.challenge.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.aconex.code.challenge.domain.Dictionary;
import com.aconex.code.challenge.domain.Encoding;
import com.aconex.code.challenge.domain.telnumber.TelephoneNumber;
import com.aconex.code.challenge.exception.ResourceNotFountException;
import com.aconex.code.challenge.exception.UnsupportedOperation;
import com.aconex.code.challenge.service.encoding.EncodeLoader;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author: malaka
 * Date: 5/13/17
 * Time: 1:45 PM
 */
public class ConverterServiceImplTest {
	@Test
	public void convert() throws Exception {

		try {
			Encoding encoding = EncodeLoader.loadEncoding();

			Dictionary dic = mock(Dictionary.class);
			when(dic.expandSearch("APPLE")).thenReturn(Collections.singleton(Collections.singletonList("APPLE")));
			when(dic.expandSearch("A")).thenReturn(Collections.singleton(Collections.singletonList("A")));
			when(dic.expandSearch("GUN")).thenReturn(Collections.singleton(Collections.singletonList("GUN")));
			when(dic.expandSearch("APPLEGUN")).thenReturn(Collections.singleton(Stream.of("APPLE", "GUN").collect(
				Collectors.toList())));
			ConverterService converterService = new ConverterServiceImpl(dic, encoding);

			//exact match
			List<TelephoneNumber> convert = converterService.convert("27753");
			Assert.assertEquals("[APPLE]", convert.toString());

			convert = converterService.convert("1277532");
			Assert.assertEquals("one number can be skipped without converting", "[1-APPLE-2]", convert.toString());

			convert = converterService.convert("12775355");
			Assert.assertEquals("two consecutive numbers are not allowed ", "[]", convert.toString());

			convert = converterService.convert("3453435");
			Assert.assertEquals("invalid words are not allowed", "[]", convert.toString());

			convert = converterService.convert("486");
			Assert.assertEquals("[GUN]", convert.toString());

			// check for combinations
			convert = converterService.convert("27753486");
			Assert.assertEquals("[APPLE-GUN]", convert.toString());

			convert = converterService.convert("277534861");
			Assert.assertEquals("[APPLE-GUN-1]", convert.toString());

			convert = converterService.convert("027753486");
			Assert.assertEquals("[0-APPLE-GUN]", convert.toString());

			convert = converterService.convert("277535486");
			Assert.assertEquals("[APPLE-5-GUN]", convert.toString());

		} catch (ResourceNotFountException | UnsupportedOperation e) {
			Assert.fail();
		}

	}
}
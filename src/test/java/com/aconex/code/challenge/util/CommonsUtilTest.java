package com.aconex.code.challenge.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author: malaka
 * Date: 5/13/17
 * Time: 4:02 AM
 */
public class CommonsUtilTest {

	@Test
	public void digitOnly() throws Exception {

		try {
			String data = "1234";
			String result = CommonsUtil.digitOnly(data);
			Assert.assertEquals(data, result);

			Assert.assertNull(CommonsUtil.digitOnly(null));

			data = "";
			result = CommonsUtil.digitOnly(data);
			Assert.assertEquals("empty string should return same", data, result);

			data = "\t1234 ";
			result = CommonsUtil.digitOnly(data);
			Assert.assertEquals("whitespace should be removed", "1234", result);

			data = "%1234#aA";
			result = CommonsUtil.digitOnly(data);
			Assert.assertEquals("non digit char should be removed", "1234", result);

		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void alphaOnly() throws Exception {

		try {
			String data = "adbAD";
			String result = CommonsUtil.alphaOnly(data);
			Assert.assertEquals(data, result);

			Assert.assertNull(CommonsUtil.alphaOnly(null));

			data = "";
			result = CommonsUtil.alphaOnly(data);
			Assert.assertEquals("empty string should return same", data, result);

			data = "\tabsdfsd ";
			result = CommonsUtil.alphaOnly(data);
			Assert.assertEquals("whitespace should be removed", "absdfsd", result);

			data = "%apple-One#";
			result = CommonsUtil.alphaOnly(data);
			Assert.assertEquals("non alpha char should be removed", "appleOne", result);

		} catch (Exception e) {
			Assert.fail();
		}

	}

}
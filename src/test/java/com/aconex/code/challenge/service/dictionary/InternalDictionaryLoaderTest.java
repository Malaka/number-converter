package com.aconex.code.challenge.service.dictionary;

import org.junit.Assert;
import org.junit.Test;

import com.aconex.code.challenge.domain.Dictionary;

/**
 * @author: malaka
 * Date: 5/13/17
 * Time: 5:03 AM
 */
public class InternalDictionaryLoaderTest {
	@Test
	public void loadInternal() throws Exception {

		try {
			InternalDictionaryLoader loader = new InternalDictionaryLoader();
			Dictionary load = loader.load();
			Assert.assertNotNull(load);
		} catch (Exception e) {
			Assert.fail();
		}
	}

}
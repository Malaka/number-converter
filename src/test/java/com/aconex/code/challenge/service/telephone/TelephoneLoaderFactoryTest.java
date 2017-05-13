package com.aconex.code.challenge.service.telephone;

import org.junit.Assert;
import org.junit.Test;

import com.aconex.code.challenge.domain.TelephoneData;

import static org.junit.Assert.fail;

/**
 * @author: malaka
 * Date: 5/13/17
 * Time: 4:28 AM
 */
public class TelephoneLoaderFactoryTest {
	@Test
	public void createDateLoader() throws Exception {
		try {
			TelephoneData telephoneData = TelephoneData.stdin();
			TelDataLoader dateLoader = TelephoneLoaderFactory.createDateLoader(telephoneData);
			Assert.assertTrue(dateLoader instanceof TelDataSTDINLoader);

			telephoneData = TelephoneData.file("./src/test/resources/test_input.txt");
			dateLoader = TelephoneLoaderFactory.createDateLoader(telephoneData);
			Assert.assertTrue(dateLoader instanceof TelDataFileLoader);
			Assert.assertTrue(dateLoader.load().findFirst().isPresent());

		} catch (Exception e) {
			fail();
		}
	}

}
package com.aconex.code.challenge.service.telephone;

import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.aconex.code.challenge.exception.ResourceNotFountException;

/**
 * @author: malaka
 * Date: 5/11/17
 * Time: 4:11 PM
 */
public class TelDataFileLoaderTest {

	@Test
	public void load() throws Exception {

		try {
			String filePath = "./src/test/resources/test_input.txt";
			TelDataFileLoader telDataFileLoader = new TelDataFileLoader(filePath);
			Stream<String> fileStream = telDataFileLoader.load();
			Assert.assertTrue(fileStream.findFirst().isPresent());
		} catch (ResourceNotFountException e) {
			Assert.fail();
		}

		try {
			String filePath = "invalid_file.txt";
			TelDataFileLoader telDataFileLoader = new TelDataFileLoader(filePath);
			Stream<String> fileStream = telDataFileLoader.load();
			Assert.fail("invalid file name. should fail");
		} catch (ResourceNotFountException e) {
			// expected
		}
	}

}
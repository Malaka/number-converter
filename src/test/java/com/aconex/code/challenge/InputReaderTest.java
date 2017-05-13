package com.aconex.code.challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.aconex.code.challenge.domain.DictionaryData;
import com.aconex.code.challenge.domain.TelephoneData;
import com.aconex.code.challenge.domain.Const.DictionaryType;
import com.aconex.code.challenge.domain.Const.TelDataType;
import com.aconex.code.challenge.exception.InvalidArgumentException;

import static org.junit.Assert.fail;

/**
 * @author: malaka
 * Date: 5/11/17
 * Time: 11:44 AM
 */
public class InputReaderTest {

	@Test
	public void processUserInputInvalid() throws Exception {
		List<String> args = Stream.of("-error").collect(Collectors.toList());
		InputReader inputReader = new InputReader();
		try {
			inputReader.processUserInput(args);
			fail("-error is invalid");
		} catch (InvalidArgumentException e) {
			//expected
		}

		args = Stream.of("--help", "-D input=file").collect(Collectors.toList());
		inputReader = new InputReader();
		try {
			inputReader.processUserInput(args);
			fail("-D input is a wrong argument");
		} catch (InvalidArgumentException e) {
			//expected
		}


		args = Stream.of(" -Pdic=file").collect(Collectors.toList());
		inputReader = new InputReader();
		try {
			inputReader.processUserInput(args);
			fail("-Pdic=file is a wrong argument");
		} catch (InvalidArgumentException e) {
			//expected
		}

		args = Stream.of(" -Ddic=file -d dataInput").collect(Collectors.toList());
		inputReader = new InputReader();
		try {
			inputReader.processUserInput(args);
			fail("-Pdic=file is a wrong argument");
		} catch (InvalidArgumentException e) {
			//expected
		}
	}


	@Test
	public void processUserInputHelp() throws Exception {

		// valid help param
		List<String> args = Stream.of("-Dinput=./data/input.txt").collect(Collectors.toList());
		InputReader inputReader = new InputReader();
		try {
			inputReader.processUserInput(args);
			Assert.assertFalse("not in help mode", inputReader.isDisplayHelp());
		} catch (InvalidArgumentException e) {
			fail("user input valid parm for input file");
		}

		args = Stream.of("--help").collect(Collectors.toList());
		inputReader = new InputReader();
		try {
			inputReader.processUserInput(args);
			Assert.assertTrue("user requested help", inputReader.isDisplayHelp());
		} catch (InvalidArgumentException e) {
			fail("--help is a valid user input");
		}
	}

	@Test
	public void processUserInputTelephone() throws Exception {

		// valid help param
		List<String> args = Stream.of("-Dinput=./data/input.txt").collect(Collectors.toList());
		InputReader inputReader = new InputReader();

		try {
			inputReader.processUserInput(args);
			TelephoneData telephoneData = inputReader.getTelephoneData();
			Assert.assertFalse("user is not in help mode", inputReader.isDisplayHelp());
			Assert.assertEquals("should be file", TelDataType.FILE, telephoneData.getDataInputType());
			Assert.assertEquals("./data/input.txt", telephoneData.getDataPath());
		} catch (InvalidArgumentException e) {
			fail("-h is a valid user input");
		}

		args = new ArrayList<>();
		inputReader = new InputReader();
		try {
			inputReader.processUserInput(args);
			TelephoneData telephoneData = inputReader.getTelephoneData();
			Assert.assertFalse("user is not in help mode", inputReader.isDisplayHelp());
			Assert.assertEquals("should be STDIN", TelDataType.STDIN, telephoneData.getDataInputType());
			Assert.assertEquals("", telephoneData.getDataPath());
		} catch (InvalidArgumentException e) {
			e.printStackTrace(System.err);
			fail("--help is a valid user input");
		}
	}

	@Test
	public void processDictionarySelection() throws Exception {

		// valid help param
		List<String> args = Stream.of("-Ddic==./data/dictionary.txt").collect(Collectors.toList());
		InputReader inputReader = new InputReader();

		try {
			inputReader.processUserInput(args);
			DictionaryData dictionaryData = inputReader.getDictionaryData();
			Assert.assertFalse("user is not in help mode", inputReader.isDisplayHelp());
			Assert.assertEquals("should be file", DictionaryType.FILE, dictionaryData.getDictionaryType());
			Assert.assertEquals("./data/dictionary.txt", dictionaryData.getDataPath());
		} catch (InvalidArgumentException e) {
			fail("-h is a valid user input");
		}

		args = new ArrayList<>();
		inputReader = new InputReader();
		try {
			inputReader.processUserInput(args);
			DictionaryData dictionaryData = inputReader.getDictionaryData();
			Assert.assertFalse("user is not in help mode", inputReader.isDisplayHelp());
			Assert.assertEquals("should be INTERNAL", DictionaryType.INTERNAL, dictionaryData.getDictionaryType());
			Assert.assertEquals("", dictionaryData.getDataPath());
		} catch (InvalidArgumentException e) {
			e.printStackTrace(System.err);
			fail("--help is a valid user input");
		}
	}

}
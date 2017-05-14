package com.aconex.code.challenge;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import com.aconex.code.challenge.domain.Dictionary;
import com.aconex.code.challenge.domain.Encoding;
import com.aconex.code.challenge.domain.telnumber.TelephoneNumber;
import com.aconex.code.challenge.exception.InvalidArgumentException;
import com.aconex.code.challenge.exception.ResourceNotFountException;
import com.aconex.code.challenge.exception.UnsupportedOperation;
import com.aconex.code.challenge.service.ConverterService;
import com.aconex.code.challenge.service.ConverterServiceImpl;
import com.aconex.code.challenge.service.dictionary.DictionaryFactory;
import com.aconex.code.challenge.service.encoding.EncodeLoader;
import com.aconex.code.challenge.service.telephone.TelDataLoader;
import com.aconex.code.challenge.service.telephone.TelephoneLoaderFactory;
import com.aconex.code.challenge.util.CommonsUtil;

/**
 * Main entry point to the 1-800Coding challenge
 *
 * @author: malaka
 * Date: 5/11/17
 * Time: 9:36 AM
 */
public class Runner {

	private static final Logger log = Logger.getLogger(Runner.class.getName());

	public static void main(String[] args) {
		try {

			List<String> userArguments = Arrays.asList(args);
			log.info(String.format("start with user arguments %s", userArguments));
			InputReader inputReader = new InputReader();
			inputReader.processUserInput(userArguments);

			// user request help
			if (inputReader.isDisplayHelp()) {
				InputReader.printHelp();
				System.exit(0);
			}

			// load encoding
			Encoding encoding = EncodeLoader.loadEncoding();

			// load dictionary
			Dictionary dictionary = DictionaryFactory.createDictionary(inputReader.getDictionaryData());
			ConverterService converterService = new ConverterServiceImpl(dictionary, encoding);

			// read user input
			TelDataLoader telDataLoader = TelephoneLoaderFactory.createDateLoader(inputReader.getTelephoneData());
			Stream<String> telephoneData = telDataLoader.load();

			// convert and display the result
			System.out.println("Start processing the numbers\n");
			telephoneData.forEach(a -> printResult(a, converterService));

		} catch (InvalidArgumentException e) {
			log.log(Level.WARNING, e.getMessage());
			InputReader.printHelp();
			System.exit(0);
		} catch (ResourceNotFountException e) {
			log.log(Level.SEVERE, e.getMessage());
			System.exit(-1);
		}
	}

	private static void printResult(String telNumber, ConverterService converterService) {
		StringBuffer sb = new StringBuffer(String.format("[%s] -> ", telNumber));
		String filteredNumber = CommonsUtil.filterDigitOnly(telNumber);
		try {
			List<TelephoneNumber> convert = converterService.convert(filteredNumber);
			sb.append(convert);
			System.out.println(sb.toString());
		} catch (UnsupportedOperation unsupportedOperation) {
			unsupportedOperation.printStackTrace(System.err);
		}
	}
}

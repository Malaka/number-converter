package com.aconex.code.challenge;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import com.aconex.code.challenge.exception.InvalidArgumentException;
import com.aconex.code.challenge.exception.ResourceNotFountException;
import com.aconex.code.challenge.service.telephone.TelDataLoader;
import com.aconex.code.challenge.service.telephone.TelephoneLoaderFactory;

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

			TelephoneLoaderFactory telephoneLoaderFactory = new TelephoneLoaderFactory();
			TelDataLoader telDataLoader = telephoneLoaderFactory.createDateLoader(inputReader.getTelephoneData());

			Stream<String> telephoneData = telDataLoader.load();

			telephoneData.forEach(System.out::println);

		} catch (InvalidArgumentException e) {
			log.log(Level.WARNING, e.getMessage());
			InputReader.printHelp();
			System.exit(0);
		} catch (ResourceNotFountException e) {
			log.log(Level.SEVERE, e.getMessage());
			System.exit(-1);
		}
	}
}

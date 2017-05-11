package com.aconex.code.challenge;

import java.util.Arrays;
import java.util.List;

import com.aconex.code.challenge.exception.InvalidArgumentException;

/**
 * Main entry point to the 1-800Coding challenge
 *
 * @author: malaka
 * Date: 5/11/17
 * Time: 9:36 AM
 */
public class Runner {

	public static void main(String[] args) {
		try {
			List<String> userArguments = Arrays.asList(args);
			InputReader inputReader = new InputReader();
			inputReader.processUserInput(userArguments);


		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}

	}
}

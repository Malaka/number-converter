package com.aconex.code.challenge;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main entry point to the 1-800Coding challenge
 *
 * @author: malaka
 * Date: 5/11/17
 * Time: 9:36 AM
 */
public class Runner {

	private static final String COMMAND_PARAM_PREFIX = "-";

	public static void main(String[] args) {
		List<String> arguments = extractArguments(args);
		System.out.println(arguments);
	}

	private static List<String> extractArguments(String[] args) {
		return Arrays.asList(args).stream()
			.filter(a -> a.startsWith(COMMAND_PARAM_PREFIX))
			.collect(Collectors.toList());
	}
}

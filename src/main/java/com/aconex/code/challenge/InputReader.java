package com.aconex.code.challenge;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.aconex.code.challenge.domain.DictionaryData;
import com.aconex.code.challenge.domain.TelephoneData;
import com.aconex.code.challenge.exception.InvalidArgumentException;

/**
 * @author: malaka
 * Date: 5/11/17
 * Time: 11:14 AM
 */
public class InputReader {

	private boolean displayHelp = false;

	private TelephoneData telephoneData;

	private DictionaryData dictionaryData;

	private static final Set<String> ARGS = Stream.of("-h", "--help", "-Dinput=", "-dinput=", "-Ddic=", "-ddic=")
		.collect(Collectors.toSet());

	public void processUserInput(List<String> userInputs) throws InvalidArgumentException {

		List<String> processedArgs = userInputs.stream()
			.filter(this::validArg)
			.collect(Collectors.toList());

		// check if there are any invalid arguments
		if (userInputs.size() != processedArgs.size()) {
			throw new InvalidArgumentException("Invalid arguments present");
		}

		//user request for help
		Optional<String> help = userInputs.stream()
			.filter(a -> (a.startsWith("-h") || a.startsWith("--help")))
			.findAny();
		if (help.isPresent()) {
			this.displayHelp = true;
		} else {

			// user input file for telephone numbers
			this.telephoneData = userInputs.stream()
				.filter(a -> (a.startsWith("-Dinput=") || a.startsWith("-dinput=")))
				.map(a -> TelephoneData.file(a.substring(8)))
				.findAny().orElse(TelephoneData.stdin());

			//user override for dictionary
			this.dictionaryData = userInputs.stream()
				.filter(a -> (a.startsWith("-Ddic=") || a.startsWith("-ddic=")))
				.map(a -> DictionaryData.file(a.substring(7)))
				.findAny().orElse(DictionaryData.internal());
		}
	}

	public static void printHelp() {
		System.out.println("################################################# HELP #########################################");
		System.out.println("./run.sh [-h] [--help] [-Dinput=<input file for tel numbers>] [-Ddic=<file path for dictionary>]");
		System.out.println("################################################################################################");
	}

	private boolean validArg(String arg) {
		return ARGS.parallelStream().anyMatch(arg::startsWith);
	}


	public boolean isDisplayHelp() {
		return displayHelp;
	}

	public TelephoneData getTelephoneData() {
		return telephoneData;
	}

	public DictionaryData getDictionaryData() {
		return dictionaryData;
	}
}

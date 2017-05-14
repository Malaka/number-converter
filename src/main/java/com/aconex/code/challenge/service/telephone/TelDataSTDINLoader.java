package com.aconex.code.challenge.service.telephone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Reads telephone numbers from STD IN
 * @author: malaka
 * Date: 5/11/17
 * Time: 2:57 PM
 */
public class TelDataSTDINLoader implements TelDataLoader {

	private static final Logger log = Logger.getLogger(TelDataSTDINLoader.class.getName());

	@Override
	public Stream<String> load() {
		log.info("reading from STDIN");
		System.out.println("No input file given. Start reading from the STDIN." +
			"\nPlease enter numbers separated by new line.You may end input by entering empty line");

		List<String> userDataInput = new ArrayList<>();
		String line;
		Scanner stdin = new Scanner(System.in);
		while (stdin.hasNextLine() && !(line = stdin.nextLine()).equals("")) {
			userDataInput.add(line);
		}
		stdin.close();
		return userDataInput.stream();
	}
}

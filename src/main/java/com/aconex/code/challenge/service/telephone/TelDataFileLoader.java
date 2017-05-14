package com.aconex.code.challenge.service.telephone;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import com.aconex.code.challenge.exception.ResourceNotFountException;

/**
 * Loads telephone number data from a user input file name
 * @author: malaka
 * Date: 5/11/17
 * Time: 2:56 PM
 */
public class TelDataFileLoader implements TelDataLoader {

	private static final Logger log = Logger.getLogger(TelDataFileLoader.class.getName());

	private String fileName;

	public TelDataFileLoader(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public Stream<String> load() throws ResourceNotFountException {
		log.info(MessageFormat.format("start Telephone date input from {0}", fileName));
		try (Scanner scanner = new Scanner(new File(fileName))) {
			List<String> telephoneNumbers = new ArrayList<>();
			while (scanner.hasNext()) {
				telephoneNumbers.add(scanner.nextLine());
			}
			return telephoneNumbers.stream();
		} catch (IOException e) {
			String errorMsg = MessageFormat.format("Give input=[{0}] for telephone file done not found", fileName);
			log.log(Level.SEVERE, errorMsg);
			throw new ResourceNotFountException(errorMsg, e);
		}
	}
}

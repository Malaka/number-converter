package com.aconex.code.challenge.service.dictionary;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aconex.code.challenge.domain.Dictionary;
import com.aconex.code.challenge.exception.ResourceNotFountException;

/**
 * Loads dictionary from a user given file nam
 * @author: malaka
 * Date: 5/12/17
 * Time: 2:14 AM
 */
public class FileDictionaryLoader extends DictionaryLoader {

	private static final Logger log = Logger.getLogger(FileDictionaryLoader.class.getName());

	private String fileName;

	public FileDictionaryLoader(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public Dictionary load() throws ResourceNotFountException {
		log.info(MessageFormat.format("start Dictionary date input from {0}", fileName));
		try (Scanner scanner = new Scanner(new File(fileName))) {
			return getDictionary(scanner);
		} catch (IOException e) {
			String errorMsg = MessageFormat.format("Give input=[{0}] for Dictionary file done not found", fileName);
			log.log(Level.SEVERE, errorMsg);
			throw new ResourceNotFountException(errorMsg, e);
		}
	}

}

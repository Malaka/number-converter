package com.aconex.code.challenge.service.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.aconex.code.challenge.domain.Dictionary;
import com.aconex.code.challenge.exception.ResourceNotFountException;
import com.aconex.code.challenge.util.CommonsUtil;

/**
 * Base dictionary loader
 *
 * @author: malaka
 * Date: 5/12/17
 * Time: 2:12 AM
 */
public abstract class DictionaryLoader {

	public abstract Dictionary load() throws ResourceNotFountException;

	protected Dictionary getDictionary(Scanner scanner) {
		List<String> words = new ArrayList<>();
		while (scanner.hasNext()) {
			String word = CommonsUtil.alphaOnly(scanner.nextLine()).toUpperCase();
			if (!words.contains(word)) { // do not want duplicate words
				words.add(word);
			}
		}
		return Dictionary.of(words);
	}
}

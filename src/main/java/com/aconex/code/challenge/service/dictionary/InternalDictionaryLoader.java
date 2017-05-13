package com.aconex.code.challenge.service.dictionary;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aconex.code.challenge.domain.Const;
import com.aconex.code.challenge.domain.Dictionary;
import com.aconex.code.challenge.exception.ResourceNotFountException;
import com.aconex.code.challenge.util.CommonsUtil;

/**
 * loads internal dictionary from the classpath
 *
 * @author: malaka
 * Date: 5/12/17
 * Time: 2:15 AM
 */
public class InternalDictionaryLoader extends DictionaryLoader {

	private static final Logger log = Logger.getLogger(InternalDictionaryLoader.class.getName());

	@Override
	public Dictionary load() throws ResourceNotFountException {
		log.info("start loading internal dictionary");
		try (Scanner scanner = new Scanner(CommonsUtil.getInputStream(Const.INTERNAL_DICTIONARY_RESOURCE))) {
			return getDictionary(scanner);
		} catch (Exception e) {
			String errorMsg = "internal dictionary loading fail.Exiting";
			log.log(Level.SEVERE, errorMsg);
			throw new ResourceNotFountException(errorMsg, e);
		}
	}
}

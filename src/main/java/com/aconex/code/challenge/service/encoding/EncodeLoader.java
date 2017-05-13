package com.aconex.code.challenge.service.encoding;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aconex.code.challenge.domain.Const;
import com.aconex.code.challenge.domain.Encoding;
import com.aconex.code.challenge.exception.ResourceNotFountException;
import com.aconex.code.challenge.util.CommonsUtil;

/**
 * Loads the number char mapping encoding from the classpath
 *
 * @author: malaka
 * Date: 5/12/17
 * Time: 2:59 AM
 */
public class EncodeLoader {

	private static final Logger log = Logger.getLogger(EncodeLoader.class.getName());

	public static Encoding loadEncoding() throws ResourceNotFountException {

		log.info("start loading internal dictionary");
		try (Scanner scanner = new Scanner(CommonsUtil.getInputStream(Const.NUMBER_ENCODING_RESOURCE))) {
			Map<Character, Character> mapData = new HashMap<>();
			while (scanner.hasNext()) {
				String line = scanner.nextLine().trim();
				if (!line.startsWith("#")) { // skip comments
					String[] lineSplit = line.split("=");
					String alpha = CommonsUtil.filterAlphaOnly(lineSplit[0]).toUpperCase();
					String number = CommonsUtil.filterDigitOnly(lineSplit[1]);
					mapData.put(alpha.charAt(0), number.charAt(0));
				}
			}
			return Encoding.of(mapData);
		} catch (Exception e) {
			String errorMsg = "internal encoding loading fail.Exiting";
			log.log(Level.SEVERE, errorMsg);
			throw new ResourceNotFountException(errorMsg, e);
		}
	}
}

package com.aconex.code.challenge.service;

import java.util.List;

import com.aconex.code.challenge.domain.telnumber.TelephoneNumber;
import com.aconex.code.challenge.exception.UnsupportedOperation;

/**
 * Given number to word conversion happens through this class.
 * @author: malaka
 * Date: 5/13/17
 * Time: 11:47 AM
 */
public interface ConverterService {

	/**
	 * convert a given telephone number to word combinations
	 * @param number telephone number
	 * @return possible word combinations for the given number
	 * @throws UnsupportedOperation when try to combine two numbers
	 */
	public List<TelephoneNumber> convert(String number) throws UnsupportedOperation;

}

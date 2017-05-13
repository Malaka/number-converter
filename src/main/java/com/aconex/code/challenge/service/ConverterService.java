package com.aconex.code.challenge.service;

import java.util.List;

import com.aconex.code.challenge.domain.telnumber.TelephoneNumber;
import com.aconex.code.challenge.exception.UnsupportedOperation;

/**
 * @author: malaka
 * Date: 5/13/17
 * Time: 11:47 AM
 */
public interface ConverterService {

	public List<TelephoneNumber> convert(String number) throws UnsupportedOperation;

}

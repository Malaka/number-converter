package com.aconex.code.challenge.service.telephone;

import java.util.stream.Stream;

import com.aconex.code.challenge.exception.ResourceNotFountException;

/**
 * Base telephone number data loader
 * @author: malaka
 * Date: 5/11/17
 * Time: 2:55 PM
 */
public interface TelDataLoader {

	public Stream<String> load() throws ResourceNotFountException;

}

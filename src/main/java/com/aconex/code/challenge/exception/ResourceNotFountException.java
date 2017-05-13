package com.aconex.code.challenge.exception;

/**
 * @author: malaka
 * Date: 5/11/17
 * Time: 3:33 PM
 */
public class ResourceNotFountException extends Exception {

	public ResourceNotFountException(String message) {
		super(message);
	}

	public ResourceNotFountException(String message, Throwable cause) {
		super(message, cause);
	}
}

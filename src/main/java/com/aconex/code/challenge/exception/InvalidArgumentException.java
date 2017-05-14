package com.aconex.code.challenge.exception;

/**
 * Throws when users enter invalid arguments to the program
 * @author: malaka
 * Date: 5/11/17
 * Time: 11:11 AM
 */
public class InvalidArgumentException extends Exception {

	public InvalidArgumentException(String message) {
		super(message);
	}
}

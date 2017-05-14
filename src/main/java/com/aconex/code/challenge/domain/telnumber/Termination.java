package com.aconex.code.challenge.domain.telnumber;

import com.aconex.code.challenge.exception.UnsupportedOperation;

/**
 * Special Node which represent the beginning of the @{@link TelephoneNumber}
 * This is used to identify when to check for word validity when possible combinations are evaluated
 *
 * @author: malaka
 * Date: 5/14/17
 * Time: 2:27 AM
 */
public class Termination extends TelNode {

	private Termination(String val) {
		super(val);
	}

	public static Termination of() {
		return new Termination("");
	}

	@Override
	public boolean isDigit() {
		return false;
	}

	@Override
	public boolean isString() {
		return false;
	}

	@Override
	public boolean isTerminating() {
		return true;
	}

	@Override
	public TelNode combine(TelNode node) throws UnsupportedOperation {
		return null;
	}
}

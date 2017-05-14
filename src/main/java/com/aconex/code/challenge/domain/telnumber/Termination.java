package com.aconex.code.challenge.domain.telnumber;

import com.aconex.code.challenge.exception.UnsupportedOperation;

/**
 * @author: malaka
 * Date: 5/14/17
 * Time: 2:27 AM
 */
public class Termination extends TelNode {

	public static Termination of() {
		return new Termination("");
	}

	private Termination(String val) {
		super(val);
	}

	@Override
	public boolean isDigit() {
		return false;
	}

	@Override
	public boolean isString() {
		return true;
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

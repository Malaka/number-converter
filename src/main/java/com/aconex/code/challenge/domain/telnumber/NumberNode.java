package com.aconex.code.challenge.domain.telnumber;

import com.aconex.code.challenge.exception.UnsupportedOperation;

/**
 * @author: malaka
 * Date: 5/13/17
 * Time: 12:34 PM
 */
public class NumberNode extends TelNode {

	public static NumberNode of(String val) {
		return new NumberNode(val);
	}

	public static NumberNode ofChar(Character val) {
		return new NumberNode(val.toString());
	}

	private NumberNode(String val) {
		super(val);
	}

	@Override
	public boolean isDigit() {
		return true;
	}

	@Override
	public boolean isString() {
		return false;
	}

	@Override
	public boolean isTerminating() {
		return false;
	}

	@Override
	public TelNode combine(TelNode node) throws UnsupportedOperation {
		throw new UnsupportedOperation("combine to digit is not supported");
	}
}

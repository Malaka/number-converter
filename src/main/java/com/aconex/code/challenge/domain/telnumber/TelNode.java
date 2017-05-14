package com.aconex.code.challenge.domain.telnumber;

import com.aconex.code.challenge.exception.UnsupportedOperation;

/**
 * @author: malaka
 * Date: 5/13/17
 * Time: 12:33 PM
 */
public abstract class TelNode {

	protected String val;

	protected TelNode(String val) {
		this.val = val;
	}

	public abstract boolean isDigit();

	public abstract boolean isString();

	public abstract boolean isTerminating();

	public abstract TelNode combine(TelNode node) throws UnsupportedOperation;

	public String getVal() {
		return val;
	}

	@Override
	public String toString() {
		return this.val;
	}
}

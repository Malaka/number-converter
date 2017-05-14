package com.aconex.code.challenge.domain.telnumber;

import com.aconex.code.challenge.exception.UnsupportedOperation;

/**
 * Base class represent part of telephone number
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TelNode)) return false;

		TelNode telNode = (TelNode) o;

		return val.equals(telNode.val);

	}

	@Override
	public int hashCode() {
		return val.hashCode();
	}
}

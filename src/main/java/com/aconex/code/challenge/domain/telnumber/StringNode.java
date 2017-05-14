package com.aconex.code.challenge.domain.telnumber;

/**
 * @author: malaka
 * Date: 5/13/17
 * Time: 12:37 PM
 */
public class StringNode extends TelNode {


	public static StringNode of(String val) {
		return new StringNode(val);
	}

	public static StringNode ofChar(Character val) {
		return new StringNode(val.toString());
	}

	private StringNode(String val) {
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
		return false;
	}

	@Override
	public TelNode combine(TelNode node) {
		return StringNode.of(this.val.concat(node.getVal()));
	}
}

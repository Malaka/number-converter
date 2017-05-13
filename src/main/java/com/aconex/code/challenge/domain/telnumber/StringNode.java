package com.aconex.code.challenge.domain.telnumber;

/**
 * @author: malaka
 * Date: 5/13/17
 * Time: 12:37 PM
 */
public class StringNode extends TelNode {

	private boolean validated;

	public static StringNode of(String val) {
		return new StringNode(val, false);
	}

	public static StringNode validWord(String val) {
		return new StringNode(val, true);
	}

	public static StringNode ofChar(Character val) {
		return new StringNode(val.toString(), false);
	}

	private StringNode(String val, boolean validated) {
		super(val);
		this.validated = validated;
	}

	@Override
	public boolean isDigit() {
		return false;
	}

	@Override
	public boolean isValidate() {
		return validated;
	}

	@Override
	public TelNode combine(TelNode node) {
		return StringNode.of(this.val.concat(node.getVal()));
	}
}

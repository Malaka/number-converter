package com.aconex.code.challenge.domain.telnumber;

import java.util.Iterator;
import java.util.List;

import com.aconex.code.challenge.domain.Const;

/**
 * @author: malaka
 * Date: 5/13/17
 * Time: 11:40 AM
 */
public class TelephoneNumber {

	private List<TelNode> nodes;

	private TelephoneNumber(List<TelNode> nodes) {
		this.nodes = nodes;
	}

	public static TelephoneNumber of(List<TelNode> nodes) {
		return new TelephoneNumber(nodes);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		Iterator<TelNode> iterator = nodes.iterator();
		while (iterator.hasNext()) {
			TelNode node = iterator.next();
			sb.append(node);
			if (iterator.hasNext()) {
				sb.append(Const.SEPARATOR);
			}
		}
		return sb.toString();
	}
}

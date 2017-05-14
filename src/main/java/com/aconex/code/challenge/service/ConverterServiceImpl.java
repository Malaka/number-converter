package com.aconex.code.challenge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.aconex.code.challenge.domain.Dictionary;
import com.aconex.code.challenge.domain.Encoding;
import com.aconex.code.challenge.domain.telnumber.NumberNode;
import com.aconex.code.challenge.domain.telnumber.StringNode;
import com.aconex.code.challenge.domain.telnumber.TelNode;
import com.aconex.code.challenge.domain.telnumber.TelephoneNumber;
import com.aconex.code.challenge.exception.UnsupportedOperation;

/**
 * ConverterService Impl which handles the conversion
 * @author: malaka
 * Date: 5/13/17
 * Time: 11:50 AM
 */
public class ConverterServiceImpl implements ConverterService {

	private Dictionary dictionary;

	private Encoding encoding;

	public ConverterServiceImpl(Dictionary dictionary, Encoding encoding) {
		this.dictionary = dictionary;
		this.encoding = encoding;
	}

	@Override
	public List<TelephoneNumber> convert(String number) throws UnsupportedOperation {
		List<List<TelNode>> encoded = encoding.encode(number);
		List<List<TelNode>> matchResults = this.expandAndSearch(encoded);
		return matchResults
			.stream()
			.map(TelephoneNumber::of)
			.collect(Collectors.toList());
	}

	private List<List<TelNode>> expandAndSearch(List<List<TelNode>> lists) throws UnsupportedOperation {
		List<List<TelNode>> resultLists = new ArrayList<>();
		if (lists.size() == 0) {
			resultLists.add(new ArrayList<>());
			return resultLists;
		} else {
			List<TelNode> firstList = lists.get(0);
			List<List<TelNode>> remainingLists = expandAndSearch(lists.subList(1, lists.size()));
			for (TelNode newAddition : firstList) {
				for (List<TelNode> remainingList : remainingLists) {
					ArrayList<TelNode> resultList = new ArrayList<>();
					if (!remainingList.isEmpty()) {
						TelNode firstRemaining = remainingList.iterator().next();
						// two consecutive numbers are not possible - ignore the combination
						if (!(newAddition.isDigit() && firstRemaining.isDigit())) {
							// new addition is a number or terminating. check for correct word through dictionary
							if ((newAddition.isDigit() || newAddition.isTerminating()) && firstRemaining.isString()) {
								validateWords(newAddition, firstRemaining, remainingList, resultLists);
							} else if (firstRemaining.isString()) { // next addition is char. possible for combining
								TelNode combine = newAddition.combine(firstRemaining);
								resultList.add(combine);
								resultList.addAll(remainingList.subList(1, remainingList.size()));
								resultLists.add(resultList);
							} else { // first remaining is number and addition is char or terminating
								if (!newAddition.isTerminating()) {
									resultList.add(newAddition);
								}
								resultList.addAll(remainingList);
								resultLists.add(resultList);
							}
						}
					} else {  // no remaining list.Add the new addition
						resultList.add(newAddition);
						resultLists.add(resultList);
					}
				}
			}
		}
		return resultLists;
	}

	private void validateWords(TelNode newAddition, TelNode firstRemainingNode, List<TelNode> remainingList, List<List<TelNode>> resultLists) {
		String possibleWord = firstRemainingNode.getVal();
		Set<List<String>> validWordComb = dictionary.expandSearch(possibleWord);
		if (!validWordComb.isEmpty()) {
			for (List<String> wordCombo : validWordComb) {
				ArrayList<TelNode> wordExpansion = new ArrayList<>();
				List<StringNode> stringNodes = wordCombo
					.stream()
					.map(StringNode::of)
					.collect(Collectors.toList());
				if (newAddition.isDigit()) {
					wordExpansion.add(NumberNode.of(newAddition.getVal()));
				}
				wordExpansion.addAll(stringNodes);
				wordExpansion.addAll(remainingList.subList(1, remainingList.size()));
				resultLists.add(wordExpansion);
			}
		}
	}
}

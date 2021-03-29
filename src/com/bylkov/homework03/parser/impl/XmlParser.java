package com.bylkov.homework03.parser.impl;

import java.util.ArrayDeque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bylkov.homework03.entity.Attribute;
import com.bylkov.homework03.entity.Node;
import com.bylkov.homework03.parser.Parser;

public class XmlParser implements Parser {

	private static final String FIRST_LINE_PATTERN = "\\<\\?.*\\?\\>";
	private static final String OPEN_ELEMENT_PATTERN = "\\<[a-z,-]+\\>";
	private static final String OPEN_ELEMENT_WITH_ATTR_PATTERN = "\\<[a-z,-]+(\\s[a-z]+\\=\"[a-zA-Z0-9]+\")+\\>";
	private static final String CLOSE_ELEMENT_PATTERN = "\\<\\/[a-z,-]+\\>";
	private static final int ATTR_NAME_INDEX = 0;
	private static final int ATTR_VALUE_INDEX = 1;
	private final Pattern firstLinePattern = Pattern.compile(FIRST_LINE_PATTERN);
	private final Pattern openElementPattern = Pattern.compile(OPEN_ELEMENT_PATTERN);
	private final Pattern openElementWithAttrPattern = Pattern.compile(OPEN_ELEMENT_WITH_ATTR_PATTERN);
	private final Pattern closeElementPattern = Pattern.compile(CLOSE_ELEMENT_PATTERN);
	
	private ArrayDeque<Node> stackNodes = new ArrayDeque<>();
	private Node rootNode = new Node();
	
	public static XmlParser getInstance() {
        return XmlParser.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final XmlParser instance = new XmlParser();
    }

    private XmlParser() {

    }

	@Override
	public Node parseStrings(List<String> fileContent) {
		for(String contentString : fileContent) {
			parseString(contentString);
		}
		return rootNode;
	}
	
	private void parseString(String stringToParse) {
		Matcher firstLineMatcher = firstLinePattern.matcher(stringToParse);
		
		if ("".equals(stringToParse) || firstLineMatcher.find()) {
			return;
		}
		
		if (stringToParse.charAt(0) == '<' && stringToParse.charAt(1) == '/') {
			parseStringWithClosingTag(stringToParse);
		}
		
		if (stringToParse.charAt(0) == '<' && stringToParse.charAt(1) != '/') {
			parseStringWithOpenTag(stringToParse);
		}
		
		if (stringToParse.charAt(0) != '<' && stringToParse.charAt(1) != '/') {
			parseStringWithElementValue(stringToParse);
		}
	}
	
	private void parseStringWithOpenTag(String stringToParse) {
		Matcher openElementMatcher = openElementPattern.matcher(stringToParse);
		Matcher openElementWithAttrMatcher = openElementWithAttrPattern.matcher(stringToParse);
		
		if (openElementMatcher.find()) {
			String buffer = stringToParse.substring(openElementMatcher.start() + 1, openElementMatcher.end() - 1);
			Node node = new Node();
			node.setName(buffer);
			stackNodes.push(node);
			if (stringToParse.length() == openElementMatcher.end()) {
				parseString("");
			} else {
				parseString(stringToParse.substring(openElementMatcher.end()));
			}
		} else {
			if (openElementWithAttrMatcher.find()) {
				String buffer = stringToParse.substring(openElementWithAttrMatcher.start() + 1, openElementWithAttrMatcher.end() - 1);
				String[] splittedBuffer = buffer.split(" ");
				Node node = new Node();
				node.setName(splittedBuffer[0]);
				for (int i = 1; i < splittedBuffer.length; i++) {
					String[] attrBuffer = splittedBuffer[i].split("=");
					node.addAttribute(new Attribute(attrBuffer[ATTR_NAME_INDEX], 
							attrBuffer[ATTR_VALUE_INDEX].substring(1, attrBuffer[ATTR_VALUE_INDEX].length() - 1)));
				}
				stackNodes.push(node);
				if (stringToParse.length() == openElementWithAttrMatcher.end()) {
					parseString("");
				} else {
					parseString(stringToParse.substring(openElementWithAttrMatcher.end()));
				}
			}
		}
	}
	
	private void parseStringWithClosingTag(String stringToParse) {
		Matcher closeElementMatcher = closeElementPattern.matcher(stringToParse);
		if (closeElementMatcher.find()) {
			Node closingElement = stackNodes.pop();
			Node currentFirst = stackNodes.peekFirst();
			if (currentFirst == null) {
				rootNode = closingElement;
			} else {
				currentFirst.addChild(closingElement);
				if (stringToParse.length() == closeElementMatcher.end()) {
					parseString("");
				} else {
					parseString(stringToParse.substring(closeElementMatcher.end()));
				}
			}
		}
	}
	
	private void parseStringWithElementValue(String stringToParse) {
		String buffer;
		String argumentToParse;
		if (stringToParse.contains("<")) {
			int border = stringToParse.indexOf("<");
			buffer = stringToParse.substring(0, border - 1);
			argumentToParse = stringToParse.substring(border);
		} else {
			buffer = stringToParse;
			argumentToParse = "";
		}
		Node currentFirst = stackNodes.peekFirst();
		currentFirst.setValue(buffer);
		parseString(argumentToParse);
	}

}

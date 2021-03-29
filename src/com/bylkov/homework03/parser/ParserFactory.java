package com.bylkov.homework03.parser;

import com.bylkov.homework03.parser.impl.XmlParser;

public class ParserFactory {
	
	private final Parser parser = XmlParser.getInstance();

	public static ParserFactory getInstance() {
        return ParserFactory.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final ParserFactory instance = new ParserFactory();
    }

    private ParserFactory() {

    }
    
    public Parser getParser() {
		return parser;
	}
}

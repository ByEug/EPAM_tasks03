package com.bylkov.homework03.reader;

import com.bylkov.homework03.reader.impl.XmlCustomFileReader;

public class CustomFileReaderFactory {
	
	private final CustomFileReader customFileReader = XmlCustomFileReader.getInstance();

	public static CustomFileReaderFactory getInstance() {
        return CustomFileReaderFactory.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final CustomFileReaderFactory instance = new CustomFileReaderFactory();
    }

    private CustomFileReaderFactory() {

    }
    
    public CustomFileReader getCustomFileReader() {
		return customFileReader;
	}
}

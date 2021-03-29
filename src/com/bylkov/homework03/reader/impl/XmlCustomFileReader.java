package com.bylkov.homework03.reader.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.bylkov.homework03.reader.CustomFileReader;


public class XmlCustomFileReader implements CustomFileReader {
	
	public static XmlCustomFileReader getInstance() {
        return XmlCustomFileReader.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final XmlCustomFileReader instance = new XmlCustomFileReader();
    }

    private XmlCustomFileReader() {

    }
	
	@Override
	public List<String> readFromFile(String fileName) throws IOException {
		ArrayList<String> fileContent = new ArrayList<>();
		File fileToRead = new File(fileName);
		
		try (FileReader fileReader = new FileReader(fileToRead);
				BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			String stringParseBuffer = bufferedReader.readLine().trim();
			while (stringParseBuffer != null) {
				fileContent.add(stringParseBuffer);
				stringParseBuffer = bufferedReader.readLine();
				if (stringParseBuffer != null) {
					stringParseBuffer = stringParseBuffer.trim();
				}
			}
		}
		return fileContent;
	}
}

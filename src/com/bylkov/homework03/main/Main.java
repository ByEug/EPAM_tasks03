package com.bylkov.homework03.main;

import java.io.IOException;
import java.util.List;

import com.bylkov.homework03.entity.Node;
import com.bylkov.homework03.output.NodePrinterFactory;
import com.bylkov.homework03.parser.ParserFactory;
import com.bylkov.homework03.reader.CustomFileReader;
import com.bylkov.homework03.reader.CustomFileReaderFactory;

public class Main {

	public static void main(String[] args) {
		
		CustomFileReader customFileReader = CustomFileReaderFactory.getInstance().getCustomFileReader();
		try {
			List<String> content = customFileReader.readFromFile("resources/breakfast.xml");
			Node rootNode = ParserFactory.getInstance().getParser().parseStrings(content);
			NodePrinterFactory.getInstance().getNodePrinter().printNode(rootNode, "\t");
			
		} catch (IOException e) {
			System.out.println("Error during the reading from file");
		}
		
	}
}

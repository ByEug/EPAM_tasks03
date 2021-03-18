package com.bylkov.homework03.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.bylkov.homework03.XmlParser;
import com.bylkov.homework03.entity.Node;

public class Main {

	public static void main(String[] args) {
		
		File xmlFile = new File("resources/breakfast.xml");
		XmlParser parser = new XmlParser();
		
		try (FileReader fileReader = new FileReader(xmlFile);
				BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			
			Node rootNode = parser.parseXmlFile(bufferedReader);
			PrintXmlInfo.printNode(rootNode, "\t");
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found in project's resources");
		} catch (IOException e) {
			System.out.println("Error during reading the file");
		}
		
	}
}

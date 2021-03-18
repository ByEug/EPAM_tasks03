package com.bylkov.homework03.main;

import com.bylkov.homework03.entity.Attribute;
import com.bylkov.homework03.entity.Node;

public class PrintXmlInfo {
	
	public static void printNode(Node node, String tab) {
		System.out.println(tab + node.getName());
		if (!node.getAttributes().isEmpty()) {
			for(Attribute attribute : node.getAttributes()) {
				System.out.println(tab + " " + attribute.getName() + "=" + attribute.getValue());
			}
		}
		if (node.getValue() != null) {
			System.out.println(tab + " " + node.getValue());
		}
		if (node.getChildrenNodes() != null) {
			for(Node childNode: node.getChildrenNodes()) {
				printNode(childNode, tab + tab);
			}
		}
	}
}

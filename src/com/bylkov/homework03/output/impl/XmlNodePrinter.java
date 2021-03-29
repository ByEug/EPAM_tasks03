package com.bylkov.homework03.output.impl;

import com.bylkov.homework03.entity.Attribute;
import com.bylkov.homework03.entity.Node;
import com.bylkov.homework03.output.NodePrinter;

public class XmlNodePrinter implements NodePrinter{

	public static XmlNodePrinter getInstance() {
        return XmlNodePrinter.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final XmlNodePrinter instance = new XmlNodePrinter();
    }

    private XmlNodePrinter() {

    }
	
	@Override
	public void printNode(Node node, String tab) {
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

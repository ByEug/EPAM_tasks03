package com.bylkov.homework03.output;

import com.bylkov.homework03.output.impl.XmlNodePrinter;

public class NodePrinterFactory {
	
	private final NodePrinter nodeReader = XmlNodePrinter.getInstance();

	public static NodePrinterFactory getInstance() {
        return NodePrinterFactory.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final NodePrinterFactory instance = new NodePrinterFactory();
    }

    private NodePrinterFactory() {

    }
    
    public NodePrinter getNodePrinter() {
		return nodeReader;
	}
}

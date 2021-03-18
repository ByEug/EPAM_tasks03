package com.bylkov.homework03.entity;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private String name;
	private List<Attribute> attributes;
	
	private List<Node> childrenNodes;
	private String value;
	
	public Node() {
		name = null;
		value = null;
		attributes = new ArrayList<>();
		childrenNodes = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public void addAttribute(Attribute attribute) {
		attributes.add(attribute);
	}
	
	public void addChild(Node node) {
		childrenNodes.add(node);
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public List<Node> getChildrenNodes() {
		return childrenNodes;
	}
	
	
}

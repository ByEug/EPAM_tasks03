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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributes == null) ? 0 : attributes.hashCode());
		result = prime * result + ((childrenNodes == null) ? 0 : childrenNodes.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (attributes == null) {
			if (other.attributes != null)
				return false;
		} else if (!attributes.equals(other.attributes))
			return false;
		if (childrenNodes == null) {
			if (other.childrenNodes != null)
				return false;
		} else if (!childrenNodes.equals(other.childrenNodes))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}

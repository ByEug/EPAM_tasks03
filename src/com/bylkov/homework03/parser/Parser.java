package com.bylkov.homework03.parser;

import java.util.List;
import com.bylkov.homework03.entity.Node;

public interface Parser {
	
	Node parseStrings(List<String> fileContent);
}

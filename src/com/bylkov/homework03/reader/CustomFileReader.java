package com.bylkov.homework03.reader;

import java.io.IOException;
import java.util.List;

public interface CustomFileReader {

	List<String> readFromFile(String fileName) throws IOException;
}

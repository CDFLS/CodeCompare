package org.ice1000.compare;

import java.io.File;
import java.io.IOException;

public interface Compare {
	String getPreprocessedCode(File file) throws IOException;

	default String getPreprocessedCode(String path) throws IOException {
		return getPreprocessedCode(new File(path));
	}

	double getSimilarity(String code1, String code2);
}

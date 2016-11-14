package org.ice1000.compare.core;

import java.io.File;
import java.io.IOException;

public interface Compare {
	/**
	 * returns the preprocessed code
	 *
	 * @param file source code file
	 * @return preprocessed code
	 * @throws IOException Check Exception is rubbish
	 */
	String getPreprocessedCode(File file) throws IOException;

	default String getPreprocessedCode(String path) throws IOException {
		return getPreprocessedCode(new File(path));
	}

	double getSimilarity(String code1, String code2);
}

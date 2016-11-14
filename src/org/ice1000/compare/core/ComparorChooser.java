package org.ice1000.compare.core;

import org.ice1000.compare.lang.CppCompare;
import org.ice1000.compare.lang.JavaCompare;
import org.ice1000.compare.lang.PascalCompare;
import org.ice1000.compare.lang.PythonCompare;

/**
 * Created by ice1000 on 2016/11/14.
 *
 * @author ice1000
 */
public class ComparorChooser {
	private static final String LANGUAGE_C_PLUS_PLUS = "C/C++";
	private static final String LANGUAGE_JAVA = "Java";
	private static final String LANGUAGE_PASCAL = "Pascal";
	private static final String LANGUAGE_PYTHON = "Python";

	private Compare cppCompare;
	private Compare javaCompare;
	private Compare pasCompare;
	private Compare pyCompare;

	public ComparorChooser() {
		cppCompare = new CppCompare();
		javaCompare = new JavaCompare();
		pasCompare = new PascalCompare();
		pyCompare = new PythonCompare();
	}

	public Compare chooseCompare(String path) {
		if (path.endsWith(".cpp") || path.endsWith(".c"))
			return cppCompare;
		else if (path.endsWith(".java"))
			return javaCompare;
		else if (path.endsWith(".pas"))
			return pasCompare;
		else if (path.endsWith(".py"))
			return pyCompare;
		else throw new RuntimeException("");
	}

	public String chooseLanguage(String path) {
		if (path.endsWith(".cpp") || path.endsWith(".c"))
			return LANGUAGE_C_PLUS_PLUS;
		else if (path.endsWith(".java"))
			return LANGUAGE_JAVA;
		else if (path.endsWith(".pas"))
			return LANGUAGE_PASCAL;
		else if (path.endsWith(".py"))
			return LANGUAGE_PYTHON;
		else throw new RuntimeException("");
	}
}
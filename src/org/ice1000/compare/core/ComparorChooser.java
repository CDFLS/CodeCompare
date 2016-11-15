package org.ice1000.compare.core;

import org.ice1000.compare.lang.*;

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
	private static final String LANGUAGE_RUBY = "Ruby";

	private Compare cppCompare;
	private Compare javaCompare;
	private Compare pasCompare;
	private Compare pyCompare;
	private Compare rbCompare;

	public ComparorChooser() {
		cppCompare = new CppCompare();
		javaCompare = new JavaCompare();
		pasCompare = new PascalCompare();
		pyCompare = new PythonCompare();
		rbCompare = new RubyCompare();
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
		else if (path.endsWith(".rb"))
			return rbCompare;
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
		else if (path.endsWith(".rb"))
			return LANGUAGE_RUBY;
		else throw new RuntimeException("");
	}
}
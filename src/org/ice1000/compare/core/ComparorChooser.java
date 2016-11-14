package org.ice1000.compare.core;

import org.ice1000.compare.data.NameCodeDataHolder;
import org.ice1000.compare.lang.CWithClassesCompare;
import org.ice1000.compare.lang.JavaCompare;
import org.ice1000.compare.lang.PascalCompare;

/**
 * Created by ice1000 on 2016/11/14.
 *
 * @author ice1000
 */
public class ComparorChooser {
	private Compare cWithClassesCompare;
	private Compare javaCompare;
	private Compare pascalCompare;

	public ComparorChooser() {
		cWithClassesCompare = new CWithClassesCompare();
		javaCompare = new JavaCompare();
		pascalCompare = new PascalCompare();
	}

	public Compare chooseCompare(String path) {
		if (path.endsWith(".cpp") || path.endsWith(".c"))
			return cWithClassesCompare;
		else if (path.endsWith(".java"))
			return javaCompare;
		else if (path.endsWith(".pas"))
			return pascalCompare;
		else throw new RuntimeException("");
	}

	public int chooseLanguage(String path) {
		if (path.endsWith(".cpp") || path.endsWith(".c"))
			return NameCodeDataHolder.LANGUAGE_C_PLUS_PLUS;
		else if (path.endsWith(".java"))
			return NameCodeDataHolder.LANGUAGE_JAVA;
		else if (path.endsWith(".pas"))
			return NameCodeDataHolder.LANGUAGE_PASCAL;
		else throw new RuntimeException("");
	}
}
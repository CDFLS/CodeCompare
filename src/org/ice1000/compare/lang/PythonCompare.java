package org.ice1000.compare.lang;

import org.ice1000.compare.core.AbstractCompare;

/**
 * Created by ice1000 on 2016/11/14.
 *
 * @author ice1000
 */
public class PythonCompare extends AbstractCompare {
	@SuppressWarnings("WeakerAccess")
	public static final String preservedWords = "and|as|assert" +
			"|break|class|continue|def|del|elif|else|except" +
			"|exec|for|finally|from|global|if|import|in|is" +
			"|lambda|not|or|pass|print|raise|return|try" +
			"|while|with|yield|__init__";

	@Override
	public String getPreservedWords() {
		return preservedWords;
	}

	public PythonCompare() {
		super();
	}
}

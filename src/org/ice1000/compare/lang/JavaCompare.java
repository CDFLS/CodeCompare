package org.ice1000.compare.lang;

import org.ice1000.compare.core.CompareImpl;

/**
 * Created by ice1000 on 2016/11/14.
 *
 * @author ice1000
 */
public class JavaCompare extends CompareImpl {

	@SuppressWarnings("WeakerAccess")
	public static final String preservedWords = "const|goto|friendly|sizeof|abstract|assert" +
			"|boolean|break|byte|case|catch|char|class|const|continue|default|do|double" +
			"|else|enum|extends|final|finally|float|for|if|implements|import|instanceof" +
			"|int|interface|long|native|new|package|private|protected|public|return|short" +
			"|static|strictfp|super|switch|synchronized|this|throw|throws|transient|try" +
			"|void|volatile|while|byValue|cast|false|future|generic|inner|operator|outer" +
			"|rest|true|var|goto|const|null";

	@Override
	public String getPreservedWords() {
		return preservedWords;
	}

	public JavaCompare() {
		super();
	}
}

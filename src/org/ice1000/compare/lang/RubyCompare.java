package org.ice1000.compare.lang;

import org.ice1000.compare.core.CompareImpl;

/**
 * Created by ice1000 on 2016/11/15.
 *
 * @author ice1000
 */
public class RubyCompare extends CompareImpl {

	@SuppressWarnings("WeakerAccess")
	public static final String preservedWords = "module|class|def" +
			"|undef|defined?|if|then|else|elsif|case|when|unless" +
			"|for|in|while|until|next，break|do|redo|retry|yield" +
			"|not|and|or|true|false|nil|rescue|ensure|super|self" +
			"|begin|end|BEGIN|END|__FILE__|__LINE__|return|alias";

	@Override
	public String getPreservedWords() {
		return preservedWords;
	}

	public RubyCompare() {
		super();
	}
}

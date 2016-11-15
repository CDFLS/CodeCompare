package org.ice1000.compare.lang;

import org.ice1000.compare.core.CompareImpl;

/**
 * Created by ice1000 on 2016/11/14.
 *
 * @author ice1000
 */
public class PascalCompare extends CompareImpl {
	@SuppressWarnings("WeakerAccess")
	public static final String preservedWords = "and|array|begin|case|const|div|do" +
			"|downto|else|end|file|for|function|goto|if|in|label|mod|nil|not|of|or" +
			"|packed|procedure|program|record|repeat|set|then|to|type|until|var|while" +
			"|with|exports|shr|string|asm|object|unit|constructor|implementation" +
			"|destructor|uses|inherited|inline|interface|library|xor|shl";

	@Override
	public String getPreservedWords() {
		return preservedWords;
	}

	public PascalCompare() {
		super();
	}
}

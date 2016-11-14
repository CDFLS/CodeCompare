package org.ice1000.compare.data;

/**
 * Created by ice1000 on 2016/11/14.
 *
 * @author ice1000
 */
public class NameCodeDataHolder {
	public static final int LANGUAGE_C_PLUS_PLUS = 0x00;
	public static final int LANGUAGE_JAVA = 0x01;

	public String code, name;
	public int language;

	public NameCodeDataHolder(String code, String name, int language) {
		this.code = code;
		this.name = name;
		this.language = language;
	}
}

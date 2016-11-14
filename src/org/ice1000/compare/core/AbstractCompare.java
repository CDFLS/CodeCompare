package org.ice1000.compare.core;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by ice1000 on 2016/11/14.
 *
 * @author ice1000
 */
public abstract class AbstractCompare implements Compare {
	@SuppressWarnings("WeakerAccess")
	public abstract String getPreservedWords();

	private HashSet<String> keyWordSet = new HashSet<>();
	private LevenshteinDistance levenshteinDistance = new LevenshteinDistance();

	@SuppressWarnings("WeakerAccess")
	public AbstractCompare() {
		Collections.addAll(keyWordSet, getPreservedWords().split("\\|"));
	}

	private String delVariables(String code) {
		code = "   " + code + "  ";
//		System.out.println("!"+code);
		int pos1 = 0;
		int pos2 = 0;
		int len = code.length();
		boolean isVariables = false;
		StringBuilder ret = new StringBuilder();
		while (pos1 < len) {
			pos2++;
			if (isVariables) {
				if (code.substring(pos2, pos2 + 2).replaceAll("[0-9a-zA-Z_][^a-zA-Z_]", "").equals("")) {
					isVariables = false;
					String vv = code.substring(pos1, pos2 + 1);
					if (keyWordSet.contains(vv)) ret.append(vv);
					//System.out.println("vv="+vv);
					pos1 = pos2 + 1;
				}
			} else {
				if (code.substring(pos2, pos2 + 2).replaceAll("[^._a-zA-Z][_a-zA-Z]", "").equals("")) {
					isVariables = true;
					ret.append(code.substring(pos1, pos2 + 1));
					//System.out.println(code.substring(pos1,pos2+1));
					pos1 = pos2 + 1;
				}
			}
			if (pos2 == len - 2) break;
		}

		return ret.toString().trim();
		//return code.replaceAll("(?<=([^\\._a-zA-Z]))[a-zA-Z_]+[0-9_a-zA-Z]*(?=([^a-zA-Z_]))", "");
	}

	@Override
	public String getPreprocessedCode(File file) throws IOException {
		String code;
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		StringBuilder buf = new StringBuilder();
		while ((line = br.readLine()) != null) buf.append(line).append("\n");
//		删除所有注释
//		code = CommentsDeleter.delComments(buf.toString());
		code = buf.toString();
		int pos1 = 0, pos2 = 0;
		int len = code.length();
		boolean isString = false;
		StringBuilder ret = new StringBuilder();
		while (pos1 < len) {
			pos2++;
			if (isString) {
				if (pos2 < len - 1) {
					if (code.substring(pos2, pos2 + 1).equals("\"") && !code.subSequence(pos2 - 1, pos2).equals("\\")) {
						isString = false;
						ret.append(delVariables(code.substring(pos1, pos2 + 1)));
						pos1 = pos2 + 1;
					}
				} else {
					break;
				}
			} else {
				if (pos2 < len - 1) {
					if (code.substring(pos2, pos2 + 1).equals("\"")) {
						isString = true;
						ret.append(delVariables(code.substring(pos1, pos2)));
						pos1 = pos2;
					}
				} else {
					ret.append(delVariables(code.substring(pos1, code.length())));
					break;
				}
			}

		}
		code = ret.toString();
		//删除所有空格和换行
		code = code.replaceAll("\\s", "");
		br.close();
		return code;
	}

	@Override
	public double getSimilarity(String code1, String code2) {
		return 1 - levenshteinDistance.ld(code1, code2) * 1.0 / Math.max(code1.length(), code2.length());
	}


}

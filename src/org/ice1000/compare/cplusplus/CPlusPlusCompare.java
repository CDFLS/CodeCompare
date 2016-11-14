package org.ice1000.compare.cplusplus;

import org.ice1000.compare.Compare;
import org.ice1000.compare.data.DataHolder;
import org.ice1000.compare.data.SimDataHolder;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class CPlusPlusCompare implements Compare {

	/**
	 * C++保留字
	 */
	@SuppressWarnings("FieldCanBeLocal")
	private String keyWords = "and|asm|auto|bad_cast|bad_typeid" +
			"|bool|break|case|catch|char|class|const|const_cast" +
			"|continue|default|delete|do|double|dynamic_cast|else" +
			"|enum|except|explicit|extern|false|finally|float|for" +
			"|friend|goto|if|inline|int|long|mutable|namespace|new" +
			"|operator|or|private|protected|public|register|reinterpret_cast" +
			"|return|short|signed|sizeof|static|static_cast|struct" +
			"|switch|template|this|throw|true|try|type_info|typedef" +
			"|typeid|typename|union|unsigned|using|virtual|void|volatile|wchar_t|while";

	private HashSet<String> keyWordSet = new HashSet<>();
	private LevenshteinDistance levenshteinDistance = new LevenshteinDistance();

	public static final double SIMILARITY_MINIMUM = 0.18;

	@SuppressWarnings("WeakerAccess")
	public CPlusPlusCompare() {
		String list[] = keyWords.split("\\|");
		Collections.addAll(keyWordSet, list);
	}

	private String delVariables(String code) {
		code = "   " + code + "  ";
		//System.out.println("!"+code);
		int pos1 = 0, pos2 = 0;
		int len = code.length();
		boolean isVariables = false;
		StringBuilder ret = new StringBuilder();
		while (pos1 < len) {
			pos2++;
			if (isVariables) {
				if (code.substring(pos2, pos2 + 2).replaceAll("[0-9a-zA-Z_][^a-zA-Z_]", "").equals("")) {
					isVariables = false;
					String vv = code.substring(pos1, pos2 + 1);
					if (this.keyWordSet.contains(vv)) ret.append(vv);
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
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		StringBuilder buf = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) buf.append(line).append("\n");
		//删除所有注释
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


	/**
	 * 写的跟坨屎一样，辣鸡
	 *
	 * @throws IOException checked exception is a rubbish
	 */
	public static void oldMain() throws IOException {
		CPlusPlusCompare cmp = new CPlusPlusCompare();
		File dic = new File(".\\AllSubmits");
		String names[] = {"1400.cpp", "1410.cpp",};
		for (String name : names) {
			File file = new File(".//" + name);
			if (!file.exists()) file.createNewFile();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			//bw.write("题目："+name);
			System.out.println("题目：" + name);
			bw.newLine();
			ArrayList<String> idList = new ArrayList<>();
			ArrayList<String> codeList = new ArrayList<>();
			//noinspection ConstantConditions
			for (File f1 : dic.listFiles()) {
				File f2 = new File(f1.getAbsoluteFile() + "\\" + name);
				if (f2.exists()) {
					idList.add(f1.getName());
					codeList.add(cmp.getPreprocessedCode(f2.getAbsolutePath()));
				}
			}
			for (int i = 0; i < codeList.size(); i++)
				for (int j = i + 1; j < codeList.size(); j++) {
					double s = cmp.getSimilarity(codeList.get(i), codeList.get(j));
					if (s >= 0.7) {
						bw.write(idList.get(i) + "\t" + idList.get(j) + "\t" + s);
						bw.newLine();
					}
				}
			bw.close();
		}
	}
}

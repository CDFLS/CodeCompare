package org.ice1000.compare.lang;

import org.ice1000.compare.core.AbstractCompare;

import java.io.*;
import java.util.ArrayList;

/**
 * Hard coded C++ preserved words
 */
public class CppCompare extends AbstractCompare {

	/**
	 * C++保留字
	 */
	@SuppressWarnings({"FieldCanBeLocal", "WeakerAccess"})
	public static final  String keyWords = "and|asm|auto|bad_cast|bad_typeid" +
			"|bool|break|case|catch|char|class|const|const_cast" +
			"|char16_t|char32_t|__restrict__|__cdecl|static_assert" +
			"|continue|default|delete|do|double|dynamic_cast|else" +
			"|enum|except|explicit|extern|false|finally|float|for" +
			"|friend|goto|if|inline|int|long|mutable|namespace|new" +
			"|operator|or|private|protected|public|register|reinterpret_cast" +
			"|return|short|signed|sizeof|static|static_cast|struct" +
			"|switch|template|this|throw|true|try|type_info|typedef" +
			"|typeid|typename|union|unsigned|using|virtual|void|volatile|wchar_t|while";

	@Override
	public String getPreservedWords() {
		return keyWords;
	}

	@SuppressWarnings("WeakerAccess")
	public CppCompare() {
		super();
	}

	/**
	 * 写的跟坨屎一样，辣鸡
	 * ——ice1000
	 *
	 * @throws IOException checked exception is a rubbish
	 */
	@SuppressWarnings("unused")
	public static void oldMain() throws IOException {
		CppCompare cmp = new CppCompare();
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

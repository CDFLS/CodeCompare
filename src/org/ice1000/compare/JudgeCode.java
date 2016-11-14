package org.ice1000.compare;

import org.ice1000.compare.core.ComparorChooser;
import org.ice1000.compare.data.NameCodeDataHolder;
import org.ice1000.compare.data.SimDataHolder;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by ice1000 on 2016/11/14.
 *
 * @author ice1000
 */
public class JudgeCode {

	public static final double SIMILARITY_MINIMUM = 0.18;

	public static void main(String[] args) throws IOException {
		ComparorChooser comparorChooser = new ComparorChooser();
		JTextArea label = new JTextArea();
		label.setEditable(false);
		label.setBackground(new Color(0x2B2B2B));
		label.setForeground(Color.WHITE);
		HashMap<String, ArrayList<NameCodeDataHolder>> sources = new HashMap<>();
		new JFrame("Comparison by ice1000, for C++ only") {{
			setSize(500, 500);
			setLayout(new BorderLayout());
			add(new JScrollPane(label), BorderLayout.CENTER);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setVisible(true);
		}};
		File boyNextDoor = new File("." + File.separator + "source");
		if (!boyNextDoor.exists()) label.append("File not found.");
		else {
			//noinspection ConstantConditions
			for (File file : boyNextDoor.listFiles()) {
				if (file.listFiles() == null) continue;
				label.append("Name: " + file.getName() + "\n");
				/// 彩蛋
				if (file.getName().equals("李斯威")) label.append("李爷太神啦 我们一起来%他\n");
				if (file.getName().equals("杨宗翰")) label.append("%%%yzh大神，杨杨得意\n");
				if (file.getName().equals("胡奕扬")) label.append("那你很棒哦\n");
				if (file.getName().equals("谭世炜")) label.append("I have a torch, I have a condom, Ah♂, 并蒂莲\n");
				if (file.getName().equals("吴沂隆")) label.append("吴队长带领我们走向世界\n");
				if (file.getName().equals("张寅森")) label.append("The weakest OIer of CDFLS\n");
				if (file.getName().equals("张恩溶")) label.append("我不做大哥好多年 我只想好好爱一回\n");
				if (file.getName().equals("田韵豪")) label.append("t123yh受到实外学生集体顶礼膜拜\n");
				if (file.getName().equals("董海辰")) label.append("870380501正在连任\n");
				for (File f : file.listFiles()) {
					// ignored file types
					if (!(f.getName().endsWith(".cpp") ||
							f.getName().endsWith(".c") ||
							f.getName().endsWith(".pas") ||
							f.getName().endsWith(".java"))) continue;
					label.append("\t" + f.getName() + "\n");
					if (!sources.containsKey(f.getName())) sources.put(f.getName(), new ArrayList<>());
					sources.get(f.getName()).add(new NameCodeDataHolder(
							comparorChooser
									.chooseCompare(f.getName())
									.getPreprocessedCode(f.getAbsolutePath()),
							file.getName(),
							comparorChooser.chooseLanguage(f.getName())
					));
				}
			}
			label.append("\n\n\nComparison result:\n");
			ArrayList<SimDataHolder> sim = new ArrayList<>();
			sources.forEach((string, codes) -> {
				sim.clear();
				label.append("\tFile Name :" + string + "\n");
				for (int i = 0; i < codes.size(); i++) {
					for (int j = i + 1; j < codes.size(); j++) {
						sim.add(new SimDataHolder(
								codes.get(i).name,
								codes.get(j).name,
								comparorChooser
										.chooseCompare(string)
										.getSimilarity(codes.get(i).code, codes.get(j).code)
						));
					}
				}
//			Collections.sort(sim);
				Collections.sort(sim, (o1, o2) -> (int) (o2.sim * 1000 - o1.sim * 1000));
//			for (SimDataHolder i : sim) {
//				label.append("\t【" +
//						i.name1 + ", " +
//						i.name2 + "】 ==> " +
//						i.sim + "\n");
//			}
				int beginSize = sim.size();
				sim.removeIf(i -> i.sim < SIMILARITY_MINIMUM);
				int endSize = sim.size();
				sim.forEach(i -> {
					label.append("\t\t【" + i.name1 + ", " + i.name2 + "】 ==> " + i.sim + "\n");
					if (i.sim >= 0.9999999999) label.append("\t↑这个是直接复制的\n");
					else if (i.sim >= 0.75) label.append("\t↑嗨呀 这两份代码有嫌疑\n");
				});
				label.append("  相似度小于 " + SIMILARITY_MINIMUM +
						" 的 " + (beginSize - endSize) + " 对代码被忽略了。\n\n");
			});
		}

		label.append("\n\nProgram Complete.\n");
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(
				"." + File.separator + "source" + File.separator + "log.txt"))) {
			writer.write(label.getText());
			writer.flush();
			writer.close();
		} catch (Exception ignored) {
		}
	}
}

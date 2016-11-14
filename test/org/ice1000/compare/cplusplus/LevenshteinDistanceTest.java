package org.ice1000.compare.cplusplus;

import org.ice1000.compare.core.LevenshteinDistance;

public class LevenshteinDistanceTest {
	public static void main(String[] args) {
		LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
		double num = levenshteinDistance.ld("人民", "中国人民是人才");
		System.out.println(num);
	}
}
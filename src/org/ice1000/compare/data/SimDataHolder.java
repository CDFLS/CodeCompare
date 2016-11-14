package org.ice1000.compare.data;

/**
 * Created by ice1000 on 2016/11/14.
 *
 * @author ice1000
 */
public class SimDataHolder implements Comparable<SimDataHolder> {
	public String name1, name2;
	public double sim;

	public SimDataHolder(String name1, String name2, double sim) {
		this.name1 = name1;
		this.name2 = name2;
		this.sim = sim;
	}

	@Override
	public int compareTo(SimDataHolder o1) {
		return o1.sim < sim ? 0 : 1;
	}
}

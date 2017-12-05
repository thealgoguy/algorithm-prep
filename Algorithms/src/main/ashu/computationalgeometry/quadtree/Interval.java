package main.ashu.computationalgeometry.quadtree;

public class Interval<K extends Comparable<K>> {
    K min;
    K max;
	public Interval(K min, K max) {
		super();
		this.min = min;
		this.max = max;
	}
	@Override
	public String toString() {
		return "[min=" + min + ", max=" + max + "]";
	}
	
	
}

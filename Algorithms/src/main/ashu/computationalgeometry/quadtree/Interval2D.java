package main.ashu.computationalgeometry.quadtree;

public class Interval2D<K extends Comparable<K>> {
	Interval<K> intervalX;
	Interval<K> intervalY;

	public Interval2D(Interval<K> intervalX, Interval<K> intervalY) {
		super();
		this.intervalX = intervalX;
		this.intervalY = intervalY;
	}
	
	public boolean contains(K x, K y) {
		return (intervalX.min.compareTo(x) <= 0) && (intervalX.max.compareTo(x) >= 0) && (intervalY.min.compareTo(y) <= 0) && (intervalY.max.compareTo(y) >= 0);
	}

	@Override
	public String toString() {
		return "[intervalX=" + intervalX + ", intervalY="
				+ intervalY + "]";
	}
  
	
}

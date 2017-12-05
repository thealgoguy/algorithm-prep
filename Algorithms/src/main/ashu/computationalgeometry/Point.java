package main.ashu.computationalgeometry;

public class Point {
	   public double x, y;
	   public Point(double x, double y) {
		   this.x = x;
		   this.y = y;
	   }
	@Override
	public String toString() {
		return ("("+this.x+","+this.y+")");
	}
}

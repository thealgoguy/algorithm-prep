package compgeometry;


//Given two line segments (p1, q1) and (p2, q2), find if the given line segments intersect with each other.
public class LineIntersection {
	private class Point {
		   private int x, y;
		   public Point(int x, int y) {
			   this.x = x;
			   this.y = y;
		   }
		@Override
		public String toString() {
			return ("("+this.x+","+this.y+")");
		}	   
	   }
	
	//Lines intersect if the end points of both the lines are in different direction wrt each other or one of them are collinear
	public boolean doIntersect(Point p1, Point p2, Point q1, Point q2) {
		int d1 = findDirection(p1, p2, q1);
		int d2 = findDirection(p1, p2, q2);
		int d3 = findDirection(q1, q2, p1);
		int d4 = findDirection(q1, q2, p2);
		
		if(d1*d2 < 0 && d3*d4 <0) return true;
		//collinearity cases
		if(d1==0 && onSameSegment(p1,q1,p2)) return true;
		if(d2==0 && onSameSegment(p1,q2,p2)) return true;
		if(d3==0 && onSameSegment(q1,p1,q2)) return true;
		if(d1==0 && onSameSegment(q1,p2,q2)) return true;
		return false;
		
	}
	public int findDirection(Point p1, Point p2, Point p3) {
		int val = (p2.y-p1.y)*(p3.x-p2.x) - (p3.y-p2.y)*(p2.x-p1.x);
		if(val < 0) return 1;  //anti
		else if(val>0) return -1; //clockwise
		else return 0;  //collinear
	}
	//returns if collinear points p1,p2,p3 are on same seg
	public boolean onSameSegment(Point p1, Point p2, Point p3) {
		return (p2.x >= Math.min(p1.x, p3.x) && p2.x <= Math.max(p1.x, p3.x)) && 
		(p2.y >= Math.min(p1.y, p3.y) && p2.y <= Math.max(p1.y, p3.y));
	}
	
	public static void main(String args []) {
		LineIntersection lines = new LineIntersection(); 
		   Point p1 = lines.new Point(1, 1);
		   Point p2 = lines.new Point(10, 1);
		   Point q1 = lines.new Point(1, 2);
		   Point q2 = lines.new Point(10, 2);
		   boolean interSect = lines.doIntersect(p1, p2, q1, q2);
		   if(interSect) System.out.println("Lines intersect");
		   else System.out.println("Lines do not intersect");
		   
		   p1 = lines.new Point(10, 0);
		   p2 = lines.new Point(0, 10);
		   q1 = lines.new Point(0, 0);
		   q2 = lines.new Point(10, 10);
		    interSect = lines.doIntersect(p1, p2, q1, q2);
		   if(interSect) System.out.println("Lines intersect");
		   else System.out.println("Lines do not intersect");
	}
}

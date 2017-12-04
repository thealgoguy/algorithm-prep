package compgeometry;

//A point P is inside a polygon if
//1. it lies on the boundary of polygon, i.e. on the segment of any side
//2. Horizontal segment(P,Infinity) makes odd number of intersections with the polygon

public class PointInsidePolygon {
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
		double val = (p2.y-p1.y)*(p3.x-p2.x) - (p3.y-p2.y)*(p2.x-p1.x);
		if(val < 0) return 1;  //anti
		else if(val>0) return -1; //clockwise
		else return 0;  //collinear
	}
	//returns if collinear points p1,p2,p3 are on same seg
	public boolean onSameSegment(Point p1, Point p2, Point p3) {
		return (p2.x >= Math.min(p1.x, p3.x) && p2.x <= Math.max(p1.x, p3.x)) && 
		(p2.y >= Math.min(p1.y, p3.y) && p2.y <= Math.max(p1.y, p3.y));
	}
	
	//main logic..O(n) n=no. of vertices
	public boolean isInside(Point [] polygon, Point p) {
		if(polygon.length <3) return false;
		// Create a point for line segment from p to infinite
	    Point extreme = new Point(Double.MIN_VALUE, p.y);
	    int count = 0;
	    //count number of intersections 
	    for(int i=0; i<polygon.length-1; i++) {
	    	if(doIntersect(polygon[i], polygon[i+1], p, extreme)) {
	    		if(findDirection(polygon[i], polygon[i+1], p)==0){
	    			return onSameSegment(polygon[i], p, polygon[i+1]);
	    		}
	    		count++;  //if nont collinear count intersections
	    	}	    	
	    }
	    return count%2 ==1;
	}
	
	public static void main(String args []) {
		Point polygon1[] = {new Point(0, 0), new Point(10, 0), new Point(10, 10), new Point(0, 10)};
		Point polygon2[] = {new Point(0, 0), new Point(5, 5), new Point(5, 0)};
		PointInsidePolygon polyPlane = new PointInsidePolygon();
		Point p = new Point(20, 20);
		boolean inside = polyPlane.isInside(polygon1, p);
		System.out.println("Point "+p+" is "+(inside==true ?"inside" : "outside")+ " polygon1");
		p = new Point(5, 5);
		inside = polyPlane.isInside(polygon1, p);
		System.out.println("Point "+p+" is "+(inside==true ?"inside" : "outside")+" polygon1");
		p = new Point(3, 3);
		inside = polyPlane.isInside(polygon2, p);
		System.out.println("Point "+p+" is "+(inside==true ?"inside" : "outside")+" polygon2");
	}
}

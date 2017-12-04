package compgeometry;
 
//Given an ordered triplet of points(p1,p2,p3), find whether if their direction p1->p2>p3 is anti-clockwise or clockwise or collinear
//Approach - if slope of vector p1p2 < slope of vector p2p3 then anti-clockwise if greater then clockwise if equal then collinear
//i.e. vector product of p1p2 and p2p3 is -ve, +ve or 0 see see saurabhschool
public class PointsInPlane {
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
   
   private Point p1, p2, p3;
   
   public String findDirection(Point p1, Point p2, Point p3) {
	   int val = (p2.y-p1.y)*(p3.x-p2.x) - (p3.y-p2.y)*(p2.x-p1.x);
	   if(val < 0) return "anti-clockwise";
	   else if(val>0) return "clockwise";
	   else return "collinear";
   }
   
   public static void main(String args []) {
	   PointsInPlane plane = new PointsInPlane(); 
	   //inner class instances can be initailized only through outer clss instance
	   Point p1 = plane.new Point(4,7);
	   Point p2 = plane.new Point(9,15);
	   Point p3 = plane.new Point(8,20);
	   String orientation = plane.findDirection(p1, p2, p3);
	   System.out.println("Orientation of "+p1+","+ p2+","+ p3+" is : "+orientation);
	   
   }
   
}

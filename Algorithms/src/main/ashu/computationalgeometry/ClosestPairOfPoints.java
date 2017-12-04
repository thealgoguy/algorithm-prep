package compgeometry;

import java.util.Arrays;
import java.util.Comparator;
import java.util.*;

//Given points in 2D plane, find the closest pair
//Approach - D&C
//T(n) = 2T(n/2) + O(n) + O(nLogn) + O(n) =  T(n x Logn x Logn)
//O(nlogn) approach - http://www.geeksforgeeks.org/closest-pair-of-points-onlogn-implementation/
//application - to detect if two aircrafts in space don't come too close

public class ClosestPairOfPoints {
	public double distance(Point p1, Point p2) {
		return Math.sqrt((p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y));
	}
    public double closestPair(Point [] points) {
    	return findClosestUtil(points, 0,points.length-1);
    	
    }   
    public double findClosestUtil(Point [] p, int low, int high) {
    	if(low >=high) return -1;
    	if(low+1==high) return distance(p[low], p[high]);
    	//sort points a/c to x-coordinates
    	Arrays.sort(p, new Comparator<Point>() {
    		public int compare(Point a, Point b) {
    			return Double.compare(a.x, b.x);
    		}
		});
    	int mid = low + (high-low)/2;
    	double d1 = findClosestUtil(p, low, mid);
    	double d2 = findClosestUtil(p, mid+1, high);
    	double delta = Math.min(d1, d2);
    	//now consider the pairs in which lie on opposite sides of y=mid and distance < delta
    	//if we consider delta on both sides, we will include all possible candidates
    	ArrayList<Point> strips = new ArrayList<Point>();
    	for(int i=low; i<=high; i++) {
    		if(Math.abs(p[i].x-p[mid].x) < delta) strips.add(p[i]);
    	}
    	double min = closestAfterMerge(strips, delta);
    	return min;
    }
    
    public double closestAfterMerge(ArrayList<Point> strips, double delta) {
    	//sort strip a/c to y val
    	Collections.sort(strips, new Comparator<Point>() {
    		public int compare(Point a, Point b) {
    			return Double.compare(a.y, b.y);
    		}
		});
    	double min = Double.MAX_VALUE;
    	// Pick all points one by one and try the next points till the difference between y is smaller than d.
        // This is a proven fact that this loop runs at most 6 times
    	for(int i=0; i<strips.size(); i++) {
    		for(int j=i+1; j<strips.size() && Math.abs(strips.get(i).y-strips.get(j).y) < delta; j++) {
    			min = Math.min(min, distance(strips.get(i), strips.get(j)));
    		}
    	}
    	return min;
    }
    
    public static void main(String args []) {
    	ClosestPairOfPoints plane = new ClosestPairOfPoints();
    	Point p[] = {new Point(2, 3), new Point(12, 30),new Point(40, 50), new Point(5, 1), new Point(12, 10), new Point(3, 4)};
    	double minDist = plane.closestPair(p);
    	System.out.println("CLosest pair distance is "+minDist);
    }
}

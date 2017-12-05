package main.ashu.dp;

import java.util.Arrays;

//https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/BoxStacking.java
public class BoxStackingProblem {
 public static void main(String args []) {
	 //Box input [] = {new Box(3,2,5), new Box(1,2,4)};
	 Box input [] = {new Box(4, 6, 7), new Box(1, 2, 3), new Box(4, 5, 6), new Box(10, 12, 32)}; 
	 //first create new boxes for possible rotations of given boxes. For simplicity, length <=width for each box
	 //(so that while comparing two boxes, we compare smaller dimensions of two boxes and larger dimensions of two boxes respectively.
	 //each box can have three orientations
	 Box arr [] = new Box[input.length*3];
	 int count = 0;
	 for (Box b : input) {
		int l = b.length;
		int w = b.width;
		int h = b.height;
		int a [] = {l,w,h};
		Arrays.sort(a);
		arr[count++] = new Box(a[0],a[1],a[2]);
		arr[count++] = new Box(a[0],a[2],a[1]);
		arr[count++] = new Box(a[1],a[2],a[0]);
	}
	 //now sort the boxes on the boxes in non-increasing order a/c to base area
	 Arrays.sort(arr);
	 //now problem is :: Given n boxes, find the longest subsequence with increasing height, 
	 //subject to the constraint that that l(i)>l(j),w(i)>w(j),h(i)>h(j) for i<j 
	 int dp [] = new int[arr.length+1];
	 //initialize the heights ending with each boxes, using 1 based indexing
	 for(int i=1; i<dp.length; i++) dp[i] = arr[i-1].height;
	 int ans = Integer.MIN_VALUE;
	 for(int i=1; i<dp.length; i++) {
		 int maxh = Integer.MIN_VALUE;
		 for(int j=1; j<i; j++) {
			 Box prev = arr[j-1];
			 Box current = arr[i-1];
			 if(prev.length > current.length && prev.width > current.width) {
				 if(dp[j] > maxh) maxh = dp[j];
			 }
		 }
		 if(maxh > 0) dp[i] += maxh;
		 ans = Math.max(dp[i], ans);
	 }
	 System.out.println("Maximum height of the box stack = "+ans);
 }
}

class Box implements Comparable<Box> {
	 int length;
	 int width;
	 int height;
	
	Box(int l, int w, int h) {
		this.length = l;
		this.width = w; 
		this.height = h;
	}
	//sorting in non-increasing order a/c to base area 
	public int compareTo(Box b) {
		//return Integer.compare(b.length*b.width, this.length*this.width);
		return (this.length * this.width >= b.length * b.width) ? -1 : 1; 
	}
	
}
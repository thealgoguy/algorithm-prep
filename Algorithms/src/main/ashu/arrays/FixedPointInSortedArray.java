package main.ashu.arrays;

//Given an array of n distinct integers sorted in ascending order, 
//find fixed Point in the array, if it exists, else returns -1. 
//Fixed Point in an array is an index i such that arr[i] is equal to i. 
//Note that integers in array can be negative.
public class FixedPointInSortedArray {
   public static void main(String args []) {
	   int a[] = {-10, -1, 0, 3, 10, 11, 30, 50, 100};
	   int n = a.length;
	   //since, array is sorted we can do binary search
	   int low = 0, high = n-1;
	   int ans = -1;
	   boolean found = false;
	   while(low <= high) {
		   int mid = low + (high-low)/2;
		   if(a[mid] == mid) {
			   ans = a[mid]; 
			   found = true;
			   break;
		   }
		   if(a[mid] < mid) low = mid+1;
		   else high = mid-1;
	   }
	   if(found) {
		   System.out.println("Fixed point is : "+ans);
	   } else {
		   System.out.println("No fixed point exists");
	   }
   }
}

package main.ashu.arrays;

//Given an array of integers which is initially increasing and then decreasing,
//find the maximum value in the array. O(logn) time
public class PivotElemenrInCicularlySortedArray {
   public static void main(String args []) {
	   int a [] = {8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1};
	   int n = a.length;
	   int l=0, r=n-1;
	   int max = a[0];
	   while(l<=r) {
		   int mid = l + (r-l)/2;
		   //check if mid is pivot
		   if(mid-1 >=0 && mid+1 <= n-1 &&a[mid] > a[mid-1] && a[mid] > a[mid+1]) {
			   max = a[mid];
			   break;
		   }
		   //move to the unsorted half
		   if(a[l] <= a[mid]) l=mid+1;
		   else r = mid-1;
	   }
	   System.out.println("Pivot element is : "+max);
   }
}

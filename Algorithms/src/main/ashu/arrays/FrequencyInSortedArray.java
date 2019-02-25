package main.ashu.arrays;

//Find the frequency of a number in  a sorted array
// binary search to find leftmost & rightmost occurrences
// bin search is also a 2-pointer technique
//O(logn)
public class FrequencyInSortedArray {
   public static void main(String args []) {
	   int a[] =  {1, 1, 2, 2, 2, 2, 3};
	   int key = 2;
	   int left = binSearchLeft(a, key);
	   int right = binSearchRight(a, key);
	   if(left >0 & right>0) {
		   System.out.println("frequency of "+key+" is : "+(right-left+1));
	   }
   }
   
   public static int binSearchLeft(int a[], int k) {
	   int n = a.length;
	   int l =0, r = n-1;
	   int left  = -1;
	   while(l <= r) {
		   int mid = l+(r-l)/2;
		   if(a[mid] == k) {
			   left = mid;
		   }
		   if(a[mid] >= k) r = mid-1;
		   else l = mid+1;
	   }
	   return left;
   }
   
   public static int binSearchRight(int a[], int k) {
	   int n = a.length;
	   int l =0, r = n-1;
	   int right  = -1;
	   while(l <= r) {
		   int mid = l+(r-l)/2;
		   if(a[mid] == k) {
			   right = mid;
		   }
		   if(a[mid] <= k) l = mid+1;
		   else r = mid-1;
	   }
	   return right;
   }
}

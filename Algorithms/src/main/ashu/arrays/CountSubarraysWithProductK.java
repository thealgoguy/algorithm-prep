package main.ashu.arrays;

//Application of window technique
//https://www.geeksforgeeks.org/number-subarrays-given-product/

public class CountSubarraysWithProductK {
   public static void main(String args []) {

		int a[] = {2, 1, 1, 1, 4, 5};
		int k =4;
		int n = a.length;
		int start=0, end=0;
		int count = 0;
		count=0; int curr =1;
		while(end <n) {
		   curr *= a[end]; //try to extend
		   //first shrink the subarray for the failure cases
		  if(curr > k) {
			  while(curr > k && start < end) {
				  curr /= a[start++];
			  }
		  }
		  //success condition case
		  if(curr == k) {
			  count++;
			  //in case upcoming indexes have 1s, count them
			  int ones = 0;
			  while(end+1 < n && a[end+1] ==1) {
				  ones++; end++;
			  }
			  count += ones;
			  //in case start indexes have 1s, consider them too
			  while(start <= end && a[start++] ==1) {
				  count += (ones + 1);  //why ?
			  }
			  curr /= a[start]; //update for next iteration since one answer found
		  }
		  
		   end++;
		}
		System.out.println(count);
	
   }
}

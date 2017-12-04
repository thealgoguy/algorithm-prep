package DP;

import java.util.Arrays;

//http://www.geeksforgeeks.org/largest-divisible-subset-array/
//Given an array the task is to find largest divisible subset in array. 
//A subset is called divisible if for every pair (x, y) in subset, either x divides y or y divides x.

public class LargestDivisileSubset {
	static String subset = "";
   public static void main(String args []) {
	   int arr[] = {1, 16, 7, 8, 4};
	   int ans = findLargestDivisibleSubset(arr);
	   System.out.println("Largest Divisible Subset Length = "+subset+" length = "+ans);
   }
   
   //sort the array and use LIS concept
   public static int findLargestDivisibleSubset(int arr []) {
	   Arrays.sort(arr);
	   int dp [] = new int[arr.length];
	   int prev [] = new int[arr.length];
	   Arrays.fill(dp, 1);
	   int ans = 1, ansIndex=0;
	   prev[0] = -1;
	   for(int i=1; i<dp.length; i++) {
		   int max = Integer.MIN_VALUE;
		   for(int j=i-1; j>=0; j--) {
			   if(arr[i] % arr[j] ==0) {
				   //max = Math.max(max, dp[j]);
				   if(max < dp[j]) {
					   max = dp[j];
					   prev[i] = j;
				   }
			   }
		   }
		   dp[i] = max + 1;
		   //ans = Math.max(ans, dp[i]);
		   if(ans < dp[i]) {
			   ans = dp[i];
			   ansIndex = i;
		   }
	   }
	   int i=ansIndex;
	   while(i>=0) {
		   subset = Integer.toString(arr[i])+" "+subset;
		   i = prev[i];
	   }
	   return ans;
   }
}

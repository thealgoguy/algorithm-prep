package main.ashu.dp;

//https://articles.leetcode.com/the-painters-partition-problem-part-ii/
//***Optimized bin-search approach - https://articles.leetcode.com/the-painters-partition-problem-part-ii/
//Loosely translated as : Given an array of n non-negative integers and a number k,
//divide the array into k or fewer partitions such that the maximum sum over all the partitions is minimized

public class PaintersPartitionProblem {
	
	public static int calculate(int a[], int k) {
		int n = a.length;
		int dp [][] = new int[k+1][n+1];
		int sum [] = new int[n+1];
		sum[0] = 0;
		for(int i=1; i<=n; i++) sum[i] = sum[i-1] + a[i-1];
		
		for(int i=1; i<=k; i++) dp[i][1] = a[0];
		for(int i=1; i<=n; i++) dp[1][i] = sum[i];
		for(int i=2; i<=k; i++) {
			for(int j=2; j<=n; j++) {
					//choose the point from (j....n-1) which minimizes the max sum over all partitions
				dp[i][j] = Integer.MAX_VALUE;
				  for(int x=1; x<=j; x++) {
					  dp[i][j] = Math.min(dp[i][j], Math.max(dp[i-1][x], sum[j]-sum[x]));
				  }
			}
		}
		return dp[k][n];
	}
   public static void main(String args []) {
	   int l [] = { 10, 20, 60, 50, 30, 40 };
	    int k = 3;
	    int n = l.length;
	    int minTime = calculate(l, k);
	    System.out.println("Minimum time required to paint all baords = "+minTime);
   }
}

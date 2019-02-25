package main.ashu.dp;

import java.util.ArrayList;

public class MinimumPartition {	  
	public static void main(String args []) {
		int a []= {20, 10, 4, 50, 100};
		int n = a.length;
		int sum = 0;
		for(int i=0; i<n; i++) sum += a[i];
		int mid = sum/2, sum1=0;
		boolean dp [][] = new boolean[sum+1][n+1];
		for(int i=0; i<=n; i++) dp[0][i] = true;    //n>=0, sum=0
		for(int i=1; i<=sum; i++) dp[i][0] = false;  //sum>0, n=0
		for(int i=1; i<=sum; i++) {
			for(int j=1; j<=n; j++) {
				dp[i][j] = (a[j-1] > i) ? dp[i][j-1] : dp[i-a[j-1]][j-1] | dp[i][j-1];
				if(dp[i][j]) {
					sum1 = (Math.abs(mid-i) < Math.abs(mid-sum1)) ? i : sum1;
				}
			}
		}
		// two sets exist with sum :  sum1 and (sum-sum1)
		ArrayList<Integer> set1 = new ArrayList<Integer>();
		ArrayList<Integer> set2 = new ArrayList<Integer>();
		findSet(a, dp, set1, sum1, n);
		System.out.print("Set 1 is : "+set1);
		findSet(a, dp, set2, sum-sum1, n);	
		System.out.print("\nSet 2 is : "+set2);		
	}			
	
	public static void findSet(int a[], boolean dp[][], ArrayList<Integer> set, int sum, int n) {
		if(n<=0 || sum <=0) return;
		if(dp[sum][n] && a[n-1] <= sum) {
			set.add(a[n-1]);
			findSet(a, dp, set, sum-a[n-1], n-1);
		}
		else {
			findSet(a, dp, set, sum, n-1);
		}
			

	}

}

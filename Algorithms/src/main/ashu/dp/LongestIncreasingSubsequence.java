package main.ashu.dp;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

	public static void main(String args []) {
		int a [] = { 10, 22, 9, 33, 21, 50, 41, 60 };
		int n = a.length;
		int dp [] = new int[n];
		Arrays.fill(dp, 1);
		int index = 0, max = 1;
		for(int i=1; i<n; i++) {
			for(int j=0; j<i; j++) {
				if(a[i] >a[j]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
			//update global max
			if(max < dp[i]) {
				max = dp[i];
				index = i;
			}
		}
		System.out.println("Length of LIS is : "+max);
		String lis = "";
		int len = max;
		for(int i=index; i>=0;) {
			if(dp[i] == len) {
				lis = a[i] + " "+lis;
				len--;
			}
			i--;	
		}
		System.out.println("LIS is : "+lis);
	}

}

package main.ashu.dp;

public class EggDropProblem {
	public static void main(String args []) {
		   int n = 36, k = 2;
		   int dp [][] = new int[k+1][n+1];
		   for(int i=1; i<=n; i++) {
			   dp[1][i] = i;
			   dp[0][i] = 0;
		   }
		   for(int i=1; i<=k; i++) {
			   dp[i][1] = 1;
		   }
		   for(int i=2; i<=k; i++) {
			   for(int j=2; j<=n; j++) {
				   int min = Integer.MAX_VALUE;
				   for(int x=1; x<=j; x++) {
					   int curr = 1 + Math.max(dp[i-1][x-1], dp[i][j-x]);
					   min = Math.min(min, curr);
				   }
				   dp[i][j] = min;
			   }
		   }
		   System.out.println("Minimum no of trials needed = "+dp[k][n]);
	   }
}

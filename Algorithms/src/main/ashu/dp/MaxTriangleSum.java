package main.ashu.dp;

public class MaxTriangleSum {
     public static void main(String args [])  {
    	 int matrix [][] = {{1,0,0}, {2,1,0}, {3,3,2}};
    	 int n = matrix.length;
    	 int dp[][] = new int[n][n];
    	 
    	 for(int i=0; i<n; i++) {
    		 for(int j=0; j<n; j++) {
    			 if(i==0) dp[i][j] = matrix[i][j];
    			 else {
    				 dp[i][j] = matrix[i][j];
    				 dp[i][j] += (j>0) ? Math.max(dp[i-1][j], dp[i-1][j-1]) : dp[i-1][j];
    			 }
    				 
    		 }
    	 }
    	 int ans = Integer.MIN_VALUE;
    	 for(int i=0; i<n; i++) ans = Math.max(ans, dp[n-1][i]);
    	 System.out.println("Maximum path sum in traingle is "+ans);
     }
}

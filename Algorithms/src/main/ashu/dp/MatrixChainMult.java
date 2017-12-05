package main.ashu.dp;

import java.util.Scanner;

//Given N matrices of (MxN dimensions). Find an order in which these matrices are multiplied, 
//so that total number of scalar multiplications are least. and dimensions 
//of each matrix given in an array P where P[i-1] and P[i] denote rows and column respectively of ith matrix,
//considering 1 based indexing. remember the recurrence relation and solve using recursion for clarity. 
//Understand why it can't be done in 1-d DP
public class MatrixChainMult {
   public static void main(String args []) {
	   Scanner sc = new Scanner(System.in);
	   String str [] = sc.nextLine().split(" ");
	   int n = str.length;
	   int p [] = new int[n];	   
	   for(int i=0; i<p.length; i++) p[i] = Integer.parseInt(str[i]);
	   int dp [][] = new int[n][n];
	   for(int i=0; i<n; i++) dp[i][i] = 0;  //cost for chain length 1, i.e. no second matrix to multiply
	   for(int len=2; len<n; len++) {
		   for(int i=1; i<n-len+1; i++) {
			   int j = i+len-1; 
			   dp[i][j] = Integer.MAX_VALUE;
			   for(int k=i; k<j; k++) {
				  dp[i][j] = Math.min(dp[i][j],dp[i][k] + dp[k+1][j] + p[i-1]*p[k]*p[j]);					  
			   }
		   }
	   }
	   System.out.println("minimum no of multiplications = "+dp[1][n-1]);
   }
}

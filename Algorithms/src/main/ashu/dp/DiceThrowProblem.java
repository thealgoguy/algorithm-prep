package main.ashu.dp;

//Given n dice each with m faces, numbered from 1 to m, find the number of ways to get sum X.
//X is the summation of values on each face when all the dice are thrown.
//http://www.geeksforgeeks.org/dice-throw-problem/
public class DiceThrowProblem {
  
	public static void main(String args []) {
		int m, n, x;
		m=4; n=2; x=1;
		System.out.println("Total number of ways for m = "+m+" , n = "+n+", x = "+x+" is : "+ways(m,n,x));
		m=2; n=2; x=3;
		System.out.println("Total number of ways for m = "+m+" , n = "+n+", x = "+x+" is : "+ways(m,n,x));
		m=6; n=3; x=8;
		System.out.println("Total number of ways for m = "+m+" , n = "+n+", x = "+x+" is : "+ways(m,n,x));
		m=4; n=2; x=5;
		System.out.println("Total number of ways for m = "+m+" , n = "+n+", x = "+x+" is : "+ways(m,n,x));
		m=4; n=3; x=5;
		System.out.println("Total number of ways for m = "+m+" , n = "+n+", x = "+x+" is : "+ways(m,n,x));
	}	
	
	public static int ways(int m, int n, int x) {
		int dp [][] = new int[x+1][n+1];         //sum x with n dice
		for(int i=0; i<=n; i++) dp[0][i] = 1;
		for(int i=1; i<=x; i++) dp[i][0] = 0;
		for(int i=1; i<=m && i<=x; i++) dp[i][1] = 1;
		for(int i=1; i<=x; i++) {
			for(int j=2; j<=n; j++) {
			      dp[i][j] = 0;
				for(int k=1; k<=m && k<i; k++) {
					dp[i][j] +=  dp[i-k][j-1];
				}
			}
		}
		return dp[x][n];
	}
	
	
}

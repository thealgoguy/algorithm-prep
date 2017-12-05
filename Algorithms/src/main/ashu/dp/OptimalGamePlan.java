package main.ashu.dp;

//Consider a row of n coins of values v1 . . . vn, where n is even. 
//We play a game against an opponent by alternating turns. In each turn, a player selects either the first or last coin from the row, 
//removes it from the row permanently, and receives the value of the coin.
//Determine the maximum possible amount of money we can definitely win if we move first.
// Note : The opponent is as clever as you(he knows DP too :P)
//Rule : Any player would pick either first or the last coin form the remaining set and the chosen coin is excluded once chosen.

//F(i, j)  represents the maximum value the user can collect from  i'th coin to j'th coin.
//F(i, j)  = Max(Vi + min(F(i+2, j), F(i+1, j-1) ), Vj + min(F(i+1, j-1), F(i, j-2) )). min is involved b/c opponent is as clever

public class OptimalGamePlan {
	  
	public static void main(String args []) {
		int arr1[] = {8, 15, 3, 7};
	    System.out.println("Maximum money collected = "+maxValueCollected(arr1));
	 
	    int arr2[] = {2, 2, 2, 2};
	    System.out.println("Maximum money collected = "+maxValueCollected(arr2));
	 
	    int arr3[] = {20, 30, 2, 2, 2, 10};
	    System.out.println("Maximum money collected = "+maxValueCollected(arr3));
	}	
	
	public static int maxValueCollected(int a[]) {
		int n = a.length;
		int dp [][] = new int[n][n];              //only need to fil the upper triangular matrix
		for(int i=0; i<n; i++) dp[i][i] = a[i];  //single coin case
		for(int i=0; i<n-1; i++) dp[i][i+1] = Math.max(a[i], a[i+1]);  //only two coins
		for(int len=1; len<=n; len++) {
			for(int i=0; i+len-1<n; i++) {
				int j = i+len-1;
				int val1 = (i+2 < n)? dp[i+2][j] : 0;
				int val2 = (i+1 <= j-1) ? dp[i+1][j-1] : 0;
				int val3 = (i <= j-2) ? dp[i][j-2] : 0; 
				dp[i][j] = Math.max(a[i]+Math.min(val1, val2), a[j]+Math.min(val2, val3));
			}
		}
		return dp[0][n-1];
	}

}

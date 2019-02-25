package main.ashu.matrix;

//Given a matrix of N * M. Find the maximum path sum in matrix.
//The maximum path is sum of all elements from first row to last row 
//where you are allowed to move only down or diagonally to left or right. 
//You can start from any element in first row(at the start of any column).
//https://www.geeksforgeeks.org/maximum-path-sum-matrix/
public class LargestSumPathInMatrix {
	public static void main(String args[]) {
		int a[][] =  { { 10, 10, 2, 0, 20, 4 },
                { 1, 0, 0, 30, 2, 5 },
                { 0, 10, 4, 0, 2, 0 },
                { 1, 0, 2, 20, 0, 4 } };
		
		int m = a.length;
		int n = a[0].length;
		int max = Integer.MIN_VALUE;
		//we don't need dp array if we are allowed to modify the original array
		//we can keep adding values to the original array itself
		int dp [][] = new int[m][n];
		//aall cols of first row
		for(int i=0; i<n; i++){
			dp[0][i] = a[0][i];
		}
		for(int i=1; i<m; i++) {
			for(int j=0; j<n; j++) {
				//if at left boundary
				if(j==0 && j+1 < n) {
					dp[i][j] = a[i][j] + Math.max(dp[i-1][j], dp[i-1][j+1]);
				}
				//right boundary
				else if(j==n-1 && j-1>=0) {
					dp[i][j] = a[i][j] + Math.max(dp[i-1][j], dp[i-1][j-1]);
				}
				//b/w left and right boundary
				else {
					dp[i][j] = a[i][j] + Math.max(dp[i-1][j], Math.max(dp[i-1][j-1], dp[i-1][j+1]));
				}
				//update global max
				if(i == m-1)     {
					max = Math.max(max, dp[i][j]);
				}
			}			
		}
		System.out.println("Max path sum in matrix is : "+max);
	}
}

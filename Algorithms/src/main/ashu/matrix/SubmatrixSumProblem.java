package main.ashu.matrix;

// matrix version of subarray sum problem - dynamic programming with prefix sum
//Given an N x N matrix and two integers S and K,find whether there exists a K x K sub-matrix
//with sum equal to S.
public class SubmatrixSumProblem {
	public static void main(String args []) {
		int arr[][] = { { 1, 2, 3, 4 }, 
				{ 5, 6, 7, 8 }, 
				{ 9, 10, 11, 12 }, 
				{ 13, 14, 15, 16 } }; 
		int K = 2; 
		int S = 14;
		int N = arr.length;
		 int dp [][] = new int[N+1][N+1];
		 //compute sums of prefix submatrixes
		 for(int i=1; i<=N; i++) {
			 for(int j=1; j<=N; j++) {
				 dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + arr[i-1][j-1];
			 }
		 }
		 
		 //now check for submatrixes of size K for sum S
		 boolean found = false;
		 int lastRow = 0, lastCol = 0;
		 for(int i=K; i<=N; i++) {
			 for(int j=K; j<=N; j++) {
				 int currentSum = dp[i][j] - dp[i-K][j] - dp[i][j-K] + dp[i-K][j-K];
				 if(currentSum == S){
					 found = true;
					 lastRow = i;
					 lastCol = j;
					 break;
				 }
			 }
		 }
		 if(!found) {
			 System.out.println("Submatrix with given sum doesn't exist");
		 } else {
			 System.out.println("Submatrix with given sum exists");
			 for(int i=lastRow-K; i<lastRow; i++) {
				 for(int j=lastCol-K; j<lastCol; j++) {
					 System.out.print(arr[i][j] +" ");
				 }
				 System.out.println();
			 }
		 }
		
	}
	
}

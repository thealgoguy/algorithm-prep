package main.ashu.matrix;

//A zigzag sequence starts from the top and ends at the bottom. 
//Two consecutive elements of sequence cannot belong to same column.
/*Maximum Zigzag sum starting from arr[i][j] to a 
bottom cell can be written as :
zzs(i, j) = arr[i][j] + max(zzs(i+1, k)), 
               where k = 0, 1, 2 and k != j
zzs(i, j) = arr[i][j], if i = n-1 
We have to find the largest among all as
Result = zzs(0, j) where 0 <= j < n*/

public class MatrixZigZagSum {
	public static void main (String[] args) {
		int n = 3; 
	    int  a[][] = { {4, 2, 1}, 
	                     {3, 9, 6}, 
	                     {11, 3, 15}};
		    int dp[][] = new int[n][n];
		    int max = Integer.MIN_VALUE;
		    for(int i=0; i<n; i++) {
		        dp[0][i] = a[0][i];
		        max = Math.max(max, dp[0][i]);
		    }
		    for(int i=1; i<n; i++) {
		        for(int j=0; j<n; j++){
		        	dp[i][j] = Integer.MIN_VALUE;
		        	//take max of previous zig-zags of different columns
		            for(int k=0; k<n; k++){
		            	if(k !=j) {
		            		dp[i][j] = Math.max(dp[i][j], dp[i-1][k]);
		            	}
		            }
		            dp[i][j] += a[i][j];
		            max = Math.max(dp[i][j], max);//need to update for last row only(i=n-1)
		        }
		    }
		    System.out.println(max);
		}

}

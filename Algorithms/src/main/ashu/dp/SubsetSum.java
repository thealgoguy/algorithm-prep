package main.ashu.dp;

import java.util.ArrayList;
import java.util.List;

public class SubsetSum {
	public static void main (String[] args) {
		int arr [] = {1, 2, 3, 4, 5};
		int sum = 10;
		int n = arr.length;
		boolean dp[][] = new boolean[ sum+1][n+1];
		for(int i=1; i<=sum; i++) dp[i][0] = false;
		for(int i=0; i<=n; i++) dp[0][i] = true;
		
		for(int i=1; i<=sum; i++) {
		    for(int j=1; j<=n; j++) {
		        dp[i][j] = ((i-arr[j-1] >=0) ? dp[i-arr[j-1]][j-1] : false) || dp[i][j-1];
		    }
		}
		if(dp[sum][n]) {
			System.out.println("Subset with sum "+sum+" exists");
			List<Integer> path = new ArrayList();
			printSubset(arr, sum, n, dp);
		}
		else 
			System.out.println("No subset with sum "+sum+" exists");
		
	}
	
	private static void printSubset(int[] a, int sum, int n, boolean[][] dp) {
		if(dp[sum][n]) System.out.println("Subset exists with sum = "+sum);
		else System.out.println("Subset doesn't exist");
		String subset = "";
		int i= sum, j=n;
		while(sum>0 && j>0) {
			if(dp[i][j] == dp[i][j-1]) {
				j--;
			}
			else {
				subset += a[j-1] +" ";
				i -= a[j-1];
				j--;
			}
		}
		System.out.println("Subset is : "+subset);
	}
	
	//using backtracking
	public static void printPath(int a[],  boolean dp[][], int sum, int n, List<Integer> path){
	    if(sum < 0 || n<=0) return;
	    if(!dp[sum][n]) return;
	    if(sum==a[n-1]){
	        path.add(a[n-1]);
	        for(int i=0; i<path.size(); i++) {
	            System.out.print(path.get(i)+" ");
	        }
	        System.out.println();
	        path.remove(path.size()-1);
	        return;
	    }
	    
	    if(sum-a[n-1] >=0 && dp[sum-a[n-1]][n-1]){
	    	path.add(a[n-1]);
	    	printPath(a, dp, sum-a[n-1], n-1, path);
	    	path.remove(path.size()-1);
	    }	          
	    if(dp[sum][n-1])
	          printPath(a, dp, sum, n-1, path);
	    
	}
}

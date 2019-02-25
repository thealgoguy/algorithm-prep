package main.ashu.dp;

import java.util.Arrays;

//variation 1 : make change of X, unlimited supply of each coin
//variation 2 : count ways to get X, unlimited supply of each coin
//variation 3 : make X using minimum coins, unlimited supply of each coin
//variation 4 : make X, limited supply of each coin - https://stackoverflow.com/questions/4198210/coin-change-with-limited-number-of-coins
public class CoinChangeProblem {
    public static void main(String args []) {
    	int coin [] = {9, 6, 5, 1}; 
    	int n = coin.length;
    	int X = 11;
    	int ways = totalWaysToMakeChange(coin, X);
    	System.out.println("Number of ways to make change = "+ways);
    	int minCoins = countMinWaysToMakeChange(coin, X);
    	System.out.println("Minimum number of coins required to make change = "+minCoins);
    }
    
    //https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
    private static int countMinWaysToMakeChange(int a[], int X) {
    	int n = a.length;
    	//dp[i] = min coins required to make change i
    	int dp [] = new int[X+1];
    	Arrays.fill(dp, Integer.MAX_VALUE);
    	dp[0] = 0; //no coin required to make sum 0
    	for(int i=1; i<=X; i++) {
    		//choose from coins smaller than i
    		for(int j=0; j<n; j++) {
    			if(a[j] <= i) {
    				int rem = dp[i-a[j]];
    				if(rem != Integer.MAX_VALUE && rem+1 < dp[i]) {
    					dp[i] = rem +1;
    				}
    			}
    		}
    	}
    	return dp[X];
    }
    
    private static int totalWaysToMakeChange(int a[], int X) {
    	int n = a.length;
    	//dp[i] = ways to make sum i with n coins
    	int dp [] = new int[X+1];
    	dp[0] = 1;
    	//Pick up all coins one by one and count their contributions 
    	//in sums bigger than the coin value
    	for(int i=0; i<n; i++) {
    		for(int j=a[i]; j<=X; j++) {
    			//if we take ith coin having value a[i], then the remaining sum(j-a[i])
    			//and ways to make remaining sum = dp[j-a[i])
    			dp[j] += dp[j-a[i]];
    		}
    	}
    	return dp[X];
    }
}

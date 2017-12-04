packag ashu

import java.util.*;

class BinKnapsack {
	//inputs for the problem - w[n], c[n], maximize the cost for all possible weights.
	//subproblem - try to fill smaller knapsacks with the same n items. to fill any knapsack we can choose from n items, hence 2D dp.
 public static void main(String args []) {
    Scanner sc = new Scanner(System.in);
	int n = sc.nextInt();
    int w [] = new int[n+1];
	int c [] = new int[n+1];
	int maxCapacity = 0;
	sc.nextLine();
	for(int i=1; i<=n; i++) {
	   String s = sc.nextLine();
	   String arr [] = s.split(" ");
	   w[i] = Integer.parseInt(arr[0]); 
	   c[i] = Integer.parseInt(arr[1]); 
	   maxCapacity += w[i];
	}
    int dp [][] = new int[maxCapacity+1][n+1];
	for(int i=0; i<=maxCapacity; i++) dp[i][0] = 0; //with no items included in knapsack, max value will be zero
	for(int i=0; i<=n; i++) dp[0][i] = 0;           //if weight of the knapsack is zero, max value will be zero
	
	for(int i=1; i<=maxCapacity; i++) {
	  for(int j=1; j<=n; j++)  {
	     if(w[j] > i) dp[i][j] = dp[i][j-1];  //taking jth items exceed knapsack capacity
		 else {
		    dp[i][j] = Math.max(dp[i][j-1], dp[i-w[j]][j-1]+c[j]); //consider the best choice among taking or leaving the jth item.
		 }
	  }
	
	}
	for(int i=1; i<=maxCapacity; i++) System.out.print(dp[i][n]+" ");
 
 }

}
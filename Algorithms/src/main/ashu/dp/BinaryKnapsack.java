package main.ashu.dp;

class BinaryKnapsack {
	//inputs for the problem - w[n], c[n], maximize the cost for all possible weights.
	//subproblem - try to fill smaller knapsacks with the same n items. 
	//to fill any knapsack we can choose from n items, hence 2D dp.
	public static void main(String args[]) {
		int val[] = new int[] { 60, 100, 120 };
		int wt[] = new int[] { 10, 20, 30 };
		int W = 50;
		int n = val.length;
		int i, w;
		int dp[][] = new int[n + 1][W + 1];
		// Build table dp[][] in bottom up manner
		for (i = 0; i <= n; i++) {
			for (w = 0; w <= W; w++) {
				if (i == 0 || w == 0)
					dp[i][w] = 0;
				else if (wt[i - 1] <= w)
					dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
				else
					dp[i][w] = dp[i - 1][w];
			}
		}
		System.out.println(dp[n][W]);
		/*for (i = 0; i <= W; i++)
			System.out.println(dp[n][i] + " ");
*/
	}

}
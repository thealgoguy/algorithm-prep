package main.ashu.dp;

//https://massivealgorithms.blogspot.in/2014/06/leetcode-best-time-to-buy-and-sell_24.html
//https://www.hackerrank.com/challenges/stockmax/problem
//The cost of a stock on each day is given in an array, 
//find the max profit that you can make by buying and selling in those days
//Each day, you can either buy one share, 
//sell any number of shares, or not make any transaction at all. 
//Note : this is simplest version of the problem where you can engage in continuous sells/buys
//Trade is easier when we travel backwards in time
public class StockProfitMaximization {
	public static void main(String args []) {
		int price [] = {1, 2, 100};
		long profit = trade1(price);
		System.out.println("Max profit that can be earned = "+profit);
	}

	//no limit on transactions(on any day either buy or sell or leave)
	//can engage in multiple transactions
	public static long trade1(int prices[]) {
		int n = prices.length;
		long p = 0;
		int max = prices[n-1];
		for(int i=n-2; i>=0; i--) {
			if(prices[i] < max) p += (max-prices[i]);
			else {
				max = prices[i];
			}
		}
		return p;
	}
	//constraint : can make at most 1 transaction(i.e. can make 1 buy and 1 sell)
	//If plotted on graph, We need to find the largest peak following the smallest valley.
	//Find i and j that maximizes Aj – Ai, where i < j.
	public static long trade2(int prices[]) {
		long minprice = Integer.MAX_VALUE;
		long maxprofit = 0;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < minprice)
				minprice = prices[i];
			else if (prices[i] - minprice > maxprofit)
				maxprofit = prices[i] - minprice;
		}
		return maxprofit;
	}
	//can do as many transactions as you like (ie, buy one and sell one share of the stock multiple times).
	//cannot engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
	//https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/solution/
	//if plotted, we're interested in all {valley, peak} pairs
	//1. Find the local minima and store it as starting index. If not exists, return.
	//2. Find the local maxima. and store it as ending index. If we reach the end, set the end as ending index.
	//3. Update the solution (Increment count of buy sell pairs)
	public static long trade3(int prices []){
		if(prices == null || prices.length == 0){  
			return 0;  
		}  
		int len = prices.length;  
		long maxProfit = 0;  
		for(int i = 1; i < len; i++){  
			int tempProfit = prices[i] - prices[i-1];  
			if(tempProfit > 0){  
				maxProfit += tempProfit;  
			}  
		}  
		return maxProfit; 
	}
	//can make at most two transactions
	//use two arrays f[i] and b[i] to record the maximum profit for price[0...i-1] and price[i...n-1]. 
	//After that, we just need to find the maximum of f[i]+b[i].
	public static long trade4(int prices []) {
		if(prices.length == 0){  
			return 0;  
		}  
		long max = 0; 
		int[] left = new int[prices.length];
		int[] right = new int[prices.length];
		process(prices, left, right);
		for(int i=0; i<prices.length; i++){  
			max = Math.max(max, left[i]+right[i]);  
		}  
		return max; 
	}

	public static void process(int[] prices, int[] left, int[] right){ 
		left[0] = 0;  
		int min = prices[0];   //keep updating min buy value
		for(int i=1; i<left.length; i++){  
			left[i] = Math.max(left[i-1], prices[i]-min);
			min = Math.min(min, prices[i]); 
		}
		right[right.length-1] = 0;  //keep updating max sell value
		int max = prices[right.length-1]; 
		for(int i=right.length-2; i>=0; i--){  
			right[i] = Math.max(right[i+1], max-prices[i]);
			max = Math.max(max, prices[i]); 
		}
	}
	//can make at most K transactions
	//not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
	//http://massivealgorithms.blogspot.in/2015/05/the-code-sniper-188-best-time-to-buy.html
	/**
	 * dp[i, j] represents the max profit up until prices[j] using at most i transactions. 
	 * dp[i, j] = max(dp[i, j-1], prices[j] - prices[m] + dp[i-1, m]) { m in range of [0, j-1] }
	 *          = max(dp[i, j-1], prices[j] + max(dp[i-1, m] - prices[m]))
	 * dp[0, j] = 0; 0 transactions makes 0 profit
	 * dp[i, 0] = 0; if there is only one price data point you can't make any transaction.
	 */
	public static long trade5(int prices [], int k) {
		int n = prices.length;
		if (n <= 1)
			return 0;
		//if k >= n/2, then you can make maximum number of transactions.
		if (k >=  n/2) {
			int maxPro = 0;
			for (int i = 1; i < n; i++) {
				if (prices[i] > prices[i-1])
					maxPro += prices[i] - prices[i-1];
			}
			return maxPro;
		}
		int[][] dp = new int[k+1][n];
		for (int i = 1; i <= k; i++) {
			int localMax = dp[i-1][0] - prices[0];
			for (int j = 1; j < n; j++) {
				dp[i][j] = Math.max(dp[i][j-1],  prices[j] + localMax);
				localMax = Math.max(localMax, dp[i-1][j] - prices[j]);
			}
		}
		return dp[k][n-1];  
	}
	
	//trade3 with cooldown
	//http://massivealgorithms.blogspot.in/2015/11/leetcode-309-best-time-to-buy-and-sell.html
	//buy[i] means we decide buy or no buy a stock at day i  to  maximize profits,
	//and sell[i] means we sell or not sell a stock at day i to  maximize profits.
	//buy[i] = max(buy[i-1] , sell[i-2] – prices[i])  // So we should use sell[i-2] means we cooldown one day.
	//sell[i] = max(sell[i-1], buy[i-1] + prices[i])
	public long trade6(int prices []){
		if(prices == null || prices.length <2) return 0;
		int n = prices.length;
		int buy [] = new int[n];
		int sell [] = new int[n];
		buy[0] = -prices[0];
		buy[1] = Math.max(-prices[0], -prices[1]);
		sell[1] = Math.max(0, prices[1] - prices[0]);
		for(int i=0; i<n-1; i++) {
			buy[i] = Math.max(sell[i - 2] - prices[i], buy[i - 1]);
            sell[i] = Math.max(buy[i - 1] + prices[i], sell[i - 1]);
		}
		return sell[n-1];
	}
}

package main.ashu.dp;

//https://www.geeksforgeeks.org/number-of-subsets-with-sum-divisible-by-m/
//Given an array of integers, find number of subsequence(subset) such that the sum of the subsequence is divisible by m.
//It is given that sum of array elements is small.
public class CountSubsetsWithSumDivisibleByK {
	public static void main(String args[]) {
		int a[] = { 1, 2, 3, 4 };
		int n = a.length;
		int sum = 0;
		for (int i = 0; i < n; i++)
			sum += a[i];
		int count = 0;
		int m = 2;
		int dp[][] = new int[sum + 1][n + 1];
		for (int i = 1; i <= sum; i++)
			dp[sum][0] = 0;
		for (int i = 0; i <= n; i++)
			dp[0][i] = 1;
		for (int i = 1; i <= sum; i++) {
			for (int j = 1; j <= n; j++) {
				dp[i][j] = (a[j - 1] > i) ? dp[i][j - 1] : (dp[i][j - 1] + dp[i
						- a[j - 1]][j - 1]); // inclusion-exclusion
			}
			if (dp[i][n] > 0 && i % m == 0)
				count += dp[i][n];
		}
		System.out.println("Numnber of subsets with sum divisible by m is : "
				+ count);
	}
}

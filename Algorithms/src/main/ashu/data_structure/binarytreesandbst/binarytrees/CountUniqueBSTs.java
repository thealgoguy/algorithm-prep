package main.ashu.data_structure.binarytreesandbst.binarytrees;

//Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
//accepted - https://leetcode.com/problems/unique-binary-search-trees/
//also called catalan number - Cn = (2n)!/n!*(n+1)!
//count of binary trees = n! * count(BSTs)
public class CountUniqueBSTs {
	public int numTrees(int n) {
		if (n <= 1)
			return 1;
		int dp[] = new int[n + 1];
		dp[0] = dp[1] = 1;
		// consider in sorted order, make each element as root
		for (int i = 2; i <= n; i++) {
			// for a[i], any of the smaller number can be made root of lft
			// subtree, count all
			for (int j = 0; j < i; j++) {
				dp[i] += dp[j] * dp[i - 1 - j];
			}
		}
		return dp[n];
	}
}

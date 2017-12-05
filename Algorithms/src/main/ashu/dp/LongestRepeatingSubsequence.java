package main.ashu.dp;
//Given a string, find the longest repeating subsequence
//Note : Variation of LCS. Find LCS(S, S)

public class LongestRepeatingSubsequence {
	public static void main(String args []) {
		String s = "ATACTCGGA";
		char ch [] = s.toCharArray();
		int n = s.length();
		int dp [][] = new int[n+1][n+1];
		for(int i=0; i<=n; i++) dp[i][i] = 0;
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=n; j++) {
				if(ch[i-1] == ch[j-1] && i != j) {
					dp[i][j] = 1 + dp[i-1][j-1];
				}
				else {
					dp[i][j] =  Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		System.out.println("Length of longest repeating subsequence is : "+dp[n][n]);
		int i=n, j=n;
		String ans = "";
		while(i>0 && j>0) {
			if(ch[i-1] == ch[j-1] && i != j) {
				ans = ch[i-1]  + " " + ans;
				i--; j--;
			}
			else if(dp[i][j] == dp[i-1][j]) i--;
			else j--;
		}

		System.out.println("Longest repeating subsequence is : "+ans);

	}
}

package main.ashu.dp;

//Given two strings str1 and str2, find the shortest string that has both str1 and str2 as subsequences.
public class ShortestCommonSupersequence { 
	public static void main(String args []) {
		String str1 = "AGGTAB";
		String str2 = "GXTXAYB";
		int m = str1.length();
		int n = str2.length();
		int dp [][] = new int[m+1][n+1];

		for(int i=0; i<=m; i++) {
			for(int j=0; j<=n; j++) {
				if(i==0) dp[i][j] = j;
				else if(j==0) dp[i][j] = i;
				else
				   dp[i][j] = (str1.charAt(i-1)==str2.charAt(j-1)) ? 1 + dp[i-1][j-1] : 1 + Math.min(dp[i][j-1], dp[i-1][j]);
			}
		}
		System.out.println("Length of smallest supersequence is : "+dp[m][n]);
		int i=m, j=n;
		String ans = "";
		while(i>0 && j>0) {
			if(dp[i][j]==dp[i-1][j-1]+1) {
				ans = str1.charAt(i-1) + ans;
				i--; j--;
			}
			else if(dp[i][j] == dp[i-1][j] +1) {
				ans = str1.charAt(i-1) + ans; 
				i--;
			}
			else {
				ans = str2.charAt(j-1)+ans ;
				j--;
			}
		}
		while(i>0) {
			ans = str1.charAt(i-1) + ans; i--;
		}
		while(j>0) {
			ans = str1.charAt(j-1) + ans; j--;
		}
		System.out.println("Shortest common supersequece is : "+ans);
	}		

}

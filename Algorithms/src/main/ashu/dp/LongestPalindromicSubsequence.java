package main.ashu.dp;

public class LongestPalindromicSubsequence {

	public static void main(String args[]) {
		String s = "GEEKS FOR GEEKS";
		char ch [] = s.toCharArray();
		int n = ch.length;
		int dp [][] = new int[n][n];
		for(int i=0; i<n; i++) dp[i][i] = 1;
		for(int len=2; len<=n; len++) {
			for(int i=0; i+len<=n; i++) {
				int j = i+len-1;
				if(i+1 ==j) {
					dp[i][j] = (ch[i]==ch[j]) ? 2 : 1;
				}
				else dp[i][j] = (ch[i]==ch[j]) ? 2+dp[i+1][j-1] : Math.max(dp[i+1][j], dp[i][j-1]);
			}
		}
		System.out.println("max = "+dp[0][n-1]);
		String pre="", suff="";
		int i=0, j=n-1;
		while(i<=j) {
			if(i+1 == j){
				if(ch[i]==ch[j]) {
					pre += ch[i]; suff = ch[i]+suff;
				}
				else pre += ch[i]; i++; j--;
			}
			else if(dp[i][j]==dp[i+1][j-1]+2) {
				pre += ch[i];  suff = ch[i]+suff;
				i++; j--;
			}
			else if(dp[i][j] ==dp[i+1][j]) i++;
			else j--;
		}
		String lps = pre+suff;
		System.out.println("LPS is : "+lps);
	}

}

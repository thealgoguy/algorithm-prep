package main.ashu.dp;

//Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.
//You have the following 3 operations permitted on a word:
//1. Insert a character
//2. Delete a character
//3. Replace a character
//accepted - https://leetcode.com/problems/edit-distance/
public class EditDistance {
	public int minDistance(String word1, String word2) {
        int m = (word1 == null || word1.length() == 0) ? 0: word1.length();
        int n = (word2 == null || word2.length() == 0) ? 0: word2.length();
        
        if(m == 0) return n;
        if(n==0) return m;
        
        int dp [][] = new int[m+1][n+1];
        for(int i=0; i<=m; i++) dp[i][0] = i;
        for(int i=0; i<=n; i++) dp[0][i] = i;
        
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
            	//no operation if chars match
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }else {
                   dp[i][j] = 1 + Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]); 
                }              
            }
        }
        return dp[m][n];
    }
}

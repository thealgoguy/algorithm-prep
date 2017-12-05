package main.ashu.palindromes;

//Given a string, find the longest palindromic substring in it.
//Approach 1 : Dynamic programming
//Approach 2 : Expanding around center
//Approach 3: Manacher's algorithm O(n) time

public class LongestPalSubstring {
	
	//O(n*n) time and O(n*n) space
	public String longestPalindromeDP(String s) {
		int n = s.length();
		if(n==0) return null;
		char ch [] = s.toCharArray();
		boolean dp [][] = new boolean[n][n];
		for(int i=0; i<n; i++) dp[i][i] = true;
		int max = 1;
		int start=0, end=0;		
		for(int len=2; len <= n; len++) {
			for(int i=0; i+len <= n; i++) {
				int j = i+len-1;
				if(i+1 == j) dp[i][j] = (ch[i] == ch[j]);
				else if(ch[i]==ch[j]) dp[i][j] = dp[i+1][j-1];
				if(dp[i][j] && max < (j-i+1)) {
					start = i; end = j;
					max = j-i+1;
				}
			}
		}
		return s.substring(start, end+1);
	}
	
	//O(n*n) time and O(1) space..expanding around center
	public String longestPalindromeUsingCenter(String s) {
	    int start = 0, end = 0;
	    for (int i = 0; i < s.length(); i++) {
	        int len1 = expandAroundCenter(s, i, i);
	        int len2 = expandAroundCenter(s, i, i + 1);
	        int len = Math.max(len1, len2);
	        if (len > end - start) {
	            start = i - (len - 1) / 2;
	            end = i + len / 2;
	        }
	    }
	    return s.substring(start, end + 1);
	}
	private int expandAroundCenter(String s, int left, int right) {
	    int L = left, R = right;
	    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
	        L--;
	        R++;
	    }
	    return R - L - 1;
	}
	
	public static void main(String args []) {
		LongestPalSubstring lp = new LongestPalSubstring();
		String s = "babaddtattarrattatddetartrateedredividerb";
		String p = lp.longestPalindromeDP(s);
		System.out.println(p);
		p = lp.longestPalindromeUsingCenter(s);
		System.out.println(p);
	}
}

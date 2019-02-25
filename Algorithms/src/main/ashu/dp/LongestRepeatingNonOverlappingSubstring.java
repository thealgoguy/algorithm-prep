package main.ashu.dp;

//https://www.geeksforgeeks.org/longest-repeating-and-non-overlapping-substring/
//The idea is to find the longest repeating suffix among all the prefixes of the given string
//Use brute-force for enumerating the prefix-suffixes and use dp table for memoizing smaller sub-problems
public class LongestRepeatingNonOverlappingSubstring {
   
	public static void main(String args []) {
		String str = "geeksforgeeks";
		int n =str.length();
		int dp [][] = new int[n+1][n+1];
		int max = Integer.MIN_VALUE;
		int maxIndex = -1;
		for(int i=1; i<=n; i++) {
			for(int j=i+1; j<=n; j++) {
				//(j-i) > dp[i-1][j-1] to avoid overlapping of suffixes
				if(str.charAt(i-1) == str.charAt(j-1) && (j-i) > dp[i-1][j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
					if(max < dp[i][j]) {
						max = Math.max(max, dp[i][j]);
						maxIndex = Math.max(i, maxIndex);
					}
				} else {
					dp[i][j] = 0;
				}
			}
		}
		String ans = "";
		if(max >0) {
			for(int i=maxIndex-max+1; i<=maxIndex; i++) {
				ans = ans + str.charAt(i-1);
			}
		}
		System.out.println("Length of ongest reeating non-overlapping substring = "+max);
		System.out.println("Longest reeating non-overlapping substring = "+ans);
	}
}

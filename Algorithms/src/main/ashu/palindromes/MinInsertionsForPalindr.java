package main.ashu.palindromes;

import java.util.Scanner;
//Let S[i, j] represents a sub-string of string S starting from index i and ending at index j (both inclusive)
//c[i, j] be the optimal solution for S[i, j].
//Obviously, c[i, j] = 0 if i >= j.
//In general, we have the recurrence:
//c[i,j] = c[i+1,j-1] if s[i]=s[j]
//       = min(c[i+1,j],c[i,j-1])+1, if s[i] != s[j]
//can also be solved using longest common substring problem
public class MinInsertionsForPalindr {
  public static void main(String args []) {
	  Scanner sc  = new Scanner(System.in);
	   System.out.println("Enter the string");
	   String str = sc.nextLine();
	   int n = str.length();
	   int dp [][] = new int[n][n];
	   for(int i=0; i<n; i++) dp[i][i] = 0;
	   for(int i=0; i<n; i++) {
		   for(int j=i+1; j<n; j++) {
			   if(i+1==j) dp[i][j] = (str.charAt(i)==str.charAt(j))?0:1;
			   else if(str.charAt(i)==str.charAt(j)) dp[i][j] = dp[i+1][j-1];
			   else dp[i][j] = Math.min(dp[i][j-1], dp[i+1][j]) + 1;
		   }
	   }
	   System.out.println("Minimum characters needed to make paliondrome is : "+dp[0][n-1]);
  }
}
//Follow up : find the minm characters to be added at the front/end of the string to make it a palindrome
//Print the resulting palindrome also after minimum insertion
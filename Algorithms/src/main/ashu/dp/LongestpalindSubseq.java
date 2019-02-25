package main.ashu.dp;

import java.util.Scanner;

public class LongestpalindSubseq {
   public static void main(String args []) {
	   Scanner sc = new Scanner(System.in);
	   String s = sc.next();
	   int n = s.length();
	   char a [] = new char[n+1];
	   int dp [][] = new int[n+1][n+1];
	   for(int i=0; i<n; i++) {
		   a[i+1] = s.charAt(i); 
	   }  
	   
	   //think in terms of length
	   //fill only the upper diagonal values
	   for(int len=1; len<=n; len++) {  //instead of calculating n*n values calculate only 1+2+3....n=n*(n+1)/2 values
		   for(int i=1; i<=n-len+1; i++) {
			   int j = i+len-1;
			   if(len==1) dp[i][j]=1;
			   else if(len==2) dp[i][j] = (a[i]==a[j]) ? 2 : 1;
			   else dp[i][j] = (a[i]==a[j]) ? dp[i+1][j-1]+2 : Math.max(dp[i+1][j], dp[i][j-1]);
		   }
	   }
	   System.out.println(dp[1][n]);
	   //how to print all the longest pal subsequences
	   //how to do this using LCS of string and its reverse
	   
	   StringBuffer lps = new StringBuffer(""); int x=1, y=n;
	   while(x<=y) {
		   if(x==y) break;   //check for odd length palindrome.
	       else if(a[x]==a[y]) { lps.append(a[x]); x++; y--;} 
		   else if(dp[x][y]==dp[x+1][y]) x++;
		   else if(dp[x][y]==dp[x][y-1]) y--;
	   }
	   String ans = lps.toString();
	   if(x==y) ans +=a[x];
	   ans += lps.reverse();
	   System.out.println("LPS is "+ans);
   }
}

package main.ashu.palindromes;

import java.util.Scanner;
//Given a string, a partitioning of the string is a palindrome partitioning if 
//every substring of the partition is a palindrome. 
//Determine the fewest cuts needed for palindrome partitioning of a given string.
//cuts[i,j] = 0 if s(i,j) is palindrome 
//else there can be (j-1) posible cuts starting from i to j-1. Find the optimal cut
//similar to Matrix Chain Multiplication....O(n3)
//can be done using 1 dimension dp also...O(n2)

public class PalindromePartitioningDP1 {
  public static void main(String args []) {
	  /*Scanner sc = new Scanner(System.in);
	  System.out.println("Enter the string");
	  String s = sc.nextLine();*/
	  String s = "ababbbabbababa";
	  int n = s.length();
	  int cuts [][] = new int[n][n];
	  boolean p [][] = new boolean[n][n];
	  for(int i=0; i<n; i++) {
		  p[i][i]=true; 
		  cuts[i][i]=0;
	  }
	  for(int len=2; len<=n; len++) {
		  for(int i=0; i<n-len+1; i++) {
			  int j = i+len-1;
			  if(len==2) p[i][j]= s.charAt(i)==s.charAt(j);
			  else p[i][j] = (s.charAt(i)==s.charAt(j)) && p[i+1][j-1];
			  if(p[i][j]) cuts[i][j] = 0;
			  else {
				  cuts[i][j] = Integer.MAX_VALUE;
				  for(int k=i; k<j; k++) {
					  cuts[i][j] = Math.min(cuts[i][j],cuts[i][k]+cuts[k+1][j]+1);
				  }
			  }
		  }
	  }
	  System.out.println("Minimum cuts needed for palindrome partitioning = "+cuts[0][n-1]);
  }
}

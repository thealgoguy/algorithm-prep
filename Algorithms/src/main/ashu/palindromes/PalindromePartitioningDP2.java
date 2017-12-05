package main.ashu.palindromes;

import java.util.Scanner;
//optimization of previous version from O(n3) to O(n2)
//Follow up : Print all palindromic partitions 
//https://www.youtube.com/watch?v=WPr1jDh3bUQ
public class PalindromePartitioningDP2 {
	  public static void main(String args []) {
		  /*Scanner sc = new Scanner(System.in);
		  System.out.println("Enter the string");
		  String s = sc.nextLine();*/
		  String s = "ababbbabbababa";
		  int n = s.length();
		  boolean p [][] = new boolean[n][n];
		  int cuts [] = new int[n];
		  for(int i=0; i<n; i++) {
			  p[i][i]=true; 
			  cuts[i]=0;
		  }
		  for(int len=2; len<=n; len++) {
			  for(int i=0; i<n-len+1; i++) {
				  int j = i+len-1;
				  if(len==2) p[i][j]= s.charAt(i)==s.charAt(j);
				  else p[i][j] = (s.charAt(i)==s.charAt(j)) && p[i+1][j-1];
			  }
		  }
		  //now calculate cuts till index i
		  cuts[0]=0;
		  cuts[1]=(s.charAt(0)==s.charAt(1)) ? 0 : 1;
		  for(int i=1; i<n; i++) {
			  if(p[0][i]) cuts[i] = 0;
			  else { //make cuts
				   cuts[i] = Integer.MAX_VALUE;
				  for(int k=0; k<i; k++) {
					  if(p[k+1][i]) {
						  cuts[i] = Math.min(cuts[i], cuts[k]+1);	
					  }
				  }
			  }
		  }
		  System.out.println("Minimum cuts needed for palindrome partitioning = "+cuts[n-1]);
	  }
}

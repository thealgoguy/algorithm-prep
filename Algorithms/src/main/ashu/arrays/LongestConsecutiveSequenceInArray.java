package main.ashu.arrays;

import java.util.HashSet;
import java.util.Set;

//Given an unsorted array of integers, 
//find the length of the longest consecutive elements sequence.
//For example,
//Given [100, 4, 200, 1, 3, 2],The longest consecutive elements sequence is [1, 2, 3, 4]. 
//Return its length: 4.  Source - https://www.geeksforgeeks.org/longest-consecutive-subsequence/
//O(n) solution
public class LongestConsecutiveSequenceInArray {
  public static void main(String args []) {
	  int a [] = {100, 4, 200, 1, 3, 2};
	  int n = a.length;
	  Set<Integer> set = new HashSet<Integer>();
	  for(int i=0; i<n; i++) {
		  set.add(a[i]);
	  }
	  int len = 0;
	  for(int i=0; i<n; i++) {
		  //check from the smallest number of any sequence
		  if(!set.contains(a[i]-1)) {
			  int j = a[i];
			  while(set.contains(j)) {
				  j++; 
			  }
			  len = Math.max(len, j-a[i]);
		  }
	  }
	  System.out.println("length of the longest consecutive sequence is : "+len);
  }
}

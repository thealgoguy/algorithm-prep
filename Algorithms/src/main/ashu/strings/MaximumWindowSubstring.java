package main.ashu.strings;

import java.util.HashMap;
import java.util.Map;

//https://www.programcreek.com/2014/05/leetcode-minimum-window-substring-java/
//Given a string S and a string T, find the minimum window in S 
//which will contain all the characters in T in complexity O(n).
//WRONG implementation - correct it
public class MaximumWindowSubstring {
   public static void main(String args []) {
	   String s = "ADOBECODEBANC";
	   String t = "ABC";
	   int m = s.length();
	   int n = t.length();
	   //create a frequency map of second string
	   Map<Character, Integer> target = new HashMap<Character, Integer>();
	   for(int i=0; i<n; i++) {
		  if(!target.containsKey(t.charAt(i))) {
			  target.put(t.charAt(i), 1);
		  } else {
			  target.put(t.charAt(i), target.get(t.charAt(i))+1);
		  }
	   }
	   //walk over s and check
	   Map<Character, Integer> map = new HashMap<Character, Integer>();
	   int max = 0;
	   int i=0, j=0;
	   while(j<m) {
		   //check if next char can be accommodated
		   if(target.containsKey(s.charAt(j)) && map.containsKey(s.charAt(j))) {
			   while(i<=j && map.get(s.charAt(j)) >= target.get(s.charAt(j))){
				   map.put(s.charAt(i), map.get(s.charAt(i))-1);
				   i++;
			   }
		   }
		   if(!map.containsKey(s.charAt(j))) {
			   map.put(s.charAt(j), 1);
		   } else {
			   map.put(s.charAt(j), map.get(s.charAt(j))+1);
		   }
		   max = Math.max(max, j-i+1);
		   j++;
	   }
	   System.out.print("Length of minimum siubsgtring containing all characters of other string = "+max);
   }
}

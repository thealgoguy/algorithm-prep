package main.ashu.strings;

import java.util.HashMap;
import java.util.Map;

//Given S string S and S string T, find the minimum window in S which will contain all the characters in T in linear time complexity.
//Note that when the count of S character C in T is N, then the count of C in minimum window in S should be at least N.
//https://www.interviewbit.com/problems/window-string/
//http://buttercola.blogspot.com/2014/09/leetcode-minimum-window-substring.html
public class MinimumWindowString {
	public static void main(String args []) {
		String S = "ADOBECODEBANC";
		String T = "ABC";
        String str = minWindow(S, T);
        System.out.println("Minimum wondow substring = "+str);
		
	}
	
	public static String minWindow(String S, String T) {
	      if (S == null || S.length() == 0 || T == null || T.length() == 0) {
	            return "";
	        }        
	        Map<Character, Integer> map = new HashMap<Character, Integer>();
	        Map<Character, Integer> dict = new HashMap<Character, Integer>(); 
	        for (int i = 0; i < T.length(); i++) {
	            map.put(T.charAt(i), 0);             
	            if (dict.containsKey(T.charAt(i))) {
	                dict.put(T.charAt(i), dict.get(T.charAt(i)) + 1);
	            } else {
	                dict.put(T.charAt(i), 1);
	            }
	        }	         
	        int start = 0;
	        int count = 0;
	        int minLen = S.length() + 1;
	        String result = "";         
	        for (int end = 0; end < S.length(); end++) {
	        	//if valid char, include and increment count if needed
	            if (map.containsKey(S.charAt(end))) {
	                map.put(S.charAt(end), map.get(S.charAt(end)) + 1);	                 
	                if (map.get(S.charAt(end)) <= dict.get(S.charAt(end))) {
	                    count++;
	                }
	            }
	            //shrink form left by removing invalid chars
	            //chars not present in dict or having higher frequency than in dict
	            while (start <= end && (!dict.containsKey(S.charAt(start)) || 
	                    map.get(S.charAt(start)) > dict.get(S.charAt(start)))) {
	                      if (map.containsKey(S.charAt(start))) {
	                          map.put(S.charAt(start), map.get(S.charAt(start)) -1);
	                      }
	                      start++;
	                } 
	               //update answer
	              if (count == T.length()) {
	                  if (end - start + 1 < minLen) {
	                      minLen = end - start + 1;
	                      result = S.substring(start, end + 1);
	                  }
	              }
	        }        
	        return result;
	    }
}

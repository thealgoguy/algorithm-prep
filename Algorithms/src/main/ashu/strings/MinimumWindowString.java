package main.ashu.strings;

//Given S string S and S string T, find the minimum window in S which will contain all the characters in T in linear time complexity.
//Note that when the count of S character C in T is N, then the count of C in minimum window in S should be at least N.
//https://www.interviewbit.com/problems/window-string/
public class MinimumWindowString {
	public static void main(String args []) {
		String S = "ADOBECODEBANC";
		String T = "ABC";

		int m = S.length();
		int n = T.length();
		
		int sh [] = new int[256];
		int th [] = new int[256];
		
		boolean found = false;
		int ms = 0, me = 0;  //for recording optimal window
		 int count=0;  
		int min = Integer.MAX_VALUE;
		for(int i=0; i<n; i++) {
			th[T.charAt(i)]++;
		}
		int start=0, end=0;  //for recording the current window
		//Now grow current window to the right, shrink if its a candidate window
		//candidate window is one which could become an optimal window
		for(end=0;end<m; end++) {            
			//increment count iff the current window needs this character
			if(sh[S.charAt(end)] < th[S.charAt(end)]) count++;
			sh[S.charAt(end)]++;  //taking the character in the current window
			if(count == n) found = true; //current window is a possible candidate

			// if its a candidate window then try to shrink it form left, without violating the condition
			//sh[S.charAt(start)] > th[S.charAt(start)] for removing excess characters
			while(count == n && sh[S.charAt(start)] > th[S.charAt(start)]) {
				sh[S.charAt(start)]--; start++;
				if(min > end-start+1) {
					min = end-start+1;
					ms=start; me=end;
				}
			}
		}
		if(found) {
			System.out.println("Minimum window substring is : "+S.substring(ms, me+1));
		}
		else {
			System.out.println("Minimum window substring doesn't exist");
		}
	}
}

package main.ashu.strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Given a string, find the longest substring without repeated characters.
//https://www.programcreek.com/2013/02/leetcode-longest-substring-without-repeating-characters-java/
//Similar - Given a string S and a string T, 
//find the minimum window in S which will contain all the characters in T in complexity O(n).
//https://www.programcreek.com/2014/05/leetcode-minimum-window-substring-java/
public class LongestSubstrUniqueChars {
	public int lengthOfLongestSubstring(String s) {
		if (s == null)
			return 0;
		char ch[] = s.toCharArray();
		int n = ch.length;
		if (n == 0)
			return 0;
		// count array of ASCII character set as hashset
		int p[] = new int[256];
		Arrays.fill(p, 0);
		// initialize the window
		int start = 0, end = 0;
		int max = 1;
		int ms = 0, me = 0;
		int c[] = new int[256];
		Arrays.fill(c, 0);

		while (end < s.length()) {
			// check if next character can be accommodated, if not shrink from
			// left till condition met
			if (c[s.charAt(end)] > 0) {  //if-condition may not be required
				while (start <= end && c[s.charAt(end)] > 0) {
					c[s.charAt(start)]--;
					start++;
				}
			}
			// now that unnecessary chars have been removed by, include the next
			// one
			c[s.charAt(end)]++;
			// update the global max
			if (max < end - start + 1) {
				max = end - start + 1;
				ms = start;
				me = end;
			}
			end++;
		}
		System.out.println("largest substring without repeated characters = "
				+ s.substring(ms, me + 1));
		return max;
	}

	public int lengthOfLongestSubstring2(String s) {
		int i = 0, j = 0, max = 0;
		Set<Character> set = new HashSet();
		while (j < s.length()) {
			if (!set.contains(s.charAt(j))) {
				set.add(s.charAt(j++));
				max = Math.max(max, set.size());
			} else {
				set.remove(s.charAt(i++));
			}
		}

		return max;
	}

	public static void main(String arg[]) {
		LongestSubstrUniqueChars sol = new LongestSubstrUniqueChars();
		String s = "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
		int len = sol.lengthOfLongestSubstring(s);
		System.out
				.println("Length of the longest substring without repeated chars = "
						+ len);
		len = sol.lengthOfLongestSubstring2(s);
		System.out
				.println("Length of the longest substring without repeated chars = "
						+ len);
	}

}
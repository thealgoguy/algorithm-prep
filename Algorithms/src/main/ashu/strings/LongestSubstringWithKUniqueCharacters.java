package main.ashu.strings;

import java.util.HashSet;
import java.util.Set;

//application of window technique
//find the substring with at most k unique characters in a give string
//https://www.programcreek.com/2013/02/longest-substring-which-contains-2-unique-characters/
public class LongestSubstringWithKUniqueCharacters {

	public static String longestSubstringWithKUniqueChars(String str, int k) {
		if (str == null || str.length() <= k)
			return str;
		Set<Character> set = new HashSet<Character>();
		int start = 0, end = 0;
		int max = Integer.MIN_VALUE;
		int ms = -1, me = -1;
		while (end < str.length()) {
			char next = str.charAt(end);
			if (set.contains(next)) { // update global max
				if (max < end - start + 1) {
					max = end - start + 1;
					ms = start;
					me = end;
				}
			} else {
				if (set.size() < k) { // can adjust next char in set ?
					set.add(next);
				} else {
					// make room in set by removing chars from left
					while (set.size() == k && start < end) {
						set.remove(str.charAt(start));
						start++;
					}
					// add the current char
					set.add(str.charAt(end));
				}
			}
			end++;
		}
		System.out.println("ms = " + ms + ", me = " + me);
		return str.substring(ms, me + 1);
	}

	public static void main(String args[]) {
		String str = "abcadcacacaca";
		int k = 3;
		String ans = longestSubstringWithKUniqueChars(str, k);

		System.out.println("max length substrig with k unique characters = "
				+ ans);
	}
}

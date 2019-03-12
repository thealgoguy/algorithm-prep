package main.ashu.stack;

import java.util.Stack;

//https://leetcode.com/problems/remove-duplicate-letters/discuss/76762/Java-O(n)-solution-using-stack-with-detail-explanation
//remove duplicate characters such that the result is lexicographicaly smallest
public class RemoveDuplicateLetters {
	public String removeDuplicateLetters(String s) {
	    Stack<Character> stack = new Stack();
	    int[] count = new int[26];
	    char[] arr = s.toCharArray();
	    for(char c : arr) {
	        count[c-'a']++;
	    }
	    boolean[] visited = new boolean[26];
	    for(char c : arr) {
	        count[c-'a']--;
	        if(!visited[c-'a']) {
	        	while(!stack.isEmpty() && stack.peek() > c && count[stack.peek()-'a'] > 0) {
		            visited[stack.peek()-'a'] = false;
		            stack.pop();
		        }
		        stack.push(c);
		        visited[c-'a'] = true;
	        }
	    }
	    StringBuilder sb = new StringBuilder();
	    for(char c : stack) {
	        sb.append(c);
	    }
	    return sb.toString();
	}
}

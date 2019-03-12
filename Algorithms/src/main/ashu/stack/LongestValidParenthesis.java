package main.ashu.stack;

import java.util.Stack;

//https://www.geeksforgeeks.org/length-of-the-longest-valid-substring/
public class LongestValidParenthesis {
   public static void main(String args []) {
	   String s = "()(()))))";
	   int n = s.length();
	   int max = 0;
	   Stack<Integer> stack = new Stack();
	   stack.push(-1);
	   for(int i=0; i<n; i++) {
		   char c = s.charAt(i);
		   if(c == '(') {
			   stack.push(i);
		   } else if(c == ')') {
			   // Pop the previous opening bracket's index
			   stack.pop();
			   //Check if this length formed with base of
	            // current valid substring is more than max so far
			   if(!stack.isEmpty()) {
				   max = Math.max(max, i - stack.peek());
			   } else {
				   // If stack is empty, push current index as 
		            // base for next valid substring (if any)
				   stack.push(i);
			   }
		   }
	   }	   
	   System.out.println("Max length valid parenthesis = "+max);
   }
}

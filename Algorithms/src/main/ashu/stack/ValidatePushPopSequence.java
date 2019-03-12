package main.ashu.stack;

import java.util.Stack;

//Given two sequences pushed and popped with distinct values, return true 
//if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.
//https://leetcode.com/problems/validate-stack-sequences/
public class ValidatePushPopSequence {
	public boolean validateStackSequences(int[] pushed, int[] popped) {
        int N = pushed.length;
      //simulate the push-pop behavior with a temp stack
        Stack<Integer> stack = new Stack();
        int j = 0;
        for (int x: pushed) {
            stack.push(x);
            while (!stack.isEmpty() && j < N && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }

        return j == N;
    }
}

package main.ashu.stack;

import java.util.Stack;

public class EvaluatePostfixExpression {
	private static int evaluatePostfix(String exp) 
    { 
        Stack<Integer> stack=new Stack<Integer>(); 
          
        for(int i=0;i<exp.length();i++) 
        { 
            char c=exp.charAt(i);            
            // If the scanned character is an operand (number here), 
            // push it to the stack. 
            if(Character.isDigit(c))  {
            	stack.push(c - '0'); 
			} else {
				stack.push(operate(stack.pop(), stack.pop(), c));				
			} 
        } 
        return stack.pop();     
    }
	
	//operands popped from stack will be in reverse order
	//in prefix, the're in correct order
	private static Integer operate(int y, int x, char op) {
		switch (op) {
		case '+' :
			return x + y;
		case '-' :
			return x - y;
		case '*' :
			return x * y;
		case '/' :
			if(y == 0) {
				throw new UnsupportedOperationException("Cannot divide by zero");
			}
			return x / y;
		}
		return 0;
			
	}
	// Driver program to test above functions
	public static void main(String[] args) {
		String exp = "231*+9-";
		System.out.println("postfix evaluation: " + evaluatePostfix(exp));
	}
}

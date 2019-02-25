package main.ashu.stack;

import java.util.Stack;

//more than two digits number not handled
public class EvaluatePrefixExpression {
	static double evaluatePrefix(String exprsn) 
	{ 
	    Stack<Double> stack = new Stack<Double>(); 
	   
	    for (int i = exprsn.length() - 1; i >= 0; i--) {    
	        // Push operand to Stack 
			if (Character.isDigit(exprsn.charAt(i))) {
				stack.push((double) (exprsn.charAt(i) - '0'));
			}  else { 
	            // Operator encountered 
	            // Pop two elements from Stack and evaluate
	            stack.push(operate(stack.pop(), stack.pop(), exprsn.charAt(i)));
	        } 
	    } 	   
	    return stack.peek(); 
	} 
	
	//we have pushed on the stack from right to left...so popped operands will be in correct order
	//in postfix, they are in reverse order
	private static Double operate(double x, double y, char op) {
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
		return 0.0;
			
	}
	  
	/* Driver program to test above function */
	public static void main(String[] args) 
	{ 
	   String exprsn = "+9*26"; 
	   System.out.println(evaluatePrefix(exprsn)); 
	   System.out.println(evaluatePrefix("-+8/632"));
	   //System.out.println(evaluatePrefix("-+7*45+2"));  ---gives exception
	} 
}

package main.ashu.stack;

import java.util.Stack;

//https://www.geeksforgeeks.org/expression-evaluation/
//use two stacks : operand stack and operator stack
public class EvaluateInfixExpression {
	
	public static int evaluate(String expression) {
		char [] tokens = expression.toCharArray();
		Stack<Integer> operand = new Stack<Integer>();
		Stack<Character> operator = new Stack<Character>();
		
		int n = expression.length();
		for (int i = 0; i < n; i++) {
			//skip spaces
			if (tokens[i] == ' ')
				continue;
			//if digit...form the number and push on operand stack
			if (Character.isDigit(tokens[i])) {
				StringBuilder sb = new StringBuilder();
				while (i < n && Character.isDigit(tokens[i])) {
					sb.append(tokens[i++]);
				}
				operand.push(Integer.valueOf(sb.toString()));
			} else if (tokens[i] == '(') { //push opening bracket on operator stack
				operator.push(tokens[i]);
			} else if (tokens[i] == ')') { //if closing bracket, solve the partial expression
				while (operator.peek() != '(') {
					operand.push(operate(operand.pop(), operand.pop(),
							operator.pop()));
				}
				operator.pop();
			} else {
				//solve for binary operator....
				//keep evaluating while top of operator stack has higher precedence than current token
				while (!operator.isEmpty()
						&& hasHigherPrecedence(operator.peek(), tokens[i])) {
					operand.push(operate(operand.pop(), operand.pop(),
							operator.pop()));
				}
				operator.push(tokens[i]);
			}
		}
		//solve the leftover elements from operand/operator stack
		while(!operator.isEmpty()){
			Integer value = operate(operand.pop(), operand.pop(), operator.pop());
			operand.push(value);
		}
		return operand.pop();
	}
	
	//check if op1 has higher precedence than op2
	private static boolean hasHigherPrecedence(char op1, char op2) {
		if(op1 == '(' || op1 == ')') return false;
		if((op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/')) return false;
		return true;
	}
	
	//operands popped from stack will be in reverse order
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
	
	private static boolean isDigit(char token) {
		return token >='0' && token <='9';
	}
	
	public static void main(String[] args) 
    { 
        System.out.println(EvaluateInfixExpression.evaluate("10 + 2 * 6")); 
        System.out.println(EvaluateInfixExpression.evaluate("100 * 2 + 12")); 
        System.out.println(EvaluateInfixExpression.evaluate("100 * ( 2 + 12 )")); 
        System.out.println(EvaluateInfixExpression.evaluate("100 * ( 2 + 12 ) / 14")); 
    } 
}

package main.ashu.stack;

import java.util.Stack;

public class BalancedParenthesesCheck {
	static boolean areParenthesisBalanced(char exp[]) 
    { 
       Stack<Character> st=new Stack();      
       for(int i=0;i<exp.length;i++) 
       { 
            
          /*If the exp[i] is a starting  
            parenthesis then push it*/
          if (exp[i] == '{' || exp[i] == '(' || exp[i] == '[') 
            st.push(exp[i]); 
       
          /* If exp[i] is an ending parenthesis  
             then pop from stack and check if the  
             popped parenthesis is a matching pair*/
          if (exp[i] == '}' || exp[i] == ')' || exp[i] == ']') 
          {             
              /* If we see an ending parenthesis without  
                 a pair then return false*/
             if (st.isEmpty()) 
               { 
                   return false; 
               }  
       
             /* Pop the top element from stack, if  
                it is not a pair parenthesis of character  
                then there is a mismatch. This happens for  
                expressions like {(}) */
             else if ( !isMatchingPair(st.pop(), exp[i]) ) 
               { 
                   return false; 
               } 
          } 
            
       } 
       
       if (st.isEmpty()) 
         return true;
       else
         { 
             return false; 
         }  
    }  
	
	static boolean isMatchingPair(char character1, char character2) 
    { 
       if (character1 == '(' && character2 == ')') 
         return true; 
       else if (character1 == '{' && character2 == '}') 
         return true; 
       else if (character1 == '[' && character2 == ']') 
         return true; 
       else
         return false; 
    } 
      
    public static void main(String[] args)  
    { 
        char exp[] = {'{','(',')','}','[',']'}; 
          if (areParenthesisBalanced(exp)) 
            System.out.println("Balanced "); 
          else
            System.out.println("Not Balanced ");   
    } 
  
}

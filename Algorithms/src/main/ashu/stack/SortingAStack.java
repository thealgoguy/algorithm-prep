package main.ashu.stack;

import java.util.ListIterator;
import java.util.Stack;

//Given a stack of integers, sort it in ascending order using recursion.
//how to do using a temporary stack ? https://www.geeksforgeeks.org/sort-stack-using-temporary-stack/
public class SortingAStack {
	// Recursive Method to insert an item x in sorted way 
    static void sortedInsert(Stack<Integer> s, int x) 
    { 
        // Base case: Either stack is empty or newly inserted 
        // item is greater than top (more than all existing) 
        if (s.isEmpty() || x > s.peek()) 
        { 
            s.push(x); 
            return; 
        } 
       
        // If top is greater, remove the top item and recur 
        int temp = s.pop(); 
        sortedInsert(s, x); 
       
        // Put back the top item removed earlier 
        s.push(temp); 
    } 
       
    // Method to sort stack 
    static void sortStack(Stack<Integer> s) 
    { 
        // If stack is not empty 
        if (!s.isEmpty()) 
        { 
            // Remove the top item 
            int x = s.pop(); 
       
            // Sort remaining stack 
            sortStack(s); 
       
            // Push the top item back in sorted stack 
            sortedInsert(s, x); 
        } 
    } 
      
    // Utility Method to print contents of stack 
    static void printStack(Stack<Integer> s) 
    { 
       ListIterator<Integer> lt = s.listIterator(); 
         
       // forwarding 
       while(lt.hasNext()) 
           lt.next(); 
         
       // printing from top to bottom 
       while(lt.hasPrevious()) 
           System.out.print(lt.previous()+" "); 
    } 
}
package main.ashu.matrix;

import java.util.Stack;

//https://leetcode.com/problems/maximal-rectangle/
//for each row, find largest rectagular area in histogram, update max
public class LargestRectangleWithAllOnes {
  public static int maxRectangle(int R,int C,int A[][]) 
  { 
      // Calculate area for first row and initialize it as result
      int result = maxHist(A[0]);     
      // iterate over row to find maximum rectangular area 
      for (int i = 1; i < R; i++) 
      {     
          for (int j = 0; j < C; j++) {
        	  if (A[i][j] == 1) A[i][j] += A[i - 1][j]; 
          }               
          result = Math.max(result, maxHist(A[i])); 
      }    
      return result; 
  } 
  
  private static int maxHist(int[] a) {
      if(a == null || a.length==0) return 0;
      int n = a.length;
      if(n==1) return a[0];
      Stack<Integer> s = new Stack<Integer>();
      int max = Integer.MIN_VALUE;
      int i = 0;
      for(i=0; i<n; i++) {
      	//put index of greater elements on stack since we are look for smaller elements
          if(s.isEmpty() || a[s.peek()]  <= a[i]) s.push(i);
          else {
                  //if smaller element is encountered, then find the area with current top as height, 
          	    //i as right boundary and predecessor on the stack as left boundary. Exclude boundaries in width calculation
                  while(!s.isEmpty()) {
                      int top = s.pop();
                      int width = (s.isEmpty()) ? i : (i - (s.peek()) - 1);
                      int area = width * a[top];
                      max = Math.max(area, max);
                      //check if we can now push the current element
                      if(s.isEmpty() || a[s.peek()] <=a[i]) {
                          s.push(i); break;
                      }
                 }           
          }
      }
      //check for remaining elements in the stack      
      while(!s.isEmpty()) {
           int  top = s.pop();
           int width = (s.isEmpty()) ? i : (i - (s.peek()) -1);
           int area = width * a[top];
           max = Math.max(area, max);
      }       
      return max;
  }
  
  public static void main (String[] args)  
 {
		int R = 4;
		int C = 4;
		int A[][] = { { 0, 1, 1, 0 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 },
				{ 1, 1, 0, 0 }, };
		System.out.print("Area of maximum rectangle is "
				+ maxRectangle(R, C, A));
	} 
}

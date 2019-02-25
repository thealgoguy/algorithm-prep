package main.ashu.dp;

import java.util.Stack;

//Take every element and move extend left(till elements are >=) and right(till elements >=) and then find rect area with this width and current height.
//This can be easily achieved using increasing stack...can also be done using dp which is costly
//Source : https://www.programcreek.com/2014/05/leetcode-largest-rectangle-in-histogram-java/
//This soln is leetcode accepted
public class LargestRectAreaHistogram {
	
	public static void main(String[] args) 
    {
        int hist[] = { 6, 2, 5, 4, 5, 1, 6 };
        System.out.println("Maximum area is " + largestRectangleArea(hist));
    }
	public static int largestRectangleArea(int[] a) {
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
}

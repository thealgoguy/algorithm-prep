package main.ashu.arrays;

import java.util.HashMap;
import java.util.Map;

//Given an array of A of n integers and an array B of m integers find the Maximum Contiguous Subarray Sum of array A 
//such that any element of array B is not present in that subarray
public class MaxSubarraySumExcludingElementsFromOtherArray {
	public static void main(String args[]) {
		int a[] = { 3, 4, 5, -4, 6 };
	    int b[] = { 1, 8, 5 };
        int m = a.length;
        int n = b.length;
        //we can sort array b and do bin search in place of map for in-place
	    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int max = Integer.MIN_VALUE;
        int prev =0;
        for(int i=0; i<n; i++) {
        	if(map.containsKey(a[i])) prev =0;
        	else {
        		int curr = Math.max(a[i], prev+a[i]);
        		max = Math.max(max, prev);
        		prev = curr;
        	}
        }
        System.out.println("max subarray sum = "+max);
        
	}
}

package main.ashu.arrays;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumProblem {
    public static void main(String args []) {
    	int a [] = {1, 4, 20, 3, 10, 5};
    	int sum = 33;
    	//approach 1....window technique    	
    	subarray_window(a, sum);
    	//approach 2.....by storing prefix sum in map
    	subarray_prefixmap(a, sum);
    }
    
    //fails in case of -ve numbers
    private static void subarray_window(int[] a, int sum) {
		System.out.println("Using window technique : ");
		int start=0;
    	int curr = 0, i=0;
    	while(i < a.length) {
    		curr += a[i];
    		//failure case check...keep shrinking from left
    		while(start < i && curr >sum) {
				curr -= a[start++];
			}
    		//success case check
    		if(curr == sum) {
    			System.out.println("Sum found between index "+start+" and "+i);
    		}
    		i++;
    	}
	}
  
    //works for -ve numbers as well
	private static void subarray_prefixmap(int[] a, int sum) {
		System.out.println("Using prefix sum map : ");
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	int curr = 0;
    	for(int i=0; i<a.length; i++) {
    		curr += a[i];
    		if(curr == sum) {
    			System.out.println("Sum found between index : "+0+" to "+i);
    		}
    		if(map.containsKey(curr - sum)) {
    			System.out.println("Sum found between index : "+(map.get(curr-sum)+1)+" and "+i);
    		}
    		else map.put(curr, i);
    	}
	}

}

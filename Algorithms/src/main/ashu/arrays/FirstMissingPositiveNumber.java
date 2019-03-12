package main.ashu.arrays;

//unsorted array, find the first missing positive number
//can use hashing
//O(1) space : use elements as indexes and mark their presence by negating them
//this doesn’t work if there are non-positive (-ve and 0) numbers. 
//So we segregate positive from negative numbers as first step and then apply the approach.
//accepted - https://leetcode.com/problems/first-missing-positive/submissions/
//look for other approaches too - bucket sort with swapping
public class FirstMissingPositiveNumber {
	static int findMissingPositive(int a[]) {
		// Put non-positive numbers(<=0) at the end
		int size = segregate(a);
		for(int i=0; i<size; i++) {
			//numbers [1....n] can be marked at indexes [0....n-1] 
            int num = Math.abs(a[i]);
            if(num-1 < size && a[num-1] > 0) {
            	a[num-1] = -a[num-1];
            }
        }
		//check the valid index which has not been negated
        for(int i=0; i<size; i++) {
            if(a[i] > 0) return i+1;
        }
        return size+1;
	}

	/*
	 * Utility function that puts all non-positive (0 and negative) numbers on
	 * right end of arr[] and return count of such numbers
	 */
	static int segregate(int a[]) {
		int n = a.length;
        int j = n-1;
        for(int i=n-1; i>=0; i--) {
            if(a[i] <=0 ) {
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
                j--;
            }
        }
        return j+1;
	}



	public static void main(String args[]) {

	}
}

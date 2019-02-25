package main.ashu.arrays;

public class LargestSubarraySumSizeGreaterThanK {
	// Returns maximum sum of a subarray with at-least 
    // k elements. 
    static int maxSumWithK(int a[], int n, int k) 
    { 
        // maxSum[i] is going to store maximum sum 
        // till index i such that a[i] is part of the 
        // sum. 
        int maxSum[] = new int [n];
        maxSum[0] = a[0]; 
  
        // We use Kadane's algorithm to fill maxSum[] 
        // Below code is taken from method 3 of 
        // https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/ 
        int curr_max = a[0]; 
        for (int i = 1; i < n; i++) 
        { 
            curr_max = Math.max(a[i], curr_max+a[i]); 
            maxSum[i] = curr_max; 
        } 
  
        // Sum of first k elements 
        int sum = 0; 
        for (int i = 0; i < k; i++) 
            sum += a[i]; 
  
        // Use the concept of sliding window 
        int result = sum; 
        for (int i = k; i < n; i++) 
        { 
            // Compute sum of k elements ending 
            // with a[i]. 
            sum = sum + a[i] - a[i-k]; 
  
            // Update result if required 
            result = Math.max(result, sum); 
  
            // Include maximum sum till [i-k] also 
            // if it increases overall max. 
            result = Math.max(result, sum + maxSum[i-k]); 
        } 
        return result; 
    } 
}

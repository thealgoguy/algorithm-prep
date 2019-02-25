package main.ashu.arrays;

import java.util.Arrays;

//preserve the relative order of non-zero elements
public class MoveAllZeroesToEndOfArray {
    public static void main(String args []) {
    	int arr[] = {0, 1, 9, 8, 4, 0, 0, 2, 7, 0, 6, 0, 9}; 
        int n = arr.length; 
        int start = 0;
        System.out.println("Original array : "+Arrays.toString(arr));
        for(int i=0; i<n; i++) {
        	if(arr[i] != 0) {
        		int t = arr[i];
        		arr[i] = arr[start];
        		arr[start++] = t;
        	}
        }
        System.out.println("Final array is : "+Arrays.toString(arr));
    }
}

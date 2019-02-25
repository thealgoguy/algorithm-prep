package main.ashu.arrays;

//Given  an array find the smallest subarray to sort that sorts the whole array

//http://stackoverflow.com/questions/15855594/min-n-m-so-that-whole-array-will-be-sorted
//visit above link for 2D visualization for clarity
//https://leetcode.com/articles/shortest-unsorted-continous-subarray/
public class SmallestUnsortedSubarrayThatSortsTheEntireArray {
     public static void main(String args []) {
    	 int nums [] = {10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60};
    	 int n = nums.length;
    	 //initialize the lowest valley and highest peak
    	 int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
         boolean flag = false;
         for (int i = 1; i < nums.length; i++) {
             if (nums[i] < nums[i - 1])
                 flag = true;
             if (flag)
                 min = Math.min(min, nums[i]);
         }
         flag = false;
         for (int i = nums.length - 2; i >= 0; i--) {
             if (nums[i] > nums[i + 1])
                 flag = true;
             if (flag)
                 max = Math.max(max, nums[i]);
         }
         int l, r;
         for (l = 0; l < nums.length; l++) {
             if (min < nums[l])
                 break;
         }
         for (r = nums.length - 1; r >= 0; r--) {
             if (max > nums[r])
                 break;
         }
         System.out.println("Index of unsorted : ("+l+", "+r+")");
    	 System.out.println("Range of unsorted array is "+"("+min+", "+max+")");
    	 //we have found the range(valley,peak) of the unsorted array
    	 //now find the numbers in the array that fall in this range
    	System.out.println("Smallest unsorted subarray length is "+ (r - l < 0 ? 0 : r - l + 1));    	 	
     }
}

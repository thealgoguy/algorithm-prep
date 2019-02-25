package main.ashu.arrays;

//Application of two-pointer technique
//2 pointer technique has two steps :
//condition evaluation for updating answer,
//condition evaluation for moving pointers
//Given two sorted arrays and a number x, find the pair whose sum is closest to x
//and the pair has an element from each array.
//Pointers one at the beginning and other at the end of the arrays respectively
//Will it work for -ve numbers ?
public class ClosestSumPairFromSortedArrays {
     public static void main(String args []) {
    	 int a[] =  {1, 4, 5, 7};
    	 int b [] = {10, 20, 30, 40};
    	 int x = 38;
    	 int minsum = Integer.MAX_VALUE;
    	 int num1 = 0, num2 = 0;
    	 int i=0, j = b.length-1;
    	 while(i<a.length && j>=0) {
    		 int sum = a[i] + b[j];
    		 //condition evaluation for updating answer
    		 if(sum == x){
    			 minsum = x;
    			 num1=a[i]; num2 = b[j];
    			 break;
    		 }
    		 if(Math.abs(minsum - x) > Math.abs(x-sum)) {
    			 minsum = sum;
    			 num1=a[i]; num2 = b[j];
    		 }
    		 //condition evaluation for moving pointers
    		 if(sum < x) i++;
    		 else j--;
    		 
    	 }
    	 System.out.println("Pair sum closest to "+x+" is : "+minsum);
    	 System.out.println("CLosest sum pair is : "+num1+" , "+num2);
     }
}

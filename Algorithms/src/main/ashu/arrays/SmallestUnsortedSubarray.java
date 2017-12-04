package threadpool;

//Given  an array find the smallest subarray to sort that sorts the whole array

//http://stackoverflow.com/questions/15855594/min-n-m-so-that-whole-array-will-be-sorted
//visit above link for 2D visualization for clarity

public class SmallestUnsortedSubarray {
     public static void main(String args []) {
    	 int a [] = {1, 3, 4, 6, 5, 2, 11, 7, 10, 14, 15, 16};
    	 int l, r;
    	 int min = Integer.MAX_VALUE;
    	 int max = Integer.MIN_VALUE;
    	 boolean minSeen = false, maxSeen = false;  //only to ArrayindexOutOfBounds issue
    	 for(int i=1; i<a.length-1; i++) {
    		 if(a[i] < a[i-1] && a[i] < a[i+1])  {
    			 if(minSeen)
    			    min = (a[min] > a[i]) ? i : min;
    			 else {
    				 min = i;
    				 minSeen = true;
    			 }
    		 }
    		 if(a[i] > a[i-1] && a[i] > a[i+1]) {
    			 if(maxSeen)
    			     max = (a[max] < a[i]) ? i : max;
    			 else {
    				 max = i;
    				 maxSeen = true;
    			 }
    		 }
    		 //System.out.println("min = "+min+" max = "+max);
    	 }  	 
    	 System.out.println("Range of unsorted array is "+"("+a[min]+", "+a[max]+")");
    	 //we have found the range(min,max) of the unsorted array
    	 //now find the numbers in the array that fall in this range
    	 int count = 0, start=0;
    	 for(int i=0; i<a.length; i++) {
    		 if(a[i] >= a[min] && a[i] <= a[max]){
    			 count++;
    			 if(start <=0) start++;
    		 }
    	 }
    	 
    	System.out.println("Smallest unsorted subarray length is "+ count);    	
    	if(count > 0) {
    		System.out.print("Subarray is : ");
    		for(int i=start; i<start+count; i++) System.out.print(a[i+start-1]+" ");
    	}  	
     }
}

package main.ashu.arrays;

//https://www.geeksforgeeks.org/smallest-difference-pair-values-two-unsorted-arrays/
//Application of two-pointer technique : both at beginning of arrays
//2 pointer technique has two steps :
//condition evaluation for updating answer,
//condition evaluation for moving pointers
public class SmallestDifferencePairInUnsortedArrays {
    public static void main(String arsg []) {
    	 int a[] = {1, 2, 11, 5};
    	 int b[] = {4, 12, 19, 23, 127, 235};
    	 
    	 int mindiff = Integer.MAX_VALUE;
    	 int i=0, j=0;
    	 int n1=0, n2=0;
    	 while(i<a.length && j<b.length) {
    		 int diff = Math.abs(a[i] - b[j]);
    		 //condition evaluation for updating answer
    		 if(Math.abs(mindiff) > diff) {
    			 mindiff = diff;
    			 n1 = a[i]; n2 = b[j];
    			 if(mindiff == 0) break;
    		 }
    		 //condition evaluation for moving pointers
    		 if(a[i]  < b[j]) i++;
    		 else  if(a[i] > b[j]) j++;
    		 else {
    			 i++; j++;
    		 }
    	 }
    	 System.out.println("Min diff b/w any pair is : "+mindiff);
    	 System.out.println("Min diff pair is : "+n1+", "+n2);
    }
}

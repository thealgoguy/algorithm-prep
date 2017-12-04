package arrays;

import java.util.Scanner;

//http://stackoverflow.com/questions/7153659/find-an-integer-not-among-four-billion-given-ones
///https://www.careercup.com/question?id=3058
// k can represent 8*k numbers...from 0 to 8*k-1
//For storing a number 'x' we go to x/8 th byte and set its(x%8)th bit (from right)
//An integer x is present if xth byte has (x%8)th bit set...O(1) time per query
//Iterate over the byte array to output all missing numbers 
//Hence to store all integers(4 billion=4*10^9), we need minimum (2^32 / 2^3) bytes = 2^29/2^20 mb = 512 mb space
//if we have less than 512 mb of memory, we need to define bit map of smaller size and multiple passes.
//Conclusion: We decrease memory through increasing file pass.

public class BitMap {
	public static void main(String args[]) {
		   int radix = 8;
		   //in two bytes we can track numbers from 0 to 15
		   byte[] bitfield = new byte[2];
		   //to map the entire integer set, do the following
		  // byte[] bitfield = new byte[Integer.MAX_VALUE/8];
		  
		   // Scanner in = new Scanner(new FileReader("a.txt"));
		   int a [] = {1,2,5,7,15,6,12};
		       for(int i=0; i<a.length; i++){
		           //int n = in.nextInt();
		           bitfield[a[i]/radix] |= (1 << (a[i]%radix));
		       }
               System.out.print("Missing numbers in the range are : ");
		       for(int i = 0; i< bitfield.length; i++){
		           for(int j =0; j<radix; j++){
		               if( (bitfield[i] & (1<<j)) == 0) System.out.print(i*radix+j+" ");
		           }
		       }
		      System.out.println("\nEnter a number to search : ");
		      Scanner sc = new Scanner(System.in);
		      int x = sc.nextInt();
		      if((bitfield[x/radix] & 1<<(x%radix)) !=0)
		    	  System.out.println(x+" is present");
		      else 
		    	  System.out.println(x+" is not present");
	   }
}

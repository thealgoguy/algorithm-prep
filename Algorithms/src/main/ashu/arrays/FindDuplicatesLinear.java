package main.ashu.arrays;

import java.util.Arrays;

//Given an array of n elements which contains elements from 0 to n-1, with any of these numbers appearing any number of times.
//hashmap/count array based approach.... O(n) space and time
//sorting based approach...O(nlogn) time O(1) space if in place soring..maybe O(n) if counting or radix/sort
//Best/tricky approach - in place array permuation O(n) time O(1) space. This is like using array itself as hshmap
//The contraints of the problem give a bit of a clue to the solution - the fact that every valid array value is also a valid array index hints at a[a[i]], 
//and the O(1) space constraint hints at the swap() operation being key
//http://stackoverflow.com/questions/5739024/finding-duplicates-in-on-time-and-o1-space
//http://stackoverflow.com/questions/3492302/easy-interview-question-got-harder-given-numbers-1-100-find-the-missing-numbe
public class FindDuplicatesLinear {
	static int swaps = 0;
   public static void main(String args []) {
	   int a[] = {1, 3, 3, 1, 3, 0, 6};
	   System.out.println("Before permutation  : "+Arrays.toString(a));
	   permute(a);
	   System.out.println("After permutation   : "+Arrays.toString(a));
	   System.out.print("Duplicates : ");
	   for(int i=0; i<a.length; i++) {
		   //a[i] !=i will be true for home location for the duplicate of a[i] if it exists.....think !!
		   if(a[i] !=i && a[a[i]]==a[i]) {
			   System.out.print(a[i]+" ");  //one duplicate of a[i] was hashed to index i by permute()
			 //To avoid duplicate printing more than once, reset home location of a[i] to index i after a duplicate of a[i] is seen at actual home a[a[i]]
			   a[a[i]] = i;  
		   }
	   }
	   System.out.println("\nNo of swaps done = "+ swaps);
   }
   
   //After the method, if any value m appears more than once, then one of those appearances is guaranteed to be in the correct position, namely A[m]. 
   //If we are careful we can use that "home" location a[m] to store information about whether any duplicates have been printed yet or not.
   public static void permute(int a[]) {
	   for(int i=0; i<a.length; i++) {
		   //make a[i] point to its home location in the array ...a[a[i]]
		   while(a[i] != a[a[i]]) {
			   int temp = a[i];
			   a[i] = a[temp];
			   a[temp] = temp;
			   swaps++;
		   }
	   }
   }
}

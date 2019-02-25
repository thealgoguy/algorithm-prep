package main.ashu.arrays;

// A majority element in an array A[] of size n is an element that appears more than n/2 times
//(and hence there is at most one such element)
//Moore's voting algorithm O(n) time
public class MajorityElementInArray {
   public static void main(String args []) {
	   int a[] = {1, 3, 3, 1, 2};
	   int n = a.length;
	   //initialize
	   int candidate = a[0];
	   int count = 1;
	   //iterate and update the candidate
	   for(int i=1; i<n; i++) {
		   if(a[i] == candidate) count++;
		   else count--;
		   if(count == 0) {
			   candidate = a[i];
			   count++;
		   }
	   }
	   if(count <=n/2) {
		   System.out.println("No majority element exists");
	   } else { //check if candidate is really in majority
		   count = 0;
		   for(int i=0; i<n; i++) {
			   if(candidate == a[i]) count++;
		   }
		   if(count > n/2) {
			   System.out.println("Majority element is : "+candidate);
		   }
	   }
	   
	   
   }
}

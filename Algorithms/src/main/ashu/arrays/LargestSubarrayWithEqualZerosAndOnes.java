package main.ashu.arrays;

import java.util.HashMap;
import java.util.Map;

//modify to form largest subarray with sum K
public class LargestSubarrayWithEqualZerosAndOnes {
   public static void main(String args []) {
	   int a[] = {1, 0, 0, 1, 0, 1, 1};
	   int n = a.length;
	   //map 0s to -1
	   for(int i=0; i<n; i++) a[i] = (a[i] == 0) ? -1: 1;
	   //now find the largest zero sum subarray
	   int l=-1, r=-1, max=-1;
	   Map<Integer, Integer> prefixSum = new HashMap();
	   int sum = 0;
	   for(int i=0; i<n; i++) {
		   sum += a[i];
		   if(sum == 0) {
			   if(max < i+1) {
				   max = i+1;
				   l = 0; r=i;
			   }
		   }
		   else if(prefixSum.containsKey(sum)) {
			   if(max < i-prefixSum.get(sum)) {
				   max = i-prefixSum.get(sum);
				   l = prefixSum.get(sum) + 1;
				   r = i;
			   }
		   }
		   if(!prefixSum.containsKey(sum))
		      prefixSum.put(sum, i); //keep only the leftmost occurence of sum in the array
	   }
	   //restore the original array
	   for(int i=0; i<n; i++) a[i] = (a[i] == -1) ? 0: 1;
	   if(max > 1) {
		   System.out.print("Subarray is : ");
		   for(int i=l; i<=r; i++) System.out.print(a[i] + " ");
	   }
   }
}

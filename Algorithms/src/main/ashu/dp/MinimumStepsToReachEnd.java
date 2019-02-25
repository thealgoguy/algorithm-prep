package main.ashu.dp;

public class MinimumStepsToReachEnd {
   public static void main(String args []) {
	    int a[] = {1, 3, 6, 1, 0, 9};
	    
	    int minSteps = findMinJumpsDP(a);
	    System.out.println("Minimum steps to reach end = "+minSteps);
	    minSteps = findMinJumpsLinear(a);
	    System.out.println("Minimum steps to reach end = "+minSteps);
   }
   //runs in O(n2) time
   private static int findMinJumpsDP(int a[]) {
	   int n = a.length;
	   int dp [] = new int[n];
	   dp[0] = 0;
	   for(int i=1; i<n; i++) {
		   dp[i] = Integer.MAX_VALUE;
		   for(int j=i-1; j>=0; j--) {
			   if(j+a[j] >= i) {
				   dp[i] = Math.min(dp[i], dp[j]+ 1);
			   }
		   }
	   }
	   return dp[n-1];
   }
   
   //https://www.geeksforgeeks.org/minimum-number-jumps-reach-endset-2on-solution/
   private static int findMinJumpsLinear(int a[]) {
	   int n = a.length;
	   if(a[0] == 0) return -1; //end not unreachable
	   int steps = a[0];
	   int maxIndex = a[0];
	   int jumps = 1;
	   for(int i=1; i<n; i++) {
		   //check if reached the end
		   if(i == n-1) {
			   return jumps;
		   }
		   //update max index reachable by jumping from current index
		   maxIndex = Math.max(maxIndex, i+a[i]);
		   steps--; //decrease the remaining steps due to previous jump
		   if(steps == 0) {
			   jumps++; //must have a jump
			   //if can't reach this index by jumping from previous positions, then end can''t be reached too
			   if(i >= maxIndex) return -1;
			   //re-initialize the remaining steps possible
			   //with max index reachable from current index - the current index
			  steps = maxIndex - i;
		   }
	   }
	   return -1;
   }
}

import java.util.Scanner;

//This is also called Kadane's algorithm.
public class LargestSubarraySum {
  public static void main(String args []){
	  Scanner sc  = new Scanner(System.in);
	  String s = sc.nextLine();
	  String sp [] = s.split(" ");
	  int n = sp.length;
	  int a [] = new int[n];
	  for(int i=0; i<n; i++) a[i] = Integer.parseInt(sp[i]);
	  //idea is to calculate the largest subarray sums ending with every indices and then finding the global max;
	  
	  int dp [] = new int[n];
	  for(int i=0; i<n; i++) dp[i] = a[i];   //initializing max sub sum ending at index i;
	  
	  for(int i=1; i<n ;i++) {
		  //int curr_sum = dp[i] + dp[i-1]; //check if subarray ending at i can be extended
		  dp[i] = Math.max(dp[i], dp[i] + dp[i-1]);
	  }
	  //now find the global max by comparing the maxes of every indices
	  int max = dp[0];
	  for(int i=1; i<n; i++) if(max < dp[i]) max = dp[i];
	  System.out.println(max);
   }
}

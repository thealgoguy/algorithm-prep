import java.util.Scanner;
//There are 2 classes of DP problems :
//1. Simple : At any step, finding the optimal solution requires an optimal solution to only one subproblem. eg. maximal subarray problem.
//2. A more interesting problem : Finding an optimal solution requires solutions to multiple subproblems. Rod cutting problem.
//http://www.radford.edu/~nokie/classes/360/dp-rod-cutting.html
public class RodCuttingProblem {
  public static void main(String args []) {
	  Scanner sc = new Scanner(System.in);
	  String price [] = sc.nextLine().split("\\s+");
	  int p [] = new int[price.length+1];
	  int n = p.length-1;
	  p[0] = 0;
	  for(int i=1; i<=n; i++) p[i] = Integer.parseInt(price[i-1]);
	  int dp [] = new int[n+1];
	  dp[0] = 0;
	  //this soln is also right uncomment it to check
	  for(int i=1; i<=n; i++) {
		  int max = Integer.MIN_VALUE;
		  //we can either or not cut the rod of length i
		  //if cutting, it can be cut at inch 1, 2, i-1.....choose the best from smaller subproblems. 
		  for(int j=1; j<i; j++) { 
			  max = Math.max(max,dp[j]+dp[i-j]);
		  }
		  //or we may not cut, cost = p[i]. Now take the max of cutting and not cutting.
		  dp[i] = Math.max(p[i], max);
	  }
	  //a better way to compare
	  /*for(int i=1; i<=n; i++) {
		  int max = Integer.MIN_VALUE;
		  //out of n-1 cuts , choose the first optimal cut and solve for remaining length
		  for(int j=1; j<i; j++) {
			  max = Math.max(max,p[j]+dp[i-j]);
		  }
		  dp[i] = Math.max(max, p[i]); //the case of not cutting
		                               //to remove above line  use j<=i in inner loop and dp[i]=max.
	  }*/
	  System.out.println("Maximum benefit = "+dp[n]);
  }
}

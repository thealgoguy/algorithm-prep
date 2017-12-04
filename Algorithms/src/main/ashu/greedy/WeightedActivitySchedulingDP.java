//Given n activities with start time, finish time and profit/value, 
//Find the maximum profit subset of jobs such that no two jobs in the subset overlap.
//approach : 1. Sort on the basis of finish time
//Let S be the optimal set of Jobs, then S may or may not contain ith job, consider both cases, subject to 
//the constraint that start(i) > finish(last selected job)
//Alert : We have to find the set of non-conflicting activities that gives maximum profit, not the set with maximum count of non-conflicting activities,
//so the idea of greedy selection wouldn't work here.
public class WeightedActivitySchedulingDP {
   public static void main(String args []) {
	   Job arr [] = {new Job(3, 10, 20),new Job(1, 2, 50),new Job(6, 19, 100),new Job(2, 100, 200)};
	   int dp [] = new int[arr.length];
	   dp[0] = arr[0].profit;
	   for(int i=1; i<arr.length; i++) { 
			   int ignore = dp[i-1];
			   int take = arr[i].profit;
			   for(int j=i-1; j>=0; j--) { 
				   if(arr[j].finish <= arr[i].start) {
					   take += dp[j]; break;   //dp[j] is the subproblem....but why ? Think...
				   }
				}
			   dp[i] = Math.max(ignore, take);
	   }
	   System.out.println("Maximum profit = "+dp[dp.length-1]);
   }
}
class Job implements Comparable<Job> {
	int start, finish, profit;
	Job(int s, int f, int p) {
		this.start=s;
		this.finish=f;
		this.profit=p;
	}
	public int compareTo(Job t) {     //sorting based on finish time, ascending order
		return Integer.compare(this.finish, t.finish);
	}
}
package main.ashu.greedy;

import java.util.Arrays;

//Job scheduling with varying profits, maximize the total profit
//MAX_PROFIT(i) = 0 if i=0;
//              = max{profit[i]+MAX_PROFIT(j), MAX_PROFIT(i-1)}; // max of including and excluding
// where j is the latest job which doesn't conflict with ith job
public class WeightedJobScheduling {
	public static void main(String args []) {
		Job job [] = new Job[4];
		int i = 0;
		job[i++] = new Job(3,10,20);
		job[i++] = new Job(1,2,50);
		job[i++] = new Job(6, 19, 100);
		job[i++] = new Job(2, 100, 200);
		Arrays.sort(job);  //sort by finish
		int dp[] = new int[4];
		dp[0] = job[0].profit;
		for(i=1; i<4; i++) {
			int last = getLatestNoConflictJob(job, i);
			int inclusion = job[i].profit + (last>=0 ? dp[last] : 0);
			int exclusion = dp[i-1];
			dp[i] = Math.max(inclusion, exclusion);
		}
		System.out.println("max profit that can be earned = "+dp[3]);		
	}
	//use binary search for optimization	
	public static int getLatestNoConflictJob(Job job [], int i) {
		/*int j = 0;
		for(j=i-1; j>=0; j--) {
			if(job[j].finish <= job[i].start) return j;
		}
		return - 1;*/		
		int lo = 0, hi = i - 1;		 
		// Perform binary Search iteratively
		while (lo <= hi)
		{
			int mid = (lo + hi) / 2;
			if (job[mid].finish <= job[i].start)
			{
				if (job[mid + 1].finish <= job[i].start)
					lo = mid + 1;
				else
					return mid;
			}
			else
				hi = mid - 1;
		} 
		return -1;
	}
}



//sorted by finsh time
class Job implements Comparable<Job>{
	int start, finish, profit;
	public Job(int start, int finish, int profit) {
		this.start = start;
		this.finish = finish;
		this.profit = profit;
	}
	public int compareTo(Job x) {
		return Integer.compare(this.finish, x.finish);
	}
	@Override
	public String toString() {
		return "["+start + ", " + finish + "]";
	}
}

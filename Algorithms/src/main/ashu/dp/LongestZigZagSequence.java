package main.ashu.dp;

//If a sequence {x1, x2, .. xn} is alternating sequence then its element satisfy one of the following relation :
//x1 < x2 > x3 < x4 > x5 < …. xn or 
//x1 > x2 < x3 > x4 < x5 > …. xn 
//variation of LIS problem
//Approach : we will have to keep track of two cases at every index :
//1. lzzs ending at i, with previous value smaller than a[i]
//2. lzzs ending at i, with previous value greater than a[i]
//lzzs = max(dp[i][0], dp[i][1]) for all 0<=i<=n
//where, dp[i][0] = max(dp[i][0], dp[j][1]+1), j<i and a[j]<a[i]
// and, dp[i][1] = max(dp[i][1], dp[j][0]+1), j<i and a[j] > a[i]

public class LongestZigZagSequence {
   
	public static void main(String args []) {
		int a [] = {10, 22, 9, 33, 49, 50, 31, 60};
		int n = a.length;
		int dp [][] = new int[n][2];
		int lzzs = Integer.MIN_VALUE;
		int index = -1;
		//base case
		for(int i=0; i<n; i++){
			dp[i][0] = dp[i][1] = 1;
		}
		
		for(int i=1; i<n; i++) {
			for(int j=i-1; j>=0; j--) {
				if(a[j] < a[i]) {
					dp[i][0] = Math.max(dp[i][0],  dp[j][1] + 1);
				}
				else if(a[j] > a[i]) {
					dp[i][1] = Math.max(dp[i][1], dp[j][0] + 1);
				}
			}
			//lzzs = Math.max(lzzs, Math.max(dp[i][0], dp[i][1]));
			if(lzzs < Math.max(dp[i][0], dp[i][1])) {
				lzzs = Math.max(dp[i][0], dp[i][1]);	
				index  = i;
			}
		}
		System.out.println("Length of longest zig zag sequence is : "+lzzs);
		
		//Print the sequence 
		String str = "";
		while(index >=0) {
			if(lzzs == Math.max(dp[index][0], dp[index][1])) {
				str = a[index]+ " "+ str;
				lzzs--;
			}
			index--;
		}
		System.out.println("lzzs is : "+str);
	}
}

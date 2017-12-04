package DP;

//Given a list of n coins you have to play a game with someone. What is the maximum value of coin that you can collect  
//if you begin the game and your opponent is as clever as you(meaning he also uses the optimal winning strategy).
//Rule : Any player would pick either first or the last coin form the remaining set and the chosen coin is excluded once chosen.
//F(i, j)  = Max(Vi + min(F(i+2, j), F(i+1, j-1) ), Vj + min(F(i+1, j-1), F(i, j-2) )) 

public class OptimalGamePlan {
	private int val[];
	
	public int playOptimally(int a[]) {
		this.val = a;
		int n = this.val.length;
		int dp [][] = new int[n][n];
		//base case...one or two coins
		for(int i=0; i<n; i++) {
			dp[i][i] = val[i];
		}
		//iterate over problem size
		for(int len = 2; len<=n; len++) {
			for(int i=0; i+len<=n; i++) {
				int j = i+len-1;
				if(i+1==j) dp[i][j] = Math.max(val[i], val[j]);
				else {
					int choice1 = val[i] + Math.min(dp[i+2][j], dp[i+1][j-1]);
					int choice2 = val[j] + Math.min(dp[i][j-2], dp[i+1][j-1]);
					dp[i][j] = Math.max(choice1, choice2);
				}			
			}
		}
		return dp[0][n-1];
	}
	
	public static void main(String args []) {
		OptimalGamePlan game = new OptimalGamePlan();
		int arr1[] = {8, 15, 3, 7};
		int maxMoney = game.playOptimally(arr1);
		System.out.println("Maximum money that can be collected = "+maxMoney);
		int arr2[] = {2, 2, 2, 2};
		maxMoney = game.playOptimally(arr2);
		System.out.println("Maximum money that can be collected = "+maxMoney);
		int arr3[] = {20, 30, 2, 2, 2, 10};
	    maxMoney = game.playOptimally(arr3);
	    System.out.println("Maximum money that can be collected = "+maxMoney);
	}
}

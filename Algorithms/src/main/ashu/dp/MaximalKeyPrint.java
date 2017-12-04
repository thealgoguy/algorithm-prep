package threadpool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//Given a number N and 4 types of moves - add a key(A), select(A), copy(A) or paste(A)
//What is the maximum number of keys(AAA...sequence) that can be printed using any sequence of the four moves.
public class MaximalKeyPrint {

	public int getLetterCount(int N) {
		if(N<=3) return N;  //base cases
		int add [] = new int[N+1];
		int select []= new int[N+1];
		int copy [] = new int[N+1];
		int paste [] = new int[N+1];
		int dp [] = new int[N+1];
		//initialization 
		add[1] = 1; add[2] = 2; add[3] = 3;
		select[1] = 0; select[2] = 1; select[3]=2;
		copy[1] = 0; copy[2] = 0; copy[3] = 1;
		paste[1] = 0; paste[2] = 0; paste[3] = 0;
		//now start solving for larger subproblems
		//note that copy will always be made on optimal select and 
		//in order to optimize paste, we need to consider the best among last three copies(by observation)
		dp[1]=1; dp[2]=2; dp[3]=3;
		for(int i=4; i<=N; i++) {
			add[i] = Math.max(add[i-1], paste[i-1]) + 1;  //not counting select, copy..? think
			select[i] = Math.max(add[i-1], paste[i-1]);   //u can't select copied value
			copy[i] = select[i-1];  //only what's selected can be copied
			//tricky part is that paste can be optimized by using any of the last three copies
			//last 4th and so on copies won't lead to optimal paste hence ignoring them...or maybe I'm wrong
			paste[i] = Math.max(2*copy[i-1], Math.max(3*copy[i-2], 4*copy[i-3]));
			dp[i] = Math.max(dp[i-1], Math.max(add[i], paste[i])); //the answer for ith stage depends on add/paste of (i-1)th stage
		}
		return dp[N];
	}

	public static void main(String args []) throws NumberFormatException, IOException {
		MaximalKeyPrint printer = new MaximalKeyPrint();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the value of N : ");
		String s;
		int N;
		while((s=br.readLine()) != null) {
			N = Integer.parseInt(s);
			System.out.print("Maximum number of A's that can be printed using "+N+" moves = "+printer.getLetterCount(N)+"\n\n");
			System.out.print("Enter the value of N : ");
		}
	}
}


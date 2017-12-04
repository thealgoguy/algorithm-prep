import java.util.Scanner;


public class CoinCollect {
    public static void main(String args []) {
    	Scanner sc = new Scanner(System.in);
    	int t = sc.nextInt();
    	while(t-- > 0) {
        	int  n = sc.nextInt();
        	int k = sc.nextInt();
        	int dp [] = new int[n+1];
        	dp[0]=0;
        	for(int i=1; i<n+1; i++) dp[i] = sc.nextInt()+dp[i-1];
        	//for(int i=2; i<=n; i++) dp[i]+=dp[i-1];
        	int max = Integer.MIN_VALUE;
        	for(int i=1; i<=n-k; i++) {
        		int sum = dp[i+k-1] - dp[i-1];
        		if(sum > max ) max = sum;
        	}
        	System.out.println(max);
    	}

    }
}

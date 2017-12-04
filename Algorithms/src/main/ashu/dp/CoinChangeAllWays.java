import java.util.Scanner;


public class CoinChangeAllWays {

    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);
        String st [] = s.nextLine().split(" ");
        int n = Integer.parseInt(st[0]);
        int m = Integer.parseInt(st[1]);
        int a [] = new int[m+1];
        long dp [][] = new long[n+1][m+1];
        String c [] = s.nextLine().split(" ");
        for(int i=0; i<m; i++) a[i+1] = Integer.parseInt(c[i]);
        for(int i=0; i<=n; i++) dp[i][0]=0;
        for(int i=0; i<=m; i++) dp[0][i]=1;
        for(int i=1; i<=n; i++) {
        	for(int j=1; j<=m; j++) { //checking for each coin if it leads to the sum
        		if(a[j]<=i) {
        			dp[i][j] += dp[i-a[j]][j]; // take jth coin, it can be taken again too
        			dp[i][j] += dp[i][j-1]; //don't take jth coin
        		}
        		else dp[i][j] = dp[i][j-1]; //can't take jth coin
        	}
        } 
        System.out.println(dp[n][m]);
    }

}

package main.ashu.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MatrixZigZagSum {

	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		int t = Integer.parseInt(br.readLine());
		while(t-- > 0) {
		    int n = Integer.parseInt(br.readLine());
		    int a [][] = new int[n][n];
		    String s [] = br.readLine().split("\\s+");
		    for(int i=0; i<n*n; i++) {
		        a[i/n][i%n] = Integer.parseInt(s[i]);
		    }
		    int dp[][] = new int[n][n];
		    int max = Integer.MIN_VALUE;
		    for(int i=0; i<n; i++) {
		        dp[0][i] = a[0][i];
		        max = Math.max(max, dp[0][i]);
		    }
		    for(int i=1; i<n; i++) {
		        for(int j=0; j<n; j++){
		        	dp[i][j] = Integer.MIN_VALUE;
		            for(int k=0; k<n; k++){
		            	if(k !=j) {
		            		dp[i][j] = Math.max(dp[i][j], dp[i-1][k]);
		            	}
		            }
		            dp[i][j] += a[i][j];
		            max = Math.max(dp[i][j], max);
		        }
		    }
		    System.out.println(max);
		}
	}

}

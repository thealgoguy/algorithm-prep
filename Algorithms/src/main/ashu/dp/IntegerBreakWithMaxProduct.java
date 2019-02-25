package main.ashu.dp;

//https://www.programcreek.com/2015/04/leetcode-integer-break-java/
//Given a positive integer n, break it into the sum of at least two positive integers and
//maximize the product of those integers.
//Return the maximum product you can get.
public class IntegerBreakWithMaxProduct {
    public static void main(String args []) {
    	int n = 10;
    	int[] dp = new int[n+1];  	 
        for(int i=1; i<n; i++){
            for(int j=1; j<i+1; j++){
                if(i+j<=n){
                	//x = i+j.....now we can choose to either further break i or j or both or none
                	//for i two choices(break, don't break)....take best ->Math.max(dp[i],i)
                	//for j two choices(break, don't break)....take best ->Math.max(dp[j],j)
                	//dp[i,j]= product of best(i) * best(j) -> Math.max(dp[i],i)*Math.max(dp[j],j)
                    dp[i+j]=Math.max(Math.max(dp[i],i)*Math.max(dp[j],j), dp[i+j]);
                }
            }
        }
        System.out.println("Max product obtained by breakin the integer = "+dp[n]);
    }
    
    //by finding pattern
    public int integerBreak(int n) {
    	 
        if(n==2) return 1;
        if(n==3) return 2;
        if(n==4) return 4;
     
        int result=1;
        if(n%3==0){
            int m = n/3;
            result = (int) Math.pow(3, m);
        }else if(n%3==2){
            int m=n/3;
            result = (int) Math.pow(3, m) * 2;
        }else if(n%3==1){
            int m=(n-4)/3;
            result = (int) Math.pow(3, m) *4;
        }
     
        return result;
    }
}

import java.util.Scanner;


public class LongestCommonSubseq {
  public static void main(String args []) {
	  Scanner sc = new Scanner(System.in);
	  String s1 = sc.nextLine();
	  String s2 = sc.nextLine();
	  int n1 = s1.length(), n2 = s2.length();
	  String [][] sol = new String[n1+1][n2+1];
	  int dp [][] = new int[n1+1][n2+1];   // using 1 based indexing to avoid unnecessary code for handling array index out of bounds exception
	  for(int i=0; i<=n1; i++) {
		  for(int j=0; j<=n2; j++) {
			  if(i==0 || j==0) { //base case any of the string is null
				  dp[i][i] = 0; 
				  sol[i][i] = "0";
			  }
			  else {
				  dp[i][j] = (s1.charAt(i-1)==s2.charAt(j-1))? dp[i-1][j-1]+1 : Math.max(dp[i-1][j], dp[i][j-1]);
				  if(s1.charAt(i-1)==s2.charAt(j-1)) sol[i][j] = "diagonal";
				  else if(dp[i][j]==dp[i-1][j]) sol[i][j] = "top";
				  else if(dp[i][j]==dp[i][j-1]) sol[i][j] = "left";
			  }
		  }
	  }
	  System.out.println("Length of LCS is : "+dp[n1][n2]);
	  //print the LCS by tracking the coordinate from rightmost coordinate to left,top or diagonally up
	  //http://algorithms.tutorialhorizon.com/dynamic-programming-longest-common-subsequence/
	  String ans = ""; 
	  int x=n1,y=n2;
	  while(sol[x][y]!="0") { //move left or up taking only diagonal coordinates
		  if("diagonal".equals(sol[x][y])){  ans =s1.charAt(x-1)+ans; x--;y--;}
		  else if("top".equals(sol[x][y])) x--;
		  else if("left".equals(sol[x][y])) y--;
	  }
	  System.out.println("LCS is : "+ ans); 
	  //now without using extra space..printing lcs using dp array itself
	  ans = ""; 
	  x=n1; y=n2;
	  while(x>0 && y>0) {
		  if(s1.charAt(x-1)==s2.charAt(y-1)) {ans = s1.charAt(x-1)+ans;x--; y--;}
		  else if(dp[x][y]==dp[x-1][y]) x--;
		  else y--;
	  }
	  System.out.println("LCS is : "+ans);
	  //how to print all possible LCS 
	  //http://stackoverflow.com/questions/22299345/how-to-print-all-possible-solutions-for-longest-common-subsequence
  }
}

package main.ashu.dp;

//Given three strings A, B and C. Write a function that checks whether C is an interleaving of A and B. 
//C is said to be interleaving A and B,if it contains all characters of A and B and 
//order of all characters in individual strings is preserved.

public class StringInterleavingProblem {

	public static boolean isInterleaved(String a, String b, String c) {
		if(a==null || b==null || c==null) return false;
		if (a.length() + b.length() != c.length()) {
			return false;
		}
		//return checkRecursively(a, b, c, 0, 0);
		return checkDP(a,b,c);
	}
	//dynamic programming implementation ....O(m*n)
    public static boolean checkDP(String a, String b, String c) {
    	int m = a.length();
    	int n = b.length();
    	boolean dp [][] = new boolean[m+1][n+1];
    	for(int i=0; i<=m; i++) {
    		for(int j=0; j<=n; j++) {
    			if(i==0 || j==0) dp[i][j] = true;  //empty strings are considered interleaved
    			else {
    				if(a.charAt(i-1)==c.charAt(i+j-1)) dp[i][j] = dp[i-1][j];
    				else if(b.charAt(j-1)==c.charAt(i+j-1)) dp[i][j] = dp[i][j-1];
    			}
    		}
    	}
    	return dp[m][n];
    }
    //inefficient due to overlapping recursive calls
	public static boolean checkRecursively(String a, String b, String c, int i, int j) {
		if (i == a.length() && j == b.length() && i + j == c.length()) {
			return true;
		}
		if (i<a.length() && a.charAt(i) == c.charAt(i + j)) {
			return checkRecursively(a, b, c, i + 1, j);
		}
		if (j<b.length() && b.charAt(j) == c.charAt(i + j)) {
			return checkRecursively(a, b, c, i, j + 1);
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(StringInterleavingProblem.isInterleaved("XXY", "XXZ", "XXZXXXY"));
		System.out.println(StringInterleavingProblem.isInterleaved("XY", "WZ", "WZXY"));
		System.out.println(StringInterleavingProblem.isInterleaved("XY", "X", "XXY"));
		System.out.println(StringInterleavingProblem.isInterleaved("YX", "X", "XXY"));
		System.out.println(StringInterleavingProblem.isInterleaved("XXY", "XXZ", "XXXXZY"));
	}
}

package geeksforgeeks;
//Given a text and a pattern containing wild card characters, find of the text contains the pattern
//Wild card patterns : * - 0 or more occurrence of sequence of chars, ? - exactly 1 occurrence of any char
//dp[i][j] = subproblem to match first i chars of text with first j chars of pattern
//dp[i[[j] = dp[i-1][j-1] if pat[j]==text[i] or pat[j]='?' and
//dp[i][j] = dp[i-1][j] || dp[i][j-1] if pat[j]='*'    cases inferred based on value of pat[j]

public class WildCardPatternMatching {
   public static void main(String args []) {
	   String text = "baaabab";
	   String pat = "*****ba*****ab";
	   //preprocessing the pattern to remove duplicate *
	   StringBuilder sb = new StringBuilder();
	   sb.append(pat.charAt(0));
	   boolean found = pat.charAt(0)=='*';
	   //if(found) sb.append('*');
	   for(int i=1; i<pat.length(); i++) {
		   if(pat.charAt(i)=='*') {
			   if(found) continue;  //ignore adjacent stars
			   else {
				   found = true;
				   sb.append('*');
			   }
		   } else { 
			   sb.append(pat.charAt(i));
			   found = false;
		   }
	   }
	   pat = sb.toString();
	   //dp[i][j] =match of text(1....i) and pat(1....j)
	   boolean dp [][] = new boolean[text.length()+1][pat.length()+1];
	   dp[0][0] = true;  //text and pat both null
	   for(int i=1; i<=text.length(); i++) dp[i][0] = false;   //pattern is  null
	   dp[0][1] = pat.charAt(0) == '*';	
	   for(int i=1; i<=text.length(); i++) {
		   for(int j=1; j<=pat.length(); j++) {
			   if(text.charAt(i-1)==pat.charAt(j-1) || pat.charAt(j-1)=='?') dp[i][j] = dp[i-1][j-1];
			   else {
				   if(pat.charAt(j-1)=='*') dp[i][j] = dp[i-1][j] || dp[i][j-1];
				   
			   }
		   }
	   }
	   if(dp[text.length()][pat.length()]) System.out.println("YES, the patern matches with the text");
	   else System.out.println("NO, the pattern doesn't match with the text");
   }
}

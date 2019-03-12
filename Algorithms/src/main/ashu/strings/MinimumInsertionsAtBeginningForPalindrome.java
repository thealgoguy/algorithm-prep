package main.ashu.strings;


public class MinimumInsertionsAtBeginningForPalindrome {
    public static void main(String args []) {
    	String str = "JAVA";
    	int min = evaluate(str);
    	System.out.println("Minimum insertions at the beginning for converting to palindrome = "+min);
    }
    //O(n) approach
    //KMP approach...find LPS[] for S = s+'$'+rev(s)
    //return n-LPS[S(N)]
    //https://www.geeksforgeeks.org/minimum-characters-added-front-make-string-palindrome/
    /*public int evaluate2(String s) {
    	if(s == null || s.isEmpty()) return 0;
    	String str = s+ "$"+ reverse(s);
    	
    }*/
    
    //O(n2) approach
    public static int evaluate(String s) {
    	if(s == null || s.isEmpty()) return 0;
    	//find the largest palindromic prefix which is a suffix
    	for(int i=s.length()-1; i>=0; i--) {
    		if(isPalindrome(s.substring(0, i+1))) {
    			return s.length()-i-1;    		}
    	}
    	return 0;
    }
    
    public static boolean isPalindrome(String s) {
    	if(s == null || s.isEmpty()) return false;
    	int i=0, j=s.length()-1;
    	while(i<j && s.charAt(i) == s.charAt(j)) {
    		i++; j--;
    	}
    	return i>=j;
    }
}

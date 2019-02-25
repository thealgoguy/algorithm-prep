package main.ashu.patternsearching;
//print all the occurrences of the anagrams of the pattern in the text
//Expected time - O(n)
//use of hashing
public class AnangramsPatternSearch {
	public static boolean success(int [] a, int [] b) {
		int i;
		for(i=0; i<a.length; i++) {
			if(a[i] != b[i]) break;
		}
		return (i>=a.length);
	}
   public static void main(String args []) {
	   String text = "BACDGABCDA";
	   String pat = "ABCD";
	   //count array to store frequency of ascii characters in text and pattern
	   int tc [] = new int[256];
	   int pc [] = new int[256];
	   int m = pat.length(), n = text.length();
	   //initialize the window for text and pattern...its like creating hashes for current window of pattern and comparing with the 
	   //hash of pattern...O(1) time because we compare only 256 chars...size of alphabet. 
	   //Its better than Rabin Karp since success() is independent of size of pat and text.
	   for(int i=0; i<pat.length(); i++) {
		   pc[pat.charAt(i)]++;
		   tc[text.charAt(i)]++;
	   }
	   for(int i=m; i<n; i++) {
		   if(success(pc,tc)) System.out.println("Anagram found at index "+(i-m)+", "+text.substring(i-m, i));
		   tc[text.charAt(i-m)]--;
		   tc[text.charAt(i)]++;
	   }
	   //last window remains compare that too
	   if(success(pc,tc)) System.out.println("Anagram found at index "+(n-m)+", "+text.substring(n-m, n));
   }
}

package Palindromes;

import java.util.Arrays;
//Clear the concept of odd and even length palindrome.
//Efficiently count number of pals in a strign using Manacher's algo and DP. 
//count = sum(ceil(k/2))where k = radius of longest palindrome at every pal center
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

//optimization over brute force can be achieved by generating substrings in increasing order of length 
//and using symmetry property of palindrome
//if S(i,j) is pal iff S(i)=S(j) and S(i+1,j-1) is palindrome. 
//This means check for length l can be efficiently made(O(1) time) if we have already done check for length l-2 plindromes.
//This is case of overlapping subproblems. Hence check in increasing order of substring length and 
//Backdrop : we are still generating all n*n substrings, some of which are'nt candidates for palindromes.
//further optimization can be achieved by skipping check for all substrings and generating only "palindromes" of odd/even lengths 
//to generate only palindromes, use the fact that palindromes are symmetrical around centers. So, iterate for all palindromic centers.
public class FindAllPalindromesDP {
	public static void main(String arg []) {
		//String s = "abcdadprcba";
		String s = "geek";	   
		printPalindromeDP(s);   //O(n*n) for count, O(n*n) extra space
	}
	public static void printPalindromeDP(String s) {
		HashMap<String,String> hm = new HashMap<String,String> (); //to avoid possible duplicates
		int count =0;
		int n = s.length();
		boolean pal [][] = new boolean[n][n]; 
		for(int i=0; i<n; i++) pal[i][i] = true; //length 1 palindromes, base case
		for(int i=0; i<n; i++) hm.put(Character.toString(s.charAt(i)),Character.toString(s.charAt(i)));
		for(int len=2; len<=n; len++) {
			for(int i=0; i<n-len; i++) {
				int j = i+len-1;
				if(i+1==j) pal[i][j] = (s.charAt(i)==s.charAt(j));
				else pal[i][j] = (s.charAt(i)==s.charAt(j)) ? pal[i+1][j-1] : false;
				if(pal[i][j]) {
					hm.put(s.substring(i,j+1), s.substring(i,j+1));
				}
			}
		}
		System.out.print("Distinct palindromes are(DP bottom-up) : ");
		Iterator key = hm.keySet().iterator();
		while(key.hasNext()) {
			count++;
			System.out.print(hm.get(key.next())+" ");
		}
		System.out.println("Total number of distinct palindromes are = "+count);
	}

	public static boolean isPalindrome(String s, int i, int j) {
		while(i<j && s.charAt(i)==s.charAt(j)) {i++;j--;}
		return (i>=j);
		//or pure recursive but iterative is better
		/*if(i>j) return false;
	   if(i==j) return true;
	   if(i+1==j) return s.charAt(i)==s.charAt(j);
	   return s.charAt(i)==s.charAt(j) && isPalindrome(s,i+1,j-1);*/
	}
}
package Palindromes;

import java.util.HashMap;
import java.util.Iterator;

//Given a string s, find all pallindromic substrings. There can be maximum n*n palindromes(as there are a total of n*n substrings).
public class FindAllPalindBruteForce {
	public static void main(String args []) {
		String s = "geek";
		printPalBruteForce(s);  //O(n*n*n) for count, O(1) space
	}	   
	public static void printPalBruteForce(String s) {
		HashMap<String,String> hm = new HashMap<String,String> (); //since many pals be duplicates
		int count = 0;
		int n = s.length();
		for(int i=0; i<n; i++) {
			for(int j=i; j<n; j++) {
				//check if substring s(i,j) is palindrome
				//note that in brute force isPalindrome() is called n*n times, no use of previous results made dumb approach
				if(isPalindrome(s,i,j)) {
					hm.put(s.substring(i,j+1), s.substring(i,j+1));
				}
			}
		}
		System.out.print("Palindromes are(brute force) : ");
		Iterator key = hm.keySet().iterator();
		while(key.hasNext()) {
			count++;
			System.out.print(hm.get(key.next())+" ");
		}
		System.out.println("\nTotal number of distinct palindromes are = "+count);
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

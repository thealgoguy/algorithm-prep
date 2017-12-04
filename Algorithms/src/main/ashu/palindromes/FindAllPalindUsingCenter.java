package Palindromes;

import java.util.HashMap;
import java.util.Iterator;
//There is no need to check for all substrings
//There is no need to check substrings of all length 1 to n...in hope for palindrome
//since palindromes are symmetrical about their center, iterate over centers and generate palindromes only
//there are n centers(character centers) for odd-length palindromes
//there are n-1 centers(spaces between characters) for even-length palindromes. 
//backdrop : we are expanding every center from scratch, which is a bit costly. No use of previous comparisons.
//Manacher's algo deals with it.
public class FindAllPalindUsingCenter {
   public static void main(String args []) {
	   String s = "geek";
	   char ch [] = s.toCharArray();
	   int n = ch.length;
	   HashMap<String, Integer> map = new HashMap<String,Integer>();
	   //generating odd length palindromes first	   
       for(int i=0; i<n; i++) map.put(Character.toString(ch[i]), 1); //length 1 palindromes
	   for(int i=1; i<n; i++) {
		   int j=i-1, k=i+1, len=1;
		   while(j>=0 && k<n) {
			   if(ch[j]==ch[k]) {len+=2;j--;k++;}
			   else break;
		   }
		   map.put(s.substring(j+1, k), len);
	   }
	   //generating even-length palindromes. 
	   //Iterate over spaces from 0 to n-2...from space after first char till space before last char
	   for(int i=0; i<n-1; i++) {
		   int j=i, k=i+1, len=0;
		   while(j>=0 && k<n) {
			   if(ch[j]==ch[k]) {len+=2;j--;k++;}
			   else break;
		   }
		  if(len>0)  map.put(s.substring(j+1, k), len); //not counting empty string, gets generated when the characters which the space is flanked by do not match.
	   }
	   //print the palindromes
	   int count=0;
	   Iterator key = map.keySet().iterator();
	   while(key.hasNext()) {
		   count++;
		   String keyVal = key.next().toString();
		   //System.out.println(keyVal+" len = "+map.get(keyVal)+" ");
		   System.out.print(keyVal+" ");
	   }
	   System.out.println("\nTotal number of distinct palindromes are = "+count);
   }
}

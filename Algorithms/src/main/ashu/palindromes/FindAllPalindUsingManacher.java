package Palindromes;

import java.util.HashMap;
import java.util.Iterator;

//very efficient when palindromes overlap. Linear time algorithm
public class FindAllPalindUsingManacher {
	private static int countOfPalindromes = 0;
	
   public static void main(String args []) {
	   //String s = "geeks";
	   String s = "xyxyx";
	   int p [] = generatePalArray(s.toCharArray());
	   HashMap<String,Integer> map = new HashMap<String,Integer> ();
	   for(int i=1; i<p.length-1; i++) {
		   if(p[i] > 0) {
			   int idx = i - p[i]; idx++;
			   int start = (idx-2)/2;
			   String palindrome = s.substring(start,start+p[i]);
			   map.put(s.substring(start,start+p[i]), p[i]); 
		   }		   		       
	   }
	   int count=0;
	   Iterator key = map.keySet().iterator();
	   while(key.hasNext()) {
		   count++;
		   String keyVal = key.next().toString();
		   System.out.print(keyVal+" ");
	   }
	   System.out.println("\nTotal number of distinct palindromes are = "+count);
	   System.out.println("Total number of palindromes including duplicates = "+countOfPalindromes);
   }
   
   public static int [] generatePalArray(char [] arr) {
	   int n = arr.length;
	   char ch [] = new char[2*n+3];
	   ch[0]='@';
	   for(int i=0; i<n; i++) {
		   ch[2*i+1] = '#'; ch[2*i+2] = arr[i];
	   }
	   ch[2*n+1] = '#';
	   ch[2*n+2] = '$';
	   int p [] = new int[ch.length]; 
	   int c=0, r=0; 
	   //start iterating form first # to last #
	   for(int i=1; i<ch.length-1; i++) {
		   int mirror = 2*c-i;
		    p[i] = (r>i) ? Math.min(p[mirror],r-i) : 0;
		    while(ch[i+p[i]+1]==ch[i-p[i]-1]) p[i]++;   //tricky loop
		    if(i+p[i] > r) { //comparing end point of radius
		    	c=i; r=i+p[i];
		    }
		    countOfPalindromes += Math.ceil(p[i]/2);
	   }
	   return p;
   }
}

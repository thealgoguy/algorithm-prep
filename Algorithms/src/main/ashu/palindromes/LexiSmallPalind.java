import java.util.HashMap;
import java.util.Set;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;


public class LexiSmallPalind {
  public static void main(String srgs[]) {
	  String input = "abchtbghca";
	  int N = input.length();
	  int arr [] = new int[26];
	 // HashMap<Character, Integer> map = new HashMap();
	  for(int i=0; i<N; i++) {
	     arr[input.charAt(i)-'a']+=	1; 
	  }
	  String head = "", tail = ""; boolean odd = false;
	  int i; int oddIndex = -1;
	  for(i=0; i<arr.length; i++) {
		  int val = arr[i];
		 if(val %2 !=0) {
			 if(!odd) {
				 odd = true; oddIndex = i;
				 for(int j=0; j<val/2; j++) head+=Character.toString(((char)(i+'a')));
				 for(int j=0; j<val/2; j++) tail = Character.toString(((char)(i+'a')))+tail;
			 }
			 else break;
		 }
		 else {
			 for(int j=0; j<val/2; j++) head+=Character.toString(((char)(i+'a')));
			 for(int j=0; j<val/2; j++) tail = Character.toString(((char)(i+'a')))+tail;
		 }
		  
	  }
	  if(i<N) System.out.println("No palindrome exists");
	  else {if(odd) head+=Character.toString(((char)(oddIndex+'a')));
	  head = head+tail;
	  System.out.println("Lexicographically smallest palindrome = "+ head);
	  }	  
  }
}

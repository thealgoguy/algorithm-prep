package main.ashu.strings;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class RemoveDupsString {
	
   public static String usingLinkedHashSet(String str) {
	   if(str==null || str.length() <2) return str;
	   int n= str.length();
	   LinkedHashSet<Character> hs = new LinkedHashSet<Character>();
	   for(int i=0; i<n; i++) {
		   hs.add(str.charAt(i));
	   }
	   StringBuilder sb = new StringBuilder();
	   Iterator<Character> it = hs.iterator();
	   while(it.hasNext()) {
		   sb.append(it.next());
	   }
	   return sb.toString();
   }
   
   public static String usingBinMap(String s) {
	   if(s==null || s.length() <2) return s;
	   int n = s.length();
	   StringBuilder sb = new StringBuilder();
	   boolean visited [] = new boolean[256];
	   for(int i=0; i<n; i++) visited[s.charAt(i)-'a'] = true;
	   for(int i=0; i<n; i++) {
		   if(visited[s.charAt(i)-'a']) {
			   sb.append(s.charAt(i));
			   visited[s.charAt(i)-'a'] = false;
		   }
	   }
	   return sb.toString();
   }
   
   //no linked hashset used, next char is matched against the unique chars discovered so far
   public static String usingNoAuxulaiaryDS(String s) {
	   if(s==null || s.length() <2) return s;
	   int n = s.length();
	   StringBuilder sb = new StringBuilder();
	   for(int i=0; i<n; i++) {
		   int j;
		   for(j=0; j<sb.length(); j++) {
			   if(s.charAt(i)==sb.charAt(j)) break;
		   }
		   if(j==sb.length()) sb.append(s.charAt(i));
	   }
	   return sb.toString();
   }
   
   public static void main(String args []) {
	   String s = "kfshffsfsfsjfjkhhkhlioeow";
	   System.out.println("Input string is : "+s);
	   System.out.println("After removing duplicates : "+usingLinkedHashSet(s));
	   System.out.println("After removing duplicates : "+usingBinMap(s));
	   System.out.println("After removing duplicates : "+usingNoAuxulaiaryDS(s));
   }
}

package main.ashu.palindromes;

import java.util.Scanner;
//Longest palindromic prefix in a string S can be defined as :
//the longest common substring between prefix S and S.reverse
//z-algo can be used for efficient computation
///similarly, longest palindromic suffix can be defined as :
//the longest common substring between prefix S.reverse and S
public class LongestPalindPrefixSuffix {
   public static void main(String argds []) {
	   int [] z = null;
	   String  str = null;
	   Scanner sc  = new Scanner(System.in);
	   System.out.println("Enter the string");
	   str = sc.nextLine();
	   String s1 = str+"#"+new StringBuffer(str).reverse();
	   z = makeZArray(s1.toCharArray());	 
	   System.out.println("Longest palindromic prefix: "+search(s1,z));
	   String s2 = new StringBuffer(str).reverse()+"#"+str;
	   z = makeZArray(s2.toCharArray());
	   System.out.println("Longest palindromic suffix: "+search(s2,z));
   }
   
   public static String search(String str, int []z) {
	   int maxlen = -1;
	   String s=null;
		  for(int i=z.length/2; i<z.length; i++) {
			  maxlen = Math.max(maxlen,z[i]);
		  }
		  for(int i=z.length/2; i<z.length; i++) {
			  if(z[i]==maxlen) { 
				  s =  (str.substring(i,i+z[i]));	break; 
			  }
		  }
		  return s;
   }
  public static int [] makeZArray(char [] ch) {
	   int z [] = new int[ch.length];
	   int l=0, r=0;
	   z[0]=0; //ignoring the first z-value
	   for(int i=1; i<z.length; i++) {
		   z[i] = (i>r) ? 0 : Math.min(r-i+1, z[i-l]);
		   int j=0;
		   while(i+z[i]<z.length && ch[i+z[i]]==ch[z[i]]) z[i]++;
		   if(i+z[i]-1 >r) {
			   l=i; r = i+z[i]-1;
		   }
	   }
	   return z;
   }
}

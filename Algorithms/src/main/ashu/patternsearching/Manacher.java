package main.ashu.patternsearching;

import java.util.Scanner;
//for a string S, rad[i] = length of longest common substring between S(i+1....n) and S(i-1....0)
//rad[i] is called radius of the palindrome with center i
//algorithm uses a main palindrome, used for computing minm radius of next palindromes
//if the next palind extends outside the main palond, the current palind is made the main palind.similar to z-algo
//rad[i] in the processed array = length of largest palindromic substring at some index k in original string.
//Solve using Manacher's algo too
//How to solve it using suffix tree ??
public class Manacher {
	//@ and $ will be end characters (2)
	//# will be center for even-length palindromes, between two chars and before first char and after last char.(n+1)
	//chars in string will be center for odd length palindromes. (n)
	//2+n+1+n = 2*n+3
	public static char [] preprocess(char [] s) {
		int n = s.length;
		char t [] = new char[2*n+3];
		t[0]='@';
		for(int i=0; i<n; i++) {
			t[2*i+1] = '#';
			t[2*i+2] = s[i];
		}
		t[2*n+1] = '#';
		t[2*n+2]='$';
		return t;
	}
	public static int [] makePraday(char [] ch) { 
		//for each item in the string we store the radius(half of the length) of the largest palindrome, 
		//at every center. If we are talking about palindromes of even length,
		//we consider the central element to the right of exact center.
		int rad [] = new int[ch.length];
		int n = rad.length;
		rad[0]=0; 
		int c=0,r=0; //c and r are center and radius of the main palindrome 
		//iterate from # to last #
		for(int i=1; i<rad.length-1; i++) {
			int mi = 2*c-i;
			rad[i] = (r>i)? Math.min(rad[mi], r-i) : 0; //minm len of radius of current palindrome. radius doesn't include the center.
			//now match chars to the left and right of center
			//s(i+1....i+rad[i]) = S(i-1....i-rad[i]). now try to extend the current palindrome
			//note that S(i-1....i-rad[i]) is reverse of s(i+1....i+rad[i]) now matching is similar to z algorithm
			//rad[i] can be defined as the length of longest common substring between S(i+1....n) and S(i-1.....0)
			while(ch[i+rad[i]+1]==ch[(i-rad[i]-1)]) rad[i]++; //tricky loop...1 added for central character
			if(i+rad[i] > r) {  //if current palind extends outside the main palind, then update the main palind
				c=i;r=i+rad[i];
			}
		}
		return rad;
	}
  public static void main(String args []) {
	  Scanner sc = new Scanner(System.in);
	  String s = sc.next();
	  char procesed []  = preprocess(s.toCharArray());
	  int p_rad [] = makePraday(procesed);
	  int max = -1, idx=-1;
	  for(int i=0; i<p_rad.length; i++) {
		  if(p_rad[i] > max) { max = p_rad[i]; idx=i;}
	  }
	 idx = idx - max; //point idx to start
	 idx++; //since boundary of any pal is # in the pal array, point it to a character
	 int start = (idx-2)/2; //map a/c to 2*i+2
	  int end = start + max;
	  System.out.println("Longest palindromic substring : "+s.substring(start,end));
  }
}

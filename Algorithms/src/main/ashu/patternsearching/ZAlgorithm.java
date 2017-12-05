package main.ashu.patternsearching;
// Given a string S, z[i]= length of the longest common substring between S(0...n) and S(i...n).
//z[i] is length of the z-box at index i
//the algorithm uses a "main z-box" which is the rightmost z-box. 
//The main z-box is used to compute the minimum length of next z-boxes.
//If next z-box extend to the right of the main z-box, it is made the main z-box
//To search for a pattern in a text, compute z-array for pat+"#"+text
//pattern exists at all indexes i where z[i]=length of the pattern and i>pattern.length
public class ZAlgorithm {
  public static void main(String arg []) {
	 String pat = "abc";
	 String text = "abc ytggtt abc jhghgjgj abc kjhjjkj";
	 String zString = pat+"#"+text;
	 char ch [] = zString.toCharArray();
	 int z [] = new int[zString.length()];
	 z[0]=0; //actually z[0]=pat.length but we will ignore the occurrence of pattern before #
	 int l=0,r =0;  //initialize the main z-box
	 for(int i=1; i<z.length; i++) {
		 z[i] = (i>r)? 0 : Math.min(z[i-l], r-i+1);  //computing z[i] from main z-box, its the minimum length
		 //this means S(0....z[i]-1) will always match with S(i.....i+z[i]-1),hence no need to do matching from scratch
		 int k=0;
		 while(i+z[i]<z.length && ch[i+z[i]]==ch[z[i]]) z[i]++;   //try to extend the current z-box
		 //ch[z[i]] iterates over S(0.....) while ch[i+z[i]] iterates over S(i......)
		 //obviously enough S(0....z[i]-1) = S(i....i+z[i]-1)
		 if(i+z[i]-1 > r) {    //if the current z-box extends to the right of the main z-box then update the main z-box
			 l=i; r=i+z[i]-1;
		 }
	 }
	 boolean found = false;
	 for(int i=pat.length(); i<z.length; i++) {
		 if(z[i]==pat.length()) {
			 found=true;
			 int idx = (i-pat.length()-1);
			 System.out.println("Pattern found at index : "+idx+" "+text.substring(idx, idx+pat.length()));
		 }
	 }
	 if(!found) System.out.println("Pattern not found");
  }
}
//Follow up : Find longest palindromic prefix and suffix using z-algo
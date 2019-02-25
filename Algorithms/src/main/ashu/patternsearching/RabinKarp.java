package main.ashu.patternsearching;

//average and best case running time of the Rabin-Karp algorithm is O(n+m), but its worst-case time is O(nm)
//poorer as compared to KMP, Z-algo, boyer moore, etc.
//best choice for multiple pattern search...used in plagiarism detection

public class RabinKarp {
	static int prime = 101;  // A prime number for modulo to avoid  integer overflow
	static int base = 256;   //alphabet size as base....for ascii
	static int n, m;
	public static void search(char [] pat, char [] text) {
		int ph=0, th=0;
		int h =1;
		for(int i=1; i<m; i++) h = (h*base) % prime;  //pow(base,m-1)
		//initializing the rolling hashes for pattern and text
		for(int i=0; i<m; i++) {
			ph = (ph*base + pat[i]) % prime;  //right shifting the hash to generate next window no...horner's rule
		    th = (th*base+text[i]) % prime;
		}
	for(int i=0; i<=n-m; i++) {
		if(ph==th) {
			int j;
			for(j=0; j<m; j++) {
				if(text[i+j] != pat[j]) break;
			}
			if(j==m) System.out.println("Pattern found at index : "+i);
		} else {
			if(i<n-m) {  //roll the hash rightward....remove first dig(-), move right(*10), add next dig
				th = ((th - text[i]*h)*10+text[i+m])%prime;
				if(th <0) th = th + prime;
			}
		}
	}
	}
	
   public static void main(String args []) {
	   String txt= "GEEKS FOR GEEKS";
	    String pat = "GEEK";
	    n = txt.length();
	    m = pat.length();
	    search(pat.toCharArray(), txt.toCharArray());
   }
}

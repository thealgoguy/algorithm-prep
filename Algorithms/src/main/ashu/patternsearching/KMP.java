package main.ashu.patternsearching;

//https://www.nayuki.io/page/knuth-morris-pratt-string-matching
public class KMP {
   public static void main(String args[]) {
	   String text = "hgffhAAACAAAAjhkhAAACAAAAhgfhfg";
		String pat = "AAACAAAA";
	   int [] lsp = generateLPSArray(pat);
	   System.out.print("LSP table is : ");
	   for(int i=0; i<lsp.length; i++) {
			System.out.print(lsp[i]+" ");
		}
	   System.out.println();
	   int j = 0;
	   for(int i=0; i<text.length(); i++) {
		   //the only smart thing than brute force thing is that instead of comparing from 
		   //the beginning of the pattern each time a match fails, we compare it from substring of the pattern 
		   //that is a potential match(which can only be  slp of the pattern
		   while (j > 0 && text.charAt(i) != pat.charAt(j)) {
	            // Fall back in the pattern
	            j = lsp[j - 1];  // Strictly decreasing
	        }
	        if (text.charAt(i) == pat.charAt(j)) {
	            // Next char matched, increment position
	            j++;
	            if (j == pat.length()) {
	            	System.out.println("Pattern found at index : "+(i-j));
	            	j=0;
	            }	            
	        }
	   }
   }
   
   //create lsp in bottom-up manner
   public static int [] generateLPSArray(String pat) {
	   int lsp [] = new int[pat.length()];
	   lsp[0] = 0;  //base case..single char string will have no PROPER prefix-suffix
	   for(int i=1; i<pat.length(); i++) {
		   //try extending from the previous lsp
		   int j=lsp[i-1];
		   while(j>0 && pat.charAt(i) != pat.charAt(j)) j = lsp[j-1];
		   if(pat.charAt(i) == pat.charAt(j)) j++;
		   lsp[i] = j;
	   }
	   return lsp;	   
   }   
}

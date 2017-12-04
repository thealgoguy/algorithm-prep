
public class KMP {
   public static void main(String args[]) {
	   String text = "abcdabc ab prt abcdabc udnd ab";
	   String pat = "abcdabc";
	   int [] slp = generateSLP(pat);
	   int k = 0;
	   for(int i=0; i<text.length(); i++) {
		   if(text.charAt(i)==pat.charAt(k)) {
			   if(k==pat.length() - 1) {
				   System.out.println("Pattern found at index "+(i-k));
				   k = 0;  //for finding multiple occurrences of the pattern
			   }
			   k++;
		   }else {
			   while(k>0 && text.charAt(i)!=pat.charAt(k)) k = slp[k-1];
			   if(text.charAt(i)==pat.charAt(k)) k++;
		   }
	   }
   }
   
   public static int [] generateSLP(String pat) {
	   int arr [] = new int[pat.length()];
	   arr[0] = 0;
	   for(int i=1; i<pat.length(); i++) {
		   int j = arr[i-1];  //assuming match for largest prefix
		   if(pat.charAt(i)==pat.charAt(j)) arr[i] = j++;
		   while(j>0 && pat.charAt(i)!=pat.charAt(j)) j = arr[j-1]; //fall back to lower prefixes		   
	   }
	   return arr;
   }
   
}

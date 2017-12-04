package geeksforgeeks;

public class StringEncode {
     public static String encode(char [] ch) {
    	 if(ch.length <=0) return "";
    	 String ans = ch[0]+ "";
    	 char pre = ch[0];
    	 int count = 1;
    	 for(int i=1; i<ch.length; i++) {
    		 if(ch[i] != pre) {
    			 ans += count;
    			 ans += ch[i];
    			 pre = ch[i];
    			 count = 1;  //reset the counter
    		 }
    		 else count++;   //increment the counter
    	 }
    	 ans += count;
    	 return ans;
     }
     
     public static void main(String args []) {
    	 String inp =  "wwwwaaabcccdd";
    	 String encoded = encode(inp.toCharArray());
    	 System.out.println("Encoded strig is : "+encoded);
     }
}

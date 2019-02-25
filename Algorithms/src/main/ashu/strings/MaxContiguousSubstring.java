package main.ashu.strings;

public class MaxContiguousSubstring {
	   public static void main(String args []) {
		   String s = "aatdfaaayhkjeeiiiip";
		   char ch [] = s.toCharArray();
		    String max="" , curr="";
		    char prev = '\0';
		    for(int i=0; i<ch.length; i++) {
		    	if(prev=='\0' || prev==ch[i]) {  //extend the previous one
		    		prev = ch[i];
		    		curr = curr+ch[i];
		    	} else { //start a new one
		    		curr = Character.toString(ch[i]);
		    		prev = ch[i];
		    	}
		    	if(max.length() < curr.length()) max = curr;
		    }		    
		    System.out.println("Longest continuous substring is : "+max+" length = "+max.length());
		    
		    getMaxContiguousWithoutExtraSpace(s);
	   }
	   
	   public static void getMaxContiguousWithoutExtraSpace(String s) {
		   int n = s.length();
	    	char ch [] = s.toCharArray();
	    	char prev = ch[0];
	    	int ws=0, we=0, maxs=0, maxe=0;   //window start/end points and max start/end point so far
	    	int maxlen = 1;
	    	for(int i=1;i<n; i++) {
	    		if(ch[i] == prev) {
	    			we++;
	    			if(maxlen < (i-ws+1)) {
	    				maxlen = i-ws+1;
	        			maxs = ws; maxe=i;
	        		}
	    		}
	    		else {
	    			ws = i; prev = ch[i];
	    		}
	    		
	    	}
	    	System.out.println("Longest continuous substring is : "+s.substring(maxs, maxe+1)+" length = "+maxlen);
	   }
	}

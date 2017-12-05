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
	   }
	}

package main.ashu.strings;

import java.util.Arrays;

public class ReverseWordsInSentence {
    public static void main(String args []) {
    	String str = "the sky is blue";
    	//remove any contiguous spaces
    	//reverse entire string, then reverse space separated chunks one by one
    	char ch [] = str.toCharArray();
    	int n = str.length();
    	reverse(ch, 0, n-1);
    	int start = 0, i=0;
    	while(i <n) {
    		if(ch[i] == ' ') {
    			reverse(ch, start, i-1);
    			start = i+1;		
    		}
    		i++;
    	}
    	reverse(ch, start, i-1);
    	System.out.println(Arrays.toString(ch));
    }
    
    public static void reverse(char ch[], int i, int j) {
    	while(i<j) {
    		char c = ch[i];
    		 ch[i] = ch[j];
    		 ch[j] = c;
    		i++; j--;
    	}
    }
}

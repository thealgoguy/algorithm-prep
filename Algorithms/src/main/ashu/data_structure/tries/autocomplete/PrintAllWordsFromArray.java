package main.ashu.data_structure.tries.autocomplete;

import java.util.List;

//Microsoft Interview : Given a dictionary and a character array, print all valid words that are possible using characters from the array.
//http://www.geeksforgeeks.org/print-valid-words-possible-using-characters-array/

public class PrintAllWordsFromArray {
    private Trie trie;
    public PrintAllWordsFromArray() {
    	this.trie = new Trie();
    }
    
    public  List<String> findWordsInDict(String [] dict, char [] arr) {
    	for(String s : dict) {
    		trie.insert(s);
    	}
    	return trie.findWordsInDict(dict, arr);
    }
    
    public static void main(String args []) {
    	PrintAllWordsFromArray p = new PrintAllWordsFromArray();
    	String [] dict = {"go", "bat", "me", "eat","goal", "boy", "run"} ;
    	char arr [] = {'e', 'o', 'b', 'a', 'm', 'g', 'l'} ;
    	List<String> words = p.findWordsInDict(dict, arr);
    	System.out.print("Words found in the dictionary are : ");
    	for(String s : words){
    		System.out.print(s+" ");
    	}
    	System.out.println();
    }
}

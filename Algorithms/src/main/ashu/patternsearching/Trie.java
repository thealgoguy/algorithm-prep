package main.ashu.patternsearching;

import java.util.HashMap;
import java.util.Map;

//Trie or Prefix tree or digital tree or radix-tree is a search tree- an ordered data structure
//A common application of a trie is storing a predictive text or autocomplete dictionary, such as found on a mobile telephone

//a trick with designing trie is that we don't explicitly store the character at a node,
//rather we store the characters stored on the children nodes along with their references in a hashmap
//explicitly using a variable is avoided because we can anyway get the character value stored at a given node by looking up map of its parent

public class Trie {

	private class TrieNode {
		Map<Character, TrieNode> children;
		boolean isEndOfWord;
		TrieNode() {
			children = new HashMap<Character, TrieNode>();
			isEndOfWord = false;
		}
		public Map<Character, TrieNode> getChildren() {
			return children;
		}
		public void setChildren(Map<Character, TrieNode> children) {
			this.children = children;
		}
		public boolean isEndOfWord() {
			return isEndOfWord;
		}
		public void setEndOfWord(boolean isEndOfWord) {
			this.isEndOfWord = isEndOfWord;
		}
	}
	
	private TrieNode root;
    Trie() {
    	root = new TrieNode();   	
    }
    
    public void insert(String str) {
    	char c [] = str.toCharArray();
    	TrieNode current = root;
    	for(int i=0; i<c.length; i++) {
    		Map<Character,TrieNode> children = current.getChildren();
    		if(children.get(c[i])==null) {
    			TrieNode node = new TrieNode();
    			children.put(c[i], node);
    		} 
    		current = children.get(c[i]);
    	}
    	current.setEndOfWord(true);;
    }
	
    public boolean search(String word) {
    	char c [] = word.toCharArray();
    	TrieNode current = root;
        int i;
    	for(i=0; i<word.length(); i++) {
    		Map<Character,TrieNode> children = current.getChildren();
    		if(children.get(c[i])==null) break;
    		current = children.get(c[i]);
    	}
    	if(i<word.length()) return false;
    	return current.isEndOfWord();
    }
    
    public void delete(String word) {
    	char c [] = word.toCharArray();
    	TrieNode current = root, parent = root;
    	int i;
    	for(i=0; i<word.length(); i++) {
    		Map<Character,TrieNode> children = current.getChildren();
    		if(children.get(c[i])==null) break;
    		parent = current;
    		current = children.get(c[i]);
    	}
    	if(i<word.length()) {
    		System.out.println("The word '"+word+"' doesn't exist int the trie");
    	} else {
    		if(current.isEndOfWord()) {
    			if(current.getChildren().isEmpty()) {
    				current = null; //delete the last char which marks end of the word and remove itss reference from the parent
    				parent.getChildren().remove(c[c.length-1]);
    			} else {
    				current.setEndOfWord(false); //in case the last node has children, mark that its no more the end of the word by setting the flag
    			}
    		}
    	}
    	//System.out.println("Insertion successful");
    }
    
    public static void main(String args []) {
    	Trie trie = new Trie();
    	trie.insert("this");
    	trie.insert("is");
    	trie.insert("great");
    	trie.insert("actually");
    	trie.insert("indeed");
    	System.out.println("Searching for 'this' after insertion:"+trie.search("this"));
    	System.out.println("Deleting 'this' from trie"); 
    	trie.delete("this");
    	System.out.println("Searching for 'this' after deletion :"+trie.search("this"));
    	
    	System.out.println("Searching for 'is' after insertion :"+trie.search("is"));
    	System.out.println("Deleting 'is' from trie");
    	trie.delete("is");
    	System.out.println("Searching for 'is' after deletion : "+trie.search("is"));
    	
    	System.out.print("Attempting to delete 'this' from trie : ");
    	trie.delete("this");
    	System.out.println("Inserting 'this' after deletion : ");
    	trie.insert("this");
    	System.out.println("Searching for 'this' after inserting again:"+trie.search("this"));
    	
    	
    }
}

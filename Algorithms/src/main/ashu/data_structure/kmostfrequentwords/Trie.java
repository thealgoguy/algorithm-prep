package main.ashu.data_structure.kmostfrequentwords;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
	private Map<Character, TrieNode> children;
	private boolean isEndOfWord;
	private int frequency;
	private int minHeapIndex;

	public TrieNode() {
		this.children = new HashMap<Character, TrieNode>();
		this.isEndOfWord = false;
		this.minHeapIndex = -1;
		this.frequency = 0;
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

	public int getMinHeapIndex() {
		return minHeapIndex;
	}

	public void setMinHeapIndex(int minHeapIndex) {
		this.minHeapIndex = minHeapIndex;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
    
}

public class Trie {
    private TrieNode root;
    public Trie() {
    	this.root = new TrieNode();  
    }
    
    public TrieNode insert(String word) {
    	char ch [] = word.toCharArray();
    	TrieNode curr = this.root;
    	for(int i=0; i<ch.length;  i++) {
    		Map<Character, TrieNode> children = curr.getChildren();
    		if(!children.containsKey(ch[i])) {
    			TrieNode newNode = new TrieNode();
    			children.put(ch[i], newNode);
    		} 
    		curr = curr.getChildren().get(ch[i]);
    	}
    	curr.setEndOfWord(true);
    	curr.setFrequency(curr.getFrequency()+1);
    	//returning the leaf node where the word ends
    	//after insertion/upadteKey in heap, the final index of the key in heap has to be updated in curr
    	return curr;  
    }
    
}

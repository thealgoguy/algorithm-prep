package AutoComplete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TrieNode {
	Map<Character, TrieNode> children;
	List<String> words;
	boolean isEndOfWord=false;
	
	public TrieNode() {
		this.children = new HashMap<Character, TrieNode>();
		this.words = new ArrayList();
	}
	public Map<Character, TrieNode> getChildren() {
		return children;
	}
	public void setChildren(Map<Character, TrieNode> children) {
		this.children = children;
	}
	public List<String> getWords() {
		return words;
	}
	public void setWords(List<String> words) {
		this.words = words;
	}
	public boolean isEndOfWord() {
		return isEndOfWord;
	}
	public void setEndOfWord(boolean isEndOfWord) {
		this.isEndOfWord = isEndOfWord;
	}
}

class CamelCaseTrie{
	TrieNode root;
	public CamelCaseTrie (){
		this.root = new TrieNode();
	}
	public void insert(String key) {
		TrieNode curr = this.root;
	    for(char ch : key.toCharArray()) {
	    	//skip lowercase letters
	    	if(Character.isLowerCase(ch)) continue;
	    	else {
	    		Map<Character, TrieNode> children = curr.getChildren();
		    	if(!children.containsKey(ch)) {
		    		TrieNode newNode = new TrieNode();
		    		children.put(ch, newNode);
		    	}
		    	curr = children.get(ch);
	    	}	    	
	    }
	    curr.getWords().add(key);
	    curr.setEndOfWord(true);
	}
	
	public List<String> getAllWords(String prefix) {
		ArrayList<String> matches = new ArrayList<String>();
		//do dfs on trie starting from root node
		TrieNode startNode = getPrefixNode(prefix);
		if(startNode==null) return null;
		getAllWords(startNode, matches);
		/*Map<Character, TrieNode> children = startNode.getChildren();
    	for(Character key : children.keySet()) {
    		getAllWords(children.get(key), matches);
    	}*/
		return matches;
	}
	public void getAllWords(TrieNode node, List<String> list) {
		if(node == null) return;
    	if(node.isEndOfWord()) {
    		list.addAll(node.getWords());
    	}
    	Map<Character, TrieNode> children = node.getChildren();
    	for(Character key : children.keySet()) {
    		getAllWords(children.get(key), list);
    	}    	
	}
	private TrieNode getPrefixNode(String prefix) {
    	if(prefix==null) return null;
    	TrieNode curr = this.root;
    	for(char c : prefix.toCharArray()) {
    		Map<Character, TrieNode> children = curr.getChildren();
    		if(!children.containsKey(c)) return null;
    		else curr = children.get(c);
    	}
    	return curr;
    }
}

public class CamelCaseAutoComplete {
   public static void main(String args []) {
	   String dict [] = {
		        "Hi", "Higher", "Hello", "HelloWorld", "HiTech", "HiGeek",
		        "HiTechWorld", "HiTechCity", "HiTechLab"
		    };
	   CamelCaseTrie ctrie = new CamelCaseTrie();
	   for(String word : dict) {
		   ctrie.insert(word);
	   }
	   String prefix = "HTWL";
	 //Start giving suggestions as a character is pressed
       for(int i=0; i<prefix.length(); i++) {
    	   String currentPrefix = prefix.substring(0, i+1);
    	   List<String> suggestions = ctrie.getAllWords(currentPrefix);
           System.out.println("Autocomplete suggestions(using trie) for "+currentPrefix+" : ");
           if(suggestions==null || suggestions.isEmpty()) {
        	   System.out.println("No suggestions found");
        	   continue;
           }
           for(String word : suggestions) {
           	System.out.println(word);
           }
           System.out.println();
       }
   }
}

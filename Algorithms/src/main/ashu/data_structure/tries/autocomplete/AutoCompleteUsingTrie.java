package main.ashu.data_structure.tries.autocomplete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

//Implementation - http://sujitpal.blogspot.in/2007/02/three-autocomplete-implementations.html
//theory - http://dhruvbird.blogspot.in/2010/09/very-fast-approach-to-search.html

//Optimizations : radix tree. Better : ternary tree uses best of binary search approach and trie approach for efficiency.
//Even better is ternary search trie(combines the time efficiency of digital tries with the space efficiency of binary search trees.)http://igoro.com/archive/efficient-auto-complete-with-a-ternary-search-tree/
//Best : Suggest tree/treap - TST+bounded heap(http://suggesttree.sourceforge.net/)

public class AutoCompleteUsingTrie {
   private Trie trie;
   
   public AutoCompleteUsingTrie() {
	   this.trie = new Trie();
   }
   
   public void load(String line) {
	   //trie will store entire line as a single word
	   this.trie.insert(line);
   }
   
   public List<String> findCompletions(String prefix) {
	   List<String> completions = trie.getPrefixMatches(prefix);
	   return completions;
   }
   
   public static void main(String args []) throws IOException {
	   AutoCompleteUsingTrie autocomp = new AutoCompleteUsingTrie();
		FileReader fileReader = new FileReader("C:\\Workspace\\Algorithm\\src\\AutoComplete\\InputFile.txt");
       BufferedReader br = new BufferedReader( fileReader );
       String currentLine = "";
       //StringBuffer strBuff = new StringBuffer("");
       while( (currentLine = br.readLine() ) != null )
       {
       	//strBuff.append(currentLine);
           currentLine = currentLine.replace("\\s+", " ");
           //for complete line suggestion, add whole line
          // autocomp.load(currentLine);  
         //for single word suggestion, load word by word
           for(String word : currentLine.split(" ")) autocomp.load(word);
       }
       fileReader.close();
       br.close();
       String prefix ="Basics";
       //Start giving suggestions as a character is pressed
       for(int i=0; i<prefix.length(); i++) {
    	   String currentPrefix = prefix.substring(0, i+1);
       	List<String> suggestions = autocomp.findCompletions(currentPrefix);
           System.out.println("Autocomplete suggestions(using TreeMap) for "+currentPrefix+" : ");
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

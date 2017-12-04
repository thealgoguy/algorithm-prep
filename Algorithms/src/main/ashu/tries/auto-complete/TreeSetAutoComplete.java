package AutoComplete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

//Implementation - http://sujitpal.blogspot.in/2007/02/three-autocomplete-implementations.html
//theory - http://dhruvbird.blogspot.in/2010/09/very-fast-approach-to-search.html
//TreeSet is like hashSet, except that it has keys ordered using their natural ordering, or by a Comparator typically provided at sorted set creation time.
//The set's iterator will traverse the set in ascending element order.

//If we dont't use treeset, we will sort keys in lexicographical order, then binary search to find first occurrence and last occurrence of the prefix and then take k matched keys.
//If their is any priority/weight with keys then sort the obtained list and return k top suggestions. 

//This is an online algo meaning suggestions to be shown after every character in the prefix entered by user

public class TreeSetAutoComplete {
	private TreeSet<String> lines;
	
	public TreeSetAutoComplete() {
		this.lines = new TreeSet<String>();
	}
    
	//if we add lines(collection of words) as key in treemap, it will suggest whole line. For suggestion single word, add words as keys.
	public void load(String line) {
		lines.add(line);
	}
	public boolean matchPrefix(String prefix) { 
		Set<String> tailSet = lines.tailSet(prefix);
		for (String tail : tailSet) {
			if (tail.startsWith(prefix)) 
				return true;
		}
		return false;
	}

	public List<String> findCompletions(String prefix) {
		List<String> completions = new ArrayList<String>();
		Set<String> tailSet = lines.tailSet(prefix);
		for (String tail : tailSet) {
			if (tail.startsWith(prefix)) {
				completions.add(tail);
			} else {
				break;
			}
		}
		return completions;
	}
	
	public static void main(String args []) throws IOException {
		TreeSetAutoComplete autocomp = new TreeSetAutoComplete();
		FileReader fileReader = new FileReader("C:\\Workspace\\Algorithm\\src\\AutoComplete\\InputFile.txt");
        BufferedReader br = new BufferedReader( fileReader );
        String currentLine = "";
        //StringBuffer strBuff = new StringBuffer("");
        while( (currentLine = br.readLine() ) != null )
        {
        	//strBuff.append(currentLine);
            currentLine = currentLine.replace("\\s+", " ");
            //for complete line suggestion, add whole line
            autocomp.load(currentLine);
            //for single word suggestion, load word by word
            //for(String word : currentLine.split(" ")) autocomp.load(word);
        }
        fileReader.close();
        br.close();
        String prefix ="Pressed";
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

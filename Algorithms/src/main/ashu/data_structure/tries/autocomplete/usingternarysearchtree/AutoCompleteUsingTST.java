package main.ashu.data_structure.tries.autocomplete.usingternarysearchtree;

import java.util.List;

public class AutoCompleteUsingTST {
    public static void main(String args []) {
    	String str [] = {"hello", "hi","hit",  "high", "him", "what", "why", "all", "call", "cat", "lost"};
    	String key = "lost";
    	TernarySearchTree tst = new TernarySearchTree();
    	for(String s : str) {
    		tst.addKey(s);
    	}
    	System.out.println("Generated TST in list form is : "+tst);
    	System.out.println("Search key is : "+key);
        boolean exists = tst.searchKey(key);
        if(exists) System.out.println("Key exists in the TST");
        else System.out.println("Key doesn't exist in the TST");
        System.out.println("Deleting the key from the TST");
        tst.delete(key);
        System.out.println("TST after deletion of the key : "+tst);
        System.out.println("Adding the key back in TST");
        tst.addKey(key);
        System.out.println("After adding key again : "+tst);
        String prefix = "hi";
        List<String> autoCompletes = tst.getAutoCompletes(prefix);
        System.out.print("Autocomplets for "+prefix+" are : "+ autoCompletes);
    }
}

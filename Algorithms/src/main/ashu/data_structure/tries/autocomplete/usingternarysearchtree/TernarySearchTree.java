package main.ashu.data_structure.tries.autocomplete.usingternarysearchtree;

import java.util.ArrayList;
import java.util.List;

//http://www.sanfoundry.com/java-program-ternary-search-tree/

public class TernarySearchTree {
   private TSTNode root;
   private ArrayList<String> al; //for printing the tree
   public TernarySearchTree() {
	   
   }
   public void addKey(String key) {
	   if(key==null || key.isEmpty()) return;
	   root = addKeyUtil(root, key, 0);
	  // addKeyUtilIterative(root, key);
   }
   
   //recursive version of insertion...one char at a time
   public TSTNode addKeyUtil(TSTNode currentNode, String key, int index) {
	   if(key==null || key.isEmpty()) return currentNode;
	   if(currentNode==null) {
		   currentNode = new TSTNode(key.charAt(index), false);
	   }
	   if(key.charAt(index) < currentNode.getCharacter()) 
		   currentNode.left = (addKeyUtil(currentNode.getLeft(), key, index));
	   else if(key.charAt(index) > currentNode.getCharacter())
		   currentNode.right = (addKeyUtil(currentNode.getRight(), key, index));
	   else {
		   index++;
		   if(key.length() == index) currentNode.setEndOfWord(true);
		   else currentNode.middle = (addKeyUtil(currentNode.getMidddle(), key, index));
	   }	   
	   return currentNode;
   }
   public boolean searchKey(String key) {
		if(key==null || key.isEmpty()) return false;
		return searchKeyUtil(key, root, 0);	   
	   }
  //recursive search in tst
  public boolean searchKeyUtil(String key, TSTNode currentNode, int index) {
	   if(key==null || key.isEmpty() || currentNode==null) return false;
	   if(key.charAt(index) < currentNode.getCharacter())
		   return searchKeyUtil(key, currentNode.getLeft(), index);
	   else if(key.charAt(index) > currentNode.getCharacter())
		   return searchKeyUtil(key, currentNode.getRight(), index);
	   else {
		   index++;
		   if(index == key.length()) return currentNode.isEndOfWord();
		   return searchKeyUtil(key, currentNode.getMidddle(), index);
	   }
  }
  //delete a word from tst
  public void delete(String key) {
	  deleteUtil(root, key.toCharArray(), 0);
  }
  public void deleteUtil(TSTNode node, char ch [], int index) {
	  if(node==null || ch==null) return;
	  if(ch[index] < node.character)
		  deleteUtil(node.left, ch, index);
	  else if(ch[index] > node.character) 
		  deleteUtil(node.right, ch, index);
	  else {
		  if(ch.length == index+1){
			 node.setEndOfWord(false);
		  }
		  else deleteUtil(node.middle, ch, index+1); 
	  }
  }
   public List<String> getAutoCompletes(String pattern) {
	   List<String> prefixes = new ArrayList<String>();
	   searchAllPrefixes(pattern, prefixes);
	   return prefixes;
   }
   
   public void searchAllPrefixes(String pattern, List<String> list) {
	   if(pattern==null ||  pattern.isEmpty() || root==null) return;
	  TSTNode temp = rootNodeForPrefixSearch(pattern);
	  getAllWords(temp.left, list, pattern);
	  getAllWords(temp.middle, list, pattern);
	  getAllWords(temp.right, list, pattern);
   }
   
   public void getAllWords(TSTNode node, List<String> list, String prefix) {
	   if(node == null) return;
	   if(node.isEndOfWord()) {
		   list.add(prefix+node.getCharacter());
	   }
	   getAllWords(node.getLeft(), list, prefix);
	   getAllWords(node.getRight(), list, prefix);
	   getAllWords(node.getMidddle(), list, prefix+node.getCharacter()); 
   }
   
   public TSTNode rootNodeForPrefixSearch(String pattern) {
	   TSTNode temp = root;
	   int index = 0;
	   while(temp != null) {
		   if(pattern.charAt(index) < temp.getCharacter())
		      temp = temp.getLeft();
		   else if(pattern.charAt(index) > temp.getCharacter())
			   temp = temp.getRight();
		   else {
			  index++;
			  if(index == pattern.length()) break;
			  temp = temp.getMidddle();
		   }
	   }
	   return temp;
   }
   
   public String toString()
   {
       al = new ArrayList<String>();
       traverse(root, "");
       return "Ternary Search Tree : "+ al;
   }
   /** function to traverse tree **/
   private void traverse(TSTNode r, String str)
   {
       if (r != null)
       {
           traverse(r.left, str);

           str = str + r.character;
           if (r.isEndOfWord)
               al.add(str);

           traverse(r.middle, str);
           str = str.substring(0, str.length() - 1);

           traverse(r.right, str);
       }
   }
}

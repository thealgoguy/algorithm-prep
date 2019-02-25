package main.ashu.arrays;

//I'm not sure if kadane's approach would work here
public class MaxXorSubarrayInArray {
	private static final Integer INT_SIZE = 32;
   public static void main(String args []) {
	   int a [] = {8, 1, 2, 12, 7, 6};
	   int n = a.length;
	   int maxXor = findMaxXor(a);
	   System.out.println("Subarray with max xor = "+maxXor);
   }
   
   private static class TrieNode {
	   //for storing values at leaf nodes(32nd bit node)
	   int value; 
	   //for storing 0 or 1
	   TrieNode [] children = new TrieNode[2];
	   public TrieNode() { 
           value = 0; 
           children[0] = null; 
           children[1] = null; 
       } 
   }
   
   private static int findMaxXor(int a[]) {
	   int n = a.length;
	   TrieNode root = new TrieNode();
	   insert(root, 0);
	   int max = Integer.MIN_VALUE;
	   int prefixXor = 0;
	   for(int i=0; i<n; i++) {
		   prefixXor = prefixXor ^ a[i];
		   insert(root, prefixXor);
		   //find max xor ending with ith element
		   //which is xor of current prefix with min xor of previous xor prefixes
		   max = Math.max(max, findMaxXorPairForPrefix(root, prefixXor));
	   }
	   return max;
   }
   
   //find max of all xor pairs of which the current prefix is a part
   //since bits in trie are stored from msb to lsb, we'd try to 
   //traverse on opposite bits for maximizing the resultant xor value
   private static int findMaxXorPairForPrefix(TrieNode root, int prefix) {
	   TrieNode temp = root;
	   for(int i=INT_SIZE-1; i>=0; i--) {
		   int val = (prefix & (1 << i)) >=1  ? 1 : 0;
		   if(temp.children[1-val] != null) {
			   temp = temp.children[1-val];
		   }else if(temp.children[val] != null) {
			   temp = temp.children[val];
		   }
	   }
	   //this is the max of all the prefixes previously inserted
	   return temp.value ^ prefix;
   }
   
   private static void insert(TrieNode root, int prefix) {
	   TrieNode temp = root;
	   //insert xor prefix into trie from msb to lsb
	   for(int i=INT_SIZE-1; i>=0; i--) {
		   //find the current set bit
		   int val = (prefix & (1 << i)) >=1 ? 1 : 0;
		   if(temp.children[val] == null) {
			   temp.children[val] = new TrieNode();
		   }
		   temp = temp.children[val];
	   }
	   //after storing all bits, store the actual integer prefix at the leaf node
	   temp.value = prefix;
   }
}

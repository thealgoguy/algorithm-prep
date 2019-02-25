package main.ashu.graphs;

import java.util.LinkedList;
import java.util.Queue;

//A BFS application
//Given a dictionary, and two words ‘start’ and ‘target’ (both of same length). 
//Find length of the smallest chain from ‘start’ to ‘target’ if it exists, such that adjacent words in the chain only differ by one character and 
//each word in the chain is a valid word i.e., it exists in the dictionary.
//It may be assumed that the ‘target’ word exists in dictionary and length of all dictionary words is same.
//http://www.geeksforgeeks.org/word-ladder-length-of-shortest-chain-to-reach-a-target-word/

public class WordLadder {
   static String dict [] = {"poon", "plee", "same", "poie", "plie", "poin", "plea"};
   
   //node for bfs
   static class Node {
	   String word;
	   int len;
	   public Node(String word, int len) {
		   this.word = word;
		   this.len = len;
	   }
   }
   
   public static void main(String args []) {
	   String start = "toon";
	   String target = "plea";
	   int shortestPath = traverse_bfs(start, target);
	   System.out.println("Minimum path length to reach the target word = "+shortestPath);
   }
   
   public static boolean adjacent(String s1, String s2) {
	   int diff = 0;
	   for(int i=0; i<s1.length(); i++) {
		   if(s1.charAt(i) != s2.charAt(i))  diff += 1;
		   if(diff >1) return false;
	   }
	   return diff==1;
   }
   
   public static int traverse_bfs(String start, String end) {
	   if(start == end) return 0;
	   Node curr = new Node(start, 1);
	   boolean found = false;
	   int steps = -1;
	   Queue<Node> q = new LinkedList<Node>();
	   boolean visited [] = new boolean[dict.length];
	   q.offer(curr);
	   while(!q.isEmpty()) {
		   Node next = q.poll();
		   //move all adjacent words
		   for(int i=0; i<dict.length; i++) {
			   if(adjacent(next.word, dict[i]) && !visited[i] && dict[i] != start) {
				   Node adj = new Node(dict[i], next.len+1);
				   visited[i] = true;
				   if(dict[i] == end) {
					   found = true;
					   return adj.len;
				   }
				   q.offer(adj);
			   }
		   }
	   }
	   return steps;
	   
   }
}

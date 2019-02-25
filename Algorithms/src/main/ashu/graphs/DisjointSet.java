package main.ashu.graphs;

import java.util.HashMap;
import java.util.Map;
//https://github.com/mission-peace/interview/blob/master/src/com/interview/graph/DisjointSet.java

public class DisjointSet {
	//define node structure for rooted tree in a disjoint set
  private class Node {
	  private long data;
	  Node parent;
	  private long rank;
  }
  
  Map<Long, Node> map = new HashMap();  //map to store all the nodes and their references in the disjoint set
  
  //initialize various disjoint sets, each with singular element and pointing to itself
  public void makeSet(long x) {
	  Node node = new Node();
	  node.data = x;
	  node.parent = node;
	  node.rank =0;
	  map.put(x, node);
  }
  
  //return the root node of the rooted tree/set x belongs to
  public Node find(long x) {
	  return findSet(map.get(x));
  }
  //uses Path Compression
  //find root of the tree by traversing up. While returning back form root to the previous nodes, make them point to root node directly.
  public Node findSet(Node node) {
	  if(node.parent == node) return node.parent;
	  node.parent = findSet(node.parent); //path compression
	  return node.parent;
  }
  //unites two different sets and makes them into one
  public void union(long x, long y) {
	  Node xRoot = find(x);
	  Node yRoot = find(y);
	   // if x and y are already in the same set (i.e., have the same root or representative)
     if (xRoot == yRoot)
         return;
     // x and y are not in same set, so we merge them
     if (xRoot.rank < yRoot.rank)
         xRoot.parent = yRoot;
     else if(xRoot.rank > yRoot.rank)
         yRoot.parent = xRoot;
     else
         yRoot.parent = xRoot;
         xRoot.rank = xRoot.rank + 1;
  }
  public static void main(String args[]) {
      DisjointSet ds = new DisjointSet();
      ds.makeSet(1);
      ds.makeSet(2);
      ds.makeSet(3);
      ds.makeSet(4);
      ds.makeSet(5);
      ds.makeSet(6);
      ds.makeSet(7);

      ds.union(1, 2);
      ds.union(2, 3);
      ds.union(4, 5);
      ds.union(6, 7);
      ds.union(5, 6);
      ds.union(3, 7);

      System.out.println(ds.find(1));
      System.out.println(ds.find(2));
      System.out.println(ds.find(3));
      System.out.println(ds.find(4));
      System.out.println(ds.find(5));
      System.out.println(ds.find(6));
      System.out.println(ds.find(7));
  }
}

package main.ashu.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//http://bgmeow.xyz/2016/09/27/Zombie-Cluster/
public class ZombieCluster {
	
	public static void main(String args []) {
		String str [] = {"1100",
				         "1110",
				         "0110",
				         "0001"};
		int clusters = zombieCluster(str);
	    System.out.println("number of zombie clusters = "+clusters);	
	    clusters = count(str);
	    System.out.println("number of zombie clusters = "+clusters);
	}
    
	private static int zombieCluster(String[] zombies) {
		if (zombies == null || zombies.length < 2) return 0;
		int n = zombies.length;
		List<Integer> adj [] = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
	        for (int j = 0 ; j < n; j++) {
	            int x = Integer.parseInt(zombies[i].charAt(j) + "");
	            if(x == 1) {
	            	adj[i].add(j);
	            }
	        }
	    }
		
		int count = 0;
		boolean visited[] = new boolean[n];
	    for(int i=0; i<n; i++) {
	    	for(int j=0; j<n; j++) {
	    		if(!visited[i]) {
	    			dfs(adj, i, visited);
	    			count++;
	    		}
	    	}
	    }
       return count;
	}
	
	public static void dfs(List<Integer> adj [], int source, boolean visited[]) {
		if (visited[source])
			return;
		visited[source] = true;
		for (int i : adj[source]) {
			if (!visited[i]) {
				dfs(adj, i, visited);
			}
		}
	}
	
	//disjoint set approach
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
	  
	  //wrong
	  private static int count(String arr []) {
		  if (arr == null || arr.length < 2) return 0;
		  ZombieCluster ds = new ZombieCluster();
			int n = arr.length;
			for (int i = 0; i < n; i++) {
				ds.makeSet(i);
			}
			for (int i = 0; i < n; i++) {
		        for (int j = 0 ; j < n; j++) {
		            int x = Integer.parseInt(arr[i].charAt(j) + "");
		            if(x == 1) {
		            	ds.union(i, j);
		            }
		        }
		    }
			int count=0;
			for (int i = 0; i < n; i++) {
				Node node = ds.find(i);
				if(node.parent == node) count++;
			}
			return count;
	  }
}

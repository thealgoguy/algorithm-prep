package main.ashu.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
//can be used to find shortest length path(not minimum weight path) between any pair of nodes in a graph

class Graph1 {
	private int V;
	private ArrayList<Integer> adj [];
	
	Graph1(int n) {
		this.V = n;
		adj = new ArrayList[n];
		for(int i=0; i<n; i++) {
			adj[i] = new ArrayList<Integer>();
		}
	}
	
	public void addEdge(int a, int b) {
		adj[a].add(b);
		adj[b].add(a);
	}
	
	public void BFSIterative(int source, boolean visited []) {
		if(visited[source]) return;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(source);
		while(!q.isEmpty()) {
			int next = q.remove();
			    //visit current level
				visited[next] = true;
				System.out.print(next+" ");
				//visit all nodes of the next level and put them in the queue
				//those already marked visited are of the previous leves, so ignore them
				for(int i : adj[next]) {
					if(!visited[i]) {
						visited[i] = true;
						q.add(i);
					}
				}
		}
	}
}

public class BFS {
  public static void main(String args []) {
	  Graph1 g = new Graph1(4);
	    g.addEdge(0, 1);
	    g.addEdge(0, 2);
	    g.addEdge(1, 2);
	    g.addEdge(2, 0);
	    g.addEdge(2, 3);
	    g.addEdge(3, 3);
	    
	    boolean visited[] = new boolean[4];	    
	    Arrays.fill(visited, false);
	    System.out.print("Breadth First Traversal Iteratively : ");
	    for(int i=0; i<3; i++) g.BFSIterative(i, visited);

  }
}

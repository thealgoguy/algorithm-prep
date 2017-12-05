package main.ashu.graphs;

import java.util.ArrayList;
import java.util.Stack;

//topological sort is possible only for DAG
//its ordering of vertices in a DAG such that for every directed edge (u,v)
//vertex u comes before vertex v. More than one such ordering is possible.
//Approach : Do depth first traversal while marking  vertex visited only when
//all its adjacent vertices and their adjacent vertices and so on are visited(fully explored)

class Graph2 {
	private int V;
	private ArrayList<Integer> adj [];

	Graph2(int n) {
		this.V = n;
		adj = new ArrayList[n];
		for(int i=0; i<n; i++) {
			adj[i] = new ArrayList<Integer>();
		}
	}

	public void addEdge(int a, int b) {
		adj[a].add(b);
		//adj[b].add(a);  since graph is directed
	}

	public void topologicalSortUtil(int source, boolean visited[], Stack<Integer> st) {
		if(visited[source]) return;
		//st.push(source);
		for(int i : adj[source]) if(!visited[i]) topologicalSortUtil(i, visited, st);
		
		//now source has been fully explored, push it on the stack
		visited[source] = true;
		st.push(source);
	}

	public void topologicalSort() {
		Stack<Integer> st = new Stack<Integer>();
		boolean visited [] = new boolean[V];
		for(int i=0; i<V; i++) {
			if(!visited[i]) {
				topologicalSortUtil(i,visited,st);
			}
		}
		while(!st.isEmpty()) {
			System.out.print(st.pop()+" ");
		}
	}

}
public class TopologicalSort {

	public static void main(String args []) {
		Graph2 g = new Graph2(6);
		g.addEdge(5, 2);
		g.addEdge(5, 0);
		g.addEdge(4, 0);
		g.addEdge(4, 1);
		g.addEdge(2, 3);
		g.addEdge(3, 1);	   
		System.out.print("Topological sorting of DAG is : ");
		g.topologicalSort();
	}
}

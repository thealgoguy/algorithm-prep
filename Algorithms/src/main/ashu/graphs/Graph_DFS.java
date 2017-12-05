package main.ashu.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Graph_DFS {
	private static class Graph {
		private int V;
		private ArrayList<Integer> adj[];
		Graph(int n) {
			this.V = n;
			adj = new ArrayList[n];
			for (int i = 0; i < n; i++) {
				adj[i] = new ArrayList<Integer>();
			}
		}

		public void addEdge(int a, int b) {
			adj[a].add(b);
			adj[b].add(a);
		}

		public void DFSRecursive(int source, boolean visited[]) {
			if (visited[source])
				return;
			visited[source] = true;
			System.out.print(source + " ");
			for (int i : adj[source]) {
				if (!visited[i]) {
					DFSRecursive(i, visited);
				}
			}
		}

		public void DFSIterative(int source, boolean visited[]) {
			if (visited[source])
				return;
			Stack<Integer> st = new Stack<Integer>();
			st.push(source);
			while (!st.isEmpty()) {
				int next = st.pop();
				if (!visited[next]) {
					visited[next] = true;
					System.out.print(next + " ");
				}
				for (int i : adj[next]) {
					if (!visited[i]) {
						st.push(i);
					}
				}
			}
		}
	}

	public static void main(String args[]) {
		Graph g = new Graph(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);

		boolean visited[] = new boolean[4];

		System.out.print("Depth First Traversal Recursively : ");
		for (int i = 0; i < 3; i++) {
			if (!visited[i])
				g.DFSRecursive(i, visited);
		}

		Arrays.fill(visited, false);
		System.out.print("\nDepth First Traversal Iteratively : ");
		for (int i = 0; i < 3; i++) {
			if (!visited[i])
				g.DFSIterative(i, visited);
		}
	}
}

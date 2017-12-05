package main.ashu.graphs;

import java.util.*;

//It's a DP approach for single source shortest paths
//detects and reports -ve cycles
//relax all edges |V| times. 
//do one more pass of relaxation, if any edge is still relaxable, there is -ve weight cycle
//else print the shortest simple path

class Graph4 {
	private int V;
	Map<Integer, Integer> adj [];
	boolean visited [];
	int p [];
	int d [];
	
	public Graph4(int n) {
		this.V = n;
		adj = new HashMap[n];
		for(int i=0; i<n; i++) adj[i] = new HashMap<Integer, Integer>();
		p = new int[n];
		d = new int[n];
		visited = new boolean[n];
		Arrays.fill(visited, false);
		Arrays.fill(p, -1);
		Arrays.fill(d, Integer.MAX_VALUE);
	}
	
	public void addEdge(int a, int b, int w) {
		adj[a].put(b, w);
	}
	
	public void relaxEdge(int u, int v, int w) {
		//for(Integer key : adj[u].keySet()) {
			if(d[v] > d[u] + w) {
				d[v] = d[u] + w;
				p[v] = u;
			}
		//}	
	}
	
	public void findShortestPath(int source) {
		d[source] = 0;
		for(int i=0; i<V; i++) {
			//iterate over all edges and relax
			for(int j=0; j<V; j++) {
				for(Integer key : adj[j].keySet()) relaxEdge(j, key, adj[j].get(key));
			}
		}
		//do one more pass for -ve cycle check
		for(int i=0; i<V; i++) {
			//iterate over all edges and relax
			for(int j=0; j<V; j++) {
				for(Integer key : adj[j].keySet()) {
					if(d[key] > d[j] + adj[j].get(key)) {
						System.out.println("Negative cycle in the graph");
						break;
					}
				}
			}
		}
		//if no -ve cycle exists, print the shortest simple paths
		for(int i=0; i<d.length; i++) {
			System.out.println("Shortest path from "+source+" to vertex "+i+" = "+d[i]); 
		}
	}
}

class Vertex1 implements Comparator<Vertex1> {
	int name;
	int dval;  //current min distance from source
	public Vertex1() {
		
	}
	public Vertex1(int name, int dval) {
		this.name = name;
		this.dval = dval;
	}
    public int getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	public int getDval() {
		return dval;
	}
	public void setDval(int dval) {
		this.dval = dval;
	}
	public int compare(Vertex1 a, Vertex1 b) {
    	return (a.dval - b.dval);
    }
}

public class BellmonFordShortestPath {
   public static void main(String arghs []) {
	   Graph4 g = new Graph4(7);
	    g.addEdge(0, 1, 2);
	    g.addEdge(0, 2, 1);
	    g.addEdge(1, 2, 3);
	    g.addEdge(2, 3, 5);
	    g.addEdge(2, 6, 12);
	    g.addEdge(3, 6, 6);
	    g.addEdge(3, 5, 6);
	    
	    g.findShortestPath(0);
   }
}

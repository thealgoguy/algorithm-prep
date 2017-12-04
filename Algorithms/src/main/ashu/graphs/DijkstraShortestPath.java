package graphs;

import java.util.*;

//Limitations of algo :
//1. will not terminate for graph having -ve weight cycle(s)
//shortest path follows optimal substructure
//best implemented using fibonacci heap O(|E| + |V| lg |V|)
//Dijkstra's is DFS with greedy approach

class Graph3 {

	private int V;
	//private ArrayList<Integer> adj [];
	Map<Integer, Integer> adj[]; //for O(1) lookup of edge weight, index of array is start vertex of edge
	PriorityQueue<Vertex> minHeap;
    int d [];
    int p[];
    boolean processed [];
	Graph3(int n) {
		this.V = n;
		//adj = new ArrayList[n];
		adj = new Map[n];
		for(int i=0; i<n; i++) {
			//adj[i] = new ArrayList<Integer>();
			adj[i] = new HashMap<Integer, Integer>();
		}
		//weight = new HashMap<String, Integer> ();
		minHeap = new PriorityQueue<Vertex>(n, new Vertex());
		processed  = new boolean[n];
		Arrays.fill(processed, false);
		p = new int[n];
		Arrays.fill(p, -1);
		d = new int[n];
		Arrays.fill(d,Integer.MAX_VALUE);
	}

	public void addEdge(int a, int b, int w) {
		adj[a].put(b, w);
	}
	
	public void calculateShortest(int source) {
		d[source] = 0;	
		minHeap.offer(new Vertex(source,d[source]));
		//for(int i=0; i<V; i++) minHeap.offer(new Vertex(i,d[i]));
	
		while(!minHeap.isEmpty()) {
			Vertex u = minHeap.poll();
			if(!processed[u.getName()]) {
				relaxEdges(u); //relax all outgoing edges of u
				processed[u.getName()] = true;
			}
			
		}
		for(int i=0; i<d.length; i++) {
			System.out.println("Shortest path from "+source+" to vertex "+i+" = "+d[i]); 
		}
	}
		
   public void relaxEdges(Vertex u) {	
	   Map<Integer, Integer> neighb = adj[u.getName()]; 
	   Set<Integer> keys = neighb.keySet();
	   for(int i : keys) {
		   if(!processed[i]) {
			   int alt = d[u.getName()] + neighb.get(i);
			   if(d[i] > alt) {
				   d[i] =  alt;
				   Vertex temp = new Vertex(i,d[i]);
				   minHeap.offer(temp);
				   p[i] = u.getName();
			   }
		   }
	   }
   }   
}

//to be used for binding every vertex with dval for use in PQ
class Vertex implements Comparator<Vertex> {
	int name;
	int dval;  //current min distance from source
	public Vertex() {
		
	}
	public Vertex(int name, int dval) {
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
	public int compare(Vertex a, Vertex b) {
    	return (a.dval - b.dval);
    }
}

public class DijkstraShortestPath {
	
  public static void main(String args []) {
	  Graph3 g = new Graph3(7);
	    g.addEdge(0, 1, 2);
	    g.addEdge(0, 2, 1);
	    g.addEdge(1, 2, 3);
	    g.addEdge(2, 3, 5);
	    g.addEdge(2, 6, 12);
	    g.addEdge(3, 6, 6);
	    g.addEdge(3, 5, 6);
	    
	    g.calculateShortest(0);
  }
}

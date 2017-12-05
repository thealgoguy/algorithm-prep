package main.ashu.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Find MST in a weighted graph
//Steps :
//1. Sort edges in non-decreasing order
//2. Make |V| disjoint sets
//3. Start processing edges one by one ignoring edges with both ends in the same disjoint set, 
//otherwise adding the edge in answer set and then doing union of the both ends of the edges
public class KruskalMST {
  
	public class EdgeComparator implements Comparator<Edge<Integer>>{
		public int compare(Edge<Integer> edge1, Edge<Integer> edge2) {
			return (edge1.getWeight() <= edge2.getWeight()) ? -1 : 1;
		}
	}
   
   public List<Edge<Integer>> getMST(Graph<Integer> graph) {
	   DisjointSet ds = new DisjointSet();
	   List<Edge<Integer>> edges = graph.getAllEdges();
	   EdgeComparator comparator = new EdgeComparator();
	   Collections.sort(edges, comparator);
	   //make disjoint sets of vertices
	   for(Vertex<Integer> vertex : graph.getAllVertex()) ds.makeSet(vertex.getId());
	   List<Edge<Integer>> ans = new ArrayList<Edge<Integer>>();
	   for(Edge<Integer> edge : edges) {
		   Vertex<Integer> v1 = edge.getVertex1();
		   Vertex<Integer> v2 = edge.getVertex2();
		   //include only if in different sets else ignore
		   if(ds.find(v1.getId()) != ds.find(v2.getId())) {
			   ans.add(edge);
			   ds.union(v1.getId(), v2.getId());
		   } 
	   }
	    return ans;
   }
   
   
   public static void main(String args []) {
	   boolean isDirected = false;
	   Graph<Integer> graph = new Graph<Integer>(isDirected);
	   graph.addEdge(1, 2, 4);
       graph.addEdge(1, 3, 1);
       graph.addEdge(2, 5, 1);
       graph.addEdge(2, 6, 3);
       graph.addEdge(2, 4, 2);
       graph.addEdge(6, 5, 2);
       graph.addEdge(6, 4, 3);
       graph.addEdge(4, 7, 2);
       graph.addEdge(3, 4, 5);
       graph.addEdge(3, 7, 8);
       KruskalMST kmst = new KruskalMST();
       List<Edge<Integer>> mstEdges = kmst.getMST(graph);
       for(Edge<Integer> edge : mstEdges) {
    	   System.out.println(edge.getVertex1() + " " + edge.getVertex2());
       }
   }
   
}

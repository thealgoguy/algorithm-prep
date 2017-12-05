package main.ashu.graphs;

// Given a binary maze, find the minimum no of steps to reach the destination form  a source.
//Use BFS search on the maze
import java.util.LinkedList;
import java.util.Queue;

class Cell {
	int x, y, distance;
	Cell parent;
	Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
class Maze {
	int arr [][];
	int N;
	Maze(int arr[][]) {
		this.arr = arr;
	}
	public boolean isSafe(int x, int y) {
		return x>=0 && x<=arr.length-1 &&y>=0 && y<=arr[0].length-1 && arr[x][y]==1;
	}
	public int findShortestPath(Cell start, Cell end) {   //BFS in maze
		boolean visited [][] = new boolean[arr.length][arr[0].length];
		Queue<Cell> q = new LinkedList<Cell>();
		q.add(start);
		start.distance = 0;   //distance from source cell
		start.parent = null;  //needed for retracing the shortest path
		int ans=-1;
		//a cell is marked discovered when all its neighbors are enqueued
		//a cell can be partially discovered(in the queue or grey),not discovered at all(white) 
		//or fully discovered(touched, its neighbors enqueued and itself dequeued)
		while(!q.isEmpty()) {
			Cell current = q.poll();			
			if(current.x==end.x && current.y==end.y){
				ans = current.distance;  
				end.parent = current;
				break;
			}
			visited[current.x][current.y] = true;
			//discovering neighbors in the order right,left,down,up
			if(isSafe(current.x,current.y+1)) {
				if(!visited[current.x][current.y+1]) {
					Cell neighb = new Cell(current.x,current.y+1);
					neighb.distance = current.distance+1;
					neighb.parent = current;
					q.add(neighb);
				}
			}
			if(isSafe(current.x,current.y-1)) {
				if(!visited[current.x][current.y-1]) {
					Cell neighb = new Cell(current.x,current.y-1);
					neighb.distance = current.distance+1;
					neighb.parent = current;
					q.add(neighb);
				}
			}
			if(isSafe(current.x+1,current.y)) {
				if(!visited[current.x+1][current.y]) {
					Cell neighb = new Cell(current.x+1,current.y);
					neighb.distance = current.distance+1;
					neighb.parent = current;
					q.add(neighb);
				}		    	
			}
			if(isSafe(current.x-1,current.y)) {
				if(!visited[current.x-1][current.y]) {
					Cell neighb = new Cell(current.x-1,current.y);
					neighb.distance = current.distance+1;
					neighb.parent = current;
					q.add(neighb);	
				}		    	
			}
		}
		return ans;
	}
	public void printShortestPath(Cell end) {
		if(end.parent ==null) {
			System.out.print("("+end.x+","+end.y+"),");
			return;
		}
		printShortestPath(end.parent);
		System.out.print("("+end.x+","+end.y+"),");
	}
}

public class ShortestPathMaze {
   public static void main(String args []) {	  
	   int net [][] =  {{1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                       {1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
                       {1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
                       {0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
                       {1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
                       {1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
                       {1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                       {1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                       {1, 1, 0, 0, 0, 0, 1, 0, 0, 1 }};
	   Maze maze = new Maze(net);
	   Cell source = new Cell(0,0);
	   Cell destination = new Cell(3,4);
	   int ans = maze.findShortestPath(source, destination);
	   if(ans <0)
	   System.out.println("Destination is unreachable");
	   else { System.out.println("Shortest path length = "+ans);
	   System.out.println("Shortest path is : ");
	   maze.printShortestPath(destination);
	   }
   }
}

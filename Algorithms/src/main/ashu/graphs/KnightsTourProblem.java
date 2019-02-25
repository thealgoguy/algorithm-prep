package main.ashu.graphs;

import java.util.LinkedList;
import java.util.Queue;

//Given a square chessboard of N x N size, the position of Knight and position of a target is given, 
//the task is to find out the minimum steps a Knight will take to reach the target position.
//How to do using DP - https://www.geeksforgeeks.org/minimum-steps-reach-target-knight-set-2/
class KnightsTourProblem {
	public static void main(String args[]) {
		int n = 30;
		boolean visited[][] = new boolean[n+1][n+1];
		int x = 1, y = 1, tx = 30, ty = 30;
		int min = -1;
		// Make a BFS walk form the starting spot
		Queue<Square> queue = new LinkedList<Square>();
		queue.offer(new Square(x, y, 0));
		while(!queue.isEmpty()) {
			Square current = queue.poll();
			int cx = current.x;
			int cy = current.y;
			visited[cx][cy] = true;
			System.out.println("Visiting "+cx+", "+cy);
			if(cx == tx && cy == ty) {
				min = current.dist;
				break;
			}
			// x and y direction, where a knight can move
		    int dx[] = {-2, -1, 1, 2, -2, -1, 1, 2};
		    int dy[] = {-1, -2, -2, -1, 1, 2, 2, 1};
		    for(int i=0; i<8; i++) {
		    	if(isSafe(cx+dx[i], cy+dy[i], n) && !visited[cx+dx[i]][cy+dy[i]]) {
		    		queue.offer(new Square(cx+dx[i], cy+dy[i], current.dist+1));
		    		visited[cx+dx[i]][cy+dy[i]] = true;
		    	}
		    }    
		}
		if(min == -1) System.out.println("Knight can't reach the target");
		else System.out.println("Minimum steps taken by knight to reach the target = "+min);
	}
	
	public static boolean isSafe(int x, int y, int n) {
		return x>=1 && x<=n && y>=1 && y<=n;
	}
}



class Square {
	public int x, y, dist = 0;

	public Square(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
}

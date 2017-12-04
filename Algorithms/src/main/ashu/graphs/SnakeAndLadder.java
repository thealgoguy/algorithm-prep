package geeksforgeeks;

import java.util.LinkedList;
import java.util.Queue;
//application of BFS

public class SnakeAndLadder {
	static class Cell {
		int position;
		int distance;
		public Cell(int p, int d) {
			this.position = p;
			this.distance = d;
		}
	}
	public static int getMinDiceThrows(int board [], int N) {
		boolean visited [] = new boolean[N];
		Queue<Cell> q = new LinkedList<Cell>();
		Cell start = new Cell(0, 0);
		if(board[0] !=-1) start.position = board[0];// check for snake or ladder
		q.add(start);
		visited[0] = true;
		Cell next = null; //for iteration and accessing out of the loop
		boolean reached  = false;
		while(!q.isEmpty()) {
			Cell current  = q.poll();
			for(int i=current.position+1; i<=current.position+6 && i<N; i++) {
				if(!visited[i])  {
					visited[i] = true;  
					next = new Cell(i, current.distance+1);
					//check for snake or ladder
					if(board[i] !=-1) next.position = board[i];
					if(next.position==N-1) {
						reached = true;
						break;
					} else q.add(next);
				}				
			}
			if(reached) break;
		}
		return next.distance;
	}
     public static void main(String args []) {
    	 int N = 30;
    	    int board [] = new int[N];
    	    //these board positions have neither ladder nor snake
    	    for (int i = 0; i<N; i++)
    	        board[i] = -1;
    	 
    	    // Ladders
    	    board[2] = 21;
    	    board[4] = 7;
    	    board[10] = 25;
    	    board[19] = 28;
    	 
    	    // Snakes
    	    board[26] = 0;
    	    board[20] = 8;
    	    board[16] = 3;
    	    board[18] = 6;
    	 
    	    System.out.println("Min Dice throws required is " +getMinDiceThrows(board, N));;
     }
}

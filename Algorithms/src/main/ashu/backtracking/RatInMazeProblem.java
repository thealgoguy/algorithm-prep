package main.ashu.backtracking;
//http://www.geeksforgeeks.org/backttracking-set-2-rat-in-a-maze/
//backtracking steps :
//Given - initial state, final state, constraints for next move
//Approach : 1. Choose a next possible state. If this is the final state mark it in soln and return true.
//2. Apply constraint check - if fails return false else mark the current state in soln. 
//3. Now recurse for next moves from current states; if any next move gives soln return true else the current state is a dead end.
// if dead end, remove the current state from soln and backtrack by returning false.
//FOLLOW-UP : Solve if movement is allowed in all the 4 dirs and calculate the shortest path b/w
//given source and destination using Dijkstr's BFS and print path using backtracking-http://stackoverflow.com/questions/19751429/print-out-all-the-cell-coordinates-for-the-shortest-path
class RatInMaze {
	   private int N;
	   private int maze [][];
	   private int sol [][];
	   private int count = 0;
	   RatInMaze(int N, int maze[][]) {
		   this.N = N;
		   this.maze = maze;
	   }
	   //constraint check for currently chosen state in backtracking
	   private boolean isSafe(int x, int y) {
		   return (x>=0 && x<N && y>=0 && y<N && maze[x][y] ==1) ? true :false;
	   }
	   private void printSol() {
		  for(int i=0;i<N; i++) {
			  for(int j=0; j<N; j++) {
				  System.out.print(sol[i][j]+" ");
			  }
			  System.out.println();
		  }
	   }
	   public void solve() {
		   sol = new int[N][N];
		   for(int i=0; i<N; i++) {
			   for(int j=0; j<N; j++)
				   sol[i][j] = 0;
		   }
		    //solve for the given initial state (0,0)
		   if(solveMaze(0,0)) { 
			  System.out.println("One feasible solution matrix is :");
			   printSol();
		   }
		   else System.out.println("No solution exists");
	   }
	   //the following method gives one possible soln only, if it exists
	   private boolean solveMaze(int x, int y) {
		   if(x==N-1 && y==N-1) {  //at final state ? stop the search
			   sol[x][y] = 1;
			   return true;
		   }
		   if(!isSafe(x,y)) return false;  //constraint failed
		   sol[x][y] = 1;  //try this state 
		   //recursive step
		   if(solveMaze(x+1,y)) return true;
		   if(solveMaze(x, y+1)) return true;  
		   sol[x][y] = 0;
		   return false;
	   }
	   //counts for and prints all ways but horribly inefficient due to repeated/overlapping recursive calls
	   //just keep track of no of times final state is visited
	   private boolean solveMazeAllWays(int x, int y) {
		   if(x==N-1 && y==N-1) {  //at final state ? stop the search
			   sol[x][y] = 1;   
			   count++;  //incrementing visit/hit count of final state
			   System.out.println("Solution No. "+count+" is :");
			   printSol();   //current soln
			   return true;
		   }
		   if(!isSafe(x,y)) return false;  //constraint failed
		   sol[x][y] = 1;  //try this state 
		   boolean isValidMove = solveMazeAllWays(x+1,y) | solveMazeAllWays(x, y+1);  //recursive step(costly due to repeated calls)
		   sol[x][y] = 0;  //tricky, here we are backtracking for printing other paths
		   return isValidMove;
	   }
	   
	   //count and store no of times a cell is hit while running from 0,0 (a subproblem in dp)
	   //here we can't generate all possible paths b/c we are'nt brute forcing using backtrack but only counting
	   //to print all paths we will have to traverse all the paths using bactktrack method if not by dumb brute force
     public boolean solveMazeAllWaysDP(int x, int y) {
		   if(x==0 && y==0) {  //base condition ,stop the search
			   sol[x][y] += 1;   
			   return true;
		   }
		   if(!isSafe(x,y)) return false;  //constraint failed
		   
		   if(isSafe(x-1,y)) {     //non-repetitive recursion....previous cell safe to visit but not visited then call recursion 
			   if(sol[x-1][y]<=0){
				   solveMazeAllWaysDP(x-1, y);
			  }
			   sol[x][y] += sol[x-1][y];
		   }
		   if(isSafe(x,y-1)) {
			    if(sol[x][y-1]<=0) {
				   solveMazeAllWaysDP(x, y-1);
			   }
			    sol[x][y] += sol[x][y-1];
		   }
		   return sol[x][y]>0;
     }
	  public void countWays() {
		  sol = new int[N][N];
		   for(int i=0; i<N; i++) {
			   for(int j=0; j<N; j++)
				   sol[i][j] = 0;
		   }
		   solveMazeAllWaysDP(N-1,N-1);
		   System.out.println("Total number of solutions calculated using DP = "+sol[N-1][N-1]);
		   printSol();
		   for(int i=0; i<N; i++) {
			   for(int j=0; j<N; j++)
				   sol[i][j] = 0;
		   }
		   System.out.println("Different solutions are : "); solveMazeAllWays(0, 0);
	  } 
}
public class RatInMazeProblem {
	public static void main(String args []) {
		int maze[][] = {{1, 1, 1, 1},
				{1, 0, 1, 1},
				{0, 1, 1, 1},
				{1, 1, 1, 1}
		};
		RatInMaze r = new RatInMaze(4, maze);
		r.solve();
		r.countWays();
	}
}

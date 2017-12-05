package main.ashu.backtracking;

public class NQueensProblem {
	static int N = 4;
	static int soln[][];

	public static void main(String args[]) {
		soln = new int[N][N];
		boolean possible = solve(0);
		if (possible)
			System.out.println(" Solution exists");
		else
			System.out.println("No solution exists");
	}

	// Note : we are moving in increasing order of cols in recursive calls
	public static boolean solve(int col) {
		// if destination reached, print soln
		if (col == N) {
			System.out.println("Solution matrix is : ");
			printSolution();
			return true;
		}
		// try moving from this spot
		boolean reached = false;
		// check all rows of this col
		for (int i = 0; i < N; i++) {
			if (isSafe(i, col)) {
				soln[i][col] = 1;
				reached |= solve(col + 1);
				soln[i][col] = 0;
			}
		}
		return reached;
	}

	// since we're placing queens in increasing order of cols,
	// need to check for smaller cols only
	public static boolean isSafe(int row, int col) {
		if (row < 0 || row > N || col < 0 || col > N)
			return false; // boundary check
		// left cols check
		for (int i = 0; i < col; i++) {
			if (soln[row][i] == 1)
				return false;
		}
		// upper left diagonal check
		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (soln[i][j] == 1)
				return false;
		}
		// lower left diagonal check
		for (int i = row + 1, j = col - 1; i < N && j >= 0; i++, j--) {
			if (soln[i][j] == 1)
				return false;
		}
		// no constraint violated return true
		return true;
	}

	public static void printSolution() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(soln[i][j] + " ");
			}
			System.out.println();
		}
	}
}

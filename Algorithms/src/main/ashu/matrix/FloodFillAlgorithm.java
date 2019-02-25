package main.ashu.matrix;

//Given a 2D screen, location of a pixel in the screen and a color, 
//replace color of the given pixel and all adjacent same colored pixels with the given color.
//Idea :  first replace the color of current pixel, then recur for 4 surrounding points
public class FloodFillAlgorithm {
	static int M, N;
	public static void main(String args []) 
	{
		int screen[][] = { { 1, 1, 1, 1, 1, 1, 1, 1 },
				{ 1, 1, 1, 1, 1, 1, 0, 0 }, { 1, 0, 0, 1, 1, 0, 1, 1 },
				{ 1, 2, 2, 2, 2, 0, 1, 0 }, { 1, 1, 1, 2, 2, 0, 1, 0 },
				{ 1, 1, 1, 2, 2, 2, 2, 0 }, { 1, 1, 1, 1, 1, 2, 1, 1 },
				{ 1, 1, 1, 1, 1, 2, 2, 1 }, };
		int x = 4, y = 4, newC = 3;
		floodFill(screen, x, y, newC);
		 M = screen.length;
		 N = screen[0].length;
		System.out.println("Updated screen after call to floodFill");
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(screen[i][j] + " ");
			System.out.println();
		}
	} 
	
	private static void floodFill(int screen[][], int x, int y, int newColor) {
		int prevC = screen[x][y]; 
	    floodFillUtil(screen, x, y, prevC, newColor); 
	}
	
	private static void floodFillUtil(int screen[][], int x, int y, int prevColor, int newColor) {
		 if (x < 0 || x >= M || y < 0 || y >= N) 
		        return; 
		    if (screen[x][y] != prevColor) 
		        return; 
		  
		    // Replace the color at (x, y) 
		    screen[x][y] = newColor; 
		  
		    // Recur for north, east, south and west 
		    floodFillUtil(screen, x+1, y, prevColor, newColor); 
		    floodFillUtil(screen, x-1, y, prevColor, newColor); 
		    floodFillUtil(screen, x, y+1, prevColor, newColor); 
		    floodFillUtil(screen, x, y-1, prevColor, newColor); 
	}
	
}

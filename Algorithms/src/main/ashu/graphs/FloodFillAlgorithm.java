package main.ashu.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//http://www.techiedelight.com/flood-fill-algorithm/
public class FloodFillAlgorithm {
  private  static class Cell {
	  private int x, y;
	  public Cell(int x, int y) {
		  this.x = x;
		  this.y = y;
	  }
  }
  
  private static final int xdir [] = {-1, -1, -1, 0, 1, 1, 1, 0};
  private static final int ydir [] = {-1, 0, 1, 1, 1, 0, -1, -1};
  
	public static boolean isSafe(char a[][], int x, int y, int color) {
		return x >= 0 && x < a.length && y >= 0 && y < a[0].length
				&& a[x][y] == color;
	}
  
	
  public static void floodFillBFS(char a[][], int x, int y, char newColor) {
	  char currentColor = a[x][y];
	  Queue<Cell> queue = new LinkedList<Cell>();
	  queue.offer(new Cell(x, y));
	  //no need to keep track of visited ones as we are marking them 
	  //with new color and won't consider them again
	  while(!queue.isEmpty()) {
		  Cell next = queue.poll();
		  a[next.x][next.y] = newColor;
		  for(int i=0; i<xdir.length; i++) {
			  if(isSafe(a, x+xdir[i], y+ydir[i], currentColor)) {
				  queue.offer(new Cell(x+xdir[i], y+ydir[i]));
			  }
		  }
	  }
  }
  
  public static void floodFillDFS(char a[][], int x, int y, char newColor) {
	  char currentColor = a[x][y];
	  a[x][y] = newColor;
	  for(int i=0; i<xdir.length; i++) {
		  if(isSafe(a, x+xdir[i], y+ydir[i], currentColor)) {
			  floodFillDFS(a,x+xdir[i], y+ydir[i], newColor);
		  }
	  }
  }
  
  public static void main(String args []) {
	  char a[][] = {
			  "YYYGGGGGGG".toCharArray(),
			  "YYYYYYGXXX".toCharArray(),
			  "GGGGGGGXXX".toCharArray(),
			  "WWWWWWWXXX".toCharArray(),
			  "WRRRRRGXXX".toCharArray(),
			  "WWWRRGGXXX".toCharArray(),
			  "WBWRRRRRRX".toCharArray(),
			  "WBBBBRRXXX".toCharArray(),
			  "WBBXXXXXXX".toCharArray()
	  };
	  
	  System.out.println("Before flood filling : ");
	  for(int i=0; i<a.length; i++) {
		  System.out.println(Arrays.toString(a[i]));
	  }
	  //starting cell
	  int x = 3, y = 9;
	  //replacement color = 
	  char replace = 'C';
	  System.out.println("After flood filling : ");
	  floodFillBFS(a, x, y, replace);
	  for(int i=0; i<a.length; i++) {
		  System.out.println(Arrays.toString(a[i]));
	  }
  }
  
}

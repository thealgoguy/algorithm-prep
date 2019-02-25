package main.ashu.matrix;

//So, we will define a 2D matrix, say dp[][], where d[i][j] indicate the number of path from cell (1, 1) to cell(i, j). 
//Also, we can define dp[i][j] as dp[i][j] = dp[i-1][j] + dp[i][j-1] + dp[i-1][j-1]
//i.e sum of path from left cell, right cell and upper left diagonal (moves allowed).
//To find the lexicographical largest path, we can you use DFS (Depth-first search)
//https://www.geeksforgeeks.org/lexicographically-largest-prime-path-from-top-left-to-bottom-right-in-a-matrix/
public class LargestPrimePathInMatrix {

}

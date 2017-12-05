package main.ashu.patternsearching;

//given a matrix of chars and a word, find the occurrence of the word in the matrix in all the eight directions; print the path as well
//application of depth first search or backtracking

public class PatternSearchInMatrix {
	static int N, M;
	
	static int xdir [] = {-1,-1,-1,0,0,1,1,1};
	static int ydir [] = {-1,0,1,-1,1,-1,0,1};
	
	public static void searchPattern(char mat [][], String pat) {
		//do dfs from each word of the matrix if the starting chars match
		String path = "";
		for(int i=0; i<mat.length; i++) {
			for(int j=0; j<mat[0].length; j++) {
				if(mat[i][j]==pat.charAt(0)) { 
					path = "("+i+","+j+")";
					dfs(pat, mat, i, j, 0, path);  //will do dfs from feasible source cell only
				}
			}
		}
	}
	
	public static void dfs(String pat,char [][] mat, int x, int y, int len, String path) {
		if(len==pat.length()-1) {
			if(mat[x][y]==pat.charAt(len)) {
				//System.out.println("Pattern found at cell : "+x+","+y);
				System.out.println(path);
				return;
			}
			return;
		}
		if(mat[x][y]==pat.charAt(len)) {
			for(int i=0; i<8; i++) {
				if(isSafe(x+xdir[i], y+ydir[i])) {
					String append = ",("+(x+xdir[i])+","+(y+ydir[i])+")";
					path += append;  //choose next move
					dfs(pat,mat,x+xdir[i], y+ydir[i], len+1, path);
					path = path.replace(append, "");  //backtrack
				}
			}

		}
	}
	
	public static boolean isSafe(int x, int y) {
		return (x>=0 && x<N && y>=0 && y<M);
	}
	
     public static void main(String args []) {
    	 char matrix[][]= { {'B', 'N', 'E', 'Y', 'S'},
                 {'H', 'E', 'D', 'E', 'S'},
                 {'S', 'G', 'N', 'D', 'E'}
               };
    	 N = matrix.length;
    	 M = matrix[0].length;
         String pat = "DES";
         searchPattern(matrix, pat);
         
     }
}

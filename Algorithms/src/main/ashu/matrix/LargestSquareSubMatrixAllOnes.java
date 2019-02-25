package main.ashu.matrix;

//Largest square sub-matrix with all 1s in a boolean matrix. 
//dp[i][j] = 1 + min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) if a[i][j]=1, else =0
public class LargestSquareSubMatrixAllOnes {

	public static void main(String args []) {
		int a[][] = { {1, 1, 0, 1, 0}, 
                     {0, 1, 1, 1, 0},
                     {1, 1, 1, 1, 0},
                     {1, 1, 1, 1, 1},
                     {0, 0, 0, 0, 0}};
		int r = a.length;
		int c = a[0].length;
		int dp [][] = new int[r][c];  //dp(i,j) = size of square matrix ending with a[i][j] and having all 1s
		for(int i=0; i<r; i++) dp[i][0] = (a[i][0]==1) ? 1 : 0;
		for(int i=0; i<c; i++) dp[0][i] = (a[0][i]==1) ? 1 : 0;
		int maxSize = 1;  ///initial size of largest square sub-matrix with all 1s
		int row=0, col=0;
		for(int i=1; i<r; i++) {
			for(int j=1; j<c; j++) {
				if(a[i][j] == 0) dp[i][j] = 0;
				else {
					dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]));
				}
				if(maxSize < dp[i][j]) {
					maxSize = dp[i][j];
					row = i; col = j;
				}
			}
		}
		System.out.println("Size pf largest square submatrix with all 1s = "+maxSize);
		System.out.println("matrix is : ");
         row = row - maxSize+1;
         col = col - maxSize+1;
         for(int i=row; i<row+maxSize; i++) {
        	 for(int j=col; j<col+maxSize; j++) {
        		 System.out.print(a[i][j]+" ");
        	 }
        	 System.out.println();
         }
	}

}

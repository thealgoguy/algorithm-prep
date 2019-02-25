package main.ashu.matrix;

public class MatrixSpiralPrint {

	public static void main(String args[]) {
		int a[][] = { { 1, 2, 3, 4, 5, 6 }, { 7, 8, 9, 10, 11, 12 },
				{ 13, 14, 15, 16, 17, 18 } };

		int m = a.length;
		int n = a[0].length;
		int dir = 1;
		int top = 0, bottom = m - 1, left = 0, right = n - 1;
		while (top <= bottom && left <= right) {
			if (dir == 1) {
				// left to right in upper boundary
				for (int i = left; i <= right; i++) {
					System.out.print(a[top][i] + " ");
				}
				top++;
			} else if (dir == 2) {
				// top to bottm in right boundary
				for (int i = top; i <= bottom; i++) {
					System.out.print(a[i][right] + " ");
				}
				right--;
			} else if (dir == 3) {
				for (int i = right; i >= left; i--) {
					System.out.print(a[bottom][i] + " ");
				}
				bottom--;
			} else {
				// bottom up
				for (int i = bottom; i >= top; i--) {
					System.out.print(a[i][left] + " ");
				}
				left++;
			}
			dir++;
			dir %= 5;
		}
	}

}

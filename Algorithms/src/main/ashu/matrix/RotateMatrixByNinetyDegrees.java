package main.ashu.matrix;

//Given a square matrix, turn it by 90 degrees in anti-clockwise direction without using any extra space.
//transpose, reverse each column
//for clockwise - reverse rows
//to rotate by 180, do above steps twice(rotate by 90 twice)
//how to rotate an image by 90 ?
public class RotateMatrixByNinetyDegrees {
	public static void main(String args[]) {
		int arr[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		System.out.println("Input matrix is : ");
		print(arr);
		rotate(arr);
		System.out.println("After rotation by 90 degrees : ");
		print(arr);
	}

	private static void rotate(int[][] arr) {
		int m = arr.length;
		int n = arr[0].length;
		transpose(arr);
		reverseColumns(arr);
	}

	private static void transpose(int arr[][]) {
		int m = arr.length;
		int n = arr[0].length;
		//swap elements  equidistant from the left diagonal on opposite sides
		for (int i = 0; i < m; i++) {
			for (int j = i; j < n; j++) {
				int temp = arr[i][j];
				arr[i][j] = arr[j][i];
				arr[j][i] = temp;
			}
		}
	}

	private static void reverseColumns(int arr[][]) {
		int m = arr.length;
		int n = arr[0].length;
		//reverse each columns
		for (int i = 0; i < n; i++) {
			for (int j = 0, k = m - 1; j < k; j++, k--) {
				int temp = arr[j][i];
				arr[j][i] = arr[k][i];
				arr[k][i] = temp;
			}
		}
	}

	private static void print(int arr[][]) {
		int m = arr.length;
		int n = arr[0].length;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}

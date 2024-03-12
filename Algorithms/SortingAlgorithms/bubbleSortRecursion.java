package Algorithms.SortingAlgorithms;

import java.util.Arrays;

public class bubbleSortRecursion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = { 2, 5, 1, 7, 8, 3 };

		System.out.println(Arrays.toString(arr));
		sortArray(arr, 6);
		System.out.println(Arrays.toString(arr));
	}

	public static void sortArray(int[] arr, int n) {

		// Base Condition - already sorted
		if (n == 0 || n == 1) {
			return;
		}

		// First case to solved
		for (int i = 0; i < n - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				int temp = arr[i];
				arr[i] = arr[i + 1];
				arr[i + 1] = temp;
			}
		}
		// Recursive call
		sortArray(arr, n - 1);

	}

}

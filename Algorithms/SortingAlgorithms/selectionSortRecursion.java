package Algorithms.SortingAlgorithms;

import java.util.Arrays;

public class selectionSortRecursion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = { 67, 34, 2, 9, 1, 45 };
		int size = arr.length;

		System.out.println(Arrays.toString(arr));

		sortedArray(arr, 0, size);
		System.out.println(Arrays.toString(arr));

	}

	public static void sortedArray(int[] arr, int start, int size) {
		// Base condition
		if (size == 0 || size == 1 || start == size) {
			return;
		}

		int minIndex = start;
		for (int j = start + 1; j < size; j++) {
			if (arr[minIndex] > arr[j]) {
				minIndex = j;
			}
		}
		// System.out.println(arr[start] + " "+arr[minIndex]);

		int temp = arr[start];
		arr[start] = arr[minIndex];
		arr[minIndex] = temp;

		// Recursive call
		sortedArray(arr, start + 1, size);

	}

}

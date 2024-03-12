package Algorithms.SortingAlgorithms;

import java.util.Arrays;

public class MergeSortRecursion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = { 3, 7, 0, 1, 5, 8, 3, 2, 34, 66, 87, 23, 12, 12, 12 };
		int n = arr.length;

		System.out.println(Arrays.toString(arr));
		mergeSort(arr, 0, n - 1);
		System.out.println(Arrays.toString(arr));
	}

	public static void mergeSort(int[] arr, int start, int end) {

		// base condition
		if (start >= end) {
			return;
		}

		int mid = start + ((end - start) / 2);

		// left part sort
		mergeSort(arr, start, mid);

		// right part sort
		mergeSort(arr, mid + 1, end);

		// merge
		merge(arr, start, end);
	}

	public static void merge(int[] arr, int start, int end) {
		int mid = start + ((end - start) / 2);

		int len1 = mid - start + 1;
		int len2 = end - mid;

		int first[] = new int[len1];
		int second[] = new int[len2];

		// copy values
		int mainArrayIndex = start;
		for (int i = 0; i < len1; i++) {
			first[i] = arr[mainArrayIndex++];
		}
		mainArrayIndex = mid + 1;
		for (int i = 0; i < len2; i++) {
			second[i] = arr[mainArrayIndex++];
		}

		// merge 2 sorted arrays
		int index1 = 0;
		int index2 = 0;
		mainArrayIndex = start;

		while ((index1 < len1) && (index2 < len2)) {

			if (first[index1] < second[index2]) {
				arr[mainArrayIndex++] = first[index1++];
			} else {
				arr[mainArrayIndex++] = second[index2++];
			}
		}

		while (index1 < len1) {
			arr[mainArrayIndex++] = first[index1++];
		}

		while (index2 < len2) {
			arr[mainArrayIndex++] = second[index2++];
		}

	}

}

package Algorithms.SortingAlgorithms;

import java.util.Arrays;

//Time: O(N * log N)
//Space: O(N)
public class MergeSortRecursion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = { 3, 7, 0, 1, 5, 8, 3, 2, 34, 66, 87, 23, 12, 12, 12 };
		int n = arr.length;

		System.out.println(Arrays.toString(arr));
		int[] ans = mergeSort(arr, 0, n - 1);
		System.out.println(Arrays.toString(ans));
	}


	private int[] merge(int[] first, int[] second){

        int m = first.length;
        int n = second.length;
        int[] ans = new int[m + n];

        int ptr1 = 0;
        int ptr2 = 0;
        int index = 0;

        while(ptr1 < m && ptr2 < n){

            if(first[ptr1] < second[ptr2]){
                ans[index] = first[ptr1];
                index++;
                ptr1++;
            }else{
                ans[index] = second[ptr2];
                index++;
                ptr2++;
            }
        }

        while(ptr1 < m){
            ans[index] = first[ptr1];
            index++;
            ptr1++;
        }

        while(ptr2 < n){
            ans[index] = second[ptr2];
            index++;
            ptr2++;
        }

        return ans;

    }
    
	private int[] mergeSort(int[] nums,int start,int end){

        //Base Case
        if(start > end){
            return new int[0];
        }
        if(start == end){
            return new int[]{nums[start]};
        }


        int mid = start + ((end - start)/2);
        int[] leftArr = mergeSort(nums, start, mid);
        int[] rightArr = mergeSort(nums, mid + 1, end);

        int[] ans = merge(leftArr, rightArr);

        return ans;

    }
}

package Heap;

import java.util.*;

public class SumOfMaxMinofAllSubArray {

    public static int solve(int[] arr, int n, int k) {

        ArrayDeque<Integer> maxi = new ArrayDeque<>(k);
        ArrayDeque<Integer> mini = new ArrayDeque<>(k);

        // Addition of first k size window
        for (int i = 0; i < k; i++) {

            while (!maxi.isEmpty() && arr[maxi.peekLast()] <= arr[i]) {
                maxi.pollLast();
            }

            while (!mini.isEmpty() && arr[mini.peekLast()] >= arr[i]) {
                mini.pollLast();
            }

            maxi.offerLast(i);
            mini.offerLast(i);
        }

        int sum = 0;
        sum += arr[maxi.peekFirst()] + arr[mini.peekFirst()];

        for (int i = k; i < n; i++) {

            while (!maxi.isEmpty() && (i - maxi.peekFirst() >= k)) {
                maxi.pollFirst();
            }

            while (!mini.isEmpty() && (i - mini.peekFirst() >= k)) {
                mini.pollFirst();
            }

            while (!maxi.isEmpty() && arr[maxi.peekLast()] <= arr[i]) {
                maxi.pollLast();
            }

            while (!mini.isEmpty() && arr[mini.peekLast()] >= arr[i]) {
                mini.pollLast();
            }

            maxi.offerLast(i);
            mini.offerLast(i);

            sum += arr[maxi.peekFirst()] + arr[mini.peekFirst()];

        }

        return sum;
    }

    public static void main(String[] args) {

        int arr[] = { 2, 5, -1, 7, -3, -1, -2 };
        int k = 4;
        System.out.println(solve(arr, 7, k));

    }

}

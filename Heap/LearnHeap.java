package Heap;

import java.util.*;

public class LearnHeap {

    static class Heap {
        int arr[];
        int size;

        Heap() {
            arr = new int[2000];
            arr[0] = -1;
            size = 0;
        }

        // Time complexity of insertion in heap is O(log n)
        void insert(int data) {

            size += 1;
            int index = size;

            arr[index] = data;

            while (index > 1) {

                int parent = index / 2;

                if (arr[parent] < arr[index]) {
                    int temp = arr[parent];
                    arr[parent] = arr[index];
                    arr[index] = temp;
                    index = parent;
                } else {
                    return;
                }
            }

        }

        void deletefromHeap() {
            if (size == 0) {
                System.out.println("nothing to delete!");
                return;
            }
            // Step 1 : put last element into first index
            arr[1] = arr[size];
            // Step 2 : remove last element
            size--;

            // Step 3 : take root node to its correct position
            int i = 1;
            while (i < size) {
                int leftIndex = 2 * i;
                int rightIndex = 2 * i + 1;

                if (leftIndex < size && arr[i] < arr[leftIndex]) {
                    int temp = arr[i];
                    arr[i] = arr[leftIndex];
                    arr[leftIndex] = temp;
                    i = leftIndex;
                } else if (rightIndex < size && arr[i] < arr[rightIndex]) {
                    int temp = arr[i];
                    arr[i] = arr[rightIndex];
                    arr[rightIndex] = temp;
                    i = rightIndex;
                } else {
                    return;
                }
            }

        }

        void print() {
            for (int i = 1; i <= size; i++) {
                System.out.print(arr[i] + "  ");
            }
            System.out.println();
        }

    }

    public static void maxHeapify(int arr[], int n, int i) {
        int largest = i;
        int left = 2 * i;
        int right = 2 * i + 1;

        if (left <= n && arr[largest] < arr[left]) {
            largest = left;
        }
        if (right <= n && arr[largest] < arr[right]) {
            largest = right;
        }

        if (largest != i) {
            int temp = arr[largest];
            arr[largest] = arr[i];
            arr[i] = temp;

            maxHeapify(arr, n, largest);
        }
    }

    public static void minHeapify(int[] arr, int n, int i) {

        int smallest = i;
        int left = 2 * i;
        int right = 2 * i + 1;
        if (left < n && arr[smallest] > arr[left]) {
            smallest = left;
        }

        if (right < n && arr[smallest] > arr[right]) {
            smallest = right;
        }

        if (smallest != i) {
            int temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;

            minHeapify(arr, n, smallest);
        }

    }

    public static void heapSortIncreasing(int[] arr, int n) {

        int size = n;

        while (size > 1) {
            // Step 1
            int temp = arr[1];
            arr[1] = arr[size];
            arr[size] = temp;
            size--;

            // Step 2
            maxHeapify(arr, size, 1);

        }

    }

    public static void heapSortDecrasing(int[] arr, int size) {

        while (size > 1) {
            int temp = arr[1];
            arr[1] = arr[size];
            arr[size] = temp;
            size--;

            minHeapify(arr, size, 1);
        }
    }

    public static void main(String[] args) {

        Heap h = new Heap();
        h.insert(50);
        h.insert(55);
        h.insert(53);
        h.insert(52);
        h.insert(54);
        h.print();

        h.deletefromHeap();
        h.print();

        int arr[] = { -1, 54, 53, 55, 52, 50 };
        int n = 5;
        for (int i = n / 2; i > 0; i--) {
            maxHeapify(arr, n, i);
        }
        for (int i = 1; i <= n; i++) {
            System.out.print(arr[i] + "  ");
        }
        System.out.println();

        // int arr1[] = {-1,54,53,55,52,50};
        // n = 5;
        // for(int i=n/2; i>0; i--) {
        // minHeapify(arr,n,i);
        // }
        // for(int i=1; i<=n; i++) {
        // System.out.print(arr1[i] + " ");
        // }
        // System.out.println();

        int arr2[] = { -1, 54, 53, 55, 52, 50 };
        ;
        n = 5;
        System.out.println("Print sorted array in Increasing order: ");
        heapSortIncreasing(arr2, n);
        for (int i = 1; i <= n; i++) {
            System.out.print(arr2[i] + "  ");
        }
        System.out.println();

        int arr3[] = { -1, 50, 51, 53, 52, 54 };
        ;
        n = 5;
        System.out.println("Print sorted array in Decrasing order: ");
        heapSortDecrasing(arr3, n);
        for (int i = 1; i <= n; i++) {
            System.out.print(arr3[i] + "  ");
        }
        System.out.println();

        /*
         * // Min and Max heap using Priority Queue
         * PriorityQueue<Integer> pq = new PriorityQueue<>();
         * pq.offer(2);
         * pq.offer(4);
         * pq.offer(50);
         * pq.offer(10);
         * System.out.println("Min heap using Priority Queue:");
         * System.out.println(pq);
         * System.out.println(pq.poll());
         * System.out.println(pq.poll());
         * System.out.println(pq.poll());
         * 
         * 
         * PriorityQueue<Integer> pq1 = new PriorityQueue<>(Comparator.reverseOrder());
         * pq1.offer(2);
         * pq1.offer(4);
         * pq1.offer(50);
         * pq1.offer(10);
         * System.out.println("Max heap using Priority Queue:");
         * System.out.println(pq1);
         * System.out.println(pq1.poll());
         * System.out.println(pq1.poll());
         * System.out.println(pq1.poll());
         */

        int a = 6;
        int b = 500;

        System.out.println(Integer.compare(a, b));

    }

}

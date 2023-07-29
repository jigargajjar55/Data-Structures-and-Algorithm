import java.util.*;

public class BookAllocationMain {

    public static void main(String[] args) {

        int arr1[] = { 12, 34, 67, 90 };
        System.out.println(Arrays.toString(arr1));
        System.out.println("The minimum pages: " + allocateBooks(arr1, arr1.length, 2));

        int arr2[] = { 5, 7, 100, 11 };
        System.out.println(Arrays.toString(arr2));
        System.out.println("The minimum pages: " + allocateBooks(arr2, arr2.length, 4));

    }

    static int allocateBooks(int[] arr, int n, int m) {
        int start = 0;

        int sum = 0;

        for (int i : arr) {
            sum += i;
        }
        int end = sum;
        int ans = -1;

        int mid = start + ((end - start) / 2);

        while (start <= end) {

            if (isPossible(arr, n, m, mid)) {

                ans = mid;
                end = mid - 1;

            } else {
                start = mid + 1;
            }

            mid = start + ((end - start) / 2);
        }

        return ans;

    }

    static boolean isPossible(int[] arr, int n, int m, int mid) {

        int studentCount = 1;
        int sumPage = 0;

        for (int i = 0; i < n; i++) {

            if (sumPage + arr[i] <= mid) {

                sumPage += arr[i];

            } else {

                studentCount += 1;
                if (studentCount > m || arr[i] > mid) {
                    return false;
                }

                sumPage = arr[i];
            }
        }

        return true;
    }
}

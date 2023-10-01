package Array;

import java.util.*;

public class sortZeroOneTwoMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int arrayA[] = { 1, 2, 1, 0, 1, 0, 2, 0, 2 };

        System.out.println(Arrays.toString(arrayA));

        // ArrayList<Integer> result = sortZeroandOne(arrayA);
        sortZeroOneTwo(arrayA);

        System.out.println(Arrays.toString(arrayA));
    }

    static void sortZeroOneTwo(int a[]) {

        int left = 0;
        int right = a.length - 1;
        int start = 0;

        while (start < right) {

            if (a[start] == 0) {
                int temp = a[left];
                a[left] = a[start];
                a[start] = temp;

                start += 1;
                left += 1;
            } else if (a[start] == 2) {
                int temp = a[right];
                a[right] = a[start];
                a[start] = temp;

                right -= 1;
            } else {
                start += 1;
            }
        }

    }

}

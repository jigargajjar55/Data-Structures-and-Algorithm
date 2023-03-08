package Strings;

import java.util.*;

public class ReverseWordsinStringII186LC {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        char testCase1[] = { 't', 'h', 'e', ' ', 's', 'k', 'y', ' ', 'i', 's', ' ', 'b', 'l', 'u', 'e' };
        int tc1length = testCase1.length;

        char testCase2[] = { 'a' };
        int tc2length = testCase2.length;

        System.out.println(Arrays.toString(testCase2));

        reverseCharArray(testCase2, 0, tc2length - 1);

        System.out.println(Arrays.toString(testCase2));

        int start = 0;
        int end = 0;
        for (int i = 1; i < tc2length; i++) {

            if (testCase2[i] == ' ') {

                end = i - 1;
                reverseCharArray(testCase2, start, end);
                System.out.println(Arrays.toString(testCase2));

                if ((i + 1) < tc2length) {
                    start = i + 1;
                }
            }

            if (i == tc2length - 1) {
                end = i;
                reverseCharArray(testCase2, start, end);
                System.out.println(Arrays.toString(testCase2));
            }

        }

        String s = "Jigar";

        for (char c : s.toCharArray()) {
            System.out.println(c);
        }

    }

    public static void reverseCharArray(char[] arr, int start, int end) {

        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
    }

}

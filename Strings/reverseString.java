package Strings;

import java.util.*;

public class reverseString {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scanner = new Scanner(System.in);

        String name = scanner.next();

        char ch[] = new char[name.length()];

        String result = "";

        for (int i = 0; i < name.length(); i++) {

            ch[i] = name.charAt(i);

            result = ch[i] + result;

        }

        System.out.println("String: " + Arrays.toString(ch));

        stringReverseRecursion(ch, 0, ch.length - 1);

        System.out.println("Reverse String: " + Arrays.toString(ch));

    }

    public static void stringReverse(char[] arr) {

        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {

            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
    }

    public static void stringReverseRecursion(char[] arr, int start, int end) {

        // Base condition
        if (start > end) {
            return;
        } else {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;

            stringReverseRecursion(arr, start, end);

        }

    }
}

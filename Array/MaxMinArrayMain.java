package Array;

import java.lang.reflect.Array;
import java.util.*;

public class MaxMinArrayMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int array[] = new int[100];

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the size of an array: ");
        int size = scanner.nextInt();

        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.println("----------------------------");

        System.out.println(getMaxValue(array));
        System.out.println(getMinValue(array));

    }

    static int getMaxValue(int[] array) {

        int maxValue = Integer.MIN_VALUE;

        for (int i = 0; i < array.length; i++) {

            maxValue = Integer.max(maxValue, array[i]);

            // if(array[i] > max){
            // max = array[i];
            // }
        }

        return maxValue;
    }

    static int getMinValue(int[] array) {

        int minValue = Integer.MAX_VALUE;

        for (int i = 0; i < array.length; i++) {

            minValue = Integer.min(minValue, array[i]);

            // if(array[i] < min){
            // min = array[i];
            // }
        }

        return minValue;
    }

}

package Array;

import java.util.*;

public class sortZerosandOnesMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int arrayA[] = { 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 };

        System.out.println(Arrays.toString(arrayA));

        // ArrayList<Integer> result = sortZeroandOne(arrayA);
        sortZeroandOne(arrayA);

        System.out.println(Arrays.toString(arrayA));
    }

    static void sortZeroandOne(int aN[]) {

        // ArrayList<Integer> result = new ArrayList<Integer>();

        int i = 0;
        int j = aN.length - 1;

        while (i < j) {

            if (aN[i] > aN[j]) {
                int temp = aN[i];
                aN[i] = aN[j];
                aN[j] = temp;
                i += 1;
                j -= 1;

            } else if (aN[i] == 0) {
                i += 1;

            } else if (aN[j] == 1) {
                j -= 1;
            }
        }

    }

}

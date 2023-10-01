package Array;

import java.util.*;

public class ReverseArrayMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int arrayNumbers[] = { 5, 7, -2, 10, 22, -2, 0, 5, 22, 1 };

        int aNLength = arrayNumbers.length;

        System.out.println(Arrays.toString(arrayNumbers));

        int reversearrayNumbers[] = new int[aNLength];

        int j = 0;

        for (int i = aNLength - 1; i >= 0; i--) {
            reversearrayNumbers[j] = arrayNumbers[i];
            j += 1;
        }

        System.out.println(Arrays.toString(reversearrayNumbers));

    }

}

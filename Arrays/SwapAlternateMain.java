package Arrays;

import java.util.Arrays;

public class SwapAlternateMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int arrayNumbers[] = { 5, 7, -2, 10, 22, -2, 5, 22, 1, 98 };

        System.out.println(Arrays.toString(arrayNumbers));

        alternateArray(arrayNumbers);

        System.out.println(Arrays.toString(arrayNumbers));
    }

    static void alternateArray(int[] aN) {

        int first = 0;
        int second = 1;

        while (second < aN.length) {
            int temp = aN[first];
            aN[first] = aN[second];
            aN[second] = temp;

            first += 2;
            second += 2;

        }
    }

}

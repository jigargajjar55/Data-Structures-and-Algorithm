package Array;

import java.util.Arrays;

public class FindUniqueValueinArrayMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int arrayNumbers[] = { 2, 3, 1, 6, 3, 6, 2 };

        System.out.println(Arrays.toString(arrayNumbers));

        // System.out.println(uniqueValue(arrayNumbers));

        System.out.println(findUnique(arrayNumbers));

    }

    // Brute force solution
    static int uniqueValue(int aN[]) {

        int aNLength = aN.length;

        boolean flag = false;
        int resultNum = 0;

        for (int i = 0; i < aNLength; i++) {

            flag = false;

            for (int j = i + 1; j < aNLength; j++) {

                if (aN[i] == aN[j]) {
                    flag = true;
                    break;
                }
            }

            if (flag == false) {
                resultNum = aN[i];
                break;
            }

        }

        return resultNum;

    }

    // Optimized solution
    static int findUnique(int aN[]) {
        int aNLength = aN.length;
        int resultNum = 0;

        for (int i = 0; i < aNLength; i++) {

            resultNum = resultNum ^ aN[i];
        }

        return resultNum;
    }

}

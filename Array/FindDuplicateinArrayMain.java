package Array;

import java.util.*;

public class FindDuplicateinArrayMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int arrayNumbers[] = { 8, 7, 2, 5, 4, 7, 1, 3, 6 };

        System.out.println(Arrays.toString(arrayNumbers));

        // System.out.println(findDuplicate(arrayNumbers));

        System.out.println(findDuplicateinArray(arrayNumbers));
    }

    // Brute force solution
    static int findDuplicate(int aN[]) {

        HashMap<Integer, Integer> elementsMap = new HashMap();
        int result = 0;
        for (int num : aN) {
            elementsMap.put(num, elementsMap.getOrDefault(num, 0) + 1);
        }

        for (int i : elementsMap.keySet()) {

            if (elementsMap.get(i) > 1) {
                result = i;
                break;
            }

        }

        return result;

    }

    // Another solution
    static int findDuplicateinArray(int aN[]) {

        int ans = 0;
        for (int i = 0; i < aN.length; i++) {
            ans = ans ^ aN[i];
        }

        for (int i = 1; i < aN.length; i++) {
            ans = ans ^ i;
        }

        return ans;
    }
}

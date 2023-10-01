package Array;

import java.util.*;

public class FindAllDuplicateMain {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int arrayNumbers[] = { 10, 2, 5, 10, 9, 1, 1, 4, 3, 7 };

        System.out.println(Arrays.toString(arrayNumbers));

        List<Integer> result = findAllDuplicateinArrayO(arrayNumbers);

        System.out.println(result);
    }

    // Brute force solution
    static List<Integer> findAllDuplicateinArray(int aN[]) {

        List<Integer> result = new ArrayList<Integer>();

        HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();

        for (int num : aN) {
            hmap.put(num, hmap.getOrDefault(num, 0) + 1);
        }

        for (int i : hmap.keySet()) {

            if (hmap.get(i) == 2) {
                result.add(i);
            }
        }

        return result;

    }

    // Optimize solution
    static List<Integer> findAllDuplicateinArrayO(int nums[]) {

        List<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;

            if (nums[index] < 0) {
                result.add(Math.abs(nums[index]));
            } else {
                nums[index] = -nums[index];
            }
        }

        return result;

    }
}

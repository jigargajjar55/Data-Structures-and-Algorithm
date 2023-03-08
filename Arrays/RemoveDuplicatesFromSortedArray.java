package Arrays;

import java.util.*;

public class RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int nums[] = { 0, 0, 1, 1, 2, 2, 2, 3, 3, 3, 4 };
        int nums1[] = { 1, 1, 2 };

        System.out.println(Arrays.toString(nums));
        int result = removeDuplicates(nums);

        System.out.println(Arrays.toString(nums));

        System.out.println(result);
    }

    static int removeDuplicates(int[] nums) {

        int result = 1;

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] != nums[i - 1]) {
                nums[result] = nums[i];
                result += 1;
            }

        }

        return result;

    }
}

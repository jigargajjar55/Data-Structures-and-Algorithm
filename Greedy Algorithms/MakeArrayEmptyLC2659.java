import java.util.*;

// https://leetcode.com/problems/make-array-empty/solutions/3466800/java-c-python-easy-sort-solution/?orderBy=most_votes

//Time : O((N) + (N * log N) + (N)), Space: O(N)
public class MakeArrayEmptyLC2659 {

    public long countOperationsToEmptyArray(int[] nums) {

        int n = nums.length;
        long result = n;

        Integer[] pos = new Integer[n];

        for (int i = 0; i < n; i++) {
            pos[i] = i;
        }

        Arrays.sort(pos, (i, j) -> Integer.compare(nums[i], nums[j]));

        for (int i = 1; i < n; i++) {
            if (pos[i] < pos[i - 1]) {
                result += (n - i);
            }
        }

        return result;

        /*
         * Map<Integer,Integer> pos = new HashMap<>();
         * 
         * for(int i=0; i<n; i++){
         * pos.put(nums[i], i);
         * }
         * 
         * Arrays.sort(nums);
         * for(int i=1; i<n; i++){
         * 
         * if(pos.get(nums[i]) < pos.get(nums[i-1])){
         * 
         * result += (n - i);
         * 
         * }
         * }
         * return result;
         * 
         */
    }
}
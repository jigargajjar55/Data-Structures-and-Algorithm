package Hashing;
import java.util.*;

public class NumberOfGoodPairs_LC1512 {
    
    // https://leetcode.com/problems/number-of-good-pairs/solutions/1457646/java-story-based-0ms-single-pass-easy-to-understand-simple-hashmap/?envType=daily-question&envId=2023-10-03

    //Time: O(N)
    //Space: O(N)
    public int numIdenticalPairs(int[] nums) {

        int n = nums.length;
        Map<Integer, Integer> hall = new HashMap<>();
        int handShake = 0;

        for(int i=0; i<n; i++){

            int likeMinded = hall.getOrDefault(nums[i], 0);

            handShake += likeMinded;

            hall.put(nums[i], likeMinded + 1);
        }

        return handShake;
        
    }
}
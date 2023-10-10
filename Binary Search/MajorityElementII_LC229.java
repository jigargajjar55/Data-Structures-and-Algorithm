import java.util.*;

public class MajorityElementII_LC229 {

    //Optimise Approach
    //Time: O(N)
    //Space: O(1)
    public List<Integer> majorityElement(int[] nums) {

        int n = nums.length;
        int times = n / 3;

        List<Integer> result = new ArrayList<>();
        
        int element1 = -(int)(1e9+1);
        int cnt1 = 0;
        int element2 = -(int)(1e9+1);
        int cnt2 = 0;

        for(int i=0; i<n; i++){

            if(cnt1 == 0 && nums[i] != element2){
                cnt1 = 1;
                element1 = nums[i];
            }else if(cnt2 == 0 && nums[i] != element1){
                cnt2 = 1;
                element2 = nums[i];
            }else if(nums[i] == element1){
                cnt1++;
            }else if(nums[i] == element2){
                cnt2++;
            }else{
                cnt1--;
                cnt2--;
            }
        }

        int c1 = 0;
        int c2 = 0;

        for(int i=0; i<n; i++){
            if(nums[i] == element1){
                c1++;
            }else if(nums[i] == element2){
                c2++;
            }
        }

        if(c1 > times){
            result.add(element1);
        }
        if(c2 > times){
            result.add(element2);
        }

        return result;
        
    }

    // One Pass Solution
    // Time: O(N)
    // Space: O(N)
    public List<Integer> majorityElement2(int[] nums) {

        int n = nums.length;
        int times = n / 3;

        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap<>();

        for (int i = 0; i < n; i++) {

            int countOfCurrElement = freq.getOrDefault(nums[i], 0);

            freq.put(nums[i], countOfCurrElement + 1);

            if (countOfCurrElement == times) {
                result.add(nums[i]);
            }
        }

        return result;

    }

    // Good Approach
    // Time: O(2 * N)
    // Space: O(N)
    public List<Integer> majorityElement1(int[] nums) {

        int n = nums.length;
        int times = n / 3;

        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap<>();

        for (int i = 0; i < n; i++) {

            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }

        for (int key : freq.keySet()) {

            int value = freq.get(key);

            if (value > times) {
                result.add(key);
            }
        }

        return result;

    }
}
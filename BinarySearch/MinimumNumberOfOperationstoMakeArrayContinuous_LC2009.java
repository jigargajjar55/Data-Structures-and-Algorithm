package BinarySearch;
import java.util.*;

public class MinimumNumberOfOperationstoMakeArrayContinuous_LC2009 {
    

    // NeetCode explaination video:  https://www.youtube.com/watch?v=Dd-yJylrcOY

    //Bruteforce approach
    //Time: O((N * log N) + (N ^ 2))
    //Space: O(N)
    public int minOperations1(int[] nums) {

        int n = nums.length;
        Arrays.sort(nums);

        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }

        int result = n;

        for(int i=0; i<n; i++){

            int mini = nums[i];
            int maxi = mini + n - 1;

            int notExist = 0;

            for(int j=mini; j<=maxi; j++){
                if(set.contains(j)){
                    continue;
                }
                notExist++;
            }
            result = Math.min(result, notExist);

        }
        return result;
    }


    //Optimized Approach
    //Time: O(N * log N)
    //Space: O(N)
    public int minOperations(int[] nums) {

        int n = nums.length;
        Arrays.sort(nums);

        List<Integer> uniqueNums = new ArrayList<>();
        uniqueNums.add(nums[0]);

        for(int i=1; i<n; i++){

            if(nums[i-1] != nums[i]){
                uniqueNums.add(nums[i]);
            }
        }

        int minOps = n;
        int size = uniqueNums.size();
        
        int right = 0;        

        for(int left=0; left<size; left++){

            
            //We need to check for range [left, right], right = left + n - 1

            while((right < size) && uniqueNums.get(right) < uniqueNums.get(left) + n){
                right++;
            }

            int windowSize = right - left;
            int opsCount = n - windowSize;

            minOps = Math.min(minOps, opsCount);
        }

        return minOps;
        
    }
}

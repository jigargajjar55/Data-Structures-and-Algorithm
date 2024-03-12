package BinarySearch;
import java.util.*;

class MinimizetheMaximumDifferenceofPairs_LC2616 {
    
    //Time: O((N * log N) + (N * log(max Diff in nums Array)), Space: O(1)
    private boolean isPossible(int mid,int[] nums,int p){
        int pair = 0;
        int n = nums.length;
        for(int i=1; i<n; i++){
            if(nums[i] - nums[i-1] <= mid){
                pair++;
                i++;
            }
        }

        if(pair >= p){
            return true;
        }else{
            return false;
        }

    }
    public int minimizeMax(int[] nums, int p) {

        int n = nums.length;
        //First Step: Sort the elements
        Arrays.sort(nums);

        int start = 0;
        int end = nums[n-1] - nums[0];
        int ans = 0;

        while(start <= end){
            int mid = start + ((end - start)/2);

            if(isPossible(mid,nums,p)){
                ans = mid;
                end = mid - 1;                
            }else{
                start = mid + 1;
            }
        }

        return ans;
        
    }
}
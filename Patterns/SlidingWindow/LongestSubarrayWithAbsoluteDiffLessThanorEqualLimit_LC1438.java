package Patterns.SlidingWindow;
import java.util.*;


public class LongestSubarrayWithAbsoluteDiffLessThanorEqualLimit_LC1438 {


    //https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/solutions/609743/java-detailed-explanation-sliding-window-deque-o-n/?envType=daily-question&envId=2024-06-23
    //Time: O(2 * N)
    //Space: O(2 * N)
    public int longestSubarray(int[] nums, int limit) {
        
        int result = 0;
        int n = nums.length;
        Deque<Integer> minDQ = new ArrayDeque<>();
        Deque<Integer> maxDQ = new ArrayDeque<>();

        int left = 0;

        for(int right=0; right<n; right++){

            while(!minDQ.isEmpty() && minDQ.peekLast() > nums[right]){
                minDQ.pollLast();
            }
            while(!maxDQ.isEmpty() && maxDQ.peekLast() < nums[right]){
                maxDQ.pollLast();
            }
            minDQ.offerLast(nums[right]);
            maxDQ.offerLast(nums[right]);

            while(maxDQ.peekFirst() - minDQ.peekFirst() > limit){

                if(maxDQ.peekFirst() == nums[left]){
                    maxDQ.pollFirst();
                }
                if(minDQ.peekFirst() == nums[left]){
                    minDQ.pollFirst();
                }
                left++;
            }

            result = Math.max(result, (right - left + 1));          
                     

        }

        return result;
    }
}
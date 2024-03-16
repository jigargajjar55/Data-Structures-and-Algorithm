package Patterns.SlidingWindow;
import java.util.*;

public class SlidingWindowMaximum_LC239 {
    

    
    //Time: O(N), Space: O(K) {For Array Deque}
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] result = new int[n-k+1];
        int index = 0;
        ArrayDeque<Integer> dq = new ArrayDeque<>();

        for(int i=0; i<k; i++){

            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i]){
                dq.pollLast();
            }
            dq.offerLast(i);
        }

        result[index] = nums[dq.peekFirst()];
        index++;

        for(int i=k; i<n; i++){

            if(!dq.isEmpty() && (i-dq.peekFirst() >= k)){
                dq.pollFirst();
            }

            while(!dq.isEmpty() && nums[dq.peekLast()] < nums[i]){
                dq.pollLast();
            }
            dq.offerLast(i);

            result[index] = nums[dq.peekFirst()];
            index++;
        }

        return result;
        
    }
}
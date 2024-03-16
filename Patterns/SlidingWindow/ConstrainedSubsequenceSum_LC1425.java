package Patterns.SlidingWindow;
import java.util.*;

public class ConstrainedSubsequenceSum_LC1425 {

    //Time: O(N * K)    
    //Space: O(N)
    public int constrainedSubsetSum1(int[] nums, int k) {

        int n = nums.length;
        int[] dp = new int[n];
        int result = Integer.MIN_VALUE;
       
        //dp[i] is the max sum we can have from A[:i] when A[i] has been chosen.
        //So we started inner loop to get max Sum from i-k to i, and add with current value in dp[i]
        for(int i = 0; i<n; i++){

            int maxi = 0;

            for(int j = Math.max(i-k, 0); j<i; j++){

                maxi = Math.max(maxi, dp[j]);                
            }

            dp[i] = nums[i] + maxi;

            result = Math.max(result, dp[i]);
            
        }

       
        return result;
        
    }
    

    
    //Time: O(N)    
    //Space: O(N)
    //We use Sliding Window Maximum logic
    public int constrainedSubsetSum2(int[] nums, int k) {

        int n = nums.length;
        int[] dp = new int[n];
        int result = Integer.MIN_VALUE;
        Deque<Integer> dq = new ArrayDeque<>();

        for(int i=0; i<n; i++){

            int maxi = Math.max(0, dq.isEmpty() ? 0 : dp[dq.peekFirst()]);

            dp[i] = nums[i] + maxi;

            result = Math.max(result, dp[i]);

            while(!dq.isEmpty() && dp[dq.peekLast()] <= dp[i]){
                dq.pollLast();
            }            
           
            dq.offerLast(i);

            while(!dq.isEmpty() && (i - dq.peekFirst()) >= k){

                dq.pollFirst();
            }
            
        }

        return result;
        
    }


    //Time: O(N * log N)
    //Space: O(N)
    //Greedy Approach
    public int constrainedSubsetSum3(int[] nums, int k) {

        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1,p2) -> Integer.compare(p2[0],p1[0]));
        int maxi = nums[0];
        pq.offer(new int[]{maxi, 0});        

        for(int i=1; i<n; i++){


            while(!pq.isEmpty() && (i - pq.peek()[1]) > k){
                pq.poll();
            }

            int curr = Math.max(0, pq.peek()[0]) + nums[i];

            if(curr > maxi){
                maxi = curr;
            }

            pq.offer(new int[]{curr, i});
        }

        return maxi;        
    }

}

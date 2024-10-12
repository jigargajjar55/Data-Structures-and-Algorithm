package Algorithms.SlidingWindow;
import java.util.*;

public class MaximumSumofAlmostUniqueSubarray_LC2841 {
    

    
    //Time: O(N),
    //Space: O(2 * K){for HashMap and Queue}
    public long maxSum(List<Integer> nums, int m, int k) {
        
        long result = 0;
        
        int size = nums.size();
        Map<Integer,Integer> freq = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        
        long tempAns = 0;
        
        for(int i=0; i<k; i++){
            
            int curr = nums.get(i);
            q.offer(i);
            
            freq.put(curr, freq.getOrDefault(curr, 0)+1);
            
            tempAns += nums.get(i);             
            
        }
        
        if(freq.size() >= m && tempAns > result){
            result = tempAns;
        }
        
        
        for(int i=k; i<size; i++){
            
            if(!q.isEmpty() && i-q.peek() >= k){
                int topIndex = q.poll();
                tempAns -= nums.get(topIndex);
                
                if(freq.get(nums.get(topIndex)) == 1){
                    freq.remove(nums.get(topIndex));
                }else{
                    int prevFreq = freq.get(nums.get(topIndex));
                    freq.put(nums.get(topIndex), prevFreq - 1);
                }
            }
            
            int curr = nums.get(i);
            q.offer(i);
            
            freq.put(curr, freq.getOrDefault(curr, 0)+1);
            
            tempAns += nums.get(i);   
            
            
            
            if(freq.size() >= m && tempAns > result){
                result = tempAns;
            }
            
            
        }
        
       
        return result;
        
    }
}
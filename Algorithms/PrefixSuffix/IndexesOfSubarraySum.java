package Algorithms.PrefixSuffix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IndexesOfSubarraySum {
    

    // https://www.geeksforgeeks.org/problems/subarray-with-given-sum-1587115621/1
    //Time: O(N)
    //Space: O(N)
    public ArrayList<Integer> subarraySum(int[] arr, int target) {
        
        int n = arr.length;
        ArrayList<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int prefixSum = 0;
        
        for(int i=0; i<n; i++){
            
            prefixSum += arr[i];
            
            if(map.containsKey(prefixSum - target)){
                result.add(map.get(prefixSum - target)+2);
                result.add(i+1);
                break;
            }
            
            map.put(prefixSum, i);
            
            
        }
        
        if(result.size() == 0){
            result.add(-1);
        }
        
        return result;
        
        
    }
}

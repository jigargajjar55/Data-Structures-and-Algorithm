package Patterns.SlidingWindow;

public class CountSubarraysWhereMaxElementAppearsatLeastKTimes_LC2962 {
    

    //Optimise approach
    //Time: O(2 * N)
    //Space: O(1)
    public long countSubarrays(int[] nums, int k) {

        int n = nums.length;

        long totalSubArraysCount = (((long)n * (n + 1))/2);        
        long badSubArraycount = 0;
       
        int freq = 0;
        int maxi = 0;
        //Find max element from nums Array
        for(int i=0; i<n; i++){
            if(nums[i] > maxi){
                maxi = nums[i];
            }
        }

        //We have to find no. of subarrays where max element appears atleast k times
        //Instead of finding subarrays where max element appears atleast k times,
        //We will get nop. of subarrays which has max element <k times
        //We will get ans = totalSubArrays - subarrays with max element <k times

        int left = 0;
        for(int right=0; right<n; right++){
            
            if(nums[right] == maxi){
                freq++;
            }

            while(freq >= k){

                if(nums[left] == maxi){
                    freq--;                    
                }
                left++;
            }

            badSubArraycount += (right - left + 1);
        }

        // System.out.println(n);
        // System.out.println(totalSubArraysCount +" : "+badSubArraycount);

        long result = totalSubArraysCount - badSubArraycount;
        return result;

       
    }
}
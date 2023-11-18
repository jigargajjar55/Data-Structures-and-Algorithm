package Algorithms.MooreVotingAlgorithm;
public class MajorityElement_LC169 {

    //Time: O(2 * N)
    //Space: O(1)
    public int majorityElement(int[] nums) {

        int n = nums.length;

        int element = 0;
        int count = 0;

        for(int i=0; i<n; i++){

            if(count == 0){
                count = 1;
                element = nums[i];
            }else if(nums[i] == element){
                count++;
            }else{
                count--;
            }
        }

        int count1 = 0;
        for(int i=0; i<n; i++){
            if(nums[i] == element){
                count1++;
            }
        }

        if(count1 > (n / 2)){
            return element;
        }else{
            return -(int)(1e9+7);
        }
        
    }
    
}

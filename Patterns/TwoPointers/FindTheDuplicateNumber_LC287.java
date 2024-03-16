package Patterns.TwoPointers;
public class FindTheDuplicateNumber_LC287 {

    // https://leetcode.com/problems/find-the-duplicate-number/solutions/1892921/9-approaches-count-hash-in-place-marked-sort-binary-search-bit-mask-fast-slow-pointers/?envType=daily-question&envId=2023-09-19
    //Using LinkedList Cycle detection algorithm
    private int checkIfDuplicateExist(int[] nums){

        int n = nums.length;

        int slow = 0;
        int fast = 0;

        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
            if(fast == n){
                break;
            }
        }while(slow != fast);

        if(fast == n){
            return -1;
        }else{
            return slow;
        }

    }
    //Time: O(N),
    //Space: O(1)
    public int findDuplicate(int[] nums) {
        

        int slow = checkIfDuplicateExist(nums);

        //If no duplicate number exist
        if(slow == -1){
            return 0;
        }

        int fast = 0;
        while(fast != slow){
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
        
    }
}
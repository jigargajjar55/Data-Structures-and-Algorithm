package BinarySearch;
import java.util.*;

public class SearchInRotatedSortedArrayII_LC81 {
    

    
    public boolean search(int[] nums, int target) {

        //Time: For Avg Case: O(log N), For Worst Case: O(N)
        //Space: O(1)

        int n = nums.length;
        int start = 0;
        int end = n-1;

        while(start <= end){

            int mid = start + ((end - start)/2);

            //If target is found
            if(nums[mid] == target){
                return true;
            }

            //Checking if it's in first half            
            if(nums[start] < nums[mid]){
                //If target is in first half, move in first half
                if(nums[start] <= target && nums[mid] >= target){
                    end = mid - 1;
                }else{
                    //else move in second half
                    start = mid + 1;
                }
            }
            //Checking if it's in second half
            else if(nums[start] > nums[mid]){
                 //If target is in second half, move in second half
                if(nums[mid] <= target && nums[end] >= target){
                    start = mid + 1;
                }else{
                    //else move in first half
                    end = mid - 1;
                }

            }
            //If it's duplicate values, increase pointer by 1
            else{
                start++;
            }
        }

        return false;
        
    }
}
package Array;

public class MonotonicArray_LC896 {

    //If we just do two pass
    //Time: O(2 * N),
    //Space: O(1)
    public boolean isMonotonic2(int[] nums) {

        int n = nums.length;
        if(n == 1){
            return true;
        }

        boolean isMonotonic = true;

        //For Checking monotone increasing
        for(int i=0; i<n-1; i++){

            if(nums[i] > nums[i+1]){
                isMonotonic = false;
                break;
            }
        }

        if(isMonotonic){
            return true;
        }

        isMonotonic = true;

        //For Checking monotone decreasing
        for(int i=0; i<n-1; i++){

            if(nums[i] < nums[i+1]){
                isMonotonic = false;
                break;
            }
        }

        return isMonotonic; 

        
    }
    

    //If we just do one pass
    //Time: O(N),
    //Space: O(1)
    public boolean isMonotonic1(int[] nums) {

        int n = nums.length;
        int inc = 1;
        int dec = 1;
        
        for(int i=1; i<n; i++){

            if(nums[i] > nums[i-1]){
                inc++;
            }else if(nums[i] < nums[i-1]){
                dec++;
            }else{
                inc++;
                dec++;
            }
        }

        boolean ans = (inc == n || dec == n);
        return ans;
        
    }
}
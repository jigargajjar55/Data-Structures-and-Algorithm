package BinarySearch;
public class FindFirstandLastPositionofElementInSortedArray_LC34 {
    

    
    //Time: O(log N),
    //Space: O(1)
    private int getOccOfElement(int[] nums,int n,int target, boolean flag){

        int start = 0;
        int end = n-1;

        int ansIndex = -1;

        while(start <= end){

            int mid = start + ((end - start)/2);

            if(nums[mid] == target){
                ansIndex = mid;

                if(flag){
                    end = mid - 1;
                }else{
                    start = mid + 1;
                }
            }else if(nums[mid] < target){

                start = mid + 1;
            }else{
                end = mid - 1;
            }
        } 

        return ansIndex;
    }
    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;

        int[] result = new int[]{-1,-1};

        int firstOcc = getOccOfElement(nums,n,target,true);

        if(firstOcc == -1){
            return result;
        }

        int lastOcc = getOccOfElement(nums,n,target,false);

        result[0] = firstOcc;
        result[1] = lastOcc;

        return result;
        
        
    }
}
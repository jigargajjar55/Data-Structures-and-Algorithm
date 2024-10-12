package Algorithms.TwoPointers;
class SquaresofaSortedArray_LC977 {
    //Time: O(N)
    //Space: O(1)
    public int[] sortedSquares(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];

        int left = 0;  
        int right = n - 1;      

        for(int index = n-1; index >= 0; index--){

            int rightSquare = (nums[right] * nums[right]);
            int leftSquare = (nums[left] * nums[left]);

            if(rightSquare > leftSquare){

                result[index] = rightSquare;
                right--;

            }else{

                result[index] = leftSquare;
                left++;
            }
        }

        return result;
        
    }
}

public class SumofAbsoluteDifferencesinSortedArray_LC1685{



    //Time: O(2 * N)
    //Space: O(1)
    public int[] getSumAbsoluteDifferences(int[] nums) {

        int totalSum = 0;
        int n = nums.length;
        for(int i=0; i<n; i++){
            totalSum += nums[i];
        }
        int[] result = new int[n];
        int prefSum = 0;
        for(int i=0; i<n; i++){

            int leftSum = prefSum;
            int rightSum = totalSum - leftSum - nums[i];

            result[i] = ((i * nums[i]) - leftSum) + (rightSum - ((n - i - 1) * nums[i]));

            prefSum += nums[i]; 


        }

        return result;
        
    }




    
    //Time: O(5 * N)
    //Space: O(4 * N)
    public int[] getSumAbsoluteDifferences1(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];
        int[] prefSum = new int[n];
        prefSum[0] = 0;
        int[] suffSum = new int[n];
        suffSum[n-1] = 0;

        for(int i=1; i<n; i++){
            prefSum[i] = prefSum[i-1] + nums[i-1];
        }

        for(int i=n-2; i>=0; i--){
            suffSum[i] = suffSum[i+1] + nums[i+1];
        }
        int[] prefDiff = new int[n];
        prefDiff[0] = 0;
        int[] suffDiff = new int[n];
        suffDiff[n-1] = 0;

        for(int i=1; i<n; i++){
            prefDiff[i] = (i * nums[i]) - prefSum[i]; 
            
        }

        for(int i=n-2; i>=0; i--){
            suffDiff[i] = suffSum[i] - ((n-i-1) * nums[i]);
        }

        for(int i=0; i<n; i++){
            result[i] = prefDiff[i] + suffDiff[i];
        }
       
        return result;
        
    }
}
public class SortArrayByParity_LC905 {
    

    //Time: O(N),
    //Space: O(N){Space for result array}
    public int[] sortArrayByParity(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];
        for(int i=0; i<n; i++){
            result[i] = nums[i];
        }

        int even = 0;
        int odd = n-1;

        while(even < odd){

            while((even < odd) && ((result[even] & 1) != 1)){
                even++;
            }

            while((even < odd) && ((result[odd] & 1) == 1)){
                odd--;
            }

            if(even < odd){
                int temp = result[even];
                result[even] = result[odd];
                result[odd] = temp;
                even++;
                odd--;
            }
        }

        return result;
        
    }
}
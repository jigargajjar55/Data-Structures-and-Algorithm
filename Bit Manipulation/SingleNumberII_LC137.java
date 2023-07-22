class Solution {
    public int singleNumber(int[] nums) {

        //Time: O(32 * N), Space: O(1)

        int ans = 0;
        int n = nums.length;

        for(int bit=0; bit<32; bit++){

            int sum = 0;

            for(int i=0; i<n; i++){

                if(((nums[i] >> bit) & 1) == 1){
                    sum++;
                    sum = sum % 3;
                }
                
            }

            if(sum != 0){
                ans |= (sum << bit);
            }
        }

        return ans;


        
    }
}
package Array;

public class Findsubarraywithequalsum {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    class Solution {
        public boolean findSubarrays(int[] nums) {

            int subAsum = 0;
            int subBsum = 0;

            for (int i = 0; (i + 1) < nums.length; i++) {

                subAsum = nums[i] + nums[i + 1];

                for (int j = i + 1; (j + 1) < nums.length; j++) {
                    subBsum = nums[j] + nums[j + 1];

                    if (subAsum == subBsum) {
                        return true;
                    }
                }

            }

            return false;
        }
    }

}

package Patterns.PrefixSuffix;

import java.util.Arrays;

public class ProductofArrayExceptSelf_LC238 {

    // https://leetcode.com/problems/product-of-array-except-self/solutions/1342916/3-minute-read-mimicking-an-interview/
    
    //Using prefix and suffix sum using arrays
    //Time: O(N)
    //Space: O(N)
    public int[] productExceptSelf1(int[] nums) {

        int n = nums.length;

        int[] result = new int[n];
        int[] pref = new int[n];
        pref[0] = 1;
        int[] suff = new int[n];
        suff[n-1] = 1;

        for(int i=1; i<n; i++){
            pref[i] = pref[i-1] * nums[i-1];
        }

        for(int i=n-2; i>=0; i--){
            suff[i] = suff[i+1] * nums[i+1];
        }

        for(int i=0; i<n; i++){
            result[i] = pref[i] * suff[i];
        }        
        return result;
    }


    //Without using arrays
    //We will try to do same as we done in prefix-suffix appraoch
    //We will store multiplication in result array itself
    //Time: O(N)
    //Space: O(1)
    public int[] productExceptSelf(int[] nums) {

        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, 1);
        //First we will try to do prefix multiplication
        int prefixProduct = 1;

        for(int i=0; i<n; i++){
            result[i] *= prefixProduct;
            prefixProduct *= nums[i];
        }

        //Second we will try to do suffix multiplication
        int suffixProduct = 1;

        for(int i=n-1; i>=0; i--){
            result[i] *= suffixProduct;
            suffixProduct *= nums[i];
        }

        return result;
        
    }


}

package Strings;

import java.util.HashSet;
import java.util.Set;

public class FindUniqueBinaryString_LC1980 {



    //Brute-force Approach
    //Time: O((N ^ 2) + (2 ^ N))
    //Space: O(N)
    private String getBinaryString(int num,int n){

        StringBuilder result = new StringBuilder();

        for(int i=n-1; i>=0; i--){
            int bit = (num >> i) & 1;

            result.append(bit);
        }

        return result.toString();

        
    }
    
    private int getNum(String num, int n){
        int result = 0;

        for(int i=0; i<n; i++){
            char ch = num.charAt(i);

            if(ch == '1'){
                result = result | (1 << (n-i-1));
            }
        }
        return result;
    }
    
    public String findDifferentBinaryString(String[] nums) {

        int n = nums.length;
        Set<Integer> set = new HashSet<>();

        for(int i=0; i<n; i++){

            int num = Integer.valueOf(nums[i],2);

           // int num = getNum(nums[i],n);
            //System.out.println(num);
            set.add(num);
        }

        int start = 0;
        int end = (int)(Math.pow(2,n));
        String result = "";

        for(int num = start; num<end; num++){

            if(!set.contains(num)){

                result = getBinaryString(num,n);
                break;
                
            }
        }

        return result;
        
    }







    
    //Optimised Approach
    //Time: O(N)
    //Space: O(N)
    public String findDifferentBinaryString1(String[] nums) {

        int n = nums.length;
        char[] result = new char[n];

        for(int i=0; i<n; i++){

            result[i] = nums[i].charAt(i) == '0' ? '1' : '0';
        }
        return new String(result);
        
    }
}
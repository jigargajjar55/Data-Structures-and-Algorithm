package Strings;

public class CheckIfStringsCanbeMadeEqualWithOperationsI_LC2839 {

    //Time: O(N + 26)
    //Space: O(2 * 26)
    public boolean canBeEqual(String s1, String s2) {

        int n = s1.length();
        int[] oddIndex = new int[26];
        int[] evenIndex = new int[26];

        for(int i=0; i<n; i++){

            int ptr1 = (int)(s1.charAt(i) - 'a');
            int ptr2 = (int)(s2.charAt(i) - 'a');

            if((i & 1) == 1){                
                oddIndex[ptr1]++;                
                oddIndex[ptr2]--;
            }else{

                evenIndex[ptr1]++;                
                evenIndex[ptr2]--;
            }
        }

        boolean isEqual = true;

        for(int i=0; i<26; i++){

            if((oddIndex[i] != 0) || (evenIndex[i] != 0)){
                isEqual = false;
                break;
            }
        }

        return isEqual;
        
    }
}
package DynamicProgramming.Strings;
public class LongestPalindromicSubstring_LC5 {
    

    //BruteForce approach:
    //Checking each substring, if its palindrome or not
    //Time: O(N ^ 3)
    //Space: O(N ^ 2)
    private boolean isPalindrome(String s,int n){
       
        int start = 0;
        int end = n-1;
        while(start <= end){

            if(s.charAt(start) != s.charAt(end)){
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    
    public String longestPalindrome(String s) {

        int n = s.length();
        //System.out.println(n);

        String result = "";

        for(int i=0; i<n; i++){

            int resultLength = result.length();

            //If we get certain result string length, we are bound to get substring greater than that
            for(int j= i + resultLength; j<n; j++){

                String temp = s.substring(i,j+1);
                int tempLength = temp.length();
                //System.out.println(temp + " " + isPalindrome(temp));

                if(isPalindrome(temp,tempLength) && (resultLength < tempLength)){
                    result = temp;
                }

            }

        }

        return result;
        
    }
}

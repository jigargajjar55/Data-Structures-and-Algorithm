package Strings;

public class IsSubsequence_LC392 {
    

    
    
    //Time: O(M){Length of String1}
    //Space: O(1) 
    public boolean isSubsequence(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();
        
        int ptr1 = 0;
        int ptr2 = 0;

        while(ptr1 < sLength && ptr2 < tLength){
            if(s.charAt(ptr1) == t.charAt(ptr2)){
                ptr1++;
            }
            ptr2++;
        }

        if(ptr1 == sLength){
            return true;
        }else{
            return false;
        }
        
    }
}
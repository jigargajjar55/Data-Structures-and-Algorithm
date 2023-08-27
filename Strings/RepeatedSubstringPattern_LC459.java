package Strings;

public class RepeatedSubstringPattern_LC459 {
    

    //Time: O(N ^ 2), Space: O(N) {N: Length of String}
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int i = 0;

        while(i < n){

            String substr = s.substring(0,i+1);
            //System.out.println(substr);
            
            int j = i+1;
            boolean canBeRepeated = false;
            while(j < n){
                if(j+i+1 <= n){
                    String newstr = s.substring(j,j+i+1);
                    if(newstr.equals(substr)){
                        j += i + 1; 
                        canBeRepeated = true;
                    }else{                        
                        canBeRepeated = false;                   
                        break;
                    }
                }else{
                    canBeRepeated = false;
                    break;
                }
            }

            if(canBeRepeated){
                return true;
            }
            i += 1; 
        }

        return false;
        
    }
}
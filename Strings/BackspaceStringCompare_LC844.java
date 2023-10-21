package Strings;

public class BackspaceStringCompare_LC844 {
    

    //Time: O(M + N)
    //Space: O(M + N)
    public boolean backspaceCompare(String s, String t) {
        int m = s.length();
        int n = t.length();

        StringBuilder s1 = new StringBuilder();
        StringBuilder t1 = new StringBuilder();

        for(int i=0; i<m; i++){
            char currCh = s.charAt(i);
            int size = s1.length();

            if(currCh == '#'){
                if(size > 0){
                    s1.deleteCharAt(size-1);
                }
               
            }else{
                s1.append(currCh);
            }
        }
        for(int i=0; i<n; i++){
            char currCh = t.charAt(i);
            int size = t1.length();

            if(currCh == '#'){
                if(size > 0){
                    t1.deleteCharAt(size-1);
                }
                
            }else{
                t1.append(currCh);
            }
        }

        if(s1.length() == t1.length()){

            for(int i=0; i<s1.length(); i++){

                if(s1.charAt(i) != t1.charAt(i)){
                    return false;
                }
            }

            return true;
        }else{
            return false;
        }
    }
}

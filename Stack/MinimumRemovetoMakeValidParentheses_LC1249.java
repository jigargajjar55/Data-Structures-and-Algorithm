package Stack;

import java.util.*;

class MinimumRemovetoMakeValidParentheses_LC1249 {
    private class Pair{
        int index;
        char ch;
        Pair(int index, char ch){
            this.index = index;
            this.ch = ch;
        }
    }
    //Time: O(2 * N)
    //Space: O(N)
    public String minRemoveToMakeValid(String s) {

        StringBuilder result = new StringBuilder();
        int n = s.length();
        Stack<Pair> st = new Stack<>();

        for(int i=0; i<n; i++){
            char currCh = s.charAt(i);

            if(currCh == '('){
                  
                int size = result.length();

                st.push(new Pair(size,currCh));

                result.append(currCh);  
                 
            }else if(currCh == ')'){

                if(st.size() > 0){
                    result.append(currCh);
                    st.pop();
                }

            }else{
                result.append(currCh);
            }
        }

        while(!st.isEmpty()){
            Pair top = st.pop();
            int removeIndex = top.index;
            result.deleteCharAt(removeIndex);
        }

        return result.toString();
        
    }
}
package Stack;

import java.util.Stack;

public class LongestValidParentheses {

    //https://www.geeksforgeeks.org/length-of-the-longest-valid-substring/
    
     //Time: O(N)
    //Space: O(N)
    static int maxLength(String str){
        
        int n = str.length();
        Stack<Integer> st = new Stack<>();
        
        for(int i=0; i<n; i++){
            
            char ch = str.charAt(i);
            
            if(ch == '('){
                st.push(i);
            }else{
                
                if(!st.isEmpty() && str.charAt(st.peek()) == '('){
                    st.pop();
                }else{
                    st.push(i);
                }
            }
        }
        
        int result = 0;
        
        int end = n;
        
        while(!st.isEmpty()){
            int start = st.pop();
            
            result = Math.max(result, end - start - 1);
            
            end = start;
        }
        
        result = Math.max(result, end);
        
        return result;
        
        
    }
}

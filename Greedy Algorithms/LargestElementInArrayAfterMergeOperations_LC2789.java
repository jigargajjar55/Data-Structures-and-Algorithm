import java.util.*;

public class LargestElementInArrayAfterMergeOperations_LC2789 {

    
    public long maxArrayValue(int[] nums) {
        
        //Time: O(N), Space: O(N)
        int n = nums.length;        
        Stack<Long> st = new Stack<>();         
        
        for(int i=n-1; i>=0; i--){
            
            int num = nums[i];
            
            if(st.isEmpty()){
                st.push((long)num);
            }else{
                
                long top = st.peek();
                
                if(num <= top){
                    st.pop();
                    st.push(top + (long)num);
                }else{
                    st.push((long)num);
                }
            }
        }
        
        long result = -1;
        
        while(!st.isEmpty()){
            long top = st.peek();
            st.pop();
            
            result = Math.max(top, result);
        }
        
        return result;
        
    }
}
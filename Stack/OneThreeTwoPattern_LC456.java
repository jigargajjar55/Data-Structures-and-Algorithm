package Stack;
import java.util.*;

public class OneThreeTwoPattern_LC456 {
    

    
    //Time: O(N),
    //Space: O(N)
    public boolean find132pattern(int[] nums) {

        int n = nums.length;
        Stack<Integer> st = new Stack<>();

        int middle = Integer.MIN_VALUE;

        for(int i=n-1; i>=0; i--){

            //Stack to maintain maximum value in selected subsequence
            while(!st.isEmpty() && st.peek() < nums[i]){
                //If stack top has lower value than current, It should pop and consider as middle element
                middle = st.peek();
                st.pop();
            }

            //If current element is grater than middle element, need to push into stack
            if(nums[i] > middle){
                st.push(nums[i]);
            }
            //If current element is lower than middle element,we will return true
            //since stack has maximum value of subsequence
            if(nums[i] < middle){
                return true;
            }
        }

        //If we don't found any subsequence, return false
        return false;
        
    }
}
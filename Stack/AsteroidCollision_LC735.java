package Stack;
import java.util.*;

public class AsteroidCollision_LC735 {
    

    //Time: O(2 * N), Space: O(N)
    public int[] asteroidCollision(int[] asteroids) {

        int n = asteroids.length;
        Stack<Integer> st = new Stack<>();
        st.push(asteroids[0]);

        for(int i=1; i<n; i++){
            int asteroid = asteroids[i];

            if(asteroid < 0){            

                while(!st.isEmpty() && st.peek() > 0 && st.peek() < Math.abs(asteroid)){
                    st.pop();
                }
                if(!st.isEmpty() && st.peek() == Math.abs(asteroid)){
                    st.pop();
                    continue;
                } 
                if(!st.isEmpty() && st.peek() > Math.abs(asteroid)){
                    continue;
                }

                if(st.isEmpty() || st.peek() < 0){
                    st.push(asteroid);
                }

            }else{
            
                st.push(asteroid);   
            }        
        }
        
        int size = st.size();
        int[] result = new int[size];
        
        for(int index=size-1; index>=0; index--){
            result[index] = st.peek();
            st.pop();
        }
       

        return result;
        
    }
}
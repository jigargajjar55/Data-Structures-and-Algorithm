import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class MinimumDistanceBetweenThreeEqualElementsII_LC3741 {
    

    //Time: O(N)
    //Space: O(N)
    public int minimumDistance(int[] nums) {

        int len = nums.length;
        Map<Integer, Deque<Integer>> map = new HashMap<>();
        int result = Integer.MAX_VALUE;

        for(int i=0; i<len; i++){
            int num = nums[i];

            Deque<Integer> dq;

            if(map.containsKey(num)){
                dq = map.get(num);

                if(dq.size() == 2){
                    result = Math.min(result, 2 * (i - dq.pollFirst()));
                }
            }else{
                dq = new ArrayDeque<>();
            }

            dq.offerLast(i);

            map.put(num, dq);
        }

        //Edge Case
        if(result == Integer.MAX_VALUE){
            return -1;
        }

        return result;
        
    }
}

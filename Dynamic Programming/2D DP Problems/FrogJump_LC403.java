import java.util.*;

public class FrogJump_LC403 {
    

    
    //Time: O(Exponential), Space: O(N){Aux. Stack space, N: Length of Array}
    private boolean solveByRecursion(int index,int jump,int n,int[] stones, Map<Long, Integer> stoneMap){

        //Base Case
        if(index == n - 1){
            return true;
        }

        boolean result = false;

        long nextStoneValue = stones[index] + jump - 1;
        
        if((index != 0) && (jump - 1 > 0) && stoneMap.containsKey(nextStoneValue)){
            boolean ans1 = solveByRecursion(stoneMap.get(nextStoneValue),jump-1,n,stones,stoneMap);
            result |= ans1;
        }

        nextStoneValue = stones[index] + jump;

        if(stoneMap.containsKey(nextStoneValue)){
            boolean ans2 = solveByRecursion(stoneMap.get(nextStoneValue),jump,n,stones,stoneMap);
            result |= ans2;
        }

        nextStoneValue = stones[index] + jump + 1;

        if((index != 0) && stoneMap.containsKey(nextStoneValue)){
            boolean ans3 = solveByRecursion(stoneMap.get(nextStoneValue),jump+1,n,stones,stoneMap);
            result |= ans3;
        }

        return result;

    }

       
    //Time: O(N * Jump), Space: O(N + (N * Jump)){Aux. Stack space and 2D DP Map, N: Length of Array} 
    private boolean solveByTopDownDP(int index,int jump,int n,int[] stones, Map<Long, Integer> stoneMap,Map<List<Integer>, Boolean> dp){

        //Base Case
        if(index == n - 1){
            return true;
        }

        List<Integer> curr = new ArrayList<>();
        curr.add(index);
        curr.add(jump);

        //Overlapping sub-problem
        if(dp.containsKey(curr)){
            return dp.get(curr);
        }

        boolean result = false;
        long nextStoneValue = stones[index] + jump - 1;
        
        if((index != 0) && (jump - 1 > 0) && stoneMap.containsKey(nextStoneValue)){
            boolean ans1 = solveByTopDownDP(stoneMap.get(nextStoneValue),jump-1,n,stones,stoneMap,dp);
            result |= ans1; 
        }

        nextStoneValue = stones[index] + jump;

        if(stoneMap.containsKey(nextStoneValue)){
            boolean ans2 = solveByTopDownDP(stoneMap.get(nextStoneValue),jump,n,stones,stoneMap,dp);
            result |= ans2;           
        }

        nextStoneValue = stones[index] + jump + 1;

        if((index != 0) && stoneMap.containsKey(nextStoneValue)){
            boolean ans3 = solveByTopDownDP(stoneMap.get(nextStoneValue),jump+1,n,stones,stoneMap,dp);
            result |= ans3;            
        }

        dp.put(curr, result);

        return result;
    }

    public boolean canCross(int[] stones) {

        int n = stones.length;
        
        Map<Long, Integer> stoneMap = new HashMap<>();
        for(int i=0; i<n; i++){
            stoneMap.put((long)stones[i], i);
        } 

        //return solveByRecursion(0,1,n,stones,stoneMap);

    
        Map<List<Integer>, Boolean> dp = new HashMap<>();

        return solveByTopDownDP(0,1,n,stones,stoneMap,dp);
        
    }
}
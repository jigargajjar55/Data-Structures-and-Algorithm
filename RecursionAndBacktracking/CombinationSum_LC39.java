package RecursionAndBacktracking;
import java.util.*;

public class CombinationSum_LC39 {
    

    //Time: O(2 ^ N){Exponential}, Space: O(target){Aux. stack space}
    private void solveByResursion(int index,int target,int[] candidates,List<Integer> output,List<List<Integer>> result){

        //Base Case
        if(target == 0){
            result.add(new ArrayList<>(output));
            return;
        }
        if(index < 0){
            return;
        }        


        //Exclude
        solveByResursion(index-1,target,candidates,output,result);

        //Include
        if(target >= candidates[index]){
            output.add(candidates[index]);

            solveByResursion(index,target - candidates[index],candidates,output,result);

            output.remove(output.size() - 1);
        }

    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        int n = candidates.length;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> output  = new ArrayList<>();

        solveByResursion(n-1,target,candidates,output,result);

        return result;
        
    }
}
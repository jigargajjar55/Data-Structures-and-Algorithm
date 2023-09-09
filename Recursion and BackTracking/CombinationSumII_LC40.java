import java.util.*;

public class CombinationSumII_LC40 {

    
    //Time: O(2 ^ N), Space: O(target){Aux. Stack space}
    private void solveByRecursion(int index,int target,int n,int[] candidates,List<Integer> output,List<List<Integer>> result){

        //Base Case
        if(target == 0){
            result.add(new ArrayList<>(output));
            return;
        }

        //Getting combination for all candidates to meet target
        for(int i=index; i<n; i++){

            /*

            Consider [1,1,2].
            INTUITIVELY (very loosely): the second 1 should not be taken if the first 1 was not taken because the same permutation could be
            attained by taking the first 1 and not taking the second 1. Path [1,2] had already been considered when processing the first 1. 
            However, the second 1 should be taken if the first 1 was taken as path [1,1,2] had not yet been considered.

            Code-wise, this means that the element at index i should NOT be taken if 2 conditions are met

            1) The previous element was not taken
            2) The previous element is the same as the current element
            Importantly, understand what cur is, I would prefer the var name star


            */
            if(i > index && candidates[i] == candidates[i-1]){
                continue;
            }

            //If current value is more than target, then it's no point to continue as array is already sorted, it will give greater value in next iteration, so we break the loop to optimize it

            if(target < candidates[i]){
                break;
            }

            output.add(candidates[i]);

            solveByRecursion(i+1,target - candidates[i],n,candidates,output,result);

            output.remove(output.size() - 1);
        }
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        //Step1: Sort an array
        Arrays.sort(candidates);
        int n = candidates.length;

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> output = new ArrayList<>();

        solveByRecursion(0,target,n,candidates,output,result);

        return result;

        
    }
}
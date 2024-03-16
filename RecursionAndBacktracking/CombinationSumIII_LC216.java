package RecursionAndBacktracking;
import java.util.*;

public class CombinationSumIII_LC216 {
    

    
    //Time: O(2 ^ N){Exponential}, Space: O(10){Aux. Stack space}
    private void solveByRecursion(int k,int val,int target,List<Integer> output,List<List<Integer>> result){

        //Base Case
        if(k == 0 && target == 0){
            result.add(new ArrayList<>(output));
            return;
        }
        if(val > 9 || target < 0){
            return;
        }

        //Exclude
        solveByRecursion(k,val+1,target,output,result);

        //Include
        if(val <= target){
            output.add(val);
            solveByRecursion(k-1,val+1,target-val,output,result);
            output.remove(output.size() - 1);
        }

    }
    public List<List<Integer>> combinationSum3(int k, int n) {       

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> output = new ArrayList<>();

        solveByRecursion(k,1,n,output,result);


        return result;
        
    }
}
package RecursionAndBacktracking;

public class Subsets_LC78 {   

    
    //Time: O(2 ^ N)
    //Space: O(N){Aux. Stack Space}
    private void solveByRecursion(int index,int n,int[] nums,List<Integer> output,List<List<Integer>> result){

        //Base Case
        if(index >= n){

            result.add(new ArrayList<>(output));
            return;
        }

        //Exclude
        solveByRecursion(index+1,n,nums,output,result);

        //Include
        output.add(nums[index]);
        solveByRecursion(index+1,n,nums,output,result);
        output.remove(output.size() - 1);
    }
    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> output = new ArrayList<>();

        solveByRecursion(0,nums.length,nums,output,result);

        return result;
        
    }
}

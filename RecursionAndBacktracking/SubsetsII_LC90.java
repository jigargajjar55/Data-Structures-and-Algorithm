package RecursionAndBacktracking;

public class SubsetsII_LC90 {   

    
    private void solveByRecursion(int index,int n,int[] nums,List<Integer> output,List<List<Integer>> result){

        //Base Case
        if(index >= n){
            
            result.add(new ArrayList<>(output));
            return;
        }

        //Include
        output.add(nums[index]);
        solveByRecursion(index+1,n,nums,output,result);
        output.remove(output.size() - 1);

        //Exclude
        while((index + 1) < n && nums[index] == nums[index+1]){
            index += 1;
        }

        solveByRecursion(index+1,n,nums,output,result);

    }
    //Time: O((N * log N) + (2 ^ N))
    //Space: O(N){Aux. Stack Space}
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        //Sort an array
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> output = new ArrayList<>();

        solveByRecursion(0,nums.length,nums,output,result);

        return result;


        
    }
}

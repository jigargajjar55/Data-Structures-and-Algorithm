package RecursionAndBacktracking;
import java.util.*;

public class Permutations_LC46 {
    

    //Time: O(N * N!), Space: O(N + N!) {Aux. Stack Space and space for storing permutatons}
    private void dfsSolve(int index,int n,int[] arr,List<List<Integer>> result){

        //Base Case
        if(index >= n){
            List<Integer> output = new ArrayList<>();
            for(int num : arr){
                output.add(num);
            }
            result.add(output);
            return;
        }


        for(int i=index; i<n; i++){
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;

            dfsSolve(index+1,n,arr,result);

            temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }

    }
    public List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = nums[i];
        }

        dfsSolve(0,n,arr,result);

        return result;        
    }
}